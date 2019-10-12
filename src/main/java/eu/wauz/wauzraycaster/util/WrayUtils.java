package eu.wauz.wauzraycaster.util;

import java.io.File;
import java.net.URL;

/**
 * Static utility methods, used across the game.
 * 
 * @author Wauzmons
 */
public class WrayUtils {
	
	/**
	 * @param path The path to a resource.
	 * @return The file from the resources folder.
	 */
	public static File getResource(String path) {
		ClassLoader classLoader = WrayUtils.class.getClassLoader();
        URL resource = classLoader.getResource(path);
        return new File(resource.getFile());
	}

}
