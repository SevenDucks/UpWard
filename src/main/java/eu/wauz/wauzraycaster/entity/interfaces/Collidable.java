package eu.wauz.wauzraycaster.entity.interfaces;

import eu.wauz.wauzraycaster.entity.Hitbox;

/**
 * Allows an entity to collide with others.
 * 
 * @author Wauzmons
 */
public interface Collidable {
	
	/**
	 * Called when the entity collides with another.
	 * 
	 * @param entity The other entity.
	 */
	public void collide(Collidable entity);
	
	/**
	 * @return The entity's current hitbox.
	 */
	public Hitbox getHitbox();

}
