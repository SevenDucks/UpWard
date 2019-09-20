package eu.wauz.wauzraycaster.util;

import java.util.Random;

public class CellularAutomaton {
	
	private int width;
	
	private int height;
	
	private boolean[][] cellMatrix;
	
	private Random random;
	
	private float chanceToStartAlive = 0.3f;
	
	private int birthLimit = 4;
	
	private int deathLimit = 3;
	
	public CellularAutomaton(int width, int height) {
		this.width = width;
		this.height = height;
		
		cellMatrix = new boolean[width][height];
		random = new Random();
		
		generateCells();
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
				cellMatrix[x][y] = random.nextFloat() < chanceToStartAlive;
			}
		}
	}
	
	public void progressCells() {
		boolean[][] newMatrix = new boolean[width][height];
		
		for(int x = 0; x < width; x++) {
			for(int y = 0; y < height; y++) {
				int neighbourCount = countLivingNeighbours(x, y);
				boolean wasAlive = cellMatrix[x][y];
				
				if(wasAlive) {
					newMatrix[x][y] = neighbourCount >= deathLimit;
				}
				else {
					newMatrix[x][y] = neighbourCount > birthLimit;
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
				if(outsideXBounds || outsideYBounds || cellMatrix[neighbourX][neighbourY]) {
					result++;
				}
			}
		}
		return result;
	}

	public boolean[][] getCellMatrix() {
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

}
