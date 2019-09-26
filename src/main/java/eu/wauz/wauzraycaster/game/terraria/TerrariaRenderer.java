package eu.wauz.wauzraycaster.game.terraria;

import eu.wauz.wauzraycaster.entity.MovingEntity;
import eu.wauz.wauzraycaster.game.GameWindow;

public class TerrariaRenderer {
	
	private int[][] pixels;
	
	private TerrariaMap map;
	
	private int startPixelX;
	
	private int startPixelY;
	
	private int startBlockX;
	
	private int startBlockY;
	
	private int blockSize = 16;
	
	public TerrariaRenderer(TerrariaMap map) {
		this.map = map;
	}
	
	public void render(GameWindow window) {// TODO doing this every frame will totally kill the performance
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
				TerrariaBlock block = map.getBlocks()[blockX][blockY];
				block.render(pixels, pixelX, pixelY);
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
	
	public int getOffsetPixels(double exactCoordinate) {
		double remains = exactCoordinate % 1;
		double pixelSize = 1.0 / blockSize;
		return (int) (remains / pixelSize);
	}

	public int getBlockSize() {
		return blockSize;
	}

	public void setBlockSize(int blockSize) {
		this.blockSize = blockSize;
	}

}
