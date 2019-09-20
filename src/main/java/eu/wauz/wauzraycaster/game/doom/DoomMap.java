package eu.wauz.wauzraycaster.game.doom;

import java.awt.Color;

import eu.wauz.wauzraycaster.game.GameMap;
import eu.wauz.wauzraycaster.game.GameWindow;
import eu.wauz.wauzraycaster.textures.GameTileset;

public class DoomMap extends GameMap {
	
	private DoomRenderer renderer = new DoomRenderer(this);
	
	public  DoomMap(int[][] mapMatrix, GameTileset tileset) {
		super(mapMatrix, tileset, true);
	}

	@Override
	public void render(GameWindow window) {
		renderer.render(window);
	}
	
	public Color getCeilingColor() {
		return renderer.getCeilingColor();
	}

	public void setCeilingColor(Color ceilingColor) {
		renderer.setCeilingColor(ceilingColor);
	}

	public Color getFloorColor() {
		return renderer.getFloorColor();
	}

	public void setFloorColor(Color floorColor) {
		renderer.setFloorColor(floorColor);
	}

}
