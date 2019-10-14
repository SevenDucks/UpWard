package eu.wauz.wauzraycaster.game.isaac;

import eu.wauz.wauzraycaster.game.GameMap;
import eu.wauz.wauzraycaster.game.GameWindow;
import eu.wauz.wauzraycaster.game.terraria.TerrariaBlock;
import eu.wauz.wauzraycaster.textures.GameTileset;

/**
 * A map for top down rougelike worlds.
 * 
 * @author Wauzmons
 */
public class IsaacMap extends GameMap {
	
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
	}

	/**
	 * Runs the renderer, to fill out the window.
	 * 
	 * @param window The window, that should be filled with pixels.
	 */
	@Override
	public void render(GameWindow window) {
		pixels = new int[window.getWidth()][window.getHeight()];
		
		TerrariaBlock floorBlock = new TerrariaBlock(getTileset().get(1), blockSize);
		TerrariaBlock wallBlock = new TerrariaBlock(getTileset().get(2), blockSize);
		
		for(int x = 0; x < mapWidth - 1; x++) {
			for(int y = 0; y < mapHeight - 1; y++) {
				if(x == 0 || y == 0) {
					wallBlock.render(pixels, x * blockSize, y * blockSize);
				}
				else {
					floorBlock.render(pixels, x * blockSize, y * blockSize);
				}
			}
		}
		
		for(int i = 0; i < window.getPixels().length; i++) {
			int x = i % window.getWidth();
			int y = (int) Math.ceil(i / window.getWidth());
			window.getPixels()[i] = this.pixels[x][y];
		}
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
