package eu.wauz.wauzraycaster.generation;

import eu.wauz.wauzraycaster.util.WrayUtils;

/**
 * Used to populate a cellular automaton with vegetation (e.g. trees).
 * 
 * @author Wauzmons
 */
public class VegetationSpawner {
	
	/**
	 * The automaton to populate.
	 */
	private CellularAutomaton automaton;
	
	/**
	 * The width of the vegetation to spawn.
	 */
	private int width = 2;
	
	/**
	 * The minimum height of the vegetation to spawn.
	 */
	private int minHeight = 7;
	
	/**
	 * The maximum height of the vegetation to spawn.
	 */
	private int maxHeight = 14;
	
	/**
	 * The minimum y value that the vegetation spawns on.
	 */
	private int minDepth = 20;
	
	/**
	 * The maximum y value that the vegetation spawns on.
	 */
	private int maxDepth = 120;
	
	/**
	 * Creates a new spawner for an automaton.
	 * 
	 * @param automaton The automaton to populate.
	 */
	public VegetationSpawner(CellularAutomaton automaton) {
		this.automaton = automaton;
	}
	
	/**
	 * Populates the automaton of this spawner.
	 * 
	 * @param id The integer value to put in the cell matrix.
	 * @param rarity The chance for the vegetation to spawn.
	 */
	public void run(int id, float rarity) {
		for(int x = 0; x < automaton.getWidth() - width; x++) {
			for(int y = minDepth; y < maxDepth; y++) {
				if(WrayUtils.randomFloat() > rarity) {
					continue;
				}
				int height = WrayUtils.randomInt(minHeight, maxHeight);
				if(hasEnoughSpace(x, y, height)) {
					fillSpace(id, x, y, height);
				}
			}
		}
	}
	
	/**
	 * Tests if an area can be filled with vegetation.
	 * 
	 * @param x The left start point of the area.
	 * @param y The bottom start point of the area.
	 * @param height The height of the area.
	 * @return If there is enough space.
	 */
	public boolean hasEnoughSpace(int x, int y, int height) {
		for(int stepX = x; stepX <= x + width - 1; stepX++) {
			for(int stepY = y; stepY > y - height; stepY--) {
				boolean outsideXBounds = stepX < 0 || stepX >= automaton.getWidth();
				boolean outsideYBounds = stepY < 0 || stepY >= automaton.getHeight();
				if(outsideXBounds || outsideYBounds) {
					return false;
				}
				if(automaton.getCellMatrix()[stepX][stepY] != 1) {
					return false;
				}
			}
			if(y + 1 >= automaton.getHeight() || automaton.getCellMatrix()[stepX][y + 1] != 0) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Fills an area with vegetation.
	 * 
	 * @param id The integer value to put in the cell matrix.
	 * @param x The left start point of the area.
	 * @param y The bottom start point of the area.
	 * @param height The height of the area.
	 */
	public void fillSpace(int id, int x, int y, int height) {
		for(int stepX = x; stepX <= x + width - 1; stepX++) {
			for(int stepY = y; stepY > y - height; stepY--) {
				automaton.getCellMatrix()[stepX][stepY] = id;
			}
		}
	}

	/**
	 * @return The width of the vegetation to spawn.
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @param width The new width of the vegetation to spawn.
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * @return The minimum height of the vegetation to spawn.
	 */
	public int getMinHeight() {
		return minHeight;
	}

	/**
	 * @param minHeight The new minimum height of the vegetation to spawn.
	 */
	public void setMinHeight(int minHeight) {
		this.minHeight = minHeight;
	}

	/**
	 * @return The maximum height of the vegetation to spawn.
	 */
	public int getMaxHeight() {
		return maxHeight;
	}

	/**
	 * @param maxHeight The new maximum height of the vegetation to spawn.
	 */
	public void setMaxHeight(int maxHeight) {
		this.maxHeight = maxHeight;
	}

	/**
	 * @return The minimum y value that the vegetation spawns on.
	 */
	public int getMinDepth() {
		return minDepth;
	}

	/**
	 * @param minDepth The new minimum y value that the vegetation spawns on.
	 */
	public void setMinDepth(int minDepth) {
		this.minDepth = minDepth;
	}

	/**
	 * @return The maximum y value that the vegetation spawns on.
	 */
	public int getMaxDepth() {
		return maxDepth;
	}

	/**
	 * @param maxDepth The new maximum y value that the vegetation spawns on.
	 */
	public void setMaxDepth(int maxDepth) {
		this.maxDepth = maxDepth;
	}

}
