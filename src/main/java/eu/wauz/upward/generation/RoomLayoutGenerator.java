package eu.wauz.upward.generation;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import eu.wauz.upwardutils.UpWardUtils;

/**
 * A two dimensional room layout generator.
 * It saves connected rooms in an array of integers.
 * Can be used for map generation and other things.
 * 
 * @author Wauzmons
 */
public class RoomLayoutGenerator {
	
	/**
	 * The width of the room matrix.
	 */
	private int width;
	
	/**
	 * The height of the room matrix.
	 */
	private int height;
	
	/**
	 * The 2D array, to save room states.
	 */
	private int[][] roomMatrix;
	
	/**
	 * How many natural neighbours a room is allowed to have.
	 */
	private int neighbourLimit = 3;
	
	/**
	 * A list of rooms, that have space for neighbours.
	 */
	private List<Pair<Integer, Integer>> freeRooms = new ArrayList<>();
	
	public RoomLayoutGenerator(int width, int height) {
		this.width = width;
		this.height = height;
		roomMatrix = new int[width][height];
	}
	
	/**
	 * Lets the generator run, to generate the room matrix.
	 * 
	 * @param maximumRooms The maximum amount of rooms, the matrix should hold.
	 */
	public void run(int maximumRooms) {
		freeRooms.clear();
		for(int x = 0; x < width; x++) {
			for(int y = 0; y < height; y++) {
				roomMatrix[x][y] = 0;
			}
		}
		
		int centerX = width / 2;
		int centerY = height / 2;
		roomMatrix[centerX][centerY] = 2;
		freeRooms.add(Pair.of(centerX, centerY));
		
		int currentRooms = 1;
		while(currentRooms < maximumRooms) {
			Pair<Integer, Integer> freeRoom = getRandomFreeRoom();
			
			int neighbourCount = countNeighbours(freeRoom.getKey(), freeRoom.getValue());
			if(neighbourCount >= neighbourLimit) {
				freeRooms.remove(freeRoom);
				continue;
			}
			
			boolean lastRoom = currentRooms == maximumRooms - 1;
			Pair<Integer, Integer> newRoom = getRandomEmptyNeighbour(freeRoom.getKey(), freeRoom.getValue());
			neighbourCount = countNeighbours(newRoom.getKey(), newRoom.getValue());
			if(neighbourCount >= neighbourLimit - (UpWardUtils.randomBoolean(0.75) ? 1 : 0)) {
				continue;
			}
			roomMatrix[newRoom.getKey()][newRoom.getValue()] = lastRoom ? 3 : 1;
			freeRooms.add(newRoom);
			currentRooms++;
		}
	}
	
	/**
	 * @param x The x coordinate of a room.
	 * @param y The y coordinate of a room.
	 * 
	 * @return How many neighbours that room has
	 * or the neighbour limit if it is on the map border.
	 */
	private int countNeighbours(int x, int y) {
		if(x == 0 || y == 0 || x == width - 1 || y == height - 1) {
			return neighbourLimit;
		}
		int neighbourCount = 0;
		if(roomMatrix[x + 1][y] > 0) {
			neighbourCount++;
		}
		if(roomMatrix[x - 1][y] > 0) {
			neighbourCount++;
		}
		if(roomMatrix[x][y + 1] > 0) {
			neighbourCount++;
		}
		if(roomMatrix[x][y - 1] > 0) {
			neighbourCount++;
		}
		return neighbourCount;
	}
	
	/**
	 * @param x The x coordinate of a room.
	 * @param y The y coordinate of a room.
	 * 
	 * @return A random space, next to the room, that is not occupied.
	 */
	private Pair<Integer, Integer> getRandomEmptyNeighbour(int x, int y) {
		List<Pair<Integer, Integer>> neighbours = new ArrayList<>();
		if(roomMatrix[x + 1][y] == 0) {
			neighbours.add(Pair.of(x + 1, y));
		}
		if(roomMatrix[x - 1][y] == 0) {
			neighbours.add(Pair.of(x - 1, y));
		}
		if(roomMatrix[x][y + 1] == 0) {
			neighbours.add(Pair.of(x, y + 1));
		}
		if(roomMatrix[x][y - 1] == 0) {
			neighbours.add(Pair.of(x, y - 1));
		}
		return neighbours.get(UpWardUtils.randomInt(neighbours.size()));
	}
	
	/**
	 * @return A random room, that has space for neighbours.
	 */
	private Pair<Integer, Integer> getRandomFreeRoom() {
		return freeRooms.get(UpWardUtils.randomInt(freeRooms.size()));
	}
	
	/**
	 * @return The 2D array, to save room states.
	 */
	public int[][] getRoomMatrix() {
		return roomMatrix;
	}

}
