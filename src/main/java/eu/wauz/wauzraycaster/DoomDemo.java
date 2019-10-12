package eu.wauz.wauzraycaster;

import java.awt.Color;

import eu.wauz.wauzraycaster.game.GameWindow;
import eu.wauz.wauzraycaster.game.doom.DoomMap;
import eu.wauz.wauzraycaster.textures.GameTexture;
import eu.wauz.wauzraycaster.textures.GameTileset;

/**
 * A demo application to show the capabilities of the engine.
 * Renders a pseudo 3D level from an array by using raycasting.
 * 
 * @author Wauzmons
 * 
 * @see DoomMap
 */
public class DoomDemo {

	/**
	 * Initializes an array of integers, that represents the game map.
	 * A tileset is created by loading textures from the resources.
	 * The integers correspond to the textures, in the order they have been added.
	 * A 720x480 pixel window with 60 fps is created, to load the map.
	 * A controllable 3D camera and an invisible entity are added to the map.
	 * After setting title and background music, the game is started.
	 * 
	 * @param args
	 * 
	 * @see GameWindow
	 */
	public static void main(String[] args) {
		int[][] mapMatrix = {
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,0,0,0,0,0,0,0,0,1,0,0,0,1,1,1,1,1},
			{1,0,0,0,0,2,0,2,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,1,0,0,0,1,0,0,0,1},
			{1,1,1,1,0,0,0,0,0,1,1,2,1,1,0,0,0,1},
			{1,1,1,1,0,0,0,0,0,1,1,2,1,1,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,1,0,0,0,1,0,0,0,1},
			{3,0,0,0,1,0,0,0,0,0,0,0,0,1,0,2,0,1},
			{1,0,0,0,0,0,0,0,0,1,0,0,0,1,0,0,0,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,1,1},
			{1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,1,0,2,0,2,0,2,0,2,0,2,0,2,0,2,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,1,0,2,0,2,0,2,0,2,0,2,0,2,0,2,0,1},
			{1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
		};
		GameTileset tileset = new GameTileset();
		tileset.add(new GameTexture("images/doom/104.png", 64));
		tileset.add(new GameTexture("images/doom/105.png", 64));
		tileset.add(new GameTexture(Color.BLACK, 64));
		DoomMap map = new DoomMap(mapMatrix, tileset);
		map.setCeilingColor(Color.ORANGE);
		GameWindow game = new GameWindow(720, 480);
		game.setFps(60);
		game.loadMap(map);
		game.placeCamera(2, 10);
		game.placeEntity(2, 15);
		game.setTitle("WOOM");
		game.setBgmPath("sound/doom/d_e1m1.mid");
		game.start();
	}

}
