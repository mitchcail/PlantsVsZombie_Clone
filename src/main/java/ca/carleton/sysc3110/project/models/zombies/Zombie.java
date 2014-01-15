package ca.carleton.sysc3110.project.models.zombies;

import ca.carleton.sysc3110.project.models.BoardElement;

// TODO: Auto-generated Javadoc
/**
 * The Class Zombie.
 * 
 * @author John Blackwood <john@xnor.ca>
 * @author Ian Wong <ian.m.y.wong@gmail.com>
 */
public class Zombie extends BoardElement {


	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 6488015468086753120L;
	
	/** The Constant WIDTH. */
	public static final int WIDTH = 100;
	
	/** The offset. */
	private int offset;
	
	/**
	 * Gets the offset.
	 *
	 * @return the offset
	 */
	public int getOffset() {
		return offset;
	}

	/**
	 * Sets the offset.
	 *
	 * @param offset the new offset
	 */
	public void setOffset(int offset) {
		this.offset = offset;
	}

	/**
	 * Instantiates a new zombie.
	 *
	 * @param row the row
	 * @param location the location
	 * @param speed the speed
	 * @param health the health
	 * @param damage the damage
	 * @param offset the offset
	 */
	public Zombie(int row, int location, int speed, int health, int damage, int offset) {
		super(row, location, WIDTH, speed, health, damage);
		this.offset = offset;
	}

}
