package ca.carleton.sysc3110.project.models.plants;

import ca.carleton.sysc3110.project.models.BoardElement;
import ca.carleton.sysc3110.project.models.Game;
import ca.carleton.sysc3110.project.models.shots.ShotType;

// TODO: Auto-generated Javadoc
/**
 * The Class Plant. Each plant will have a cost to plant (sunCost) and in the
 * future we may consider adding a sunUpkeepCost which would be a cost applied
 * every turn or cycle used to upkeep the existing plants. The plant speed is 0
 * because most plants are stationary. The cooldown is the variable used to
 * control how frequently that plant can be planted. The shotCoolDown and
 * shotTimer variables are used to regular how frequently the plants will fire.
 * Once the cooldown decreases below the timer value the variables are reset and
 * another shot is fired. Finally the shot type will indicate what kind of shot
 * the plant shoots such as SEED, NONE, and possible future implementation of
 * ICE_SEED.
 * 
 * @author John Blackwood <john@xnor.ca>
 * @author Ian Wong <ian.m.y.wong@gmail.com>
 */
public class Plant extends BoardElement {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 63238788778705824L;

	/** The Constant PLANT_SPEED. */
	public static final int PLANT_SPEED = 0;

	/* (non-Javadoc)
	 * @see ca.carleton.sysc3110.project.models.BoardElement#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + cooldown;
		result = prime * result + shotCooldown;
		result = prime * result + Float.floatToIntBits(shotTimer);
		result = prime * result + ((shotType == null) ? 0 : shotType.hashCode());
		result = prime * result + sunCost;
		result = prime * result + sunUpkeep;
		return result;
	}

	/* (non-Javadoc)
	 * @see ca.carleton.sysc3110.project.models.BoardElement#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Plant other = (Plant) obj;
		if (cooldown != other.cooldown)
			return false;
		if (shotCooldown != other.shotCooldown)
			return false;
		if (Float.floatToIntBits(shotTimer) != Float.floatToIntBits(other.shotTimer))
			return false;
		if (shotType != other.shotType)
			return false;
		if (sunCost != other.sunCost)
			return false;
		if (sunUpkeep != other.sunUpkeep)
			return false;
		return true;
	}

	/** The Constant PLANT_WIDTH. */
	public static final int PLANT_WIDTH = 100;

	/** The cooldown. */
	protected int cooldown;

	/** The sun cost. */
	protected final int sunCost;

	/** The sun generation. */
	protected final int sunUpkeep;

	/** The shot cooldown. */
	protected final int shotCooldown;

	/** The shot timer. */
	protected float shotTimer;

	/** The shot type. */
	private final ShotType shotType;

	/**
	 * Instantiates a new plant.
	 * 
	 * @param row
	 *            the row
	 * @param location
	 *            the location
	 * @param health
	 *            the health
	 * @param damage
	 *            the damage
	 * @param cost
	 *            the cost
	 * @param cooldown
	 *            the cooldown
	 * @param shotCooldown
	 *            the shot cooldown
	 * @param sunGeneration
	 *            how much sun the plant generates (or costs) per turn
	 * @param shotType
	 *            the shot type
	 */
	public Plant(int row, int location, int health, int damage, int cost, int cooldown, int shotCooldown,
			int sunGeneration, ShotType shotType) {
		super(row, location, PLANT_WIDTH, PLANT_SPEED, health, damage);
		this.sunCost = cost;
		this.cooldown = cooldown;
		this.sunUpkeep = sunGeneration;
		this.shotCooldown = shotCooldown;
		this.shotTimer = 0;
		this.shotType = shotType;
	}

	/**
	 * Gets the cooldown.
	 * 
	 * @return the cooldown
	 */
	public int getCooldown() {
		return cooldown;
	}

	/**
	 * Gets the sun cost.
	 * 
	 * @return the sun cost
	 */
	public int getSunCost() {
		return sunCost;
	}

	/**
	 * Gets the sun generation.
	 * 
	 * @return the sun generation
	 */
	public int getSunUpkeep() {
		return sunUpkeep;
	}

	/**
	 * Should shoot.
	 * 
	 * @return true, if successful
	 */
	public boolean shouldShoot() {
		return this.shotTimer >= shotCooldown;
	}

	/**
	 * Gets the current value of the shot timer.
	 * 
	 * @return a The value of the current shot timer value
	 */
	public float getShotTimer()
	{
		return shotTimer;
	}
	
	/**
	 * Increment shot timer.
	 */
	public void incrementShotTimer() {
		this.shotTimer += Game.TICK_SCALE;
	}
	
	/**
	 * Decrement shot timer.
	 */
	public void decrementShotTimer() {
		this.shotTimer -= Game.TICK_SCALE;
	}

	/**
	 * Reset shot timer.
	 */
	public void resetShotTimer() {
		this.shotTimer -= this.shotCooldown;
	}
	
	/**
	 * Unreset shot timer (for undo feature).
	 */
	public void unresetShotTimer()
	{
		this.shotTimer += this.shotCooldown;
	}

	/**
	 * Gets the shot type.
	 * 
	 * @return the shot type
	 */
	public ShotType getShotType() {
		return this.shotType;
	}

	/**
	 * Gets the shot cooldown.
	 *
	 * @return the shot cooldown
	 */
	public int getShotCooldown() {
		return this.shotCooldown;
	}

}