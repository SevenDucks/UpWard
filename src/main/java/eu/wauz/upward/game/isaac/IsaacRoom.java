package eu.wauz.upward.game.isaac;

import java.util.ArrayList;
import java.util.List;

import eu.wauz.upward.entity.MovingEntity;
import eu.wauz.upward.entity.isaac.IsaacCamera;
import eu.wauz.upward.entity.isaac.IsaacDoor;
import eu.wauz.upward.game.GameBlock;
import eu.wauz.upward.game.GameWindow;
import eu.wauz.upward.textures.GameTileset;
import eu.wauz.upward.util.UpWardOptions;

/**
 * A single room of a map for top down rougelike worlds.
 * 
 * @author Wauzmons
 */
public class IsaacRoom {
	
	/**
	 * The floor, that this room is located in.
	 */
	private IsaacFloor floor;
	
	/**
	 * The pixels for the game window, that stay the same for this room.
	 */
	private int[][] staticPixels;
	
	/**
	 * The width of the room in blocks.
	 */
	private int width;
	
	/**
	 * The height of the room in blocks.
	 */
	private int height;
	
	/**
	 * The length of a block in pixels.
	 */
	private int blockSize = 16;
	
	/**
	 * The tileset to use, for texturing the map.
	 */
	private GameTileset tileset;
	
	/**
	 * All entities located in this room.
	 */
	private List<MovingEntity> entities = new ArrayList<>();
	
	/**
	 * The neighbour rooms of this room.
	 */
	private IsaacRoom topRoom, bottomRoom, leftRoom, rightRoom;
	
	/**
	 * Creates a new isaac room with the given sizes.
	 * 
	 * @param floor The floor, that this room is located in.
	 * @param width The width of the room in blocks.
	 * @param height The height of the room in blocks.
	 * @param blockSize The length of a block in pixels.
	 * @param tileset The tileset to use, for texturing the map.
	 */
	public IsaacRoom(IsaacFloor floor, int width, int height, int blockSize, GameTileset tileset) {
		this.floor = floor;
		this.width = width;
		this.height = height;
		this.blockSize = blockSize;
		this.tileset = tileset;
	}
	
	/**
	 * Generates the pixels for the game window, that stay the same for this room.
	 */
	public void generateStaticPixels() {
		GameWindow window = UpWardOptions.WINDOWS.getMainWindow();
		staticPixels = new int[window.getGameWidth()][window.getGameHeight()];
		
		renderWalls();
		renderFloor();
		renderCorners();
		renderOpenDoors();
	}
	
	/**
	 * Renders the floor, based on the tileset.
	 */
	public void renderFloor() {
		GameBlock floorBlock = new GameBlock(tileset.get(1), blockSize);
		
		for(int x = 1; x < width - 1; x++) {
			for(int y = 1; y < height - 1; y++) {
				floorBlock.render(staticPixels, x * blockSize, y * blockSize, 0);
			}
		}
	}
	
	/**
	 * Renders the walls, based on the tileset.
	 */
	public void renderWalls() {
		GameBlock wallBlock = new GameBlock(tileset.get(2), blockSize);
		
		int rightEdge = (width - 1) * blockSize;
		int bottomEdge = (height - 1) * blockSize;
		
		for(int x = 1; x < width - 1; x++) {
			wallBlock.render(staticPixels, x * blockSize, 0, 0);
			wallBlock.render(staticPixels, x * blockSize, bottomEdge, 2);
		}
		for(int y = 1; y < height - 1; y++) {
			wallBlock.render(staticPixels, 0, y * blockSize, 3);
			wallBlock.render(staticPixels, rightEdge, y * blockSize, 1);
		}
	}
	
	/**
	 * Renders the corners, based on the tileset.
	 */
	public void renderCorners() {
		GameBlock cornerBlock = new GameBlock(tileset.get(3), blockSize);
		
		int rightEdge = (width - 1) * blockSize;
		int bottomEdge = (height - 1) * blockSize;
		
		cornerBlock.render(staticPixels, 0, 0, 0);
		cornerBlock.render(staticPixels, rightEdge, 0, 1);
		cornerBlock.render(staticPixels, rightEdge, bottomEdge, 2);
		cornerBlock.render(staticPixels, 0, bottomEdge, 3);
	}
	
