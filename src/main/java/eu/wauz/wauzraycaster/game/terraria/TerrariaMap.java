package eu.wauz.wauzraycaster.game.terraria;

import eu.wauz.wauzraycaster.game.GameMap;
import eu.wauz.wauzraycaster.game.GameWindow;
import eu.wauz.wauzraycaster.generation.CellularAutomaton;
import eu.wauz.wauzraycaster.generation.ResourceSpawner;
import eu.wauz.wauzraycaster.generation.VegetationSpawner;
import eu.wauz.wauzraycaster.textures.GameTileset;

public class TerrariaMap extends GameMap {
	
	private TerrariaRenderer renderer = new TerrariaRenderer(this);
	
	private TerrariaBlock[][] blocks;
	
	public TerrariaMap(int[][] mapMatrix, GameTileset tileset) {
		super(mapMatrix, tileset, false);
	}
	
	public void generate() {
		blocks = new TerrariaBlock[mapWidth][mapHeight];
		
		CellularAutomaton automaton = new CellularAutomaton(mapWidth, mapHeight).withTerrariaPreset();
		
		ResourceSpawner resourceSpawner = new ResourceSpawner(automaton);
		resourceSpawner.setMinDeadNeighbours(4);
		resourceSpawner.setMinDepth(100);
		resourceSpawner.setMaxDepth(250);
		resourceSpawner.run(2, 0.030f);
		
		VegetationSpawner vegetationSpawner = new VegetationSpawner(automaton);
		vegetationSpawner.setWidth(1);
		vegetationSpawner.setMinHeight(10);
		vegetationSpawner.setMaxHeight(20);
		vegetationSpawner.run(2, 0.1f);
		
		vegetationSpawner.setWidth(1);
		vegetationSpawner.setMinHeight(1);
		vegetationSpawner.setMaxHeight(1);
		vegetationSpawner.run(3, 1.0f);
		
		
		int[][] cellMatrix = automaton.getCellMatrix();
		for(int x = 0; x < mapWidth; x++) {
			for(int y = 0; y < mapHeight; y++) {
				int blockId = cellMatrix[x][y];
				getMapMatrix()[x][y] = blockId;
				blocks[x][y] = new TerrariaBlock(getTileset().get(blockId + 1), renderer.getBlockSize());
			}
		}
	}

	@Override
	public void render(GameWindow window) {
		renderer.render(window);
	}

	public int getBlockSize() {
		return renderer.getBlockSize();
	}

	public void setBlockSize(int blockSize) {
		renderer.setBlockSize(blockSize);
	}

	public TerrariaBlock[][] getBlocks() {
		return blocks;
	}

	public void setBlocks(TerrariaBlock[][] blocks) {
		this.blocks = blocks;
	}
	
}
