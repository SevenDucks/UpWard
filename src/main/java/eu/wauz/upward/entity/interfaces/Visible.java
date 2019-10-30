package eu.wauz.upward.entity.interfaces;

import eu.wauz.upward.game.GameMap;

/**
 * Marks an entity as renderable on the game map.
 * 
 * @author Wauzmons
 */
public interface Visible {
	
	/**
	 * Runs the renderer, to place the entity in the window.
	 * 
	 * @param window The game map, where the entity is located.
	 */
	public void render(GameMap map);

}
