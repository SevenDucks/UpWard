package eu.wauz.upward.textures;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;

import eu.wauz.upward.util.UpWardUtils;

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
	 * The chance that an alternative variant of this texture is chosen.
	 */
	private double alternativeChance = 0.2;
	
	/**
	 * Alternative variants of this texture.
	 */
	private final List<GameTexture> alternativeTextures = new ArrayList<>();
	
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
			image = ImageIO.read(UpWardUtils.getResource(path));
			int x = image.getWidth();
			int y = image.getHeight();
			image.getRGB(0, 0, x, y, pixels, 0, x);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Runs the renderer, to place the texture in the window.
	 * 
	 * @param pixels The pixels to put the texture in.
	 * @param startX The x coordinate that should be started on.
	 * @param startY The y coordinate that should be started on.
	 */
	public void render(int[][] pixels, int startX, int startY) {
		int[] texturePixels = getPixels();
		int pixel = -1;
		for(int pixelY = startY; pixelY < startY + size && pixelY < pixels[0].length; pixelY++) {
			for(int pixelX = startX; pixelX < startX + size && pixelX < pixels.length; pixelX++) {
				pixel++;
				if(pixelX < 0 || pixelY < 0 || (texturePixels[pixel] >> 24) == 0x00) {
					continue;
				}
				pixels[pixelX][pixelY] = texturePixels[pixel];
			}
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
	 * @return The rgb pixels of the texture or an alternative version.
	 */
	public int[] getPixels() {
		if(!alternativeTextures.isEmpty() && UpWardUtils.randomBoolean(alternativeChance)) {
			int randomIndex = UpWardUtils.randomInt(alternativeTextures.size());
			return alternativeTextures.get(randomIndex).getPixels();
		}
		else {
			return pixels;
		}
	}
	
	/**
	 * @return The chance that an alternative variant of this texture is chosen.
	 */
	public double getAlternativeChance() {
		return alternativeChance;
	}
	
	/**
	 * @param alternativeChance The new chance that an alternative variant of this texture is chosen.
	 */
	public void setAlternativeChance(double alternativeChance) {
		this.alternativeChance = alternativeChance;
	}
	
	/**
	 * @param alternativeTextures Alternative variants of this texture.
	 */
	public void addAlternatives(GameTexture... alternativeTextures) {
		this.alternativeTextures.addAll(Arrays.asList(alternativeTextures));
	}
	
}
