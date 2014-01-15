package ca.carleton.sysc3110.project.models.actions;

import ca.carleton.sysc3110.project.models.plants.Plant;
import ca.carleton.sysc3110.project.models.shots.ShotType;

// TODO: Auto-generated Javadoc
/**
 * The Class PlantShoot.
 * @author John Blackwood <john@xnor.ca>
 * @author Ian Wong <ian.m.y.wong@gmail.com>
 */
public class PlantShoot extends Action {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -3198190992452713484L;

	/* (non-Javadoc)
	 * @see ca.carleton.sysc3110.project.models.actions.Action#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + damage;
		result = prime * result + location;
		result = prime * result + row;
		result = prime * result + ((shotType == null) ? 0 : shotType.hashCode());
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
		PlantShoot other = (PlantShoot) obj;
		if (damage != other.damage)
			return false;
		if (location != other.location)
			return false;
		if (row != other.row)
			return false;
		if (shotType != other.shotType)
			return false;
		return true;
	}

	/** The shot type. */
	public final ShotType shotType; // enum with SEED, NONE

	/** The location. */
	public final int location;

	/** The row. */
	public final int row;

	/** The damage. */
	public final int damage;

	/**
	 * Instantiates a new plant shoot.
	 * 
	 * @param p
	 *            the p
	 */
	public PlantShoot(Plant p) {
		super(p, ActionType.PLANT_SHOOT);
		this.shotType = p.getShotType();
		this.row = p.getRow();
		this.location = p.getLocation() + p.getWidth();
		this.damage = p.getDamage();
	}

}
