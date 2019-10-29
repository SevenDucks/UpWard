package eu.wauz.wauzraycaster.game.isaac;

import eu.wauz.wauzraycaster.generation.RoomLayoutGenerator;

/**
 * A single floor of a map for top down rougelike worlds.
 * 
 * @author Wauzmons
 */
public class IsaacFloor {
	
	/**
	 * The actual game map, that is rendered.
	 */
	private IsaacMap map;
	
	/**
	 * An array of all rooms on this floor.
	 */
	private IsaacRoom[][] rooms;
	
	/**
	 * The room, that the player starts in.
	 */
	private IsaacRoom startingRoom;
	
	/**
	 * The room, that the player is located in.
	 */
	private IsaacRoom currentRoom;
	
	/**
	 * Creates a floor filled with rooms.
	 * 
	 * @param map The actual game map, that is rendered.
	 */
	public IsaacFloor(IsaacMap map) {
		this.map = map;
		
		int floorWidth = 21;
		int floorHeight = 21;
		
		RoomLayoutGenerator generator = new RoomLayoutGenerator(floorWidth, floorHeight);
		rooms = new IsaacRoom[floorWidth][floorHeight];
		generator.run(25);
		int[][] roomMatrix = generator.getRoomMatrix();
		
		for(int x = 0; x < floorWidth; x++) {
			for(int y = 0; y < floorHeight; y++) {
				int roomType = roomMatrix[x][y];
				if(roomType == 0) {
					continue;
				}
				IsaacRoom room = new IsaacRoom(this, map.getMapWidth(), map.getMapHeight(), map.getBlockSize(), map.getTileset());
				rooms[x][y] = room;
			}
		}
		
		for(int x = 0; x < floorWidth; x++) {
			for(int y = 0; y < floorHeight; y++) {
				IsaacRoom room = rooms[x][y];
				if(room == null) {
					continue;
				}
				if(rooms[x][y + 1] != null) {
					room.setTopRoom(rooms[x][y + 1]);
				}
				if(rooms[x][y - 1] != null) {
					room.setBottomRoom(rooms[x][y - 1]);
				}
				if(rooms[x - 1][y] != null) {
					room.setLeftRoom(rooms[x - 1][y]);
				}
				if(rooms[x + 1][y] != null) {
					room.setRightRoom(rooms[x + 1][y]);
				}
				room.generateStaticPixels();
			}
		}
		
		startingRoom = rooms[floorWidth / 2][floorHeight / 2];
	}

	/**
	 * @return An array of all rooms on this floor.
	 */
	public IsaacRoom[][] getRooms() {
		return rooms;
	}
	
	/**
	 * @return The room, that the player starts in.
	 */
	public IsaacRoom getStartingRoom() {
		return startingRoom;
	}

	/**
	 * @return The room, that the player is located in.
	 */
	public IsaacRoom getCurrentRoom() {
		return currentRoom;
	}

	/**
	 * @param currentRoom The new room, that the player is located in.
	 */
	public void setCurrentRoom(IsaacRoom currentRoom) {
		if(this.currentRoom != null) {
			this.currentRoom.unloadEntities();
		}
		this.currentRoom = currentRoom;
		this.currentRoom.loadEntities();
		map.resetCachedRoom();
	}

}
