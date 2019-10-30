package eu.wauz.wauzraycaster.entity.isaac;

import eu.wauz.wauzraycaster.entity.interfaces.Collidable;
import eu.wauz.wauzraycaster.game.GameMap;
import eu.wauz.wauzraycaster.game.isaac.IsaacRoom;

/**
 * A door to travel between rooms on an isaac map.
 * 
 * @author Wauzmons
 * 
 * @see IsaacEntity
 * @see IsaacRoom
 */
public class IsaacDoor extends IsaacEntity {
	
	/**
	 * The room the door leads to.
	 */
	private IsaacRoom room;

	/**
	 * Creates a new door between two rooms, with given starting position.
	 * 
	 * @param room The room the door leads to.
	 * @param xPos
	 * @param yPos
	 */
	public IsaacDoor(IsaacRoom room, double xPos, double yPos) {
		super(xPos, yPos);
		this.room = room;
	}

	/**
	 * The door cannot move.
	 */
	@Override
	public void updatePosition(int[][] map) {
		
	}
	
	/**
	 * The door hitbox is not visible.
	 */
	@Override
	public void render(GameMap map) {
		
	}
	
	/**
	 * Called when the entity collides with another.
	 * 
	 * @param entity The other entity.
	 */
	@Override
	public void collide(Collidable entity) {
		if(entity != null && entity.getFaction() == IsaacFaction.PLAYER) {
			room.getFloor().setCurrentRoom(room);
		}
	}

}
