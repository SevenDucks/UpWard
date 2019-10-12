package eu.wauz.wauzraycaster.game;

import java.awt.event.KeyEvent;

/**
 * Class to hold the keys, bound to the game controls.
 * 
 * @author Wauzmons
 */
public class GameControls {
	
	/**
	 * The key bound to left rotations or movements.
	 */
	private int rotateLeft = KeyEvent.VK_A;
	
	/**
	 * The key bound to right rotations or movements.
	 */
	private int rotateRight = KeyEvent.VK_D;
	
	/**
	 * The key bound to forward or upward movements.
	 */
	private int moveForward = KeyEvent.VK_W;
	
	/**
	 * The key bound to backward or downward movements.
	 */
	private int moveBackward = KeyEvent.VK_S;
	
	/**
	 * The key bound to sideway left movements.
	 */
	private int moveLeft = KeyEvent.VK_Q;
	
	/**
	 * The key bound to sideway right movements.
	 */
	private int moveRight = KeyEvent.VK_E;

	/**
	 * @return The key bound to left rotations or movements.
	 */
	public int getRotateLeft() {
		return rotateLeft;
	}

	/**
	 * @param rotateLeft The new key bound to left rotations or movements.
	 */
	public void setRotateLeft(int rotateLeft) {
		this.rotateLeft = rotateLeft;
	}

	/**
	 * @return The key bound to right rotations or movements.
	 */
	public int getRotateRight() {
		return rotateRight;
	}

	/**
	 * @param rotateRight The new key bound to right rotations or movements.
	 */
	public void setRotateRight(int rotateRight) {
		this.rotateRight = rotateRight;
	}

	/**
	 * @return The key bound to forward or upward movements.
	 */
	public int getMoveForward() {
		return moveForward;
	}

	/**
	 * @param moveForward The new key bound to forward or upward movements.
	 */
	public void setMoveForward(int moveForward) {
		this.moveForward = moveForward;
	}

	/**
	 * @return The key bound to backward or downward movements.
	 */
	public int getMoveBackward() {
		return moveBackward;
	}

	/**
	 * @param moveBackward The new key bound to backward or downward movements.
	 */
	public void setMoveBackward(int moveBackward) {
		this.moveBackward = moveBackward;
	}

	/**
	 * @return The key bound to sideway left movements.
	 */
	public int getMoveLeft() {
		return moveLeft;
	}

	/**
	 * @param moveLeft The new key bound to sideway left movements.
	 */
	public void setMoveLeft(int moveLeft) {
		this.moveLeft = moveLeft;
	}

	/**
	 * @return The key bound to sideway right movements.
	 */
	public int getMoveRight() {
		return moveRight;
	}

	/**
	 * @param moveRight The new key bound to sideway right movements.
	 */
	public void setMoveRight(int moveRight) {
		this.moveRight = moveRight;
	}

}
