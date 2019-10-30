package eu.wauz.upward.util;

import java.io.File;
import java.net.URL;
import java.util.Random;

/**
 * Static utility methods, used across the game.
 * 
 * @author Wauzmons
 */
public class UpWardUtils {
	
	/**
	 * An instance of the random class, to have a fixed seed.
	 */
	private static Random random = new Random();
	
	/**
	 * @param path The path to a resource.
	 * 
	 * @return The file from the resources folder.
	 */
	public static File getResource(String path) {
		ClassLoader classLoader = UpWardUtils.class.getClassLoader();
        URL resource = classLoader.getResource(path);
        return new File(resource.getFile());
	}
	
	/**
	 * @param inputPixels The pixel array to rotate.
	 * @param size The width and height of the pixel array
	 * @param rotation How much the texture should be rotated. 1 equals 90 degrees.
	 * 
	 * @return The rotated pixel array.
	 */
	public static int[] rotateClockwise(int[] inputPixels, int size, int rotation) {
		if(rotation == 0) {
			return inputPixels;
		}
		else {
			int[] pixels = new int[inputPixels.length];
			for(int i = rotation; i > 0; i--) {
				for (int x = 0; x < size; x++) {
					for (int y = 0; y < size; y++) {
						pixels[x * size + y] = inputPixels[(size - 1) * size - (((x * size + y) % size) * size) + (x * size + y) / size];
					}
				}
			}
			return rotateClockwise(pixels, size, rotation - 1);
		}
	}
	
	/**
	 * @return A random boolean.
	 */
	public static boolean randomBoolean() {
		return random.nextBoolean();
	}
	
	/**
	 * @param chance The chance for true between 0 and 1.
	 * 
	 * @return A random boolean.
	 */
	public static boolean randomBoolean(double chance) {
		return random.nextDouble() < chance;
	}
	
	/**
	 * @param bound The exclusive maximum value.
	 * 
	 * @return A random integer.
	 */
	public static int randomInt(int bound) {
		return random.nextInt(bound);
	}
	
	/**
	 * @param min Minimum returned value.
	 * @param max Maximum returned value.
	 * 
	 * @return A random integer between given values.
	 */
	public static int randomInt(int min, int max) {
		return random.nextInt(max - min + 1) + min;
	}
	
	/**
	 * @return a random float.
	 */
	public static float randomFloat() {
		return random.nextFloat();
	}
	
}
