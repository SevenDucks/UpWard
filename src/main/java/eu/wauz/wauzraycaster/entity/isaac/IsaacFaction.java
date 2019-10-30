package eu.wauz.wauzraycaster.entity.isaac;

/**
 * Factions represent groups of entities, that help to determine,
 * how they interact with each other.
 * Each faction has a fixed integer id.
 * 
 * @author Wauzmons
 */
public class IsaacFaction {
	
	/**
	 * The faction id of players.
	 */
	public final static int PLAYER = 100;
	
	/**
	 * The faction id of projectiles of players.
	 */
	public final static int PLAYER_PROJECTILE = 110;
	
	/**
	 * The faction id of enemies or other harmful objects.
	 */
	public final static int ENEMY = 600;

}
