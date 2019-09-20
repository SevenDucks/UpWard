package eu.wauz.wauzraycaster.game.terraria;

import java.awt.Color;

import eu.wauz.wauzraycaster.game.GameMap;
import eu.wauz.wauzraycaster.game.GameWindow;
import eu.wauz.wauzraycaster.textures.GameTileset;
import eu.wauz.wauzraycaster.util.CellularAutomaton;

public class CellularAutomatonMap extends GameMap {
	
	private int[] pixels;
	
	public CellularAutomatonMap(int[][] mapMatrix, GameTileset tileset) {
		super(mapMatrix, tileset, false);
		
		CellularAutomaton automaton = new CellularAutomaton(mapWidth, mapHeight);
		automaton.setChanceToStartAlive(0.25f);
		automaton.setBirthLimit(3);
		automaton.setDeathLimit(3);
		automaton.run(10);
		boolean[][] cellMatrix = automaton.getCellMatrix();
		
		pixels = new int[mapWidth * mapHeight];
		for(int i = 0; i < pixels.length; i++) {
			int x = i;
			int y = 0;
			while (x >= mapWidth) {
				x -= mapWidth;
				y++;
			}
			Color color = cellMatrix[x][y] ? Color.ORANGE : Color.DARK_GRAY;
			pixels[i] = color.getRGB();
		}
	}
	
	@Override
	public void render(GameWindow window) {
		for(int i = 0; i < window.getPixels().length; i++) {
			window.getPixels()[i] = pixels[i];
		}
	}

}
