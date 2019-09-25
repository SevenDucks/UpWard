package eu.wauz.wauzraycaster.entity.terraria;

import java.awt.event.KeyEvent;

import eu.wauz.wauzraycaster.entity.Controller;
import eu.wauz.wauzraycaster.entity.MovingEntity;
import eu.wauz.wauzraycaster.util.WrayOptions;

public class TerrariaCamera extends MovingEntity implements Controller {
	
	private boolean up, down, left, right;

	public TerrariaCamera(double xPos, double yPos, double xDir, double yDir, double xPlane, double yPlane) {
		super(xPos, yPos, xDir, yDir, xPlane, yPlane);
		MOVEMENT_SPEED = 0.0625;
	}

	@Override
	public void keyTyped(KeyEvent key) {
		
	}
	
	@Override
	public void keyPressed(KeyEvent key) {
		if(key.getKeyCode() == WrayOptions.CONTROLS.getMoveForward()) {
			up = true;
		}
		else if(key.getKeyCode() == WrayOptions.CONTROLS.getMoveBackward()) {
			down = true;
		}
		else if(key.getKeyCode() == WrayOptions.CONTROLS.getRotateLeft()) {
			left = true;
		}
		else if(key.getKeyCode() == WrayOptions.CONTROLS.getRotateRight()) {
			right = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent key) {
		if(key.getKeyCode() == WrayOptions.CONTROLS.getMoveForward()) {
			up = false;
		}
		else if(key.getKeyCode() == WrayOptions.CONTROLS.getMoveBackward()) {
			down = false;
		}
		else if(key.getKeyCode() == WrayOptions.CONTROLS.getRotateLeft()) {
			left = false;
		}
		else if(key.getKeyCode() == WrayOptions.CONTROLS.getRotateRight()) {
			right = false;
		}
	}

	@Override
	public void updatePosition(int[][] map) {
		if(up) {
			moveUp(map);
		}
		if(down) {
			moveDown(map);
		}
		if(left) {
			moveLeft(map);
		}
		if(right) {
			moveRight(map);
		}
	}
	
	public void moveUp(int[][] map) {
		yPos -= MOVEMENT_SPEED;
	}
	
	public void moveDown(int[][] map) {
		yPos += MOVEMENT_SPEED;
	}
	
	public void moveLeft(int[][] map) {
		xPos -= MOVEMENT_SPEED;
	}
	
	public void moveRight(int[][] map) {
		xPos += MOVEMENT_SPEED;
	}

}
