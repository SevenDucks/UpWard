package eu.wauz.uwt;

import java.awt.Color;
import java.awt.Toolkit;

import javax.swing.JFrame;

import eu.wauz.upwardutils.UpWardUtils;

/**
 * The base of a top-level window with a title and a border.
 * 
 * @author Wauzmons
 */
public class UFrame extends JFrame {

	/**
	 * This element's UUID.
	 */
	private static final long serialVersionUID = -413955229682582547L;
	
	/**
	 * Initializes an empty frame.
	 */
	public UFrame() {
		String iconPath = UpWardUtils.getResource(getClass(), "images/icon.png").getAbsolutePath();
		setIconImage(Toolkit.getDefaultToolkit().getImage(iconPath));
		
		setTitle("UpWard");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBackground(Color.BLACK);
	}
	
	/**
	 * Centers the window and sets its visibility to true.
	 */
	public void open() {
		setLocationRelativeTo(null);
		setVisible(true);
	}

}
