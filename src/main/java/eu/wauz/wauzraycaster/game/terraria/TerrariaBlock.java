package eu.wauz.wauzraycaster.game.terraria;

import eu.wauz.wauzraycaster.textures.GameTexture;

public class TerrariaBlock {
	
	private GameTexture texture;
	
	private int blockSize;
	
	private boolean passable = false;
	
	public TerrariaBlock(GameTexture texture, int blockSize) {
		this.texture = texture;
		this.blockSize = blockSize;
	}
	
	public void render(int[][] pixels, int startX, int startY) {
		for(int pixelX = startX; pixelX < startX + blockSize && pixelX < pixels.length; pixelX++) {
			for(int pixelY = startY; pixelY < startY + blockSize && pixelY < pixels[0].length; pixelY++) {
				if(pixelX < 0 || pixelY < 0) {
					continue;
				}
				pixels[pixelX][pixelY] = texture.getPixels()[0];
			}
		}
	}

	public boolean isPassable() {
		return passable;
	}

	public void setPassable(boolean passable) {
		this.passable = passable;
	}

	public GameTexture getTexture() {
		return texture;
	}

	public void setTexture(GameTexture texture) {
		this.texture = texture;
	}

}
