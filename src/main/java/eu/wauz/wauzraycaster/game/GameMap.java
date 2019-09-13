package eu.wauz.wauzraycaster.game;

import java.awt.Color;

import org.apache.commons.lang3.ArrayUtils;

import eu.wauz.wauzraycaster.textures.GameTexture;
import eu.wauz.wauzraycaster.textures.GameTileset;

public class GameMap {
	
	private int[][] mapMatrix;
	
	private GameTileset tileset;
	
	private Color ceilingColor = Color.GRAY;
	
	private Color floorColor = Color.DARK_GRAY;
	
	public GameMap(int[][] mapMatrix, GameTileset tileset) {
		ArrayUtils.reverse(mapMatrix);
		
		this.mapMatrix = mapMatrix;
		this.tileset = tileset;
	}
	
	public int[] render(GameCamera camera, int[] pixels, int width, int height) {
		/** Set Floor and Ceiling */
		for(int n = 0; n < pixels.length / 2; n++) {
	        pixels[n] = ceilingColor.getRGB();
	    }
	    for(int i = pixels.length / 2; i < pixels.length; i++) {
	        pixels[i] = floorColor.getRGB();
	    }
	    
	    for(int x = 0; x < width; x++) {
	        double cameraX = 2 * x / (double)(width) -1;
	        double rayDirX = camera.getxDir() + camera.getxPlane() * cameraX;
	        double rayDirY = camera.getyDir() + camera.getyPlane() * cameraX;
	        
	        /** Determine Map Position */
	        int mapX = (int) camera.getxPos();
	        int mapY = (int) camera.getyPos();
	        
	        /** Length of Ray from current Position to next X or Y-Side */
	        double sideDistX;
	        double sideDistY;
	        
	        /** Length of Ray from one Side to next in Map */
	        double deltaDistX = Math.sqrt(1 + (rayDirY * rayDirY) / (rayDirX * rayDirX));
	        double deltaDistY = Math.sqrt(1 + (rayDirX * rayDirX) / (rayDirY * rayDirY));
	        double perpWallDist;
	        
	        /** Direction to go in X and Y */
	        int stepX, stepY;
	        boolean hit = false;	/** Was a wall hit? */
	        int side = 0;			/** Was the wall vertical or horizontal? */
	        
	        /** Figure out the Step Direction and initial Distance to a Side */
	        if (rayDirX < 0)
	        {
	            stepX = -1;
	            sideDistX = (camera.getxPos() - mapX) * deltaDistX;
	        }
	        else
	        {
	            stepX = 1;
	            sideDistX = (mapX + 1.0 - camera.getxPos()) * deltaDistX;
	        }
	        
	        if (rayDirY < 0)
	        {
	            stepY = -1;
	            sideDistY = (camera.getyPos() - mapY) * deltaDistY;
	        }
	        else
	        {
	            stepY = 1;
	            sideDistY = (mapY + 1.0 - camera.getyPos()) * deltaDistY;
	        }
	        
	        
	        /** Loop to find where the Ray hits a Wall */
	        while(!hit) {
	        	/** Jump to next Square */
	            if (sideDistX < sideDistY)
	            {
	                sideDistX += deltaDistX;
	                mapX += stepX;
	                side = 0;
	            }
	            else
	            {
	                sideDistY += deltaDistY;
	                mapY += stepY;
	                side = 1;
	            }
	            /** Check if ray has hit a wall */
	            if(mapMatrix[mapX][mapY] > 0) {
	            	hit = true;
	            }
	        }
	        
	        /** Calculate Distance to the Point of Impact */
	        if(side == 0) {
	        	perpWallDist = Math.abs((mapX - camera.getxPos() + (1 - stepX) / 2) / rayDirX);
	        }
	        else {
	        	perpWallDist = Math.abs((mapY - camera.getyPos() + (1 - stepY) / 2) / rayDirY);   
	        }
	        
	        /** Now Calculate the Height of the Wall based on the Distance from the Camera */
	        int lineHeight;
	        if(perpWallDist > 0) lineHeight = Math.abs((int) (height / perpWallDist));
	        else lineHeight = height;
	        
	        /** Calculate Lowest and Highest Pixel to fill in current Stripe */
	        int drawStart = -lineHeight/2+ height/2;
	        if(drawStart < 0) {
	        	drawStart = 0;
	        }
	        int drawEnd = lineHeight/2 + height/2;
	        if(drawEnd >= height)  {
	        	drawEnd = height - 1;
	        }
	        
	        /** Add a Texture */
	        int texNum = mapMatrix[mapX][mapY];
	        double wallX;	/** Exact Position of where Wall was hit */
	        if(side == 1) {
	        	/** Y-Axis Wall */
	            wallX = (camera.getxPos() + ((mapY - camera.getyPos() + (1 - stepY) / 2) / rayDirY) * rayDirX);
	        }
	        else {
	        	/** X-Axis Wall */
	            wallX = (camera.getyPos() + ((mapX - camera.getxPos() + (1 - stepX) / 2) / rayDirX) * rayDirY);
	        }
	        wallX -= Math.floor(wallX);
	        
	        /** X Coordinate on the Texture */
	        int texX = (int) (wallX * (tileset.get(texNum).getSize()));
	        if(side == 0 && rayDirX > 0) {
	        	texX = tileset.get(texNum).getSize() - texX - 1;
	        }
	        else if(side == 1 && rayDirY < 0) {
	        	texX = tileset.get(texNum).getSize() - texX - 1;
	        }
	        
	        /** Y Coordinate on the Texture */
	        for(int y = drawStart; y < drawEnd; y++) {
	            int texY = (((y * 2 - height + lineHeight) << 6) / lineHeight) / 2;
	            int color;
	            GameTexture texture = tileset.get(texNum);
	            int pixel = texX + texY * texture.getSize();
	            
	            if(side==0) {
	            	color = texture.getPixels()[pixel];
	            }
	            else {
	            	/** Make Y Sides Darker */
	            	color = (texture.getPixels()[pixel] >> 1) & 8355711;
	            }
	            pixels[x + y * width] = color;
	        }
	    }
	    return pixels;
	}

	public int[][] getMapMatrix() {
		return mapMatrix;
	}

	public void setMapMatrix(int[][] mapMatrix) {
		this.mapMatrix = mapMatrix;
	}

	public Color getCeilingColor() {
		return ceilingColor;
	}

	public void setCeilingColor(Color ceilingColor) {
		this.ceilingColor = ceilingColor;
	}

	public Color getFloorColor() {
		return floorColor;
	}

	public void setFloorColor(Color floorColor) {
		this.floorColor = floorColor;
	}
	
}
