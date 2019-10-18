package eu.wauz.wauzraycaster;

import eu.wauz.wauzraycaster.game.GameWindow;
import eu.wauz.wauzraycaster.game.terraria.CellularAutomatonMap;

/**
 * A demo application to show the capabilities of the engine.
 * Renders a randomly generated outline for a Terraria clone.
 * 
 * @author Wauzmons
 * 
 * @see TerrariaDemo Full Demo
 * @see CellularAutomatonMap
 */
public class TerrariaCellularAutomataDemo {
	
	/**
	 * The Cellular Automaton Map is initialized with an empty array,
	 * that will hold the map data, that is freshly generated every frame.
	 * A 1200x350 pixel window with 1 fps is created, to load the map.
	 * 
	 * @param args
	 * 
	 * @see GameWindow
	 */
	public static void main(String[] args) {
		int width = 1200;
		int height = 350;
		int[][] mapMatrix = new int[width][height];
		CellularAutomatonMap map = new CellularAutomatonMap(mapMatrix);
		GameWindow game = new GameWindow(width, height, 1);
		game.setFps(1);
		game.loadMap(map);
		game.setTitle("Cellular Automaton");
		game.start();
	}

}
