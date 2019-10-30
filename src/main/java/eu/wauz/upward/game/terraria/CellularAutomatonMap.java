package eu.wauz.upward.game.terraria;

import java.awt.Color;

import eu.wauz.upward.game.GameMap;
import eu.wauz.upward.game.GameWindow;
import eu.wauz.upward.generation.CellularAutomaton;
import eu.wauz.upward.generation.ResourceSpawner;
import eu.wauz.upward.generation.VegetationSpawner;

/**
 * A map for random terraria-like world outlines.
 * 
 * @author Wauzmons
 */
public class CellularAutomatonMap extends GameMap {
	
	/**
	 * The pixels for the game window.
	 */
	private int[] pixels;
	
	/**
	 * Creates a new cellular automaton map with the size of the given map matrix.
	 * 
	 * @param mapMatrix The empty game map, for measurement.
	 */
	public CellularAutomatonMap(int[][] mapMatrix) {
		super(mapMatrix, null, false);
	}
	
	/**
	 * Runs the renderer, to fill out the window.
	 * 
	 * @param window The window, that should be filled with pixels.
	 * 
	 * @see CellularAutomatonMap#generate()
	 */
	@Override
	public void render(GameWindow window) {
		generate();
		for(int i = 0; i < window.getPixels().length; i++) {
			window.getPixels()[i] = pixels[i];
		}
	}
	
	/**
	 * Creates a new cellular automaton, for a new map.
	 * Automatically maps values to pixel colors.
	 * 
	 * @see CellularAutomaton
	 */
	public void generate() {
		CellularAutomaton automaton = new CellularAutomaton(mapWidth, mapHeight).withTerrariaPreset();
		
		ResourceSpawner resourceSpawner = new ResourceSpawner(automaton);
		resourceSpawner.setMinDeadNeighbours(4);
		
		resourceSpawner.setMinDepth(100);
		resourceSpawner.setMaxDepth(250);
		resourceSpawner.run(2, 0.030f);
		
		resourceSpawner.setMinDepth(150);
		resourceSpawner.setMaxDepth(300);
		resourceSpawner.run(3, 0.025f);
		
		resourceSpawner.setMinDepth(200);
		resourceSpawner.setMaxDepth(350);
		resourceSpawner.run(4, 0.020f);
		
		VegetationSpawner vegetationSpawner = new VegetationSpawner(automaton);
		vegetationSpawner.run(5, 0.3f);
		
		int[][] cellMatrix = automaton.getCellMatrix();
		
		pixels = new int[mapWidth * mapHeight];
		for(int i = 0; i < pixels.length; i++) {
			int x = i;
			int y = 0;
			while (x >= mapWidth) {
				x -= mapWidth;
				y++;
			}
			Color color;
			switch (cellMatrix[x][y]) {
			case 1:
				color = Color.ORANGE;
				break;
			case 2:
				color = Color.GREEN;
				break;
			case 3:
				color = Color.CYAN;
				break;
			case 4:
			case 5:
				color = Color.MAGENTA;
				break;
			default:
				color = Color.DARK_GRAY;
				break;
			}
			pixels[i] = color.getRGB();
		}
	}

}
