package eu.wauz.wauzraycaster;

import java.awt.Color;

import eu.wauz.wauzraycaster.entity.isaac.IsaacCamera;
import eu.wauz.wauzraycaster.entity.isaac.IsaacTestEntity;
import eu.wauz.wauzraycaster.game.GameWindow;
import eu.wauz.wauzraycaster.game.isaac.IsaacMap;
import eu.wauz.wauzraycaster.textures.GameTexture;
import eu.wauz.wauzraycaster.textures.GameTileset;

/**
 * A demo application to show the capabilities of the engine.
 * Renders a randomly generated top down rougelike world for an Isaac clone.
 * 
 * @author Wauzmons
 * 
 * @see IsaacMap
 */
public class IsaacDemo {

	/**
	 * The Isaac Map is initialized with an empty array, to determine the room size.
	 * A tileset is created by loading textures from the resources.
	 * The Isaac Map creates 32x32 sized blocks from the assigned textures.
	 * A 416x288 pixel window with doubled size and 30 fps is created, to load the random map.
	 * A controllable 2D camera is added to the map, before the game starts.
	 * 
	 * @param args
	 * 
	 * @see GameWindow
	 */
	public static void main(String[] args) {
		int[][] mapMatrix = new int[13][9];
		GameTileset tileset = new GameTileset();
		
		GameTexture floorTexture = new GameTexture("images/isaac/floor.png", 32);
		floorTexture.addAlternatives(new GameTexture(Color.RED.darker().darker(), 32));
		tileset.add(floorTexture);
		
		tileset.add(new GameTexture("images/isaac/wall.png", 32));
		tileset.add(new GameTexture("images/isaac/corner.png", 32));
		tileset.add(new GameTexture("images/isaac/door_closed.png", 32));
		tileset.add(new GameTexture("images/isaac/door_opened.png", 32));
		
		IsaacMap map = new IsaacMap(mapMatrix, tileset);
		map.setBlockSize(32);
		GameWindow game = new GameWindow(416, 288, 2);
		game.setFps(30);
		game.loadMap(map);
		
		IsaacCamera camera = new IsaacCamera(6, 4);
		camera.setTexture(new GameTexture("images/isaac/joe.png", 32));
		camera.setShotTexture(new GameTexture(Color.CYAN, 8));
		camera.setShotSize(0.25);
		game.placeCamera(camera);
		
		IsaacTestEntity entity = new IsaacTestEntity(2, 6);
		entity.setTexture(new GameTexture("images/isaac/flungus.png", 32));
		game.placeEntity(entity);
		
		IsaacTestEntity entity2 = new IsaacTestEntity(10, 2);
		entity2.setTexture(new GameTexture("images/isaac/flungus.png", 32));
		game.placeEntity(entity2);
		
		game.setTitle("The Binding of Joe");
		game.start();
	}
	
}
