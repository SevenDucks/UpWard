package eu.wauz.upward.game;

import eu.wauz.upward.textures.GameTexture;
import eu.wauz.upwardutils.UpWardUtils;

/**
 * A block with a square shaped texture for creating 2D worlds.
 * 
 * @author Wauzmons
 */
public class GameBlock {
	
	/**
	 * The texture of the block.
	 */
	private GameTexture texture;
	
	/**
	 * The length of the block in pixels.
	 */
	private int blockSize;
	
	/**
	 * If entities can walk through this block.
	 */
	private boolean passable = false;
	
	/**
	 * Creates a block with given texture and size.
	 * 
	 * @param texture The texture of the block.
	 * @param blockSize The length of the block in pixels.
	 */
	public GameBlock(GameTexture texture, int blockSize) {
		this.texture = texture;
		this.blockSize = blockSize;
	}
	
	/**
	 * Renders the block at the given position.
	 * 
	 * @param pixels The array, that the block will be rendered into.
	 * @param startX The left pixel to start.
	 * @param startY The top pixel to start.
	 * @param rotation How much the texture should be rotated. 1 equals 90 degrees.
	 */
	public void render(int[][] pixels, int startX, int startY, int rotation) {
		int[] texturePixels = UpWardUtils.rotateClockwise(texture.getPixels(), blockSize, rotation);
			
		int pixel = -1;
		for(int pixelY = startY; pixelY < startY + blockSize && pixelY < pixels[0].length; pixelY++) {
			for(int pixelX = startX; pixelX < startX + blockSize && pixelX < pixels.length; pixelX++) {
				pixel++;
				if(pixelX < 0 || pixelY < 0) {
					continue;
				}
				pixels[pixelX][pixelY] = texturePixels[pixel];
			}
		}
	}

	/**
	 * @return If entities can walk through this block.
	 */
	public boolean isPassable() {
		return passable;
	}

	/**
	 * @param passable If entities can walk through this block now.
	 */
	public void setPassable(boolean passable) {
		this.passable = passable;
	}

	/**
	 * @return The texture of the block.
	 */
	public GameTexture getTexture() {
		return texture;
	}

	/**
	 * @param texture The new texture of the block.
	 */
	public void setTexture(GameTexture texture) {
		this.texture = texture;
	}

}
