package eu.wauz.wauzraycaster;

import java.awt.Color;

import eu.wauz.wauzraycaster.game.GameWindow;
import eu.wauz.wauzraycaster.game.doom.DoomMap;
import eu.wauz.wauzraycaster.game.terraria.CellularAutomatonMap;
import eu.wauz.wauzraycaster.textures.GameTexture;
import eu.wauz.wauzraycaster.textures.GameTileset;

public class Main {
	
	private static GameWindow game;
	
	public static void main(String [] args) {
		loadCellularAutomatonTestMap(1200, 350);
	}
	
	public static void loadCellularAutomatonTestMap(int width, int height) {
		int[][] mapMatrix = new int[width][height];
		CellularAutomatonMap map = new CellularAutomatonMap(mapMatrix);
		game = new GameWindow(width, height);
		game.setFps(1);
		game.loadMap(map);
		game.setTitle("Cellular Automaton");
		game.start();
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
			{1,0,0,0,1,0,0,0,0,0,0,0,0,1,0,2,0,1},
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
		DoomMap map = new DoomMap(mapMatrix, tileset);
		map.setCeilingColor(Color.ORANGE);
		game = new GameWindow(720, 480);
		game.setFps(60);
		game.loadMap(map);
		game.placeCamera(2, 10);
		game.placeEntity(2, 15);
		game.setTitle("WOOM");
		game.setBgmPath("sound/doom/d_e1m1.mid");
		game.start();
	}

	public static GameWindow getGame() {
		return game;
	}

	public static void setGame(GameWindow game) {
		Main.game = game;
	}

}
