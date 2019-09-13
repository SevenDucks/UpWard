package eu.wauz.wauzraycaster.textures;

import java.util.ArrayList;
import java.util.List;

public class GameTileset {
	
	private List<GameTexture> textures = new ArrayList<>();
	
	public void add(GameTexture texture) {
		textures.add(texture);
	}
	
	public GameTexture get(int id) {
		return textures.get(id - 1);
	}

}
