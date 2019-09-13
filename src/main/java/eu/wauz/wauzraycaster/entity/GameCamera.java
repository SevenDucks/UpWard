package eu.wauz.wauzraycaster.entity;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import eu.wauz.wauzraycaster.Options;

public class GameCamera extends MovingEntity implements KeyListener {
	
	private boolean left, right, forward, backward;
	
	public GameCamera(double xPos, double yPos, double xDir, double yDir, double xPlane, double yPlane) {
		super(xPos, yPos, xDir, yDir, xPlane, yPlane);
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

}
