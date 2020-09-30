package eu.wauz.upward.game.isaac;

import java.awt.Color;

import eu.wauz.upward.game.GameMap;
import eu.wauz.upward.game.GameWindow;
import eu.wauz.upward.generation.PathGenerator;

/**
 * A map for random paths.
 * 
 * @author Wauzmons
 */
public class PathMap extends GameMap {
	
	/**
	 * The pixels for the game window.
	 */
	private int[] pixels;
	
	/**
	 * Creates a new path map with the size of the given map matrix.
	 * 
	 * @param mapMatrix The empty game map, for measurement.
	 */
	public PathMap(int[][] mapMatrix) {
		super(mapMatrix, null, false);
	}

	/**
	 * Runs the renderer, to fill out the window.
	 * 
	 * @param window The window, that should be filled with pixels.
	 * 
	 * @see PathMap#generate()
	 */
	@Override
	public void render(GameWindow window) {
		generate();
		for(int i = 0; i < window.getPixels().length; i++) {
			window.getPixels()[i] = pixels[i];
		}
	}
	
	/**
	 * Creates a new path generator, for a new map.
	 * Automatically maps values to pixel colors.
	 * 
	 * @see PathGenerator
	 */
	public void generate() {
		PathGenerator generator = new PathGenerator(mapWidth, mapHeight);
		generator.run();
		
		int[][] pathMatrix = generator.getPathMatrix();
		
		pixels = new int[mapWidth * mapHeight];
		for(int i = 0; i < pixels.length; i++) {
			int x = i;
			int y = 0;
			while (x >= mapWidth) {
				x -= mapWidth;
				y++;
			}
			Color color;
			switch (pathMatrix[x][y]) {
			case 1:
				color = Color.ORANGE;
				break;
			default:
				color = Color.DARK_GRAY;
				break;
			}
			pixels[i] = color.getRGB();
		}
	}
	
}
