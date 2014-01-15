package ca.carleton.sysc3110.project.models;

import java.io.Serializable;

import ca.carleton.sysc3110.project.models.actions.Action;

// TODO: Auto-generated Javadoc
/**
 * The board element acts as the parent class for every active element on the board such as the
 * zombies, plants, and seeds (bullets). All active elements have characteristics such as
 * move speed (positive for bullets, negative for zombies, zero for plants), health (capacity 
 * to receive damage from
 * (enemy units) and damage (health reduction for other elements it collides with). Furthermore,
 * elements are placed in specific lane and and starting location. Width is an implementation
 * characteristic used for programming the unit collision. The are four important methods called
 * actionPerformed, hit, move, compareTo. Hit and move are the most simple to explain. Hit is called
 * when the game deduces that an element has been hit (zombie hit by seed bullet, plant being eaten),
 * and subtracts a given amount of damage from the elements health. Move is called methodically relative
 * game tick counter and just moves the zombies towards and plants and the bullets towards the zombies. 
 * The actionPerformed method is meant to the deal with a variety of Action object that cover such things 
 * as creating/moving a zombie, moving a seed, creating/shooting a plant. CompareTo is an overridden 
 * method from the interface Comparable used to compare board elements. The comparison is based on 
 * the position of the elements so the comparator will sort the element according to which is farther left
 * (closest to the house).
 * 
 * @author John Blackwood <john@xnor.ca>
 * @author Nikola Neskovic <nick_neskov@hotmail.com>
 * @author Ian Wong <ian.m.y.wong@gmail.com>
 */
public class BoardElement implements Comparable<BoardElement>, Serializable {

	/** The Serial ID. */
	private static final long serialVersionUID = -2432605453926552838L;

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + damage;
		result = prime * result + health;
		result = prime * result + id;
		result = prime * result + location;
		result = prime * result + row;
		result = prime * result + speed;
		result = prime * result + width;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BoardElement other = (BoardElement) obj;
		if (damage != other.damage)
			return false;
		if (health != other.health)
			return false;
		if (location != other.location)
			return false;
		if (row != other.row)
			return false;
		if (speed != other.speed)
			return false;
		if (width != other.width)
			return false;
		return true;
	}

	/** The number of elements. */
	protected static int numElements = 0;

	/**
	 * Gets the num elements.
	 *
	 * @return the num elements
	 */
	public static int getNumElements(){return numElements;}
	
	/**
	 * Sets the num elements.
	 *
	 * @param n the new num elements
	 */
	public static void setNumElements(int n){numElements = n;}

	/** The health. */
	protected int health;

	/** The location. */
	protected int location;
	
	/** The unique ID of the element. */
	protected int id;

	/** The row. */
	protected int row;

	/** The speed. */
	protected int speed;

	/** The width. */
	protected final int width;

	/** The damage. */
	protected final int damage;

	/**
	 * Instantiates a new board element.
	 * 
	 * @param row
	 *            the row
	 * @param location
	 *            the location
	 * @param width
	 *            the width
	 * @param speed
	 *            the speed
	 * @param health
	 *            the health
	 * @param damage
	 *            the damage
	 */
	public BoardElement(int row, int location, int width, int speed, int health, int damage) {
		BoardElement.numElements++;
		this.row = row;
		this.location = location;
		this.width = width;
		this.speed = speed;
		this.health = health;
		this.damage = damage;
		this.id = BoardElement.numElements;
	}
	
	/**
	 * Gets the id.
	 * 
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Gets the damage.
	 * 
	 * @return the damage
	 */
	public int getDamage() {
		return damage;
	}

	/**
	 * Gets the health.
	 * 
	 * @return the health
	 */
	public int getHealth() {
		return health;
	}

	/**
	 * Gets the location.
	 * 
	 * @return the location
	 */
	public int getLocation() {
		return location;
	}

	/**
	 * Gets the row.
	 * 
	 * @return the row
	 */
	public int getRow() {
		return row;
	}

	/**
	 * Gets the speed.
	 * 
	 * @return the speed
	 */
	public int getSpeed() {
		return speed;
	}

	/**
	 * Gets the width.
	 * 
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Sets the health.
	 * 
	 * @param health
	 *            the new health
	 */
	public void setHealth(int health) {
		this.health = health;
	}

	/**
	 * Sets the location.
	 * 
	 * @param location
	 *            the new location
	 */
	public void setLocation(int location) {
		this.location = location;
	}

	/**
	 * Sets the row.
	 * 
	 * @param row
	 *            the new row
	 */
	public void setRow(int row) {
		this.row = row;
	}

	/**
	 * Sets the speed.
	 * 
	 * @param speed
	 *            the new speed
	 */
	public void setSpeed(int speed) {
		this.speed = speed;
	}

	/**
	 * Perform action.
	 * 
	 * @param a
	 *            the a
	 * @return the action
	 */
	public Action performAction(Action a) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(BoardElement o) {
		// changed from this.location + this.width, o.location + o.width
		return Integer.compare(this.location, o.location);
	}

	/**
	 * Hit.
	 * 
	 * @param damage
	 *            the damage
	 * @return true, if successful
	 */
	public boolean hit(int damage) {
		this.health -= damage;
		if (health <= 0) {
			return false;
		}
		return true;
	}

	/**
	 * Move.
	 */
	public void move() {
		this.location += this.speed * Game.TICK_SCALE;
	}

}
