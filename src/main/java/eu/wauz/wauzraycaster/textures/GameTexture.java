package eu.wauz.wauzraycaster.textures;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import eu.wauz.wauzraycaster.util.WrayUtils;

/**
 * A square shaped texture, which is rendered into the game.
 * 
 * @author Wauzmons
 */
public class GameTexture {
	
	/**
	 * The path to the texture in the resources folder.
	 */
	private String path;

	/**
	 * The texture as buffered image.
	 */
	private BufferedImage image;
	
	/**
	 * The width / height of the texture in pixels.
	 */
	private final int size;
	
	/**
	 * The rgb pixels of the texture.
	 */
	private final int[] pixels;
	
	/**
	 * Creates a texture from a file in the resources folder.
	 * 
	 * @param path The path to the texture in the resources folder.
	 * @param size The width / height of the texture in pixels.
	 */
	public GameTexture(String path, int size) {
		this.path = path;
		this.size = size;
		this.pixels = new int[size * size];
		loadImage();
	}
	
	/**
	 * Creates a texture from a single color.
	 * 
	 * @param color The plain color of the texture.
	 * @param size The width / height of the texture in pixels.
	 */
	public GameTexture(Color color, int size) {
		this.size = size;
		this.pixels = new int[size * size];
		for(int i = 0; i < pixels.length; i++) {
			pixels[i] = color.getRGB();
		}
	}
	
	/**
	 * Loads the pixels from a file in the resources folder.
	 * 
	 * @see GameTexture#GameTexture(String, int)
	 */
	private void loadImage() {
		try {
			image = ImageIO.read(WrayUtils.getResource(path));
			int x = image.getWidth();
			int y = image.getHeight();
			image.getRGB(0, 0, x, y, pixels, 0, x);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @return The texture as buffered image.
	 */
	public BufferedImage getImage() {
		return image;
	}

	/**
	 * @param image The texture as new buffered image.
	 */
	public void setImage(BufferedImage image) {
		this.image = image;
	}

	/**
	 * @return The width / height of the texture in pixels.
	 */
	public int getSize() {
		return size;
	}

	/**
	 * @return The rgb pixels of the texture.
	 */
	public int[] getPixels() {
		return pixels;
	}
	
}
