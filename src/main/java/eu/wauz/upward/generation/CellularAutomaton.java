package eu.wauz.upward.generation;

import eu.wauz.upwardutils.UpWardUtils;

/**
 * A two dimensional cellular automaton.
 * It saves its cell states in an array of integers.
 * Can be used for map generation and other things.
 * 
 * @author Wauzmons
 */
public class CellularAutomaton {
	
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
	 * The left space that is guaranteed to start alive.
	 */
	private int livingXSpaceLeft = 0;
	
	/**
	 * The right space that is guaranteed to start alive.
	 */
	private int livingXSpaceRight = 0;
	
	/**
	 * The top space that is guaranteed to start dead.
	 */
	private int deadYSpaceTop = 0;
	
	/**
	 * The botton space that is guaranteed to start dead.
	 */
	private int deadYSpaceBottom = 0;
	
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
				if(livingYSpaceTop > y || height - livingYSpaceBottom < y || livingXSpaceLeft > x || width - livingXSpaceRight < x) {
					alive = true;
				}
				else if(deadYSpaceTop > y || height - deadYSpaceBottom < y || deadXSpaceLeft > x || width - deadXSpaceRight < x) {
					alive = false;
				}
				else {
					alive = UpWardUtils.randomFloat() < chanceToStartAlive;
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
	 * 
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
	 * @return A configured automaton, with a generated cave room.
	 */
	public CellularAutomaton withCavePreset() {
		setChanceToStartAlive(0.46f);
		setBirthLimit(4);
		setDeathLimit(4);
		setLivingYSpaceTop(8);
		setLivingYSpaceBottom(8);
		setLivingXSpaceLeft(8);
		setLivingXSpaceRight(8);
		run(8);
		
		return this;
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
	 * @return The 2D array, to save cell states.
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
	 * @return The left space that is guaranteed to start alive.
	 */
	public int getLivingXSpaceLeft() {
		return livingXSpaceLeft;
	}

	/**
	 * @param livingXSpaceLeft The new left space that is guaranteed to start alive.
	 */
	public void setLivingXSpaceLeft(int livingXSpaceLeft) {
		this.livingXSpaceLeft = livingXSpaceLeft;
	}

	/**
	 * @return The right space that is guaranteed to start alive.
	 */
	public int getLivingXSpaceRight() {
		return livingXSpaceRight;
	}

	/**
	 * @param livingXSpaceRight The new right space that is guaranteed to start alive.
	 */
	public void setLivingXSpaceRight(int livingXSpaceRight) {
		this.livingXSpaceRight = livingXSpaceRight;
	}

	/**
	 * @return The top space that is guaranteed to start dead.
	 */
	public int getDeadYSpaceTop() {
		return deadYSpaceTop;
	}

	/**
	 * @param deadYSpaceTop The new top space that is guaranteed to start dead.
	 */
	public void setDeadYSpaceTop(int deadYSpaceTop) {
		this.deadYSpaceTop = deadYSpaceTop;
	}

	/**
	 * @return The bottom space that is guaranteed to start dead.
	 */
	public int getDeadYSpaceBottom() {
		return deadYSpaceBottom;
	}

	/**
	 * @param deadYSpaceBottom The new bottom space that is guaranteed to start dead.
	 */
	public void setDeadYSpaceBottom(int deadYSpaceBottom) {
		this.deadYSpaceBottom = deadYSpaceBottom;
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
