package eu.wauz.upward.textures;

import java.util.ArrayList;
import java.util.List;

/**
 * A collection of game textures, indexed by addition order.
 * 
 * @author Wauzmons
 */
public class GameTileset {
	
	/**
	 * The inner list of game textures.
	 * 
	 * @see GameTexture
	 */
	private List<GameTexture> textures = new ArrayList<>();
	
	/**
	 * @param texture The game texture to add to this tileset.
	 */
	public void add(GameTexture texture) {
		textures.add(texture);
	}
	
	/**
	 * @param id The id of the requested texture, starting from 1.
	 * 
	 * @return The requested game texture.
	 */
	public GameTexture get(int id) {
		return textures.get(id - 1);
	}

}
