package eu.wauz.wauzraycaster;

import eu.wauz.wauzraycaster.game.GameWindow;
import eu.wauz.wauzraycaster.game.isaac.RoomLayoutMap;

/**
 * TODO: Document me!
 * 
 * @author Wauzmons
 */
public class IsaacRoomLayoutDemo {
	
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
