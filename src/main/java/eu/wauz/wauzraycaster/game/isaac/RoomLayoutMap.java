package eu.wauz.wauzraycaster.game.isaac;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import eu.wauz.wauzraycaster.game.GameMap;
import eu.wauz.wauzraycaster.game.GameWindow;
import eu.wauz.wauzraycaster.util.WrayUtils;

/**
 * TODO: Document me!
 * 
 * @author Wauzmons
 */
public class RoomLayoutMap extends GameMap {
	
	private int[] pixels;
	
	private int neighbourLimit = 3;

	public RoomLayoutMap(int[][] mapMatrix) {
		super(mapMatrix, null, false);
	}

	@Override
	public void render(GameWindow window) {
		generate();
		for(int i = 0; i < window.getPixels().length; i++) {
			window.getPixels()[i] = pixels[i];
		}
	}
	
	private List<Pair<Integer, Integer>> freeRooms = new ArrayList<>();
	
	public void generate() {
		freeRooms.clear();
		for(int x = 0; x < mapWidth; x++) {
			for(int y = 0; y < mapHeight; y++) {
				getMapMatrix()[x][y] = 0;
			}
		}
		
		int centerX = mapWidth / 2;
		int centerY = mapHeight / 2;
		getMapMatrix()[centerX][centerY] = 2;
		freeRooms.add(Pair.of(centerX, centerY));
		
		int currentRooms = 1;
		int maximumRooms = 25;
		while(currentRooms < maximumRooms) {
			Pair<Integer, Integer> freeRoom = getRandomFreeRoom();
			
			int neighbourCount = countNeighbours(freeRoom.getKey(), freeRoom.getValue());
			if(neighbourCount >= neighbourLimit) {
				freeRooms.remove(freeRoom);
				continue;
			}
			
			boolean lastRoom = currentRooms == maximumRooms - 1;
			Pair<Integer, Integer> newRoom = getRandomNeighbour(freeRoom.getKey(), freeRoom.getValue());
			neighbourCount = countNeighbours(newRoom.getKey(), newRoom.getValue());
			if(neighbourCount >= neighbourLimit - (WrayUtils.randomBoolean(0.75) ? 1 : 0)) {
				continue;
			}
			getMapMatrix()[newRoom.getKey()][newRoom.getValue()] = lastRoom ? 3 : 1;
			freeRooms.add(newRoom);
			currentRooms++;
		}
		
		pixels = new int[mapWidth * mapHeight];
		for(int i = 0; i < pixels.length; i++) {
			int x = i;
			int y = 0;
			while (x >= mapWidth) {
				x -= mapWidth;
				y++;
			}
			Color color;
			switch (getMapMatrix()[x][y]) {
			case 1:
				color = Color.ORANGE;
				break;
			case 2:
				color = Color.BLUE;
				break;
			case 3:
				color = Color.RED;
				break;
			default:
				color = Color.DARK_GRAY;
				break;
			}
			pixels[i] = color.getRGB();
		}
	}
	
	private int countNeighbours(int x, int y) {
		if(x == 0 || y == 0 || x == mapWidth - 1 || y == mapHeight - 1) {
			return neighbourLimit;
		}
		int neighbourCount = 0;
		if(getMapMatrix()[x + 1][y] > 0) {
			neighbourCount++;
		}
		if(getMapMatrix()[x - 1][y] > 0) {
			neighbourCount++;
		}
		if(getMapMatrix()[x][y + 1] > 0) {
			neighbourCount++;
		}
		if(getMapMatrix()[x][y - 1] > 0) {
			neighbourCount++;
		}
		return neighbourCount;
	}
	
	private Pair<Integer, Integer> getRandomNeighbour(int x, int y) {
		List<Pair<Integer, Integer>> neighbours = new ArrayList<>();
		if(getMapMatrix()[x + 1][y] == 0) {
			neighbours.add(Pair.of(x + 1, y));
		}
		if(getMapMatrix()[x - 1][y] == 0) {
			neighbours.add(Pair.of(x - 1, y));
		}
		if(getMapMatrix()[x][y + 1] == 0) {
			neighbours.add(Pair.of(x, y + 1));
		}
		if(getMapMatrix()[x][y - 1] == 0) {
			neighbours.add(Pair.of(x, y - 1));
		}
		return neighbours.get(WrayUtils.randomInt(neighbours.size()));
	}
	
	private Pair<Integer, Integer> getRandomFreeRoom() {
		return freeRooms.get(WrayUtils.randomInt(freeRooms.size()));
	}

}
