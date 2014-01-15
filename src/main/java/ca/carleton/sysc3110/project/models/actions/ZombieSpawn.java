package ca.carleton.sysc3110.project.models.actions;

import ca.carleton.sysc3110.project.models.zombies.ZombieType;

// TODO: Auto-generated Javadoc
/**
 * The Class ZombieSpawn.
 * @author John Blackwood <john@xnor.ca>
 * @author Ian Wong <ian.m.y.wong@gmail.com>
 */
public class ZombieSpawn extends Action {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7163517446731269846L;

	/* (non-Javadoc)
	 * @see ca.carleton.sysc3110.project.models.actions.Action#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + offset;
		result = prime * result + row;
		result = prime * result + ((zombieType == null) ? 0 : zombieType.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see ca.carleton.sysc3110.project.models.actions.Action#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ZombieSpawn other = (ZombieSpawn) obj;
		if (offset != other.offset)
			return false;
		if (row != other.row)
			return false;
		if (zombieType != other.zombieType)
			return false;
		return true;
	}

	/** The row. */
	public final int row;

	/** The zombie type. */
	public final ZombieType zombieType;
	
	/** The location offset.*/
	public final int offset;

	/**
	 * Instantiates a new zombie spawn.
	 *
	 * @param row the row
	 * @param offset the offset
	 * @param type the type
	 */
	public ZombieSpawn(int row, int offset, ZombieType type) {
		super(null, ActionType.ZOMBIE_SPAWN);
		this.zombieType = type;
		this.row = row;
		this.offset = offset;
	}

}
