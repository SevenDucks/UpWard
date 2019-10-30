package eu.wauz.wauzraycaster;

import eu.wauz.wauzraycaster.game.GameWindow;
import eu.wauz.wauzraycaster.game.isaac.RoomLayoutMap;

/**
 * A demo application to show the capabilities of the engine.
 * Renders a randomly generated floor outline for an Isaac clone.
 * 
 * @author Wauzmons
 * 
 * @see IsaacDemo Full Demo
 * @see RoomLayoutMap
 */
public class IsaacRoomLayoutDemo {
	
	/**
	 * The Room Layout Map is initialized with an empty array,
	 * that will hold the map data, that is freshly generated every frame.
	 * A 21x21 pixel window with 16x size and 0.5 fps is created, to load the map.
	 * 
	 * @param args
	 * 
	 * @see GameWindow
	 */
	public static void main(String[] args) {
		int width = 21;
		int height = 21;
		int[][] mapMatrix = new int[width][height];
		RoomLayoutMap map = new RoomLayoutMap(mapMatrix);
		GameWindow game = new GameWindow(width, height, 16);
		game.setFps(0.5);
		game.loadMap(map);
		game.setTitle("Room Layout");
		game.start();
	}

}
