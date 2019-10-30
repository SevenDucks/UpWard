package eu.wauz.upward.entity.isaac;

import java.awt.event.KeyEvent;

import eu.wauz.upward.entity.interfaces.Collidable;
import eu.wauz.upward.entity.interfaces.Controller;
import eu.wauz.upward.entity.interfaces.Damageable;
import eu.wauz.upward.game.isaac.IsaacMap;
import eu.wauz.upward.gui.HitpointHearts;
import eu.wauz.upward.textures.GameTexture;
import eu.wauz.upward.util.UpWardOptions;

/**
 * A camera entity, that can move and shoot across an isaac map.
 * 
 * @author Wauzmons
 *
 * @see IsaacEntity
 * @see IsaacMap
 */
public class IsaacCamera extends IsaacEntity implements Controller, Damageable {
	
	/**
	 * If the entity is shooting in this direction.
	 */
	protected boolean shootUp, shootDown, shootLeft, shootRight;
	
	/**
	 * The size of this entity's shot hitbox in blocks.
	 */
	private double shotSize;
	
	/**
	 * The appearance of this entity's shots.
	 */
	private GameTexture shotTexture;

	/**
	 * How much life the entity currently has.
	 */
	private int hitpoints = 3;
	
	/**
	 * How much life the entity can maximally have.
	 */
	private int maxHitpoints = 3;
	
	/**
	 * The amount of ticks, till the next hit is possible.
	 */
	private int hitCooldownTicks = 0;

	/**
	 * The amount of ticks, till the next projectile can be shot.
	 */
	private int nextShotTicks = 0;
	
	/**
	 * Creates a new entity, with given starting position.
	 * 
	 * @param xPos
	 * @param yPos
	 */
	public IsaacCamera(double xPos, double yPos) {
		super(xPos, yPos);
		guiElements.add(new HitpointHearts(this, 6, 6, 4));
		setFaction(IsaacFaction.PLAYER);
		setMovementSpeed(0.1);
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
		if(key.getKeyCode() == UpWardOptions.CONTROLS.getMoveForward()) {
			up = true;
		}
		else if(key.getKeyCode() == UpWardOptions.CONTROLS.getMoveBackward()) {
			down = true;
		}
		else if(key.getKeyCode() == UpWardOptions.CONTROLS.getRotateLeft()) {
			left = true;
		}
		else if(key.getKeyCode() == UpWardOptions.CONTROLS.getRotateRight()) {
			right = true;
		}
		
		
		else if(key.getKeyCode() == UpWardOptions.CONTROLS.getInteractTop()) {
			stopShooting();
			shootUp = true;
		}
		else if(key.getKeyCode() == UpWardOptions.CONTROLS.getInteractBottom()) {
			stopShooting();
			shootDown = true;
		}
		else if(key.getKeyCode() == UpWardOptions.CONTROLS.getInteractLeft()) {
			stopShooting();
			shootLeft = true;
		}
		else if(key.getKeyCode() == UpWardOptions.CONTROLS.getInteractRight()) {
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
		if(key.getKeyCode() == UpWardOptions.CONTROLS.getMoveForward()) {
			up = false;
		}
		else if(key.getKeyCode() == UpWardOptions.CONTROLS.getMoveBackward()) {
			down = false;
		}
		else if(key.getKeyCode() == UpWardOptions.CONTROLS.getRotateLeft()) {
			left = false;
		}
		else if(key.getKeyCode() == UpWardOptions.CONTROLS.getRotateRight()) {
			right = false;
		}
		
		else if(key.getKeyCode() == UpWardOptions.CONTROLS.getInteractTop()) {
			shootUp = false;
		}
		else if(key.getKeyCode() == UpWardOptions.CONTROLS.getInteractBottom()) {
			shootDown = false;
		}
		else if(key.getKeyCode() == UpWardOptions.CONTROLS.getInteractLeft()) {
			shootLeft = false;
		}
		else if(key.getKeyCode() == UpWardOptions.CONTROLS.getInteractRight()) {
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
		
		if(nextShotTicks == 0) {
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
		
		if(hitCooldownTicks > 0) {
			hitCooldownTicks--;
		}
		if(nextShotTicks > 0) {
			nextShotTicks--;
		}
	}
	
	/**
	 * Lets the entity shoot a projectile in the given direction.
	 * 
	 * @param direction The direction, where every increase is a 90 degree rotation.
	 */
	public void shoot(int direction) {
		IsaacProjectile projectile = new IsaacProjectile(this, shotSize, direction, IsaacFaction.PLAYER_PROJECTILE);
		projectile.setTexture(shotTexture);
		nextShotTicks = 25;
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

	/**
	 * @return How much life the entity currently has.
	 */
	@Override
	public int getHitpoints() {
		return hitpoints;
	}

	/**
	 * @param hitpoints How much life the entity has now.
	 */
	@Override
	public void setHitpoints(int hitpoints) {
		this.hitpoints = hitpoints;
	}

	/**
	 * @return How much life the entity can maximally have.
	 */
	@Override
	public int getMaximumHitpoints() {
		return maxHitpoints;
	}

	/**
	 * @param maxHitpoints How much life the entity can maximally have now.
	 */
	@Override
	public void setMaxHitpoints(int maxHitpoints) {
		this.maxHitpoints = maxHitpoints;
	}

	/**
	 * Called if the entity runs out of hitpoints.
	 * 
	 * @see Damageable#changeHitpoints(int)
	 */
	@Override
	public void die() {
		UpWardOptions.WINDOWS.getMainWindow().removeEntity(this);
	}
	
	/**
	 * @return The default amount of hit cooldown ticks that will be set.
	 */
	@Override
	public int getInvincibilityTicks() {
		return 30;
	}

	/**
	 * @return The amount of ticks, till the next hit is possible.
	 * 
	 * @see Damageable#changeHitpoints(int) Automatically set on hitpoint change.
	 */
	@Override
	public int getHitCooldownTicks() {
		return hitCooldownTicks;
	}

	/**
	 * @param hitCooldownTicks The new amount of ticks, till the next hit is possible.
	 * 
	 * @see Damageable#changeHitpoints(int) Automatically set on hitpoint change.
	 */
	@Override
	public void setHitCooldownTicks(int hitCooldownTicks) {
		this.hitCooldownTicks = hitCooldownTicks;
	}

	/**
	 * Called when the entity collides with another.
	 * 
	 * @param entity The other entity.
	 */
	@Override
	public void collide(Collidable entity) {
		if(entity != null && entity.getFaction() == IsaacFaction.ENEMY) {
			changeHitpoints(-1);
		}
	}
	
}
