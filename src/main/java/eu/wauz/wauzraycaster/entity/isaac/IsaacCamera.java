package eu.wauz.wauzraycaster.entity.isaac;

import java.awt.event.KeyEvent;

import eu.wauz.wauzraycaster.entity.Controller;
import eu.wauz.wauzraycaster.game.isaac.IsaacMap;
import eu.wauz.wauzraycaster.textures.GameTexture;
import eu.wauz.wauzraycaster.util.WrayOptions;

/**
 * A camera entity, that can move and shoot across an isaac map.
 * 
 * @author Wauzmons
 *
 * @see IsaacEntity
 * @see IsaacMap
 */
public class IsaacCamera extends IsaacEntity implements Controller {
	
	/**
	 * The unix timestamp, of the last projectile, that was shot.
	 */
	private long lastShotTimestamp = 0;
	
	/**
	 * The size of this entity's shot hitbox in blocks.
	 */
	private double shotSize;
	
	/**
	 * The appearance of this entity's shots.
	 */
	private GameTexture shotTexture;

	
	/**
	 * Creates a new entity, with given starting position.
	 * 
	 * @param xPos
	 * @param yPos
	 */
	public IsaacCamera(double xPos, double yPos) {
		super(xPos, yPos);
		MOVEMENT_SPEED = 0.0625;
	}

	/**
	 * Determine what to do if a key is typed.
	 * Initializes shots.
	 */
	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	/**
	 * Determine what to do if a key is pressed.
	 * Starts directional movement.
	 */
	@Override
	public void keyPressed(KeyEvent key) {
		if(key.getKeyCode() == WrayOptions.CONTROLS.getMoveForward()) {
			up = true;
		}
		else if(key.getKeyCode() == WrayOptions.CONTROLS.getMoveBackward()) {
			down = true;
		}
		else if(key.getKeyCode() == WrayOptions.CONTROLS.getRotateLeft()) {
			left = true;
		}
		else if(key.getKeyCode() == WrayOptions.CONTROLS.getRotateRight()) {
			right = true;
		}
		
		if(lastShotTimestamp + 1000 < System.currentTimeMillis()) {
			if(key.getKeyCode() == WrayOptions.CONTROLS.getInteractTop()) {
				shoot(0);
			}
			else if(key.getKeyCode() == WrayOptions.CONTROLS.getInteractRight()) {
				shoot(1);
			}
			else if(key.getKeyCode() == WrayOptions.CONTROLS.getInteractBottom()) {
				shoot(2);
			}
			else if(key.getKeyCode() == WrayOptions.CONTROLS.getInteractLeft()) {
				shoot(3);
			}
		}
	}

	/**
	 * Determine what to do if a key is released.
	 * Stops directional movement.
	 */
	@Override
	public void keyReleased(KeyEvent key) {
		if(key.getKeyCode() == WrayOptions.CONTROLS.getMoveForward()) {
			up = false;
		}
		else if(key.getKeyCode() == WrayOptions.CONTROLS.getMoveBackward()) {
			down = false;
		}
		else if(key.getKeyCode() == WrayOptions.CONTROLS.getRotateLeft()) {
			left = false;
		}
		else if(key.getKeyCode() == WrayOptions.CONTROLS.getRotateRight()) {
			right = false;
		}
	}

	/**
	 * Moves into the active directions, if possible.
	 */
	@Override
	public void updatePosition(int[][] map) {
		if(up) {
			moveUp(map);
		}
		if(down) {
			moveDown(map);
		}
		if(left) {
			moveLeft(map);
		}
		if(right) {
			moveRight(map);
		}
	}
	
	/**
	 * Lets the entity shoot a projectile in the given direction.
	 * 
	 * @param direction The direction, where every increase is a 90 degree rotation.
	 */
	public void shoot(int direction) {
		IsaacProjectile projectile = new IsaacProjectile(this, direction);
		projectile.setTexture(shotTexture);
		projectile.setSize(shotSize);
		lastShotTimestamp = System.currentTimeMillis();
	}

	/**
	 * @return The size of this entity's shot hitbox in blocks.
	 */
	public double getShotSize() {
		return shotSize;
	}

	/**
	 * @param shotSize The new size of this entity's shot hitbox in blocks.
	 */
	public void setShotSize(double shotSize) {
		this.shotSize = shotSize;
	}

	/**
	 * @return The appearance of this entity's shots.
	 */
	public GameTexture getShotTexture() {
		return shotTexture;
	}

	/**
	 * @param shotTexture The new appearance of this entity's shots.
	 */
	public void setShotTexture(GameTexture shotTexture) {
		this.shotTexture = shotTexture;
	}

}
