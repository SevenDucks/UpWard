package eu.wauz.wauzraycaster.game.terraria;

import eu.wauz.wauzraycaster.entity.MovingEntity;
import eu.wauz.wauzraycaster.game.GameMap;
import eu.wauz.wauzraycaster.game.GameWindow;
import eu.wauz.wauzraycaster.generation.CellularAutomaton;
import eu.wauz.wauzraycaster.textures.GameTileset;

public class TerrariaMap extends GameMap {
	
	private TerrariaBlock[][] blocks;
	
	private int[][] pixels;
	
	private static final int BLOCK_SIZE = 16;
	
	public TerrariaMap(int[][] mapMatrix, GameTileset tileset) {
		super(mapMatrix, tileset, false);
		generate();
	}
	
	public void generate() {
		CellularAutomaton automaton = new CellularAutomaton(mapWidth, mapHeight).withTerrariaPreset();
		int[][] cellMatrix = automaton.getCellMatrix();
		blocks = new TerrariaBlock[mapWidth][mapHeight];
		pixels = new int[mapWidth][mapHeight];
		
		for(int x = 0; x < mapWidth; x++) {
			for(int y = 0; y < mapHeight; y++) {
				int blockId = cellMatrix[x][y];
				getMapMatrix()[x][y] = blockId;
				blocks[x][y] = new TerrariaBlock(getTileset().get(blockId + 1), BLOCK_SIZE);
			}
		}
	}

	@Override
	public void render(GameWindow window) {
//		long millis = System.currentTimeMillis();
		
		int windowWidth = window.getWidth();
		
		if(!(window.getCurrentCamera() instanceof MovingEntity)) {
			return;
		}
		
		MovingEntity camera = (MovingEntity) window.getCurrentCamera();
		StartingBlock startingBlock = new StartingBlock(camera.getxPos(), camera.getyPos(), window.getWidth(), window.getHeight());
		int startPixelX = startingBlock.getStartPixelX();
		int startPixelY = startingBlock.getStartPixelY();
		
		int blockX = startingBlock.getStartBlockX();
		for(int pixelX = startPixelX; pixelX < window.getWidth() + BLOCK_SIZE; pixelX += BLOCK_SIZE) {// TODO Limit Checks
			int blockY = startingBlock.getStartBlockY();
			for(int pixelY = startPixelY; pixelY < window.getHeight() + BLOCK_SIZE; pixelY += BLOCK_SIZE) {
				TerrariaBlock block = blocks[blockX][blockY];
				block.render(pixels, pixelX, pixelY);
				blockY++;
			}
			blockX++;
		}
		
		int[] pixels = new int[window.getWidth() * window.getHeight()];
		for(int i = 0; i < pixels.length; i++) {
			int x = i % window.getWidth();
			int y = (int) Math.ceil(i / windowWidth);
			window.getPixels()[i] = this.pixels[x][y];
		}
		
//		System.out.println("Render-Time: " + (System.currentTimeMillis() - millis) + "   " + (1000 / window.getFps()));
	}
	
	private class StartingBlock {
		
		private int startPixelX;
		
		private int startPixelY;
		
		private int startBlockX;
		
		private int startBlockY;
		
		public StartingBlock(double centerX, double centerY, int pixelsX, int pixelsY) {// TODO Exact Position
			startBlockX = (int) Math.floor(centerX);
			startBlockY = (int) Math.floor(centerY);
			int offsetX = getOffsetPixels(centerX);
			int offsetY = getOffsetPixels(centerY);
			
			for(int currentX = pixelsX / 2; currentX > - BLOCK_SIZE; currentX -= BLOCK_SIZE) {
				startPixelX = currentX;
				startBlockX -= 1;
			}
			
			for(int currentY = pixelsY / 2; currentY > - BLOCK_SIZE; currentY -= BLOCK_SIZE) {
				startPixelY = currentY;
				startBlockY -= 1;
			}
		}
		
		public int getOffsetPixels(double exactCoordinate) {
			double remains = 2.125 % 1;
			double pixelSize = 1.0 / BLOCK_SIZE;
			return (int) (remains / pixelSize);
		}

		public int getStartPixelX() {
			return startPixelX;
		}

		public int getStartPixelY() {
			return startPixelY;
		}

		public int getStartBlockX() {
			return startBlockX;
		}

		public int getStartBlockY() {
			return startBlockY;
		}
		
	}

}
