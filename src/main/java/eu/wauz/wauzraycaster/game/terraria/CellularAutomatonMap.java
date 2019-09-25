package eu.wauz.wauzraycaster.game.terraria;

import java.awt.Color;

import eu.wauz.wauzraycaster.game.GameMap;
import eu.wauz.wauzraycaster.game.GameWindow;
import eu.wauz.wauzraycaster.generation.CellularAutomaton;
import eu.wauz.wauzraycaster.generation.ResourceSpawner;
import eu.wauz.wauzraycaster.generation.VegetationSpawner;

public class CellularAutomatonMap extends GameMap {
	
	private int[] pixels;
	
	public CellularAutomatonMap(int[][] mapMatrix) {
		super(mapMatrix, null, false);
	}
	
	@Override
	public void render(GameWindow window) {
		gnerate();
		for(int i = 0; i < window.getPixels().length; i++) {
			window.getPixels()[i] = pixels[i];
		}
	}
	
	public void gnerate() {
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
