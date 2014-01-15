package ca.carleton.sysc3110.project.models.zombies;

import ca.carleton.sysc3110.project.models.plants.Sunflower;

// TODO: Auto-generated Javadoc
/**
 * The Class WalkingUndead. The basic zombie has a speed of 18 units ~1 tile.
 * The zombie does 10 damage so it can kill a sunflower in one turn and a
 * SEED_SPITTER in three turns. The zombie has 100 health so it will take turns
 * for the SEED_SPITTER to kill it.
 * 
 * @author John Blackwood <john@xnor.ca>
 * @author Nikola Neskovic <nick_neskov@hotmail.com>
 * @author Ian Wong <ian.m.y.wong@gmail.com>
 * 
 */
public class WalkingUndead extends Zombie {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2659083018139706544L;

	/** The Constant SPEED. */
	public static final int SPEED = -18;

	/** The Constant HEALTH. */
	public static final int HEALTH = 100;

	/** The Constant DAMAGE. */
	public static final int DAMAGE = Sunflower.HEALTH;

	/**
	 * Instantiates a new walking undead.
	 *
	 * @param row the row
	 * @param location the location
	 * @param offset the offset
	 */
	public WalkingUndead(int row, int location, int offset) {
		super(row, location, SPEED, HEALTH, DAMAGE, offset);
	}

}
