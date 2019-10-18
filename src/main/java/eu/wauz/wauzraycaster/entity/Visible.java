package eu.wauz.wauzraycaster.entity;

import eu.wauz.wauzraycaster.game.GameMap;

/**
 * Marks an entity as renderable on the game map.
 * 
 * @author Wauzmons
 */
public interface Visible {
	
	public void render(GameMap map);

}
