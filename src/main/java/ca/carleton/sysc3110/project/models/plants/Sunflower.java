package ca.carleton.sysc3110.project.models.plants;

import ca.carleton.sysc3110.project.models.shots.ShotType;

// TODO: Auto-generated Javadoc
/**
 * The Class Sunflower. The sunflower acts as an economic plant. It's purpose is
 * to provide the plants with money (sunpoints) in order to amass a defense
 * against the zombies. Therefore the sunflower has a NEGATIVE operating cost
 * because it gives money back to the user. It does NO damage and, consequently,
 * has NO shot type. You are allowed to place one every turn.
 * 
 * @author John Blackwood <john@xnor.ca>
 * @author Nikola Neskovic <nick_neskov@hotmail.com>
 * @author Ian Wong <ian.m.y.wong@gmail.com>
 */
public class Sunflower extends Plant {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -6092716415576117613L;

	/** The Constant HEALTH. */
	public static final int HEALTH = 10;

	/** The Constant COST. */
	public static final int COST = 50;

	/** The Constant COOLDOWN. */
	public static final int COOLDOWN = 1;

	/** The Constant UPKEEP_COST. */
	public static final int UPKEEP_COST = -10;

	/** The Constant DAMAGE. */
	public static final int DAMAGE = 0;

	/** The Constant SHOT_COOLDOWN. */
	public static final int SHOT_COOLDOWN = 0;

	/** The Constant SHOT_TYPE. */
	public static final ShotType SHOT_TYPE = ShotType.NONE;

	/**
	 * Instantiates a new sunflower.
	 * 
	 * @param row
	 *            the row
	 * @param location
	 *            the location
	 */
	public Sunflower(int row, int location) {
		super(row, location, HEALTH, DAMAGE, COST, COOLDOWN, SHOT_COOLDOWN, UPKEEP_COST, SHOT_TYPE);
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ca.carleton.sysc3110.project.models.plants.Plant#shouldShoot()
	 */
	@Override
	public boolean shouldShoot() {
		return false;
	}

}
