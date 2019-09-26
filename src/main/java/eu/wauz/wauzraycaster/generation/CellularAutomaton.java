package eu.wauz.wauzraycaster.generation;

import java.util.Random;

public class CellularAutomaton {
	
	private Random random;
	
	private int width;
	
	private int height;
	
	private int[][] cellMatrix;
	
	private float chanceToStartAlive = 0.3f;
	
	private int birthLimit = 4;
	
	private int deathLimit = 3;
	
	private int livingYSpaceTop = 0;
	
	private int livingYSpaceBottom = 0;
	
	private int deadXSpaceLeft = 0;
	
	private int deadXSpaceRight = 0;
	
	public CellularAutomaton(int width, int height) {
		random = new Random();
		
		this.width = width;
		this.height = height;
		cellMatrix = new int[width][height];
	}
	
	public void run(int numberOfSteps) {
		generateCells();
		for(int i = 0; i < numberOfSteps; i++) {
			progressCells();
		}
	}
	
	public void generateCells() {
		for(int x = 0; x < width; x++) {
			for(int y = 0; y < height; y++) {
				boolean alive;
				if(livingYSpaceTop > y || height - livingYSpaceBottom < y) {
					alive = true;
				}
				else if(deadXSpaceLeft > x || width - deadXSpaceRight < x) {
					alive = false;
				}
				else {
					alive = random() < chanceToStartAlive;
				}
				cellMatrix[x][y] = alive ? 1 : 0;
			}
		}
	}
	
	public void progressCells() {
		int[][] newMatrix = new int[width][height];
		
		for(int x = 0; x < width; x++) {
			for(int y = 0; y < height; y++) {
				int neighbourCount = countLivingNeighbours(x, y);
				boolean wasAlive = cellMatrix[x][y] == 1;
				
				if(wasAlive) {
					newMatrix[x][y] = neighbourCount >= deathLimit ? 1 : 0;
				}
				else {
					newMatrix[x][y] = neighbourCount > birthLimit ? 1 : 0;
				}
			}
		}
		cellMatrix = newMatrix;
	}
	
	public int countLivingNeighbours(int x, int y) {
		int result = 0;
		
		for(int stepX = -1; stepX < 2; stepX++) {
			for(int stepY = -1; stepY < 2; stepY++) {
				if(stepX == 0 && stepY == 0) {
					continue;
				}
				int neighbourX = x + stepX;
				int neighbourY = y + stepY;
				
				boolean outsideXBounds = neighbourX < 0 || neighbourX >= width;
				boolean outsideYBounds = neighbourY < 0 || neighbourY >= height;
				if(outsideXBounds || outsideYBounds || cellMatrix[neighbourX][neighbourY] == 1) {
					result++;
				}
			}
		}
		return result;
	}
	
	public int countDeadNeighbours(int x, int y) {
		return 8 - countLivingNeighbours(x, y);
	}
	
	public CellularAutomaton withTerrariaPreset() {
		setChanceToStartAlive(0.28f);
		setBirthLimit(3);
		setDeathLimit(3);
		setLivingYSpaceTop(100);
		setLivingYSpaceBottom(30);
		setDeadXSpaceLeft(15);
		setDeadXSpaceRight(15);
		run(18);
		
		return this;
	}
	
	public float random() {
		return random.nextFloat();
	}
	
	public int between(int min, int max) {
		return random.nextInt(max - min + 1) + min;
	}
	
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int[][] getCellMatrix() {
		return cellMatrix;
	}

	public float getChanceToStartAlive() {
		return chanceToStartAlive;
	}

	public void setChanceToStartAlive(float chanceToStartAlive) {
		this.chanceToStartAlive = chanceToStartAlive;
	}

	public int getBirthLimit() {
		return birthLimit;
	}

	public void setBirthLimit(int birthLimit) {
		this.birthLimit = birthLimit;
	}

	public int getDeathLimit() {
		return deathLimit;
	}

	public void setDeathLimit(int deathLimit) {
		this.deathLimit = deathLimit;
	}

	public int getLivingYSpaceTop() {
		return livingYSpaceTop;
	}

	public void setLivingYSpaceTop(int livingYSpaceTop) {
		this.livingYSpaceTop = livingYSpaceTop;
	}

	public int getLivingYSpaceBottom() {
		return livingYSpaceBottom;
	}

	public void setLivingYSpaceBottom(int livingYSpaceBottom) {
		this.livingYSpaceBottom = livingYSpaceBottom;
	}

	public int getDeadXSpaceLeft() {
		return deadXSpaceLeft;
	}

	public void setDeadXSpaceLeft(int deadXSpaceLeft) {
		this.deadXSpaceLeft = deadXSpaceLeft;
	}

	public int getDeadXSpaceRight() {
		return deadXSpaceRight;
	}

	public void setDeadXSpaceRight(int deadXSpaceRight) {
		this.deadXSpaceRight = deadXSpaceRight;
	}

}
