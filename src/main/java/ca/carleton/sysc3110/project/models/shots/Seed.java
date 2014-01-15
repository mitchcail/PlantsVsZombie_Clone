package ca.carleton.sysc3110.project.models.shots;


// TODO: Auto-generated Javadoc
/**
 * The Class Seed. A specialized type of shot (aka bullet, seed) that deals
 * damage to any zombie it comes into contact with. The shot will travel at the
 * same speed at the zombies but in the reverse direction.
 * 
 * @author John Blackwood <john@xnor.ca>
 * @author Ian Wong <ian.m.y.wong@gmail.com>
 */
public class Seed extends Shot {


	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 7995371195481341456L;
	
	/** The Constant SPEED. */
	public static final int SPEED = 25;

	/**
	 * Instantiates a new seed.
	 * 
	 * @param row
	 *            the row
	 * @param location
	 *            the location
	 * @param damage
	 *            the damage
	 */
	public Seed(int row, int location, int damage) {
		super(row, location, SPEED, damage);
	}

}
