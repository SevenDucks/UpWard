package eu.wauz.upward.game.isaac;

import java.awt.Color;

import eu.wauz.upward.game.GameMap;
import eu.wauz.upward.game.GameWindow;
import eu.wauz.upward.generation.RoomLayoutGenerator;

/**
 * A map for random isaac-like floor outlines.
 * 
 * @author Wauzmons
 */
public class RoomLayoutMap extends GameMap {
	
	/**
	 * The pixels for the game window.
	 */
	private int[] pixels;
	
	/**
	 * Creates a new room layout map with the size of the given map matrix.
	 * 
	 * @param mapMatrix The empty game map, for measurement.
	 */
	public RoomLayoutMap(int[][] mapMatrix) {
		super(mapMatrix, null, false);
	}

	/**
	 * Runs the renderer, to fill out the window.
	 * 
	 * @param window The window, that should be filled with pixels.
	 * 
	 * @see RoomLayoutMap#generate()
	 */
	@Override
	public void render(GameWindow window) {
		generate();
		for(int i = 0; i < window.getPixels().length; i++) {
			window.getPixels()[i] = pixels[i];
		}
	}
	
	/**
	 * Creates a new room layout generator, for a new map.
	 * Automatically maps values to pixel colors.
	 * 
	 * @see RoomLayoutGenerator
	 */
	public void generate() {
		RoomLayoutGenerator generator = new RoomLayoutGenerator(mapWidth, mapHeight);
		generator.run(32);
		
		int[][] roomMatrix = generator.getRoomMatrix();
		
		pixels = new int[mapWidth * mapHeight];
		for(int i = 0; i < pixels.length; i++) {
			int x = i;
			int y = 0;
			while (x >= mapWidth) {
				x -= mapWidth;
				y++;
			}
			Color color;
			switch (roomMatrix[x][y]) {
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
	
}
