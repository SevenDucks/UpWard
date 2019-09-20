package eu.wauz.wauzraycaster.game;

import org.apache.commons.lang3.ArrayUtils;

import eu.wauz.wauzraycaster.textures.GameTileset;

public abstract class GameMap {
	
	protected final int mapWidth;
	
	protected final int mapHeight;
	
	private final int[][] mapMatrix;
	
	private GameTileset tileset;
	
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
	
	public abstract void render(GameWindow window);
	
	public int getMapWidth() {
		return mapWidth;
	}

	public int getMapHeight() {
		return mapHeight;
	}

	public int[][] getMapMatrix() {
		return mapMatrix;
	}

	public GameTileset getTileset() {
		return tileset;
	}
	
}
