package eu.wauz.wauzraycaster.entity.terraria;

import java.awt.event.KeyEvent;

import eu.wauz.wauzraycaster.entity.MovingEntity;
import eu.wauz.wauzraycaster.entity.interfaces.Controller;
import eu.wauz.wauzraycaster.game.terraria.TerrariaMap;
import eu.wauz.wauzraycaster.util.WrayOptions;

/**
 * A camera entity, that can move freely across a terraria map.
 * 
 * @author Wauzmons
 *
 * @see TerrariaMap
 */
public class TerrariaCamera extends MovingEntity implements Controller {
	
	/**
	 * If the entity is moving in this direction.
	 */
	private boolean up, down, left, right;

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
	public TerrariaCamera(double xPos, double yPos, double xDir, double yDir, double xPlane, double yPlane) {
		super(xPos, yPos, xDir, yDir, xPlane, yPlane);
		movementSpeed = 0.25;
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

	/**
	 * Determine what to do if a key is released.
	 * Stops directional movement.
	 */
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

	/**
	 * Moves into the active directions, if possible.
	 */
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
	
	/**
	 * Moves up.
	 * 
	 * @param map The map to move on.
	 */
	public void moveUp(int[][] map) {
		yPos -= movementSpeed;
	}
	
	/**
	 * Moves down.
	 * 
	 * @param map The map to move on.
	 */
	public void moveDown(int[][] map) {
		yPos += movementSpeed;
	}
	
	/**
	 * Moves left.
	 * 
	 * @param map The map to move on.
	 */
	public void moveLeft(int[][] map) {
		xPos -= movementSpeed;
	}
	
	/**
	 * Moves right.
	 * 
	 * @param map The map to move on.
	 */
	public void moveRight(int[][] map) {
		xPos += movementSpeed;
	}

}
