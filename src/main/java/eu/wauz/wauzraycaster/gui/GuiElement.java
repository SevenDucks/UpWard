package eu.wauz.wauzraycaster.gui;

/**
 * An element of a Graphical User Interface.
 * 
 * @author Wauzmons
 */
public interface GuiElement {
	
	/**
	 * Draws the gui element onto the given pixel array.
	 * 
	 * @param pixels
	 */
	public void render(int[][] pixels);

}
