package eu.wauz.wauzraycaster.entity.doom;

import eu.wauz.wauzraycaster.entity.MovingEntity;

public abstract class DoomEntity extends MovingEntity {

	public DoomEntity(double xPos, double yPos, double xDir, double yDir, double xPlane, double yPlane) {
		super(xPos, yPos, xDir, yDir, xPlane, yPlane);
	}
	
	public void moveForward(int[][] map) {
		double forwardX = xPos + xDir * MOVEMENT_SPEED;
		double forwardY = yPos + yDir * MOVEMENT_SPEED;
		move(map, forwardX, forwardY);
	}
	
	public void moveBackward(int[][] map) {
		double backwardX = xPos - xDir * MOVEMENT_SPEED;
		double backwardY = yPos - yDir * MOVEMENT_SPEED;
		move(map, backwardX, backwardY);
	}
	
	public void moveLeft(int[][] map) {
		moveSideways(map, +1.57085);
	}
	
	public void moveRight(int[][] map) {
		moveSideways(map, -1.57085);
	}
	
	public void moveSideways(int[][] map, double rotation) {
		double sidewayX = xPos + getRotationDir(rotation, xDir, yDir, false) * MOVEMENT_SPEED;
		double sidewayY = yPos + getRotationDir(rotation, xDir, yDir, true) * MOVEMENT_SPEED;
		move(map, sidewayX, sidewayY);
	}
	
	public void move(int[][] map, double xPosNew, double yPosNew) {
		if(map [(int) xPosNew] [(int) yPos] == 0) {
			xPos = xPosNew;
		}
		if(map [(int) xPos] [(int) yPosNew] == 0) {
			yPos = yPosNew;
		}
	}
	
	public void rotate(double rotationSpeed) {
		xDir = getRotationDir(rotationSpeed, xDir, yDir, false);
		yDir = getRotationDir(rotationSpeed, xDir, yDir, true);
		
		xPlane = getRotationDir(rotationSpeed, xPlane, yPlane, false);
		yPlane = getRotationDir(rotationSpeed, xPlane, yPlane, true);
	}
	
	private double getRotationDir(double rotationSpeed, double x, double y, boolean swapAxis) {
		if(swapAxis) {
			return x * Math.sin(rotationSpeed) + y * Math.cos(rotationSpeed);
		}
		else {
			return x * Math.cos(rotationSpeed) - y * Math.sin(rotationSpeed);
		}
	}

}
