package eu.wauz.wauzraycaster.entity.isaac;

import eu.wauz.wauzraycaster.game.isaac.IsaacMap;
import eu.wauz.wauzraycaster.util.WrayOptions;

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
	 * In how many ticks, the entity will die.
	 */
	private int ticksTillDeath = 30;
	
	/**
	 * The entity who shot this projectile.
	 */
	private IsaacEntity shooter;
	
	/**
	 * Creates a new projectile, with given starting position.
	 * 
	 * @param shooter The entity who shot this projectile
	 * @param direction The direction, where every increase is a 90 degree rotation.
	 */
	public IsaacProjectile(IsaacEntity shooter, int direction) {
		super(shooter.getxPos() + 0.375, shooter.getyPos() + 0.25);
		this.shooter = shooter;
		MOVEMENT_SPEED = 0.12;
		
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
		
		WrayOptions.WINDOWS.getMainWindow().placeEntity(this);
	}

	/**
	 * Lets the projectile fly in its direction, till it dies.
	 */
	@Override
	public void updatePosition(int[][] map) {
		if(ticksTillDeath == 0) {
			WrayOptions.WINDOWS.getMainWindow().removeEntity(this);
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

}
