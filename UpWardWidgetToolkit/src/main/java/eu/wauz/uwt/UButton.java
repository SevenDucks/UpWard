package eu.wauz.uwt;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

/**
 * A basic push button.
 * 
 * @author Wauzmons
 */
public class UButton extends JButton {

	/**
	 * This element's UUID.
	 */
	private static final long serialVersionUID = 5261994755986197919L;
	
	/**
	 * Initializes a push button, which executes the given runnable.
	 * 
	 * @param text The label of the button.
	 * @param runnable The action of the button.
	 */
	public UButton(String text, final Runnable runnable) {
		setFocusPainted(false);
		setForeground(Color.WHITE);
		setBackground(Color.BLACK);
		
		setText(text);
		addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				runnable.run();
			}
			
		});
	}
	
	/**
	 * Changes the foreground of the button.
	 * 
	 * @param color The new color.
	 * 
	 * @return The recolored button.
	 */
	public UButton withForeground(Color color) {
		setForeground(color);
		return this;
	}
	
	/**
	 * Changes the background of the button.
	 * 
	 * @param color The new color.
	 * 
	 * @return The recolored button.
	 */
	public UButton withBackground(Color color) {
		setBackground(color);
		return this;
	}

}
