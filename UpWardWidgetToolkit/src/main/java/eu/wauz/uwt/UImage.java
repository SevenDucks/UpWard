package eu.wauz.uwt;

import java.awt.Color;
import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * A basic image container.
 * 
 * @author Wauzmons
 */
public class UImage extends JPanel {

	/**
	 * This element's UUID.
	 */
	private static final long serialVersionUID = 7460103099435328795L;
	
	/**
	 * Initializes a new image container for the given file.
	 * 
	 * @param file The image file.
	 * @param width The width of the image.
	 * @param height The height of the image.
	 */
	public UImage(File file, int width, int height) {
		try {
			Image image = ImageIO.read(file).getScaledInstance(width, height, Image.SCALE_DEFAULT);
			JLabel label = new JLabel(new ImageIcon(image));
	        setBackground(Color.BLACK);
			add(label);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
