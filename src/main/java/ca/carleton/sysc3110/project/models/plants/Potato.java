package ca.carleton.sysc3110.project.models.plants;

import ca.carleton.sysc3110.project.models.shots.ShotType;

// TODO: Auto-generated Javadoc
/**
 * The Class SeedSpitter. The SeedSpitter is the basic attack unit for the
 * plants. It takes up one tile of space and period fires a SEED bullet. It is
 * on a two turn cooldown and requires 75 sun points to purchase.
 * 
 * @author John Blackwood <john@xnor.ca>
 * @author Nikola Neskovic <nick_neskov@hotmail.com>
 */
public class Potato extends Plant {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5052607919583498035L;

	/** The Constant HEALTH. */
	public static final int HEALTH = SeedSpitter.HEALTH * 10;

	/** The Constant COST. */
	public static final int COST = 200;

	/** The Constant COOLDOWN. */
	public static final int COOLDOWN = 4;

	/** The Constant UPKEEP_COST. */
	public static final int UPKEEP_COST = 5;

	/** The Constant DAMAGE. */
	public static final int DAMAGE = 0;

	/** The Constant SHOT_COOLDOWN. */
	public static final int SHOT_COOLDOWN = Integer.MAX_VALUE;

	/** The Constant SHOT_TYPE. */
	public static final ShotType SHOT_TYPE = ShotType.NONE;

	/**
	 * Instantiates a new seed spitter.
	 * 
	 * @param row
	 *            the row
	 * @param location
	 *            the location
	 */
	public Potato(int row, int location) {
		super(row, location, HEALTH, DAMAGE, COST, COOLDOWN, SHOT_COOLDOWN, UPKEEP_COST, SHOT_TYPE);
	}

}
