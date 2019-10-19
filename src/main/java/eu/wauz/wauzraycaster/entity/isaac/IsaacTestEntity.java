package eu.wauz.wauzraycaster.entity.isaac;

import eu.wauz.wauzraycaster.game.isaac.IsaacMap;
import eu.wauz.wauzraycaster.util.WrayUtils;

/**
 * A test entity, that can move and shoot across an isaac map.
 * 
 * @author Wauzmons
 *
 * @see IsaacEntity
 * @see IsaacMap
 */
public class IsaacTestEntity extends IsaacEntity {
	
	/**
	 * In how many ticks, the entity will change its movements.
	 */
	private int ticksTillBehaviourChange = 0;

	/**
	 * Creates a new entity, with given starting position.
	 * 
	 * @param xPos
	 * @param yPos
	 */
	public IsaacTestEntity(double xPos, double yPos) {
		super(xPos, yPos);
		MOVEMENT_SPEED = 0.06;
	}

	/**
	 * Lets the entity randomly stroll around.
	 * Changes direction every 30 ticks.
	 */
	@Override
	public void updatePosition(int[][] map) {
		if(ticksTillBehaviourChange == 0) {
			up = WrayUtils.randomBoolean();
			down = WrayUtils.randomBoolean();
			left = WrayUtils.randomBoolean();
			right = WrayUtils.randomBoolean();
			ticksTillBehaviourChange = 30;
			
		}
		else {
			ticksTillBehaviourChange--;
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

}
