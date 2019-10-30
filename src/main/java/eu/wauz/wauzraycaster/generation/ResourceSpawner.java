package eu.wauz.wauzraycaster.generation;

import eu.wauz.wauzraycaster.util.WrayUtils;

/**
 * Used to populate a cellular automaton with resources (e.g. ores).
 * 
 * @author Wauzmons
 */
public class ResourceSpawner {
	
	/**
	 * The automaton to populate.
	 */
	private CellularAutomaton automaton;
	
	/**
	 * The minimum y value that the resource spawns on.
	 */
	private int minDepth = 100;
	
	/**
	 * The maximum y value that the resource spawns on.
	 */
	private int maxDepth = 200;
	
	/**
	 * The amount of dead neighbours a cell needs, to become a resource.
	 */
	private int minDeadNeighbours = 5;
	
	/**
	 * Creates a new spawner for an automaton.
	 * 
	 * @param automaton The automaton to populate.
	 */
	public ResourceSpawner(CellularAutomaton automaton) {
		this.automaton = automaton;
	}
	
	/**
	 * Populates the automaton of this spawner.
	 * 
	 * @param id The integer value to put in the cell matrix.
	 * @param rarity The chance for the resource to spawn.
	 */
	public void run(int id, float rarity) {
		for(int x = 0; x < automaton.getWidth(); x++) {
			for(int y = minDepth; y < maxDepth; y++) {
				if(WrayUtils.randomFloat() > rarity || y < minDepth || y > maxDepth) {
					continue;
				}
				if(automaton.countDeadNeighbours(x, y) >= minDeadNeighbours) {
					automaton.getCellMatrix()[x][y] = id;
				}
			}
		}
	}

	/**
	 * @return The minimum y value that the resource spawns on.
	 */
	public int getMinDepth() {
		return minDepth;
	}

	/**
	 * @param minDepth The new minimum y value that the resource spawns on.
	 */
	public void setMinDepth(int minDepth) {
		this.minDepth = minDepth;
	}

	/**
	 * @return The maximum y value that the resource spawns on.
	 */
	public int getMaxDepth() {
		return maxDepth;
	}

	/**
	 * @param maxDepth The new maximum y value that the resource spawns on.
	 */
	public void setMaxDepth(int maxDepth) {
		this.maxDepth = maxDepth;
	}

	/**
	 * @return The amount of dead neighbours a cell needs, to become a resource.
	 */
	public int getMinDeadNeighbours() {
		return minDeadNeighbours;
	}

	/**
	 * @param minDeadNeighbours The new amount of dead neighbours a cell needs, to become a resource.
	 */
	public void setMinDeadNeighbours(int minDeadNeighbours) {
		this.minDeadNeighbours = minDeadNeighbours;
	}

}
