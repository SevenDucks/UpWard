package eu.wauz.wauzraycaster.entity.isaac;

import eu.wauz.wauzraycaster.entity.Hitbox;
import eu.wauz.wauzraycaster.entity.MovingEntity;
import eu.wauz.wauzraycaster.entity.interfaces.Collidable;
import eu.wauz.wauzraycaster.entity.interfaces.Visible;
import eu.wauz.wauzraycaster.game.GameMap;
import eu.wauz.wauzraycaster.game.isaac.IsaacMap;
import eu.wauz.wauzraycaster.textures.GameTexture;

/**
 * An entity, that can move and shoot across an isaac map.
 * 
 * @author Wauzmons
 *
 * @see MovingEntity
 * @see IsaacMap
 */
public abstract class IsaacEntity extends MovingEntity implements Visible, Collidable {
	
	/**
	 * If the entity is moving in this direction.
	 */
	protected boolean up, down, left, right;
	
	/**
	 * The faction (player, enemy) id that this entity belongs to.
	 */
	protected int faction = 0;
	
	/**
	 * The size of the entity's hitbox in blocks.
	 */
	private double size = 1;
	
	/**
	 * The entity's current hitbox.
	 */
	private Hitbox hitbox;
	
	/**
	 * The appearance of this entity.
	 */
	private GameTexture texture;

	/**
	 * Creates a new entity, with given starting position.
	 * 
	 * @param xPos
	 * @param yPos
	 */
	public IsaacEntity(double xPos, double yPos) {
		super(xPos, yPos, 0, 0, 0, 0);
		hitbox = new Hitbox(xPos, yPos, size, size);
	}

	/**
	 * Moves up, if possible.
	 * 
	 * @param map The map to move on.
	 */
	public void moveUp(int[][] map) {
		move(map, xPos, yPos - getRegulatedMovementSpeed());
	}
	
	/**
	 * Moves down, if possible.
	 * 
	 * @param map The map to move on.
	 */
	public void moveDown(int[][] map) {
		move(map, xPos, yPos + getRegulatedMovementSpeed());
	}
	
	/**
	 * Moves left, if possible.
	 * 
	 * @param map The map to move on.
	 */
	public void moveLeft(int[][] map) {
		move(map, xPos - getRegulatedMovementSpeed(), yPos);
	}
	
	/**
	 * Moves right, if possible.
	 * 
	 * @param map The map to move on.
	 */
	public void moveRight(int[][] map) {
		move(map, xPos + getRegulatedMovementSpeed(), yPos);
	}
	
	/**
	 * Moves the entity, if possible.
	 * 
	 * @param map The map to move on.
	 * @param xPosNew The x position to move to.
	 * @param yPosNew The y position to move to.
	 */
	public void move(int[][] map, double xPosNew, double yPosNew) {
		if(map [(int) xPosNew] [(int) yPos] == 0 && map [(int) Math.ceil(xPosNew + size) - 1] [(int) yPos] == 0) {
			xPos = xPosNew;
		}
		else {
			collide(null);
		}
		if(map [(int) xPos] [(int) yPosNew] == 0 && map [(int) xPos] [(int) Math.ceil(yPosNew + size) - 1] == 0) {
			yPos = yPosNew;
		}
		else {
			collide(null);
		}
		hitbox.resize(xPos, yPos, size, size);
		hitbox.checkForCollisions(this);
	}
	
	/**
	 * Regulates the movement speed, by reducing it, if the entity moves vertically.
	 * 
	 * @return The regulated movement speed.
	 */
	public double getRegulatedMovementSpeed() {
		return (up || down) && (left || right) ? movementSpeed / 1.4142 : movementSpeed;
	}
	
	/**
	 * Runs the renderer, to place the entity in the window.
	 * 
	 * @param window The game map, where the entity is located.
	 */
	@Override
	public void render(GameMap map) {
		if(!(map instanceof IsaacMap)) {
			return;
		}
		IsaacMap isaacMap = (IsaacMap) map;
		int[][] pixels = isaacMap.getPixels();
		int blockSize = isaacMap.getBlockSize();
		int startX = getStartingPixel(xPos, blockSize);
		int startY = getStartingPixel(yPos, blockSize);
		texture.render(pixels, startX, startY);
		drawGui(pixels);
	}
	
	/**
	 * Finds the coordinate, where the rendering should start.
	 * 
	 * @param exactCoordinate The decimal coordinate of the entity.
	 * @param blockSize The length of a block in pixels.
	 * 
	 * @return The pixel where rendering should start.
	 */
	public int getStartingPixel(double exactCoordinate, int blockSize) {
		int startingPixel = ((int) Math.floor(exactCoordinate)) * blockSize;
		double remains = exactCoordinate % 1;
		double pixelSize = 1.0 / blockSize;
		int offsetPixels = (int) (remains / pixelSize);
		return startingPixel + offsetPixels;
	}
	
	/**
	 * Called when the entity collides with another.
	 * 
	 * @param entity The other entity.
	 */
	@Override
	public void collide(Collidable entity) {
		
	}

	/**
	 * @return The size of the entity's hitbox in blocks.
	 */
	public double getSize() {
		return size;
	}

	/**
	 * @param size The new size of the entity's hitbox in blocks.
	 */
	public void setSize(double size) {
		this.size = size;
	}
	
	/**
	 * @return The entity's current hitbox.
	 */
	@Override
	public Hitbox getHitbox() {
		return hitbox;
	}

	/**
	 * @param hitbox The entity's new hitbox.
	 */
	public void setHitbox(Hitbox hitbox) {
		this.hitbox = hitbox;
	}

	/**
	 * @return The appearance of this entity.
	 */
	public GameTexture getTexture() {
		return texture;
	}

	/**
	 * @param texture The new appearance of this entity.
	 */
	public void setTexture(GameTexture texture) {
		this.texture = texture;
	}

	/**
	 * The faction (player, enemy) id that this entity belongs to.
	 */
	@Override
	public int getFaction() {
		return faction;
	}

	/**
	 * @param faction The new faction (player, enemy) id that this entity belongs to.
	 */
	public void setFaction(int faction) {
		this.faction = faction;
	}
	
}
