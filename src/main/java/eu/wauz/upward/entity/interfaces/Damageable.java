package eu.wauz.upward.entity.interfaces;

/**
 * Allows an entity to be damaged or die.
 * 
 * @author Wauzmons
 */
public interface Damageable {

	/**
	 * @return How much life the entity currently has.
	 */
	public int getHitpoints();
	
	/**
	 * @param hitpoints How much life the entity has now.
	 */
	public void setHitpoints(int hitpoints);
	
	/**
	 * @return How much life the entity can maximally have.
	 */
	public int getMaximumHitpoints();
	
	/**
	 * @param maxHitpoints How much life the entity can maximally have now.
	 */
	public void setMaxHitpoints(int maxHitpoints);
	
	/**
	 * Changes the entity's hitpoints by the given amount.
	 * Can take positive, aswell as negative values.
	 * If the new value exceeds the maximum, it will be set to the maximum.
	 * If it falls to or below 0, the entity will die.
	 * 
	 * @param amount By how much to change the hitpoints.
	 * 
	 * @see Damageable#die()
	 */
	public default void changeHitpoints(int amount) {
		int newHitpoints = getHitpoints() + amount;
		
		if(newHitpoints > getMaximumHitpoints()) {
			newHitpoints = getMaximumHitpoints();
		}
		else if(amount < 0 && getHitCooldownTicks() == 0) {
			if(newHitpoints <= 0) {
				setHitpoints(0);
				die();
			}
			else {
				setHitpoints(newHitpoints);
				setHitCooldownTicks(getInvincibilityTicks());
			}
		}
	}
	
	/**
	 * Called if the entity runs out of hitpoints.
	 * 
	 * @see Damageable#changeHitpoints(int)
	 */
	public void die();
	
	/**
	 * @return The default amount of hit cooldown ticks that will be set.
	 */
	public default int getInvincibilityTicks() {
		return 10;
	}
	
	/**
	 * @return The amount of ticks, till the next hit is possible.
	 * 
	 * @see Damageable#changeHitpoints(int) Automatically set on hitpoint change.
	 */
	public int getHitCooldownTicks();
	
	/**
	 * @param hitCooldownTicks The new amount of ticks, till the next hit is possible.
	 * 
	 * @see Damageable#changeHitpoints(int) Automatically set on hitpoint change.
	 */
	public void setHitCooldownTicks(int hitCooldownTicks);
	
}
