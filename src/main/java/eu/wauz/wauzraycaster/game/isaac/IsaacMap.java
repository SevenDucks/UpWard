package eu.wauz.wauzraycaster.game.isaac;

import eu.wauz.wauzraycaster.entity.MovingEntity;
import eu.wauz.wauzraycaster.entity.interfaces.Visible;
import eu.wauz.wauzraycaster.game.GameMap;
import eu.wauz.wauzraycaster.game.GameWindow;
import eu.wauz.wauzraycaster.textures.GameTileset;

/**
 * A map for top down rougelike worlds.
 * 
 * @author Wauzmons
 */
public class IsaacMap extends GameMap {
	
	/**
	 * The pixels for the game window, that stay the same for one room.
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
	 * The floor, that the player is located in.
	 */
	private IsaacFloor currentFloor;
	
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
			currentFloor = new IsaacFloor(this);
			IsaacRoom room = currentFloor.getStartingRoom();
			staticPixels = room.getStaticPixels();
			pixels = new int[window.getGameWidth()][window.getGameHeight()];
			currentFloor.setCurrentRoom(room);
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
	
	/**
	 * Sets the static pixels to the currently loaded room.
	 */
	public void resetCachedRoom() {
		IsaacRoom room = currentFloor.getCurrentRoom();
		staticPixels = room.getStaticPixels();
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
