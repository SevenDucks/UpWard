package eu.wauz.uwt;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

/**
 * A basic text label.
 * 
 * @author Wauzmons
 */
public class ULabel extends JLabel {

	/**
	 * This element's UUID.
	 */
	private static final long serialVersionUID = 6034723556356334631L;
	
	/**
	 * Initializes a label with the given text.
	 * 
	 * @param text Text of the label.
	 */
	public ULabel(String text) {
		setText(text);
	}
	
	/**
	 * Formats the label to display as title.
	 * 
	 * @return The formatted label.
	 */
	public ULabel inTitleFormat() {
		setFont(new Font(Font.SANS_SERIF, Font.BOLD, 32));
        setForeground(Color.WHITE);
        setBackground(Color.BLACK);
        return this;
	}

}
