package ca.carleton.sysc3110.project.models.plants;

import ca.carleton.sysc3110.project.models.shots.ShotType;

// TODO: Auto-generated Javadoc
/**
 * The Class SeedSpitter. The SeedSpitter is the basic attack unit for the
 * plants. It takes up one tile of space and period fires a SEED bullet. It is
 * on a two turn cooldown and requires 75 sun points to purchase.
 * 
 * @author John Blackwood <john@xnor.ca>
 * @author Ian Wong <ian.m.y.wong@gmail.com>
 */
public class SeedSpitter extends Plant {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 593244083597967403L;

	/** The Constant HEALTH. */
	public static final int HEALTH = 30;

	/** The Constant COST. */
	public static final int COST = 75;

	/** The Constant COOLDOWN. */
	public static final int COOLDOWN = 2;

	/** The Constant UPKEEP_COST. */
	public static final int UPKEEP_COST = 0;

	/** The Constant DAMAGE. */
	public static final int DAMAGE = 25;

	/** The Constant SHOT_COOLDOWN. */
	public static final int SHOT_COOLDOWN = 5;

	/** The Constant SHOT_TYPE. */
	public static final ShotType SHOT_TYPE = ShotType.SEED;

	/**
	 * Instantiates a new seed spitter.
	 * 
	 * @param row
	 *            the row
	 * @param location
	 *            the location
	 */
	public SeedSpitter(int row, int location) {
		super(row, location, HEALTH, DAMAGE, COST, COOLDOWN, SHOT_COOLDOWN, UPKEEP_COST, SHOT_TYPE);
		// TODO Auto-generated constructor stub
	}

}
