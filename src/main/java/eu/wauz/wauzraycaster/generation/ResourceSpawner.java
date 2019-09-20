package eu.wauz.wauzraycaster.generation;

public class ResourceSpawner {
	
	private CellularAutomaton automaton;
	
	private int minDepth = 100;
	
	private int maxDepth = 200;
	
	private int minDeadNeighbours = 5;
	
	public ResourceSpawner(CellularAutomaton automaton) {
		this.automaton = automaton;
	}
	
	public void run(int id, float rarity) {
		for(int x = 0; x < automaton.getWidth(); x++) {
			for(int y = minDepth; y < maxDepth; y++) {
				if(automaton.random() > rarity || y < minDepth || y > maxDepth) {
					continue;
				}
				if(automaton.countDeadNeighbours(x, y) >= minDeadNeighbours) {
					automaton.getCellMatrix()[x][y] = id;
				}
			}
		}
	}

	public int getMinDepth() {
		return minDepth;
	}

	public void setMinDepth(int minDepth) {
		this.minDepth = minDepth;
	}

	public int getMaxDepth() {
		return maxDepth;
	}

	public void setMaxDepth(int maxDepth) {
		this.maxDepth = maxDepth;
	}

	public int getMinDeadNeighbours() {
		return minDeadNeighbours;
	}

	public void setMinDeadNeighbours(int minDeadNeighbours) {
		this.minDeadNeighbours = minDeadNeighbours;
	}

}
