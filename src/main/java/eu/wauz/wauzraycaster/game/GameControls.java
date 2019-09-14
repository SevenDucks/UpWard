package eu.wauz.wauzraycaster.game;

import java.awt.event.KeyEvent;

public class GameControls {
	
	private int rotateLeft = KeyEvent.VK_A;
	
	private int rotateRight = KeyEvent.VK_D;
	
	private int moveForward = KeyEvent.VK_W;
	
	private int moveBackward = KeyEvent.VK_S;
	
	private int moveLeft = KeyEvent.VK_Q;
	
	private int moveRight = KeyEvent.VK_E;

	public int getRotateLeft() {
		return rotateLeft;
	}

	public void setRotateLeft(int rotateLeft) {
		this.rotateLeft = rotateLeft;
	}

	public int getRotateRight() {
		return rotateRight;
	}

	public void setRotateRight(int rotateRight) {
		this.rotateRight = rotateRight;
	}

	public int getMoveForward() {
		return moveForward;
	}

	public void setMoveForward(int moveForward) {
		this.moveForward = moveForward;
	}

	public int getMoveBackward() {
		return moveBackward;
	}

	public void setMoveBackward(int moveBackward) {
		this.moveBackward = moveBackward;
	}

	public int getMoveLeft() {
		return moveLeft;
	}

	public void setMoveLeft(int moveLeft) {
		this.moveLeft = moveLeft;
	}

	public int getMoveRight() {
		return moveRight;
	}

	public void setMoveRight(int moveRight) {
		this.moveRight = moveRight;
	}

}
