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
	 * If the entity is shooting in this direction.
	 */
	protected boolean shootUp, shootDown, shootLeft, shootRight;
	
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
	 * Starts directional movement or shooting.
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
		
		
		else if(key.getKeyCode() == WrayOptions.CONTROLS.getInteractTop()) {
			stopShooting();
			shootUp = true;
		}
		else if(key.getKeyCode() == WrayOptions.CONTROLS.getInteractBottom()) {
			stopShooting();
			shootDown = true;
		}
		else if(key.getKeyCode() == WrayOptions.CONTROLS.getInteractLeft()) {
			stopShooting();
			shootLeft = true;
		}
		else if(key.getKeyCode() == WrayOptions.CONTROLS.getInteractRight()) {
			stopShooting();
			shootRight = true;
		}
	}

	/**
	 * Determine what to do if a key is released.
	 * Stops directional movement or shooting.
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
		
		else if(key.getKeyCode() == WrayOptions.CONTROLS.getInteractTop()) {
			shootUp = false;
		}
		else if(key.getKeyCode() == WrayOptions.CONTROLS.getInteractBottom()) {
			shootDown = false;
		}
		else if(key.getKeyCode() == WrayOptions.CONTROLS.getInteractLeft()) {
			shootLeft = false;
		}
		else if(key.getKeyCode() == WrayOptions.CONTROLS.getInteractRight()) {
			shootRight = false;
		}
	}

	/**
	 * Moves into the active directions, if possible.
	 * Spawns new projectiles if currently shooting.
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
		
		if(lastShotTimestamp + 800 < System.currentTimeMillis()) {
			if(shootUp) {
				shoot(0);
			}
			if(shootRight) {
				shoot(1);
			}
			if(shootDown) {
				shoot(2);
			}
			if(shootLeft) {
				shoot(3);
			}
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
	 * Lets the entity stop shooting projectiles.
	 */
	private void stopShooting() {
		shootUp = false;
		shootDown = false;
		shootLeft = false;
		shootRight = false;
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
