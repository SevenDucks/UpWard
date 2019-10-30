package eu.wauz.wauzraycaster.game;

import org.apache.commons.lang3.ArrayUtils;

import eu.wauz.wauzraycaster.textures.GameTileset;

/**
 * An abstract map for game levels.
 * 
 * @author Wauzmons
 */
public abstract class GameMap {
	
	/**
	 * The width of the game map.
	 */
	protected final int mapWidth;
	
	/**
	 * The height of the game map.
	 */
	protected final int mapHeight;
	
	/**
	 * The game map, values may correspond to the textures in the tileset.
	 */
	private final int[][] mapMatrix;
	
	/**
	 * The tileset to use, for texturing the map.
	 */
	private final GameTileset tileset;
	
	/**
	 * Creates a new map from the given values.
	 * 
	 * @param mapMatrix The game map, values may correspond to the textures in the tileset.
	 * @param tileset The tileset to use, for texturing the map.
	 * @param inverted If the matrix should be inverted.
	 */
	public GameMap(int[][] mapMatrix, GameTileset tileset, boolean inverted) {
		if(inverted) {
			ArrayUtils.reverse(mapMatrix);
			for(int[] array : mapMatrix) {
				ArrayUtils.reverse(array);
			}
			mapWidth = mapMatrix[0].length;
			mapHeight = mapMatrix.length;
		}
		else {
			mapWidth = mapMatrix.length;
			mapHeight = mapMatrix[0].length;
		}
		this.mapMatrix = mapMatrix;
		this.tileset = tileset;
	}
	
	/**
	 * Runs the renderer, to fill out the window.
	 * 
	 * @param window The window, that should be filled with pixels.
	 */
	public abstract void render(GameWindow window);
	
	/**
	 * @return The width of the game map.
	 */
	public int getMapWidth() {
		return mapWidth;
	}

	/**
	 * @return The height of the game map.
	 */
	public int getMapHeight() {
		return mapHeight;
	}

	/**
	 * @return The game map, values may correspond to the textures in the tileset.
	 */
	public int[][] getMapMatrix() {
		return mapMatrix;
	}

	/**
	 * @return The tileset to use, for texturing the map.
	 */
	public GameTileset getTileset() {
		return tileset;
	}
	
}
