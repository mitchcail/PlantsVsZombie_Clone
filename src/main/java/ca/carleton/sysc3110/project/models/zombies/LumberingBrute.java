package ca.carleton.sysc3110.project.models.zombies;

// TODO: Auto-generated Javadoc
/**
 * The Class Lumbering Brute. The slow and steady zombie has a speed of 10 units.
 * The zombie does 50 damage so it can kill almost any plant in a single turn.
 * The zombie has 700 health so it will take a long time for a SEED_SPITTER to kill it.
 * 
 * @author Ian Wong <ian.m.y.wong@gmail.com>
 */
public class LumberingBrute extends Zombie {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5072450804563833830L;

	/** The Constant SPEED. */
	public static final int SPEED = -10;

	/** The Constant HEALTH. */
	public static final int HEALTH = 700;

	/** The Constant DAMAGE. */
	public static final int DAMAGE = 50;

	/**
	 * Instantiates a new walking undead.
	 *
	 * @param row the row
	 * @param location the location
	 * @param offset the offset
	 */
	public LumberingBrute(int row, int location, int offset) 
	{
		super(row, location, SPEED, HEALTH, DAMAGE, offset);
	}

}
