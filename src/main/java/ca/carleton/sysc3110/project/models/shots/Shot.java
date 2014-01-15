package ca.carleton.sysc3110.project.models.shots;

import ca.carleton.sysc3110.project.models.BoardElement;

// TODO: Auto-generated Javadoc
/**
 * The Class Shot. The WIDTH constant is specific to the seed shot. It's 0
 * because the idea is that the seed will hit the zombie centrally as opposed to
 * hitting the front of the zombie box, it is a measure of future
 * implementations and not entirely pertinent to the text based version. Health
 * is another constant used to initialize the health variable in the parent
 * class. It's arbitrarily set to anything higher than 0 because it should die
 * on impact with a zombie.
 * 
 * @author John Blackwood <john@xnor.ca>
 * @author Ian Wong <ian.m.y.wong@gmail.com>
 * 
 */
public class Shot extends BoardElement {


	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7950817811208199651L;

	/** The Constant WIDTH. */
	public static final int WIDTH = 20;

	/** The Constant HEALTH. */
	public static final int HEALTH = 1;

	/**
	 * Instantiates a new shot.
	 * 
	 * @param row
	 *            the row
	 * @param location
	 *            the location
	 * @param speed
	 *            the speed
	 * @param damage
	 *            the damage
	 */
	public Shot(int row, int location, int speed, int damage) {
		super(row, location, WIDTH, speed, HEALTH, damage);
	}

}
