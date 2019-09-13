package eu.wauz.wauzraycaster.textures;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import eu.wauz.wauzraycaster.WrayUtils;

public class GameTexture {
	
	private String path;
	
	private final int size;
	
	private final int[] pixels;
	
	public GameTexture(String path, int size) {
		this.path = path;
		this.size = size;
		this.pixels = new int[size * size];
		load();
	}
	
	private void load() {
		try {
			BufferedImage image = ImageIO.read(WrayUtils.getResource(path));
			int x = image.getWidth();
			int y = image.getHeight();
			image.getRGB(0, 0, x, y, pixels, 0, x);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int getSize() {
		return size;
	}

	public int[] getPixels() {
		return pixels;
	}
	
}
