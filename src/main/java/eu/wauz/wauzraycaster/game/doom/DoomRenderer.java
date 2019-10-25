package eu.wauz.wauzraycaster.game.doom;

import java.awt.Color;

import eu.wauz.wauzraycaster.entity.doom.DoomCamera;
import eu.wauz.wauzraycaster.game.GameWindow;
import eu.wauz.wauzraycaster.textures.GameTexture;
import eu.wauz.wauzraycaster.textures.GameTileset;

/**
 * A renderer for pseudo 3D doom-like levels.
 * 
 * @author Wauzmons
 */
public class DoomRenderer {
	
	/**
	 * The pixels for the game window.
	 */
	private int[] pixels;
	
	/**
	 * The height of the game window.
	 */
	private int windowWidth;
	
	/**
	 * The width of the game window.
	 */
	private int windowHeight;
	
	/**
	 * The map, this renderer is working with.
	 */
	private DoomMap map;
	
	/**
	 * The tileset to use, for texturing the map.
	 */
	private GameTileset tileset;
	
	/**
	 * The color of the level's ceiling.
	 */
	private Color ceilingColor;
	
	/**
	 * The color of the level's floor.
	 */
	private Color floorColor;
	
	/**
	 * Creates a renderer, that fills out the pixels of the game window.
	 * 
	 * @param map The map, this renderer is working with.
	 */
	public DoomRenderer(DoomMap map) {
		this.map = map;
		tileset = map.getTileset();
		
		ceilingColor = Color.GRAY;
		floorColor = Color.DARK_GRAY;
	}

	/**
	 * Runs the renderer, to fill out the window.
	 * TODO: Split this up. It is far to complex.
	 * 
	 * @param window The window, that should be filled with pixels.
	 */
	public void render(GameWindow window) {
		if(!(window.getCurrentCamera() instanceof DoomCamera)) {
			return;
		}
		
		DoomCamera camera = (DoomCamera) window.getCurrentCamera();
		pixels = window.getPixels();
		windowWidth = window.getGameWidth();
		windowHeight = window.getGameHeight();
		int[][] mapMatrix = map.getMapMatrix();
		
		paintFloorAndCeiling();
	    
	    for(int x = 0; x < windowWidth; x++) {
	        double cameraX = 2 * x / (double)(windowWidth) - 1;
	        double rayDirX = camera.getxDir() + camera.getxPlane() * cameraX;
	        double rayDirY = camera.getyDir() + camera.getyPlane() * cameraX;
	        
	        /** Determine the camera position. */
	        int mapX = (int) camera.getxPos();
	        int mapY = (int) camera.getyPos();
	        
	        /** The length of the ray from the current position to the next x or y-side. */
	        double sideDistX;
	        double sideDistY;
	        
	        /** The length of the ray from one side to the next of the map. */
	        double deltaDistX = Math.sqrt(1 + (rayDirY * rayDirY) / (rayDirX * rayDirX));
	        double deltaDistY = Math.sqrt(1 + (rayDirX * rayDirX) / (rayDirY * rayDirY));
	        double perpWallDist;
	        
	        /** Direction to go in x and y. */
	        int stepX, stepY;
	        
	        /** Was a wall hit? */
	        boolean hit = false;	
	        
	        /** Was the wall vertical or horizontal? */
	        int side = 0;
	        
	        /** Figure out the step direction and initial distance to a side. */
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
	        
	        
	        /** Loop to find where the ray hits a wall. */
	        while(!hit) {
	        	/** Jump to the next square. */
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
	            
	            /** Check if the ray has hit a wall. */
	            if(mapMatrix[mapX][mapY] > 0) {
	            	hit = true;
	            }
	        }
	        
	        /** Calculate the distance to the point of impact. */
	        if(side == 0) {
	        	perpWallDist = Math.abs((mapX - camera.getxPos() + (1 - stepX) / 2) / rayDirX);
	        }
	        else {
	        	perpWallDist = Math.abs((mapY - camera.getyPos() + (1 - stepY) / 2) / rayDirY);   
	        }
	        
	        /** Now calculate the height of the wall, based on the distance from the camera. */
	        int lineHeight;
	        if(perpWallDist > 0) {
	        	lineHeight = Math.abs((int) (windowHeight / perpWallDist));
	        }
	        else {
	        	lineHeight = windowHeight;
	        }
	        
	        /** Calculate the lowest and highest pixel, to fill in the current stripe. */
	        int drawStart = -lineHeight/2+ windowHeight/2;
	        if(drawStart < 0) {
	        	drawStart = 0;
	        }
	        int drawEnd = lineHeight/2 + windowHeight/2;
	        if(drawEnd >= windowHeight)  {
	        	drawEnd = windowHeight - 1;
	        }
	        
	        /** Add a texture. */
	        int texNum = mapMatrix[mapX][mapY];
	        
	        /** The exact position of where the wall was hit. */
	        double wallX;
	        
	        if(side == 1) {
	        	/** A y-axis wall. */
	            wallX = (camera.getxPos() + ((mapY - camera.getyPos() + (1 - stepY) / 2) / rayDirY) * rayDirX);
	        }
	        else {
	        	/** A x-axis wall. */
	            wallX = (camera.getyPos() + ((mapX - camera.getxPos() + (1 - stepX) / 2) / rayDirX) * rayDirY);
	        }
	        wallX -= Math.floor(wallX);
	        
	        /** The x coordinate on the texture. */
	        int texX = (int) (wallX * (tileset.get(texNum).getSize()));
	        if(side == 0 && rayDirX > 0) {
	        	texX = tileset.get(texNum).getSize() - texX - 1;
	        }
	        else if(side == 1 && rayDirY < 0) {
	        	texX = tileset.get(texNum).getSize() - texX - 1;
	        }
	        
	        /** The y coordinate on the texture. */
	        for(int y = drawStart; y < drawEnd; y++) {
	            int texY = (((y * 2 - windowHeight + lineHeight) << 6) / lineHeight) / 2;
	            int color;
	            GameTexture texture = tileset.get(texNum);
	            int pixel = texX + texY * texture.getSize();
	            
	            if(side==0) {
	            	color = texture.getPixels()[pixel];
	            }
	            else {
	            	/** Make y sides darker. */
	            	color = (texture.getPixels()[pixel] >> 1) & 8355711;
	            }
	            pixels[x + y * windowWidth] = color;
	        }
	    }
	}
	
	/**
	 * Fills the upper half of the pixels with the ceiling color,
	 * and the lower half with the floor color.
	 */
	private void paintFloorAndCeiling() {
		for(int n = 0; n < pixels.length / 2; n++) {
	        pixels[n] = ceilingColor.getRGB();
	    }
	    for(int i = pixels.length / 2; i < pixels.length; i++) {
	        pixels[i] = floorColor.getRGB();
	    }
	}

	/**
	 * @return The color of the level's ceiling.
	 */
	public Color getCeilingColor() {
		return ceilingColor;
	}

	/**
	 * @param ceilingColor The new color of the level's ceiling.
	 */
	public void setCeilingColor(Color ceilingColor) {
		this.ceilingColor = ceilingColor;
	}

	/**
	 * @return The color of the level's floor.
	 */
	public Color getFloorColor() {
		return floorColor;
	}

	/**
	 * @param floorColor The new color of the level's floor.
	 */
	public void setFloorColor(Color floorColor) {
		this.floorColor = floorColor;
	}
	
}
