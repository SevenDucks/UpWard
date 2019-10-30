package eu.wauz.upward.entity.doom;

import eu.wauz.upward.entity.MovingEntity;
import eu.wauz.upward.game.doom.DoomMap;

/**
 * An entity, that can move across a pseudo 3D doom map.
 * 
 * @author Wauzmons
 *
 * @see MovingEntity
 * @see DoomMap
 */
public abstract class DoomEntity extends MovingEntity {

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
	public DoomEntity(double xPos, double yPos, double xDir, double yDir, double xPlane, double yPlane) {
		super(xPos, yPos, xDir, yDir, xPlane, yPlane);
	}
	
	/**
	 * Moves forward, if possible.
	 * 
	 * @param map The map to move on.
	 */
	public void moveForward(int[][] map) {
		double forwardX = xPos + xDir * movementSpeed;
		double forwardY = yPos + yDir * movementSpeed;
		move(map, forwardX, forwardY);
	}
	
	/**
	 * Moves backward, if possible.
	 * 
	 * @param map The map to move on.
	 */
	public void moveBackward(int[][] map) {
		double backwardX = xPos - xDir * movementSpeed;
		double backwardY = yPos - yDir * movementSpeed;
		move(map, backwardX, backwardY);
	}
	
	/**
	 * Moves left, if possible.
	 * 
	 * @param map The map to move on.
	 */
	public void moveLeft(int[][] map) {
		moveSideways(map, +1.57085);
	}
	
	/**
	 * Moves right, if possible.
	 * 
	 * @param map The map to move on.
	 */
	public void moveRight(int[][] map) {
		moveSideways(map, -1.57085);
	}
	
	/**
	 * Moves sideways, if possible.
	 * 
	 * @param map The map to move on.
	 * @param rotation Which direction to move in.
	 */
	public void moveSideways(int[][] map, double rotation) {
		double sidewayX = xPos + getRotationDir(rotation, xDir, yDir, false) * movementSpeed;
		double sidewayY = yPos + getRotationDir(rotation, xDir, yDir, true) * movementSpeed;
		move(map, sidewayX, sidewayY);
	}
	
	/**
	 * Moves the entity, if possible.
	 * 
	 * @param map The map to move on.
	 * @param xPosNew The x position to move to.
	 * @param yPosNew The y position to move to.
	 */
	public void move(int[][] map, double xPosNew, double yPosNew) {
		if(map [(int) xPosNew] [(int) yPos] == 0) {
			xPos = xPosNew;
		}
		if(map [(int) xPos] [(int) yPosNew] == 0) {
			yPos = yPosNew;
		}
	}
	
	/**
	 * Rotates to the given direction.
	 * 
	 * @param rotationSpeed The speed to rotate, if negative, in the oposing direction.
	 */
	public void rotate(double rotationSpeed) {
		xDir = getRotationDir(rotationSpeed, xDir, yDir, false);
		yDir = getRotationDir(rotationSpeed, xDir, yDir, true);
		
		xPlane = getRotationDir(rotationSpeed, xPlane, yPlane, false);
		yPlane = getRotationDir(rotationSpeed, xPlane, yPlane, true);
	}
	
	/**
	 * Determines a rotated direction.
	 * 
	 * @param rotationSpeed The speed to rotate, if negative, in the oposing direction.
	 * @param x The current x plane.
	 * @param y The current y plane.
	 * @param swapAxis If it should rotate on y, instead of x.
	 * 
	 * @return The rotated direction.
	 */
	private double getRotationDir(double rotationSpeed, double x, double y, boolean swapAxis) {
		if(swapAxis) {
			return x * Math.sin(rotationSpeed) + y * Math.cos(rotationSpeed);
		}
		else {
			return x * Math.cos(rotationSpeed) - y * Math.sin(rotationSpeed);
		}
	}

}