	/**
	 * Renders open doors, based on the tileset.
	 */
	public void renderOpenDoors() {
		GameBlock openDoorBlock = new GameBlock(tileset.get(5), blockSize);
		
		if(topRoom != null) {
			entities.add(new IsaacDoor(topRoom, width / 2, 0.1));
			openDoorBlock.render(staticPixels, (width / 2) * blockSize, 0, 0);
		}
		if(rightRoom != null) {
			entities.add(new IsaacDoor(rightRoom, width - 1.1, height / 2));
			openDoorBlock.render(staticPixels, (width - 1) * blockSize, (height / 2) * blockSize, 1);
		}
		if(bottomRoom != null) {
			entities.add(new IsaacDoor(bottomRoom, width / 2, height - 1.1));
			openDoorBlock.render(staticPixels, (width / 2) * blockSize, (height - 1) * blockSize, 2);
		}
		if(leftRoom != null) {
			entities.add(new IsaacDoor(leftRoom, 0.1, height / 2));
			openDoorBlock.render(staticPixels, 0, (height / 2) * blockSize, 3);
		}
	}
	
	/**
	 * Loads all entities in this room into the game window.
	 */
	public void loadEntities() {
		GameWindow window = UpWardOptions.WINDOWS.getMainWindow();
		for(MovingEntity entity : entities) {
			window.placeEntity(entity);
		}
	}
	
	/**
	 * Unloads all entities from the game window, back to this room.
	 * The camera is kept in the window, but teleported to the opposite end.
	 */
	public void unloadEntities() {
		entities.clear();
		GameWindow window = UpWardOptions.WINDOWS.getMainWindow();
		for(MovingEntity entity : new ArrayList<>(window.getEntities())) {
			if(entity instanceof IsaacCamera) {
				IsaacCamera camera =(IsaacCamera) entity;
				double xPos = camera.getxPos();
				double yPos = camera.getyPos();
				
				if(xPos > width - 3) {
					camera.setxPos(1 + camera.getOffsetLeft());
				}
				if(xPos < 3) {
					camera.setxPos(width - 1 - camera.getOffsetRight() - (camera.getSize() / 2));
				}
				if(yPos > height - 3) {
					camera.setyPos(1 + camera.getOffsetTop());
				}
				if(yPos < 3) {
					camera.setyPos(height - 1 - camera.getOffsetBottom() - camera.getSize());
				}
			}
			else {
				window.removeEntity(entity);
				entities.add(entity);
			}
		}
	}
	
	/**
	 * @return The floor, that this room is located in.
	 */
	public IsaacFloor getFloor() {
		return floor;
	}

	/**
	 * @return The pixels for the game window, that stay the same for this room.
	 */
	public int[][] getStaticPixels() {
		return staticPixels;
	}

	/**
	 * @return The top neighbour room of this room.
	 */
	public IsaacRoom getTopRoom() {
		return topRoom;
	}

	/**
	 * @param topRoom The new top neighbour room of this room.
	 */
	public void setTopRoom(IsaacRoom topRoom) {
		this.topRoom = topRoom;
	}

	/**
	 * @return The bottom neighbour room of this room.
	 */
	public IsaacRoom getBottomRoom() {
		return bottomRoom;
	}

	/**
	 * @param bottomRoom The new bottom neighbour room of this room.
	 */
	public void setBottomRoom(IsaacRoom bottomRoom) {
		this.bottomRoom = bottomRoom;
	}

	/**
	 * @return The left neighbour room of this room.
	 */
	public IsaacRoom getLeftRoom() {
		return leftRoom;
	}

	/**
	 * @param leftRoom The new left neighbour room of this room.
	 */
	public void setLeftRoom(IsaacRoom leftRoom) {
		this.leftRoom = leftRoom;
	}

	/**
	 * @return The right neighbour room of this room.
	 */
	public IsaacRoom getRightRoom() {
		return rightRoom;
	}

	/**
	 * @param rightRoom The new right neighbour room of this room.
	 */
	public void setRightRoom(IsaacRoom rightRoom) {
		this.rightRoom = rightRoom;
	}

}
