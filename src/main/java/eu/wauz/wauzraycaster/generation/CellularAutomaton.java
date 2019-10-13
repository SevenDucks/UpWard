package eu.wauz.wauzraycaster.generation;

import java.util.Random;

/**
 * A two dimensional cellular automaton.
 * It saves its cell states in an array of integers.
 * Can be used for map generation and other things.
 * 
 * @author Wauzmons
 */
public class CellularAutomaton {
	
	/**
	 * The instance of the random class, used for seeding.
	 */
	private Random random;
	
	/**
	 * The width of the cell matrix.
	 */
	private int width;
	
	/**
	 * The height of the cell matrix.
	 */
	private int height;
	
	/**
	 * The 2D array, to save cell states.
	 */
	private int[][] cellMatrix;
	
	/**
	 * The chance that a cell starts alive.
	 */
	private float chanceToStartAlive = 0.3f;
	
	/**
	 * If a dead cell has at least this amount of neighbours, it will be reborn.
	 */
	private int birthLimit = 4;
	
	/**
	 * If a living cell has less than this amount of neighbours, it will die.
	 */
	private int deathLimit = 3;
	
	/**
	 * The top space that is guaranteed to start alive.
	 */
	private int livingYSpaceTop = 0;
	
	/**
	 * The bottom space that is guaranteed to start alive.
	 */
	private int livingYSpaceBottom = 0;
	
	/**
	 * The left space that is guaranteed to start dead.
	 */
	private int deadXSpaceLeft = 0;
	
	/**
	 * The right space that is guaranteed to start dead.
	 */
	private int deadXSpaceRight = 0;
	
	/**
	 * Creates a new cellular automaton with given sizes.
	 * 
	 * @param width The width of the cell matrix.
	 * @param height The height of the cell matrix.
	 */
	public CellularAutomaton(int width, int height) {
		random = new Random();
		
		this.width = width;
		this.height = height;
		cellMatrix = new int[width][height];
	}
	
	/**
	 * Lets the automaton run, to generate the cell matrix.
	 * 
	 * @param numberOfSteps How many generations the automaton will go through.
	 */
	public void run(int numberOfSteps) {
		generateCells();
		for(int i = 0; i < numberOfSteps; i++) {
			progressCells();
		}
	}
	
	/**
	 * Generates the initial cells for the matrix.
	 */
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
	
	/**
	 * Progresses the cells to the next generation.
	 */
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
	
	/**
	 * @param x The x coordinate of a cell.
	 * @param y The y coordinate of a cell.
	 * @return How many living neighbours that cell has.
	 */
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
	
	/**
	 * @param x The x coordinate of a cell.
	 * @param y The y coordinate of a cell.
	 * @return How many dead neighbours that cell has.
	 */
	public int countDeadNeighbours(int x, int y) {
		return 8 - countLivingNeighbours(x, y);
	}
	
	/**
	 * @return A configured automaton, with a generated terraria style world.
	 */
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
	
	/**
	 * @return A random float, based on the seed.
	 */
	public float random() {
		return random.nextFloat();
	}
	
	/**
	 * @param min Minimum returned value.
	 * @param max Maximum returned value.
	 * @return A random int, based on the seed.
	 */
	public int between(int min, int max) {
		return random.nextInt(max - min + 1) + min;
	}
	
	/**
	 * @return The width of the cell matrix.
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @param width The new width of the cell matrix.
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * @return The height of the cell matrix.
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @param height The new height of the cell matrix.
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * The 2D array, to save cell states.
	 */
	public int[][] getCellMatrix() {
		return cellMatrix;
	}

	/**
	 * @return The chance that a cell starts alive.
	 */
	public float getChanceToStartAlive() {
		return chanceToStartAlive;
	}

	/**
	 * @param chanceToStartAlive The new chance that a cell starts alive.
	 */
	public void setChanceToStartAlive(float chanceToStartAlive) {
		this.chanceToStartAlive = chanceToStartAlive;
	}

	/**
	 * @return If a dead cell has at least this amount of neighbours, it will be reborn.
	 */
	public int getBirthLimit() {
		return birthLimit;
	}

	/**
	 * @param birthLimit If a dead cell has at least this new amount of neighbours, it will be reborn.
	 */
	public void setBirthLimit(int birthLimit) {
		this.birthLimit = birthLimit;
	}

	/**
	 * @return If a living cell has less than this amount of neighbours, it will die.
	 */
	public int getDeathLimit() {
		return deathLimit;
	}

	/**
	 * @param deathLimit If a living cell has less than this new amount of neighbours, it will die.
	 */
	public void setDeathLimit(int deathLimit) {
		this.deathLimit = deathLimit;
	}

	/**
	 * @return The top space that is guaranteed to start alive.
	 */
	public int getLivingYSpaceTop() {
		return livingYSpaceTop;
	}

	/**
	 * @param livingYSpaceTop The new top space that is guaranteed to start alive.
	 */
	public void setLivingYSpaceTop(int livingYSpaceTop) {
		this.livingYSpaceTop = livingYSpaceTop;
	}

	/**
	 * @return The bottom space that is guaranteed to start alive.
	 */
	public int getLivingYSpaceBottom() {
		return livingYSpaceBottom;
	}

	/**
	 * @param livingYSpaceBottom The new bottom space that is guaranteed to start alive.
	 */
	public void setLivingYSpaceBottom(int livingYSpaceBottom) {
		this.livingYSpaceBottom = livingYSpaceBottom;
	}

	/**
	 * @return The left space that is guaranteed to start dead.
	 */
	public int getDeadXSpaceLeft() {
		return deadXSpaceLeft;
	}

	/**
	 * @param deadXSpaceLeft The new left space that is guaranteed to start dead.
	 */
	public void setDeadXSpaceLeft(int deadXSpaceLeft) {
		this.deadXSpaceLeft = deadXSpaceLeft;
	}

	/**
	 * @return The right space that is guaranteed to start dead.
	 */
	public int getDeadXSpaceRight() {
		return deadXSpaceRight;
	}

	/**
	 * @param deadXSpaceRight The new right space that is guaranteed to start dead.
	 */
	public void setDeadXSpaceRight(int deadXSpaceRight) {
		this.deadXSpaceRight = deadXSpaceRight;
	}

}
