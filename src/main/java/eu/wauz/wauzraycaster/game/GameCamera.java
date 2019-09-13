package eu.wauz.wauzraycaster.game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import eu.wauz.wauzraycaster.Options;

public class GameCamera implements KeyListener {
	
	private double xPos, yPos, xDir, yDir, xPlane, yPlane;
	
	private boolean left, right, forward, backward;
	
	private final double MOVEMENT_SPEED = 0.08;
	
	private final double ROTATION_SPEED = 0.04;
	
	public GameCamera(double xPos, double yPos, double xDir, double yDir, double xPlane, double yPlane) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.xDir = xDir;
		this.yDir = yDir;
		this.xPlane = xPlane;
		this.yPlane = yPlane;
	}
	
	@Override
	public void keyTyped(KeyEvent key) {
		
	}
	
	@Override
	public void keyPressed(KeyEvent key) {
		if(key.getKeyCode() == Options.CONTROLS.getMoveLeft()) {
			left = true;
		}
		else if(key.getKeyCode() == Options.CONTROLS.getMoveRight()) {
			right = true;
		}
		else if(key.getKeyCode() == Options.CONTROLS.getMoveForward()) {
			forward = true;
		}
		else if(key.getKeyCode() == Options.CONTROLS.getMoveBackward()) {
			backward = true;
		}
	}
	
	@Override
	public void keyReleased(KeyEvent key) {
		if(key.getKeyCode() == Options.CONTROLS.getMoveLeft()) {
			left = false;
		}
		else if(key.getKeyCode() == Options.CONTROLS.getMoveRight()) {
			right = false;
		}
		else if(key.getKeyCode() == Options.CONTROLS.getMoveForward()) {
			forward = false;
		}
		else if(key.getKeyCode() == Options.CONTROLS.getMoveBackward()) {
			backward = false;
		}
	}
	
	public void updatePosition(int[][] map) {
		if(forward) {
			moveForward(map);
		}
		if(backward) {
			moveBackward(map);
		}
		if(left) {
			rotate(ROTATION_SPEED);
		}
		if(right) {
			rotate(- ROTATION_SPEED);
		}
	}
	
	public void moveForward(int[][] map) {
		double forwardX = xPos + xDir * MOVEMENT_SPEED;
		double forwardY = yPos + yDir * MOVEMENT_SPEED;
		
		if(map [(int) forwardX] [(int) yPos] == 0) {
			xPos = forwardX;
		}
		if(map [(int) xPos] [(int) forwardY] == 0) {
			yPos = forwardY;
		}
	}
	
	public void moveBackward(int[][] map) {
		double backwardX = xPos - xDir * MOVEMENT_SPEED;
		double backwardY = yPos - yDir * MOVEMENT_SPEED;
		
		if(map [(int) backwardX] [(int) yPos] == 0) {
			xPos = backwardX;
		}
		if(map [(int) xPos] [(int) backwardY] == 0) {
			yPos = backwardY;
		}
	}
	
	public void rotate(double rotationSpeed) {
		double xDirOld = xDir;
		xDir = xDir * Math.cos(rotationSpeed) - yDir * Math.sin(rotationSpeed);
		yDir = xDirOld * Math.sin(rotationSpeed) + yDir * Math.cos(rotationSpeed);
		
		double xPlaneOld = xPlane;
		xPlane = xPlane * Math.cos(rotationSpeed) - yPlane * Math.sin(rotationSpeed);
		yPlane = xPlaneOld * Math.sin(rotationSpeed) + yPlane * Math.cos(rotationSpeed);
	}

	public double getxPos() {
		return xPos;
	}

	public void setxPos(double xPos) {
		this.xPos = xPos;
	}

	public double getyPos() {
		return yPos;
	}

	public void setyPos(double yPos) {
		this.yPos = yPos;
	}

	public double getxDir() {
		return xDir;
	}

	public void setxDir(double xDir) {
		this.xDir = xDir;
	}

	public double getyDir() {
		return yDir;
	}

	public void setyDir(double yDir) {
		this.yDir = yDir;
	}

	public double getxPlane() {
		return xPlane;
	}

	public void setxPlane(double xPlane) {
		this.xPlane = xPlane;
	}

	public double getyPlane() {
		return yPlane;
	}

	public void setyPlane(double yPlane) {
		this.yPlane = yPlane;
	}

}
