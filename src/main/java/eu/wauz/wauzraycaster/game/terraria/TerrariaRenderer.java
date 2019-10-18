package eu.wauz.wauzraycaster.game.terraria;

import eu.wauz.wauzraycaster.entity.MovingEntity;
import eu.wauz.wauzraycaster.game.GameWindow;
import eu.wauz.wauzraycaster.game.GameBlock;

/**
 * A renderer for random terraria-like world generation.
 * 
 * @author Wauzmons
 */
public class TerrariaRenderer {
	
	/**
	 * The pixels for the game window.
	 */
	private int[][] pixels;
	
	/**
	 * The map, this renderer is working with.
	 */
	private TerrariaMap map;
	
	/**
	 * The horizontal pixel, where the rendering should start.
	 */
	private int startPixelX;
	
	/**
	 * The vertical pixel, where the rendering should start.
	 */
	private int startPixelY;
	
	/**
	 * The horizontal block, where the rendering should start.
	 */
	private int startBlockX;
	
	/**
	 * The vertical block, where the rendering should start.
	 */
	private int startBlockY;
	
	/**
	 * The length of a block in pixels.
	 */
	private int blockSize = 16;
	
	/**
	 * Creates a renderer, that fills out the pixels of the game window.
	 * 
	 * @param map The map, this renderer is working with.
	 */
	public TerrariaRenderer(TerrariaMap map) {
		this.map = map;
	}
	
	/**
	 * Runs the renderer, to fill out the window.
	 * TODO: Doing this every frame will totally kill the performance.
	 * 
	 * @param window The window, that should be filled with pixels.
	 */
	public void render(GameWindow window) {
		long millis = System.currentTimeMillis();
		
		int windowWidth = window.getWidth();
		int windowHeight = window.getHeight();
		pixels = new int[windowWidth][windowHeight];
		
		if(!(window.getCurrentCamera() instanceof MovingEntity)) {
			return;
		}
		
		MovingEntity camera = (MovingEntity) window.getCurrentCamera();
		determineStartingBlock(camera.getxPos(), camera.getyPos(), windowWidth, windowHeight);
		
		int blockX = startBlockX;
		for(int pixelX = startPixelX; pixelX < windowWidth + blockSize; pixelX += blockSize) {
			int blockY = startBlockY;
			for(int pixelY = startPixelY; pixelY < windowHeight + blockSize; pixelY += blockSize) {
				if(blockX < 0 || blockY < 0) {
					continue;
				}
				if(blockX >= map.getBlocks().length || blockY >= map.getBlocks()[0].length) {
					continue;
				}
				GameBlock block = map.getBlocks()[blockX][blockY];
				block.render(pixels, pixelX, pixelY, 0);
				blockY++;
			}
			blockX++;
		}
		
		int[] pixels = new int[windowWidth * windowHeight];
		for(int i = 0; i < pixels.length; i++) {
			int x = i % windowWidth;
			int y = (int) Math.ceil(i / windowWidth);
			window.getPixels()[i] = this.pixels[x][y];
		}
		
		System.out.println("Render-Time: " + (System.currentTimeMillis() - millis) + "\t\t" + (1000 / window.getFps()));
	}
		
	/**
	 * Determines the points, where rendering should start.
	 * Used to assure, that only visible blocks are calculated.
	 * 
	 * @param centerX The x postion of the camera.
	 * @param centerY The y postion of the camera.
	 * @param pixelsX The total visible pixels on the x axis.
	 * @param pixelsY The total visible pixels on the y axis.
	 */
	public void determineStartingBlock(double centerX, double centerY, int pixelsX, int pixelsY) {
		startBlockX = (int) Math.floor(centerX);
		startBlockY = (int) Math.floor(centerY);
		int offsetX = getOffsetPixels(centerX);
		int offsetY = getOffsetPixels(centerY);
		
		for(int currentX = pixelsX / 2 - offsetX; currentX > - blockSize; currentX -= blockSize) {
			startPixelX = currentX;
			startBlockX -= 1;
		}
		
		for(int currentY = pixelsY / 2 - offsetY; currentY > - blockSize; currentY -= blockSize) {
			startPixelY = currentY;
			startBlockY -= 1;
		}
	}
	
	/**
	 * How many pixels the coordinate is offset from the full (.0) block position.
	 * Used for calculating screen locations of blocks, that are only partially visible.
	 * 
	 * @param exactCoordinate The decimal coordinate of the camera.
	 * @return How many pixels it is from the .0 block position.
	 */
	public int getOffsetPixels(double exactCoordinate) {
		double remains = exactCoordinate % 1;
		double pixelSize = 1.0 / blockSize;
		return (int) (remains / pixelSize);
	}

	/**
	 * @return The length of a block in pixels.
	 */
	public int getBlockSize() {
		return blockSize;
	}

	/**
	 * @param blockSize The new length of a block in pixels.
	 */
	public void setBlockSize(int blockSize) {
		this.blockSize = blockSize;
	}

}
