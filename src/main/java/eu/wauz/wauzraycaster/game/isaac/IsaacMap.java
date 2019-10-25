package eu.wauz.wauzraycaster.game.isaac;

import eu.wauz.wauzraycaster.game.GameMap;
import eu.wauz.wauzraycaster.game.GameWindow;
import eu.wauz.wauzraycaster.entity.MovingEntity;
import eu.wauz.wauzraycaster.entity.interfaces.Visible;
import eu.wauz.wauzraycaster.game.GameBlock;
import eu.wauz.wauzraycaster.textures.GameTileset;

/**
 * A map for top down rougelike worlds.
 * 
 * @author Wauzmons
 */
public class IsaacMap extends GameMap {
	
	/**
	 * The pixels for the game window, that stay the same.
	 */
	private int[][] staticPixels;
	
	/**
	 * The pixels for the game window.
	 */
	private int[][] pixels;
	
	/**
	 * The length of a block in pixels.
	 */
	private int blockSize = 16;
	
	/**
	 * Creates a new isaac map with the size of the given map matrix.
	 * 
	 * @param mapMatrix The empty game map, for measurement.
	 * @param tileset The tileset to use, for texturing the map.
	 */
	public IsaacMap(int[][] mapMatrix, GameTileset tileset) {
		super(mapMatrix, tileset, false);
		
		for(int x = 0; x < mapWidth; x++) {
			for(int y = 0; y < mapHeight; y++) {
				if(x == 0 || y == 0 || x == mapWidth - 1 || y == mapHeight - 1) {
					mapMatrix[x][y] = 1;
				}
				else {
					mapMatrix[x][y] = 0;
				}
			}
		}
	}

	/**
	 * Runs the renderer, to fill out the window.
	 * 
	 * @param window The window, that should be filled with pixels.
	 */
	@Override
	public void render(GameWindow window) {
		if(staticPixels == null) {
			staticPixels = new int[window.getGameWidth()][window.getGameHeight()];
			pixels = new int[window.getGameWidth()][window.getGameHeight()];
			
			renderWalls();
			renderFloor();
			renderCorners();
			renderOpenDoors();
		}
		for(int i = 0; i < pixels.length; i++) {
			pixels[i] = staticPixels[i].clone();
		}
		for(MovingEntity entity : window.getEntities()) {
			if(entity instanceof Visible) {
				((Visible) entity).render(this);
			}
		}
		for(int i = 0; i < window.getPixels().length; i++) {
			int x = i % window.getGameWidth();
			int y = (int) Math.ceil(i / window.getGameWidth());
			window.getPixels()[i] = pixels[x][y];
		}
	}
	
	public void renderFloor() {
		GameBlock floorBlock = new GameBlock(getTileset().get(1), blockSize);
		
		for(int x = 1; x < mapWidth - 1; x++) {
			for(int y = 1; y < mapHeight - 1; y++) {
				floorBlock.render(staticPixels, x * blockSize, y * blockSize, 0);
			}
		}
	}
	
	public void renderWalls() {
		GameBlock wallBlock = new GameBlock(getTileset().get(2), blockSize);
		
		int rightEdge = (mapWidth - 1) * blockSize;
		int bottomEdge = (mapHeight - 1) * blockSize;
		
		for(int x = 1; x < mapWidth - 1; x++) {
			wallBlock.render(staticPixels, x * blockSize, 0, 0);
			wallBlock.render(staticPixels, x * blockSize, bottomEdge, 2);
		}
		for(int y = 1; y < mapHeight - 1; y++) {
			wallBlock.render(staticPixels, 0, y * blockSize, 3);
			wallBlock.render(staticPixels, rightEdge, y * blockSize, 1);
		}
	}
	
	public void renderCorners() {
		GameBlock cornerBlock = new GameBlock(getTileset().get(3), blockSize);
		
		int rightEdge = (mapWidth - 1) * blockSize;
		int bottomEdge = (mapHeight - 1) * blockSize;
		
		cornerBlock.render(staticPixels, 0, 0, 0);
		cornerBlock.render(staticPixels, rightEdge, 0, 1);
		cornerBlock.render(staticPixels, rightEdge, bottomEdge, 2);
		cornerBlock.render(staticPixels, 0, bottomEdge, 3);
	}
	
	public void renderOpenDoors() {
		GameBlock openDoorBlock = new GameBlock(getTileset().get(5), blockSize);
		
		openDoorBlock.render(staticPixels, (mapWidth / 2) * blockSize, 0, 0);
		openDoorBlock.render(staticPixels, (mapWidth - 1) * blockSize, (mapHeight / 2) * blockSize, 1);
		openDoorBlock.render(staticPixels, (mapWidth / 2) * blockSize, (mapHeight - 1) * blockSize, 2);
		openDoorBlock.render(staticPixels, 0, (mapHeight / 2) * blockSize, 3);
	}

	/**
	 * @return The pixels for the game window.
	 */
	public int[][] getPixels() {
		return pixels;
	}

	/**
	 * @param pixels The pixels for the game window.
	 */
	public void setPixels(int[][] pixels) {
		this.pixels = pixels;
	}

	/**
	 * @return The length of a block in pixels.
	 */
	public int getBlockSize() {
		return blockSize;
	}

	/**
	 * @param blockSize The new length of a block in pixels.
	 */
	public void setBlockSize(int blockSize) {
		this.blockSize = blockSize;
	}
	
}
