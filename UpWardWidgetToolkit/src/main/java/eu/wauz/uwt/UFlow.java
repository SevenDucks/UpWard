package eu.wauz.uwt;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.LayoutManager;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/**
 * A basic container with flow or box layout.
 * 
 * @author Wauzmons
 */
public class UFlow extends JPanel {
	
	/**
	 * This element's UUID.
	 */
	private static final long serialVersionUID = 1580312346517621084L;
	
	/**
	 * Initializes an empty flow panel.
	 * 
	 * @param vertical If the flow should be vertical.
	 */
	public UFlow(boolean vertical) {
		LayoutManager layout;
		if(vertical) {
			layout = new BoxLayout(this, vertical ? BoxLayout.Y_AXIS : BoxLayout.X_AXIS);
		}
		else {
			layout = new FlowLayout();
		}
		setLayout(layout);
		setBackground(Color.BLACK);
	}
	
	/**
	 * Adds a title border around the flow panel.
	 * 
	 * @param text The title of the panel.
	 */
	public void addTitle(String text) {
		TitledBorder border = BorderFactory.createTitledBorder(text);
		border.setTitleColor(Color.WHITE);
		setBorder(border);
	}
	
}
