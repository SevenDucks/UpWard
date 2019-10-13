package eu.wauz.wauzraycaster.entity;

/**
 * An entity with a changeable position on a map.
 * 
 * @author Wauzmons
 */
public abstract class MovingEntity {
	
	/**
	 * The map coordinates of the entity.
	 */
	protected double xPos, yPos, xDir, yDir, xPlane, yPlane;
	
	/**
	 * The movement speed in tiles per frame.
	 */
	protected double MOVEMENT_SPEED = 0.08;
	
	/**
	 * The rotation speed in tiles per frame.
	 */
	protected double ROTATION_SPEED = 0.04;
	
	/**
	 * Creates a new entity, with given starting position.
	 * 
	 * @param xPos
	 * @param yPos
	 * @param xDir
	 * @param yDir
	 * @param xPlane
	 * @param yPlane
	 */
	public MovingEntity(double xPos, double yPos, double xDir, double yDir, double xPlane, double yPlane) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.xDir = xDir;
		this.yDir = yDir;
		this.xPlane = xPlane;
		this.yPlane = yPlane;
	}
	
	/**
	 * Updates the position of the entity on the map.
	 * Called every frame of the game window.
	 * 
	 * @param map The map as matrix, where a 0 means air.
	 */
	public abstract void updatePosition(int[][] map);

	/**
	 * @return The x position on the map.
	 */
	public double getxPos() {
		return xPos;
	}

	/**
	 * @param xPos The new x position on the map.
	 */
	public void setxPos(double xPos) {
		this.xPos = xPos;
	}

	/**
	 * @return The y position on the map.
	 */
	public double getyPos() {
		return yPos;
	}

	/**
	 * @param yPos The new y position on the map.
	 */
	public void setyPos(double yPos) {
		this.yPos = yPos;
	}

	/**
	 * @return The x direction on the map.
	 */
	public double getxDir() {
		return xDir;
	}

	/**
	 * @param xDir The new x direction on the map.
	 */
	public void setxDir(double xDir) {
		this.xDir = xDir;
	}

	/**
	 * @return The y direction on the map.
	 */
	public double getyDir() {
		return yDir;
	}

	/**
	 * @param yDir The new y direction on the map.
	 */
	public void setyDir(double yDir) {
		this.yDir = yDir;
	}

	/**
	 * @return The x plane on the map.
	 */
	public double getxPlane() {
		return xPlane;
	}

	/**
	 * @param xPlane The new x plane on the map.
	 */
	public void setxPlane(double xPlane) {
		this.xPlane = xPlane;
	}

	/**
	 * @return The y plane on the map.
	 */
	public double getyPlane() {
		return yPlane;
	}

	/**
	 * @param yPlane The new y plane on the map.
	 */
	public void setyPlane(double yPlane) {
		this.yPlane = yPlane;
	}

}
