package eu.wauz.wauzraycaster;

import java.awt.Color;

import eu.wauz.wauzraycaster.entity.terraria.TerrariaCamera;
import eu.wauz.wauzraycaster.game.GameWindow;
import eu.wauz.wauzraycaster.game.doom.DoomMap;
import eu.wauz.wauzraycaster.game.terraria.CellularAutomatonMap;
import eu.wauz.wauzraycaster.game.terraria.TerrariaMap;
import eu.wauz.wauzraycaster.textures.GameTexture;
import eu.wauz.wauzraycaster.textures.GameTileset;

public class Main {
	
	private static GameWindow game;
	
	public static void main(String [] args) {
		loadDoomTestMap();
//		loadCellularAutomatonTestMap();
//		loadTerrariaTestMap();
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
		game = new GameWindow(720, 480);
		game.setFps(60);
		game.loadMap(map);
		game.placeCamera(2, 10);
		game.placeEntity(2, 15);
		game.setTitle("WOOM");
		game.setBgmPath("sound/doom/d_e1m1.mid");
		game.start();
	}
	
	public static void loadCellularAutomatonTestMap() {
		int width = 1200;
		int height = 350;
		int[][] mapMatrix = new int[width][height];
		CellularAutomatonMap map = new CellularAutomatonMap(mapMatrix);
		game = new GameWindow(width, height);
		game.setFps(1);
		game.loadMap(map);
		game.setTitle("Cellular Automaton");
		game.start();
	}
	
	public static void loadTerrariaTestMap() {		
		int[][] mapMatrix = new int[1200][350];
		GameTileset tileset = new GameTileset();
		tileset.add(new GameTexture("images/terraria/dirt.png", 16));
		tileset.add(new GameTexture(Color.BLUE.darker().darker(), 16));
		tileset.add(new GameTexture("images/terraria/stone.png", 16));
		tileset.add(new GameTexture("images/terraria/leaf.png", 16));
		tileset.add(new GameTexture("images/terraria/water.png", 16));
		TerrariaMap map = new TerrariaMap(mapMatrix, tileset);
		map.setBlockSize(16);
		map.generate();
		game = new GameWindow(720, 480);
		game.setFps(60);
		game.loadMap(map);
		game.placeCamera(new TerrariaCamera(400, 120, 0, 0, 0, 0));
		game.setTitle("Terrario");
		game.start();
	}

	public static GameWindow getGame() {
		return game;
	}

	public static void setGame(GameWindow game) {
		Main.game = game;
	}

}
