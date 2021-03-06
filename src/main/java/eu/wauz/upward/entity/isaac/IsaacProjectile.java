package eu.wauz.upward.entity.isaac;

import eu.wauz.upward.UpWardOptions;
import eu.wauz.upward.entity.interfaces.Collidable;
import eu.wauz.upward.game.isaac.IsaacMap;

/**
 * A projectile, that can damage entities on an isaac map.
 * 
 * @author Wauzmons
 *
 * @see IsaacEntity
 * @see IsaacMap
 */
public class IsaacProjectile extends IsaacEntity {
	
	/**
	 * The entity who shot this projectile.
	 */
	private IsaacEntity shooter;
	
	/**
	 * In how many ticks, the entity will die.
	 */
	private int ticksTillDeath = 30;
	
	/**
	 * Creates a new projectile, with given starting position.
	 * 
	 * @param shooter The entity who shot this projectile.
	 * @param The size of this projectile's hitbox in blocks.
	 * @param direction The direction, where every increase is a 90 degree rotation.
	 * @param faction The faction (player, enemy) id that this entity belongs to.
	 */
	public IsaacProjectile(IsaacEntity shooter, double size, int direction, int faction) {
		super(
				shooter.getxPos() + (shooter.getSize() - size) / 2 - shooter.getOffsetLeft(),
				shooter.getyPos() + (shooter.getSize() - size) / 2 - shooter.getOffsetTop());
		this.shooter = shooter;
		setSize(size);
		setFaction(faction);
		setMovementSpeed(0.14);
		
		switch (direction) {
		case 0:
			up = true;
			break;
		case 1:
			right = true;
			break;
		case 2:
			down = true;
			break;
		case 3:
			left = true;
			break;
		default:
			break;
		}
		
		UpWardOptions.WINDOWS.getMainWindow().placeEntity(this);
	}

	/**
	 * Lets the projectile fly in its direction, till it dies.
	 */
	@Override
	public void updatePosition(int[][] map) {
		if(ticksTillDeath == 0) {
			UpWardOptions.WINDOWS.getMainWindow().removeEntity(this);
			return;
		}
		else {
			ticksTillDeath--;
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
	}
	
	/**
	 * Called when the entity collides with another.
	 * 
	 * @param entity The other entity.
	 */
	@Override
	public void collide(Collidable entity) {
		if(entity == null || getFaction() < entity.getFaction()) {
			UpWardOptions.WINDOWS.getMainWindow().removeEntity(this);
		}
	}

	/**
	 * @return The entity who shot this projectile.
	 */
	public IsaacEntity getShooter() {
		return shooter;
	}

	/**
	 * @param shooter The new entity who shot this projectile.
	 */
	public void setShooter(IsaacEntity shooter) {
		this.shooter = shooter;
	}

	/**
	 * @return In how many ticks, the entity will die.
	 */
	public int getTicksTillDeath() {
		return ticksTillDeath;
	}

	/**
	 * @param ticksTillDeath In how many ticks, the entity will die.
	 */
	public void setTicksTillDeath(int ticksTillDeath) {
		this.ticksTillDeath = ticksTillDeath;
	}

}
