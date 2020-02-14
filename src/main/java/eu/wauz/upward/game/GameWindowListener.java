package eu.wauz.upward.game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import eu.wauz.upward.UpWardOptions;

/**
 * The default listener of a game window.
 * Used to handle game independent key events.
 * 
 * @author Wauzmons
 */
public class GameWindowListener implements KeyListener {
	
	/**
	 * If the info string should be visible in the game window.
	 */
	private boolean showInfoString = false;

	/**
	 * Determine what to do if a key is typed.
	 */
	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	/**
	 * Determine what to do if a key is pressed.
	 * Function 3 toggles the info string display.
	 */
	@Override
	public void keyPressed(KeyEvent key) {
		if(key.getKeyCode() == UpWardOptions.CONTROLS.getFunction03()) {
			showInfoString = !showInfoString;
		}
	}

	/**
	 * Determine what to do if a key is released.
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	/**
	 * Checks if the info string should be shown.
	 * 
	 * @return If the info string should be visible in the game window.
	 */
	public boolean isShowInfoString() {
		return showInfoString;
	}

}
