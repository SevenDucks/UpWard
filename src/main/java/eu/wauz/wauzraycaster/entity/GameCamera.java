package eu.wauz.wauzraycaster.entity;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import eu.wauz.wauzraycaster.util.WrayOptions;

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
		if(key.getKeyCode() == WrayOptions.CONTROLS.getRotateLeft()) {
			leftRotate = true;
		}
		else if(key.getKeyCode() == WrayOptions.CONTROLS.getRotateRight()) {
			rightRotate = true;
		}
		else if(key.getKeyCode() == WrayOptions.CONTROLS.getMoveForward()) {
			forward = true;
		}
		else if(key.getKeyCode() == WrayOptions.CONTROLS.getMoveBackward()) {
			backward = true;
		}
		else if(key.getKeyCode() == WrayOptions.CONTROLS.getMoveLeft()) {
			left = true;
		}
		else if(key.getKeyCode() == WrayOptions.CONTROLS.getMoveRight()) {
			right = true;
		}
	}
	
	@Override
	public void keyReleased(KeyEvent key) {
		if(key.getKeyCode() == WrayOptions.CONTROLS.getRotateLeft()) {
			leftRotate = false;
		}
		else if(key.getKeyCode() == WrayOptions.CONTROLS.getRotateRight()) {
			rightRotate = false;
		}
		else if(key.getKeyCode() == WrayOptions.CONTROLS.getMoveForward()) {
			forward = false;
		}
		else if(key.getKeyCode() == WrayOptions.CONTROLS.getMoveBackward()) {
			backward = false;
		}
		else if(key.getKeyCode() == WrayOptions.CONTROLS.getMoveLeft()) {
			left = false;
		}
		else if(key.getKeyCode() == WrayOptions.CONTROLS.getMoveRight()) {
			right = false;
		}
	}
	
	@Override
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
