package eu.wauz.upward.entity.isaac;

import eu.wauz.upward.entity.Hitbox;
import eu.wauz.upward.entity.MovingEntity;
import eu.wauz.upward.entity.interfaces.Collidable;
import eu.wauz.upward.entity.interfaces.Visible;
import eu.wauz.upward.game.GameMap;
import eu.wauz.upward.game.isaac.IsaacMap;
import eu.wauz.upward.textures.GameTexture;

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
	 * The top offset of the entity's hitbox in blocks.
	 */
	private double offsetTop = 0;
	
	/**
	 * The bottom offset of the entity's hitbox in blocks.
	 */
	private double offsetBottom = 0;
	
	/**
	 * The left offset of the entity's hitbox in blocks.
	 */
	private double offsetLeft = 0;
	
	/**
	 * The right offset of the entity's hitbox in blocks.
	 */
	private double offsetRight = 0;
	
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
		if(map [(int) xPosNew] [(int) yPos] == 0 && map [(int) Math.ceil(xPosNew + size - offsetLeft - offsetRight) - 1] [(int) yPos] == 0) {
			xPos = xPosNew;
		}
		else {
			xPos = xPosNew > xPos ? (Math.floor(xPosNew) + offsetLeft + offsetRight) : Math.ceil(xPosNew);
			collide(null);
		}
		if(map [(int) xPos] [(int) yPosNew] == 0 && map [(int) xPos] [(int) Math.ceil(yPosNew + size - offsetTop - offsetBottom) - 1] == 0) {
			yPos = yPosNew;
		}
		else {
			yPos = yPosNew > yPos ? (Math.floor(yPosNew) + offsetTop + offsetBottom) : Math.ceil(yPosNew);
			collide(null);
		}
		resizeHitbox();
	}
	
	/**
	 * Recalculates size and position of the entity's hitbox.
	 */
	public void resizeHitbox() {
		hitbox.resize(xPos, yPos, size - offsetLeft - offsetRight, size - offsetTop - offsetBottom);
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
		int startX = getStartingPixel(xPos - offsetLeft, blockSize);
		int startY = getStartingPixel(yPos - offsetTop, blockSize);
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
		resizeHitbox();
	}
	
	/**
	 * @return The top offset of the entity's hitbox in blocks.
	 */
	public double getOffsetTop() {
		return offsetTop;
	}
	
	/**
	 * @param offsetTop The new top offset of the entity's hitbox in blocks.
	 */
	public void setOffsetTop(double offsetTop) {
		this.offsetTop = offsetTop;
		resizeHitbox();
	}
	
	/**
	 * @return The bottom offset of the entity's hitbox in blocks.
	 */
	public double getOffsetBottom() {
		return offsetBottom;
	}
	
	/**
	 * @param offsetBottom The new bottom offset of the entity's hitbox in blocks.
	 */
	public void setOffsetBottom(double offsetBottom) {
		this.offsetBottom = offsetBottom;
		resizeHitbox();
	}
	
	/**
	 * @return The left offset of the entity's hitbox in blocks.
	 */
	public double getOffsetLeft() {
		return offsetLeft;
	}
	
	/**
	 * @param offsetLeft The new left offset of the entity's hitbox in blocks.
	 */
	public void setOffsetLeft(double offsetLeft) {
		this.offsetLeft = offsetLeft;
		resizeHitbox();
	}
	
	/**
	 * @return The right offset of the entity's hitbox in blocks.
	 */
	public double getOffsetRight() {
		return offsetRight;
	}
	
	/**
	 * @param offsetRight The new right offset of the entity's hitbox in blocks.
	 */
	public void setOffsetRight(double offsetRight) {
		this.offsetRight = offsetRight;
		resizeHitbox();
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
