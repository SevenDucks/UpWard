package eu.wauz.wauzraycaster.util;

import eu.wauz.wauzraycaster.game.GameControls;
import eu.wauz.wauzraycaster.game.GameWindow;

/**
 * Static option fields, used by the game.
 * 
 * @author Wauzmons
 */
public class WrayOptions {
	
	/**
	 * Class to hold the keys, bound to the game controls.
	 */
	public static final GameControls CONTROLS = new GameControls();
	
	/**
	 * Class to hold the windows of the game.
	 */
	public static final WindowHolder WINDOWS = new WindowHolder();
	
	/**
	 * Class to hold the windows of the game.
	 * 
	 * @author Wauzmons
	 * 
	 * @see GameWindow
	 */
	public static class WindowHolder {
		
		/**
		 * The main window of the game.
		 */
		private GameWindow mainWindow;

		/**
		 * @return The main window of the game.
		 */
		public GameWindow getMainWindow() {
			return mainWindow;
		}

		/**
		 * @param mainWindow The new main window of the game.
		 */
		public void setMainWindow(GameWindow mainWindow) {
			this.mainWindow = mainWindow;
		}
		
	}

}
