package eu.wauz.wauzraycaster.game;

import java.awt.event.KeyEvent;

public class GameControls {
	
	private int moveLeft = KeyEvent.VK_A;
	
	private int moveRight = KeyEvent.VK_D;
	
	private int moveForward = KeyEvent.VK_W;
	
	private int moveBackward = KeyEvent.VK_S;

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
	
}
