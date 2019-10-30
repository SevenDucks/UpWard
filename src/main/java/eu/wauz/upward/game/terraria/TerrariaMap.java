package eu.wauz.upward.game.terraria;

import eu.wauz.upward.game.GameBlock;
import eu.wauz.upward.game.GameMap;
import eu.wauz.upward.game.GameWindow;
import eu.wauz.upward.generation.CellularAutomaton;
import eu.wauz.upward.generation.ResourceSpawner;
import eu.wauz.upward.generation.VegetationSpawner;
import eu.wauz.upward.textures.GameTileset;

/**
 * A map for random terraria-like world generation.
 * 
 * @author Wauzmons
 */
public class TerrariaMap extends GameMap {
	
	/**
	 * The renderer, that fills out the pixels of the game window.
	 */
	private TerrariaRenderer renderer = new TerrariaRenderer(this);
	
	/**
	 * The blocks, that make up the world.
	 */
	private GameBlock[][] blocks;
	
	/**
	 * Creates a new terraria map with the size of the given map matrix.
	 * 
	 * @param mapMatrix The empty game map, for measurement.
	 * @param tileset The tileset to use, for texturing the map.
	 */
	public TerrariaMap(int[][] mapMatrix, GameTileset tileset) {
		super(mapMatrix, tileset, false);
	}

	/**
	 * Runs the renderer, to fill out the window.
	 * 
	 * @param window The window, that should be filled with pixels.
	 * 
	 * @see TerrariaRenderer
	 */
	@Override
	public void render(GameWindow window) {
		renderer.render(window);
	}
	
	/**
	 * Creates a new cellular automaton, for a new map.
	 * Automatically maps values to block textures from the tileset.
	 * 
	 * @see CellularAutomaton
	 * @see GameBlock
	 */
	public void generate() {
		blocks = new GameBlock[mapWidth][mapHeight];
		
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
				blocks[x][y] = new GameBlock(getTileset().get(blockId + 1), renderer.getBlockSize());
			}
		}
	}

	/**
	 * @return The length of a block in pixels.
	 */
	public int getBlockSize() {
		return renderer.getBlockSize();
	}

	/**
	 * @param blockSize The new length of a block in pixels.
	 */
	public void setBlockSize(int blockSize) {
		renderer.setBlockSize(blockSize);
	}

	/**
	 * @return The blocks, that make up the world.
	 */
	public GameBlock[][] getBlocks() {
		return blocks;
	}

	/**
	 * @param blocks The new blocks, that make up the world.
	 */
	public void setBlocks(GameBlock[][] blocks) {
		this.blocks = blocks;
	}
	
}
