package eu.wauz.wauzraycaster.entity.doom;

import java.awt.event.KeyEvent;

import eu.wauz.wauzraycaster.entity.interfaces.Controller;
import eu.wauz.wauzraycaster.game.doom.DoomMap;
import eu.wauz.wauzraycaster.util.WrayOptions;

/**
 * A camera entity, that can move across a pseudo 3D doom map.
 * 
 * @author Wauzmons
 *
 * @see DoomEntity
 * @see DoomMap
 */
public class DoomCamera extends DoomEntity implements Controller {
	
	/**
	 * If the entity is moving in this direction
	 */
	private boolean leftRotate, rightRotate, forward, backward, left, right;
	
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
	public DoomCamera(double xPos, double yPos, double xDir, double yDir, double xPlane, double yPlane) {
		super(xPos, yPos, xDir, yDir, xPlane, yPlane);
	}
	
	/**
	 * Determine what to do if a key is typed.
	 */
	@Override
	public void keyTyped(KeyEvent key) {
		
	}
	
	/**
	 * Determine what to do if a key is pressed.
	 * Starts directional movement.
	 */
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
	
	/**
	 * Determine what to do if a key is released.
	 * Stops directional movement.
	 */
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
	
	
	/**
	 * Moves into the active directions, if possible.
	 */
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
