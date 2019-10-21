package eu.wauz.wauzraycaster.entity.doom;

import eu.wauz.wauzraycaster.game.doom.DoomMap;

/**
 * A test entity, that can move across a pseudo 3D doom map.
 * Is invisible and can only rotate and move forward.
 * 
 * @author Wauzmons
 *
 * @see DoomEntity
 * @see DoomMap
 */
public class DoomTestEntity extends DoomEntity {
	
	/**
	 * Creates a new entity, with given starting position.
	 * 
	 * @param xPos
	 * @param yPos
	 * @param xDir
	 * @param yDir
	 * @param xPlane
	 * @param yPlane
	 */
	public DoomTestEntity(double xPos, double yPos, double xDir, double yDir, double xPlane, double yPlane) {
		super(xPos, yPos, xDir, yDir, xPlane, yPlane);
	}
	
	/**
	 * Moves rotates, while constantly moving forward, if possible.
	 */
	@Override
	public void updatePosition(int[][] map) {
		if(true) {
			moveForward(map);
		}
		if(true) {
			rotate(rotationSpeed);
		}
	}

}
