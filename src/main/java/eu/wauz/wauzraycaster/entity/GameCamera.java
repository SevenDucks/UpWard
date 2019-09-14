package eu.wauz.wauzraycaster.entity;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import eu.wauz.wauzraycaster.Options;

public class GameCamera extends MovingEntity implements KeyListener {
	
	private boolean leftRotate, rightRotate, forward, backward, left, right;
	
	public GameCamera(double xPos, double yPos, double xDir, double yDir, double xPlane, double yPlane) {
		super(xPos, yPos, xDir, yDir, xPlane, yPlane);
	}
	
	@Override
	public void keyTyped(KeyEvent key) {
		
	}
	
	@Override
	public void keyPressed(KeyEvent key) {
		if(key.getKeyCode() == Options.CONTROLS.getRotateLeft()) {
			leftRotate = true;
		}
		else if(key.getKeyCode() == Options.CONTROLS.getRotateRight()) {
			rightRotate = true;
		}
		else if(key.getKeyCode() == Options.CONTROLS.getMoveForward()) {
			forward = true;
		}
		else if(key.getKeyCode() == Options.CONTROLS.getMoveBackward()) {
			backward = true;
		}
		else if(key.getKeyCode() == Options.CONTROLS.getMoveLeft()) {
			left = true;
		}
		else if(key.getKeyCode() == Options.CONTROLS.getMoveRight()) {
			right = true;
		}
	}
	
	@Override
	public void keyReleased(KeyEvent key) {
		if(key.getKeyCode() == Options.CONTROLS.getRotateLeft()) {
			leftRotate = false;
		}
		else if(key.getKeyCode() == Options.CONTROLS.getRotateRight()) {
			rightRotate = false;
		}
		else if(key.getKeyCode() == Options.CONTROLS.getMoveForward()) {
			forward = false;
		}
		else if(key.getKeyCode() == Options.CONTROLS.getMoveBackward()) {
			backward = false;
		}
		else if(key.getKeyCode() == Options.CONTROLS.getMoveLeft()) {
			left = false;
		}
		else if(key.getKeyCode() == Options.CONTROLS.getMoveRight()) {
			right = false;
		}
	}
	
	public void updatePosition(int[][] map) {
		if(leftRotate) {
			rotate(ROTATION_SPEED);
		}
		if(rightRotate) {
			rotate(- ROTATION_SPEED);
		}
		if(forward) {
			moveForward(map);
		}
		if(backward) {
			moveBackward(map);
		}
		if(left) {
			moveLeft(map);
		}
		if(right) {
			moveRight(map);
		}
	}

}
