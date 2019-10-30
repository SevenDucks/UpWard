package eu.wauz.wauzraycaster.entity.isaac;

import java.awt.Color;

import eu.wauz.wauzraycaster.entity.interfaces.Collidable;
import eu.wauz.wauzraycaster.entity.interfaces.Damageable;
import eu.wauz.wauzraycaster.game.isaac.IsaacMap;
import eu.wauz.wauzraycaster.textures.GameTexture;
import eu.wauz.wauzraycaster.util.WrayOptions;
import eu.wauz.wauzraycaster.util.WrayUtils;

/**
 * A test entity, that can move and shoot across an isaac map.
 * 
 * @author Wauzmons
 *
 * @see IsaacEntity
 * @see IsaacMap
 */
public class IsaacTestEntity extends IsaacEntity implements Damageable {
	
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
	 * The amount of ticks, till the entity will change its movements.
	 */
	private int behaviourChangeTicks = 0;

	/**
	 * Creates a new entity, with given starting position.
	 * 
	 * @param xPos
	 * @param yPos
	 */
	public IsaacTestEntity(double xPos, double yPos) {
		super(xPos, yPos);
		setFaction(IsaacFaction.ENEMY);
		setMovementSpeed(0.08);
	}

	/**
	 * Lets the entity randomly stroll around.
	 * Changes direction every 20 ticks.
	 */
	@Override
	public void updatePosition(int[][] map) {
		if(behaviourChangeTicks == 0) {
			up = WrayUtils.randomBoolean();
			down = WrayUtils.randomBoolean();
			left = WrayUtils.randomBoolean();
			right = WrayUtils.randomBoolean();
			behaviourChangeTicks = 20;
		}
		else {
			behaviourChangeTicks--;
		}
		
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
		
		if(hitCooldownTicks > 0) {
			hitCooldownTicks--;
		}
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
		WrayOptions.WINDOWS.getMainWindow().removeEntity(this);
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
		if(entity != null && entity.getFaction() == IsaacFaction.PLAYER_PROJECTILE) {
			changeHitpoints(-1);
			
			GameTexture bloodTexture = new GameTexture(Color.RED, 8);
			for(int direction = 0; direction < 4; direction++) {
				IsaacProjectile projectile = new IsaacProjectile(this, 0.25, direction, IsaacFaction.ENEMY);
				projectile.setTexture(bloodTexture);
				projectile.setMovementSpeed(0.1);
				projectile.setTicksTillDeath(60);
			}
		}
	}

}
