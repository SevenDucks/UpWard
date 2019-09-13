package eu.wauz.wauzraycaster;

import java.awt.Color;

import eu.wauz.wauzraycaster.game.GameMap;
import eu.wauz.wauzraycaster.game.GameWindow;
import eu.wauz.wauzraycaster.textures.GameTexture;
import eu.wauz.wauzraycaster.textures.GameTileset;

public class Main {
	
	private static GameWindow game;
	
	public static void main(String [] args) {
		loadDoomTestMap();
	}
	
	public static void loadDoomTestMap() {
		int[][] mapMatrix = {
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,0,0,0,0,0,0,0,0,1,0,0,0,1,1,1,1,1},
			{1,0,0,0,0,2,0,2,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,1,0,0,0,1,0,0,0,1},
			{1,1,1,1,0,0,0,0,0,1,1,2,1,1,0,0,0,1},
			{1,1,1,1,0,0,0,0,0,1,1,2,1,1,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,1,0,0,0,1,0,0,0,1},
			{1,0,0,0,1,0,0,0,0,0,0,0,0,1,0,1,0,1},
			{1,0,0,0,0,0,0,0,0,1,0,0,0,1,0,0,0,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
		};
		GameTileset tileset = new GameTileset();
		tileset.add(new GameTexture("images/doom/104.png", 64));
		tileset.add(new GameTexture("images/doom/105.png", 64));
		GameMap map = new GameMap(mapMatrix, tileset);
		map.setCeilingColor(Color.ORANGE);
		game = new GameWindow(720, 480);
		game.setFps(60);
		game.loadMap(map);
		game.placeCamera(2, 2);
		game.placeEntity(2, 11);
	}

	public static GameWindow getGame() {
		return game;
	}

	public static void setGame(GameWindow game) {
		Main.game = game;
	}

}
