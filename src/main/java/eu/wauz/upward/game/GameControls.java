package eu.wauz.upward.game;

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
	 * The key bound to interactions with an object above.
	 */
	private int interactTop = KeyEvent.VK_UP;
	
	/**
	 * The key bound to interactions with an object below.
	 */
	private int interactBottom = KeyEvent.VK_DOWN;
	
	/**
	 * The key bound to interactions with an object to the left.
	 */
	private int interactLeft = KeyEvent.VK_LEFT;
	
	/**
	 * The key bound to interactions with an object to the right.
	 */
	private int interactRight = KeyEvent.VK_RIGHT;
	
	/**
	 * The key bound to the F1 function.
	 */
	private int function01 = KeyEvent.VK_F1;
	
	/**
	 * The key bound to the F2 function.
	 */
	private int function02 = KeyEvent.VK_F2;
	
	/**
	 * The key bound to the F3 function.
	 */
	private int function03 = KeyEvent.VK_F3;
	
	/**
	 * The key bound to the F4 function.
	 */
	private int function04 = KeyEvent.VK_F4;
	
	/**
	 * The key bound to the F5 function.
	 */
	private int function05 = KeyEvent.VK_F5;
	
	/**
	 * The key bound to the F6 function.
	 */
	private int function06 = KeyEvent.VK_F6;
	
	/**
	 * The key bound to the F7 function.
	 */
	private int function07 = KeyEvent.VK_F7;
	
	/**
	 * The key bound to the F8 function.
	 */
	private int function08 = KeyEvent.VK_F8;
	
	/**
	 * The key bound to the F9 function.
	 */
	private int function09 = KeyEvent.VK_F9;
	
	/**
	 * The key bound to the F10 function.
	 */
	private int function10 = KeyEvent.VK_F10;
	
	/**
	 * The key bound to the F11 function.
	 */
	private int function11 = KeyEvent.VK_F11;
	
	/**
	 * The key bound to the F12 function.
	 */
	private int function12 = KeyEvent.VK_F12;

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

	/**
	 * @return The key bound to interactions with an object above.
	 */
	public int getInteractTop() {
		return interactTop;
	}

	/**
	 * @param interactTop The new key bound to interactions with an object above.
	 */
	public void setInteractTop(int interactTop) {
		this.interactTop = interactTop;
	}

	/**
	 * @return The key bound to interactions with an object below.
	 */
	public int getInteractBottom() {
		return interactBottom;
	}

	/**
	 * @param interactBottom The new key bound to interactions with an object below.
	 */
	public void setInteractBottom(int interactBottom) {
		this.interactBottom = interactBottom;
	}

	/**
	 * @return The key bound to interactions with an object to the left.
	 */
	public int getInteractLeft() {
		return interactLeft;
	}

	/**
	 * @param interactLeft The new key bound to interactions with an object to the left.
	 */
	public void setInteractLeft(int interactLeft) {
		this.interactLeft = interactLeft;
	}

	/**
	 * @return The key bound to interactions with an object to the right.
	 */
	public int getInteractRight() {
		return interactRight;
	}

	/**
	 * @param interactRight The new key bound to interactions with an object to the right.
	 */
	public void setInteractRight(int interactRight) {
		this.interactRight = interactRight;
	}

	/**
	 * @return The key bound to the F1 function.
	 */
	public int getFunction01() {
		return function01;
	}

	/**
	 * @param function01 The new key bound to the F1 function.
	 */
	public void setFunction01(int function01) {
		this.function01 = function01;
	}

	/**
	 * @return The key bound to the F2 function.
	 */
	public int getFunction02() {
		return function02;
	}

	/**
	 * @param function02 The new key bound to the F2 function.
	 */
	public void setFunction02(int function02) {
		this.function02 = function02;
	}

	/**
	 * @return The key bound to the F3 function.
	 */
	public int getFunction03() {
		return function03;
	}

	/**
	 * @param function03 The new key bound to the F3 function.
	 */
	public void setFunction03(int function03) {
		this.function03 = function03;
	}

	/**
	 * @return The key bound to the F4 function.
	 */
	public int getFunction04() {
		return function04;
	}

	/**
	 * @param function04 The new key bound to the F4 function.
	 */
	public void setFunction04(int function04) {
		this.function04 = function04;
	}

	/**
	 * @return The key bound to the F5 function.
	 */
	public int getFunction05() {
		return function05;
	}

	/**
	 * @param function05 The new key bound to the F5 function.
	 */
	public void setFunction05(int function05) {
		this.function05 = function05;
	}

	/**
	 * @return The key bound to the F6 function.
	 */
	public int getFunction06() {
		return function06;
	}

	/**
	 * @param function06 The new key bound to the F7 function.
	 */
	public void setFunction06(int function06) {
		this.function06 = function06;
	}

	/**
	 * @return The key bound to the F7 function.
	 */
	public int getFunction07() {
		return function07;
	}

	/**
	 * @param function07 The new key bound to the F7 function.
	 */
	public void setFunction07(int function07) {
		this.function07 = function07;
	}

	/**
	 * @return The key bound to the F8 function.
	 */
	public int getFunction08() {
		return function08;
	}

	/**
	 * @param function08 The new key bound to the F8 function.
	 */
	public void setFunction08(int function08) {
		this.function08 = function08;
	}

	/**
	 * @return The key bound to the F9 function.
	 */
	public int getFunction09() {
		return function09;
	}

	/**
	 * @param function09 The new key bound to the F9 function.
	 */
	public void setFunction09(int function09) {
		this.function09 = function09;
	}

	/**
	 * @return The key bound to the F10 function.
	 */
	public int getFunction10() {
		return function10;
	}

	/**
	 * @param function10 The new key bound to the F10 function.
	 */
	public void setFunction10(int function10) {
		this.function10 = function10;
	}

	/**
	 * @return The key bound to the F11 function.
	 */
	public int getFunction11() {
		return function11;
	}

	/**
	 * @param function11 The new key bound to the F11 function.
	 */
	public void setFunction11(int function11) {
		this.function11 = function11;
	}

	/**
	 * @return The key bound to the F12 function.
	 */
	public int getFunction12() {
		return function12;
	}

	/**
	 * @param function12 The new key bound to the F12 function.
	 */
	public void setFunction12(int function12) {
		this.function12 = function12;
	}

}
