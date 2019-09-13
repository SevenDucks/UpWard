package eu.wauz.wauzraycaster.textures;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

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
			BufferedImage image = ImageIO.read(getFile());
			int x = image.getWidth();
			int y = image.getHeight();
			image.getRGB(0, 0, x, y, pixels, 0, x);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private File getFile() {
		ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(path);
        return new File(resource.getFile());
	}

	public int getSize() {
		return size;
	}

	public int[] getPixels() {
		return pixels;
	}
	
}
