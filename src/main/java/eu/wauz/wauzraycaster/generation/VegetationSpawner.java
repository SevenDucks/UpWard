package eu.wauz.wauzraycaster.generation;

public class VegetationSpawner {
	
	private CellularAutomaton automaton;
	
	private int width = 2;
	
	private int minHeight = 7;
	
	private int maxHeight = 14;
	
	private int minDepth = 20;
	
	private int maxDepth = 120;
	
	public VegetationSpawner(CellularAutomaton automaton) {
		this.automaton = automaton;
	}
	
	public void run(int id, float rarity) {
		for(int x = 0; x < automaton.getWidth() - width; x++) {
			for(int y = minDepth; y < maxDepth; y++) {
				if(automaton.random() > rarity) {
					continue;
				}
				int height = automaton.between(minHeight, maxHeight);
				if(hasEnoughSpace(x, y, height)) {
					fillSpace(id, x, y, height);
				}
			}
		}
	}
	
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
	
	public void fillSpace(int id, int x, int y, int height) {
		for(int stepX = x; stepX <= x + width - 1; stepX++) {
			for(int stepY = y; stepY > y - height; stepY--) {
				automaton.getCellMatrix()[stepX][stepY] = id;
			}
		}
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getMinHeight() {
		return minHeight;
	}

	public void setMinHeight(int minHeight) {
		this.minHeight = minHeight;
	}

	public int getMaxHeight() {
		return maxHeight;
	}

	public void setMaxHeight(int maxHeight) {
		this.maxHeight = maxHeight;
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

}
