package eu.wauz.wauzraycaster;

import java.io.File;
import java.net.URL;

public class WrayUtils {
	
	public static File getResource(String path) {
		ClassLoader classLoader = WrayUtils.class.getClassLoader();
        URL resource = classLoader.getResource(path);
        return new File(resource.getFile());
	}

}
