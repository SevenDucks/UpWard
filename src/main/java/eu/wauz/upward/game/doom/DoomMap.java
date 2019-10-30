package eu.wauz.upward.game.doom;

import java.awt.Color;

import eu.wauz.upward.game.GameMap;
import eu.wauz.upward.game.GameWindow;
import eu.wauz.upward.textures.GameTileset;

/**
 * A map for pseudo 3D doom-like levels.
 * 
 * @author Wauzmons
 */
public class DoomMap extends GameMap {
	
	/**
	 * The renderer, that fills out the pixels of the game window.
	 */
	private DoomRenderer renderer = new DoomRenderer(this);
	
	/**
	 * Creates a new doom map from the given map matrix.
	 * 
	 * @param mapMatrix The game map, values correspond to the textures in the tileset.
	 * @param tileset The tileset to use, for texturing the map.
	 */
	public  DoomMap(int[][] mapMatrix, GameTileset tileset) {
		super(mapMatrix, tileset, true);
	}

	/**
	 * Runs the renderer, to fill out the window.
	 * 
	 * @param window The window, that should be filled with pixels.
	 * 
	 * @see DoomRenderer
	 */
	@Override
	public void render(GameWindow window) {
		renderer.render(window);
	}
	
	/**
	 * @return The color of the level's ceiling.
	 */
	public Color getCeilingColor() {
		return renderer.getCeilingColor();
	}

	/**
	 * @param ceilingColor The new color of the level's ceiling.
	 */
	public void setCeilingColor(Color ceilingColor) {
		renderer.setCeilingColor(ceilingColor);
	}

	/**
	 * @return The color of the level's floor.
	 */
	public Color getFloorColor() {
		return renderer.getFloorColor();
	}

	/**
	 * @param floorColor The new color of the level's floor.
	 */
	public void setFloorColor(Color floorColor) {
		renderer.setFloorColor(floorColor);
	}

}
