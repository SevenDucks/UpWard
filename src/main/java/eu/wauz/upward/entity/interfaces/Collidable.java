package eu.wauz.upward.entity.interfaces;

import eu.wauz.upward.entity.Hitbox;

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
	
	/**
	 * @return The faction (player, enemy) id that this entity belongs to.
	 */
	public int getFaction();

}
