package eu.wauz.upward.gui;

import java.awt.Color;

import eu.wauz.upward.entity.interfaces.Damageable;
import eu.wauz.upward.textures.GameTexture;

/**
 * A gui element to display the life of an entity as hearts (or something similar).
 * 
 * @author Wauzmons
 */
public class HitpointHearts implements GuiElement {
	
	/**
	 * The entity that gets its hitpoints displayed.
	 */
	private Damageable entity;
	
	/**
	 * The x coordinate of the display.
	 */
	private int startX;
	
	/**
	 * The y coordinate of the display.
	 */
	private int startY;
	
	/**
	 * The spacing between icons.
	 */
	private int spacing;
	
	/**
	 * The icon of a filled heart / health block.
	 */
	private GameTexture filledTexture = new GameTexture(Color.RED, 12);
	
	/**
	 * The icon of an empty heart / health block.
	 */
	private GameTexture emptyTexture = new GameTexture(Color.DARK_GRAY, 12);
	
	/**
	 * Creates a new hitpoint display for given values.
	 * 
	 * @param entity The entity that gets its hitpoints displayed.
	 * @param startX The x coordinate of the display.
	 * @param startY The y coordinate of the display.
	 * @param spacing The spacing between icons.
	 */
	public HitpointHearts(Damageable entity, int startX, int startY, int spacing) {
		this.entity = entity;
		this.startX = startX;
		this.startY = startY;
		this.spacing = spacing;
	}

	/**
	 * Draws the gui element onto the given pixel array.
	 * 
	 * @param pixels
	 */
	@Override
	public void render(int[][] pixels) {
		for(int i = 0; i < entity.getMaximumHitpoints(); i++) {
			GameTexture texture = i < entity.getHitpoints() ? filledTexture : emptyTexture;
			texture.render(pixels, startX + i * (texture.getSize() + spacing), startY);
		}
	}

	/**
	 * @return The entity that gets its hitpoints displayed.
	 */
	public Damageable getEntity() {
		return entity;
	}

	/**
	 * @param entity The new entity that gets its hitpoints displayed.
	 */
	public void setEntity(Damageable entity) {
		this.entity = entity;
	}

	/**
	 * @return The x coordinate of the display.
	 */
	public int getStartX() {
		return startX;
	}

	/**
	 * @param startX The new x coordinate of the display.
	 */
	public void setStartX(int startX) {
		this.startX = startX;
	}

	/**
	 * @return The y coordinate of the display.
	 */
	public int getStartY() {
		return startY;
	}

	/**
	 * @param startY The new y coordinate of the display.
	 */
	public void setStartY(int startY) {
		this.startY = startY;
	}

	/**
	 * @return The spacing between icons.
	 */
	public int getSpacing() {
		return spacing;
	}

	/**
	 * @param spacing The new spacing between icons.
	 */
	public void setSpacing(int spacing) {
		this.spacing = spacing;
	}

	/**
	 * @return The icon of a filled heart / health block.
	 */
	public GameTexture getFilledTexture() {
		return filledTexture;
	}

	/**
	 * @param filledTexture The new icon of a filled heart / health block.
	 */
	public void setFilledTexture(GameTexture filledTexture) {
		this.filledTexture = filledTexture;
	}

	/**
	 * @return The icon of an empty heart / health block.
	 */
	public GameTexture getEmptyTexture() {
		return emptyTexture;
	}

	/**
	 * @param emptyTexture The new icon of an empty heart / health block.
	 */
	public void setEmptyTexture(GameTexture emptyTexture) {
		this.emptyTexture = emptyTexture;
	}

}
