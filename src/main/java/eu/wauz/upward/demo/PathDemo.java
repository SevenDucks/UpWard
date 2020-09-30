package eu.wauz.upward.demo;

import eu.wauz.upward.game.GameWindow;
import eu.wauz.upward.game.isaac.PathMap;

/**
 * A demo application to show the capabilities of the engine.
 * Renders a randomly generated path.
 * 
 * @author Wauzmons
 * 
 * @see PathMap
 */
public class PathDemo implements Runnable {
	
	/**
	 * The Path Map is initialized with an empty array,
	 * that will hold the map data, that is freshly generated every frame.
	 * A 21x21 pixel window with 32x size and 0.5 fps is created, to load the map.
	 * 
	 * @see GameWindow
	 */
	@Override
	public void run() {
		int width = 5;
		int height = 13;
		int[][] mapMatrix = new int[width][height];
		PathMap map = new PathMap(mapMatrix);
		GameWindow game = new GameWindow(width, height, 32);
		game.setFps(0.5);
		game.loadMap(map);
		game.setTitle("Path");
		game.start();
	}

}
