package eu.wauz.upward.entity;

import java.awt.Rectangle;
import java.util.ArrayList;

import eu.wauz.upward.UpWardOptions;
import eu.wauz.upward.entity.interfaces.Collidable;

/**
 * A hitbox for collision detection of entities.
 * 
 * @author Wauzmons
 */
public class Hitbox {
	
	/**
	 * The rectangle, that represents the hitbox size and position.
	 */
	Rectangle rectangle;
	
	/**
	 * Creates a new hitbox with given size and position.
	 * 
	 * @param xPos
	 * @param yPos
	 * @param width
	 * @param height
	 */
	public Hitbox(double xPos, double yPos, double width, double height) {
		resize(xPos, yPos, width, height);
	}
	
	/**
	 * Sets the hitbox size and position.
	 * 
	 * @param xPos
	 * @param yPos
	 * @param width
	 * @param height
	 */
	public void resize(double xPos, double yPos, double width, double height) {
		rectangle = new Rectangle((int) (xPos * 10000), (int) (yPos * 10000), (int) (width * 10000), (int) (height * 10000));
	}
	
	/**
	 * Lets intersecting entities collide.
	 * 
	 * @param entity The entity that owns this hitbox.
	 */
	public void checkForCollisions(Collidable entity) {
		for(MovingEntity otherEntity : new ArrayList<>(UpWardOptions.WINDOWS.getMainWindow().getEntities())) {
			if(!(otherEntity instanceof Collidable)) {
				continue;
			}
			Collidable collidable = ((Collidable) otherEntity);
			if(!collidable.equals(entity) && collidable.getHitbox().intersects(this)) {
				entity.collide(collidable);
				collidable.collide(entity);
			}
		}
	}
	
	/**
	 * @param hitbox Another hitbox.
	 * 
	 * @return If the hitboxes intersect.
	 */
	public boolean intersects(Hitbox hitbox) {
		return rectangle.intersects(hitbox.getRectangle());
	}
	
	/**
	 * @return The rectangle, that represents the hitbox size and position.
	 */
	public Rectangle getRectangle() {
		return rectangle;
	}

}
