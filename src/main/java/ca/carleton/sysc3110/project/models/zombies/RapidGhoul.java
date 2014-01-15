package ca.carleton.sysc3110.project.models.zombies;

// TODO: Auto-generated Javadoc
/**
 * The Class Rapid Ghoul. The fast zombie has a speed of 30 units.
 * The zombie does 5 damage so it can kill a sunflower in two turns and a
 * SEED_SPITTER in 6 turns. The zombie has 50 health so it will take fewer turns
 * for a SEED_SPITTER to kill it.
 * 
 * @author Ian Wong <ian.m.y.wong@gmail.com>
 * 
 */
public class RapidGhoul extends Zombie {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 896350080935086716L;

	/** The Constant SPEED. */
	public static final int SPEED = -30;

	/** The Constant HEALTH. */
	public static final int HEALTH = 50;

	/** The Constant DAMAGE. */
	public static final int DAMAGE = 5;

	/**
	 * Instantiates a new walking undead.
	 *
	 * @param row the row
	 * @param location the location
	 * @param offset the offset
	 */
	public RapidGhoul(int row, int location, int offset) {
		super(row, location, SPEED, HEALTH, DAMAGE, offset);
	}

}
