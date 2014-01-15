package ca.carleton.sysc3110.project.models.actions;

import ca.carleton.sysc3110.project.models.plants.PlantType;

// TODO: Auto-generated Javadoc
/**
 * The Class PlantCreate.
 * @author John Blackwood <john@xnor.ca>
 * @author Ian Wong <ian.m.y.wong@gmail.com>
 */
public class PlantCreate extends Action {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 9126186116327236771L;

	/* (non-Javadoc)
	 * @see ca.carleton.sysc3110.project.models.actions.Action#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + location;
		result = prime * result + ((plantType == null) ? 0 : plantType.hashCode());
		result = prime * result + row;
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
		PlantCreate other = (PlantCreate) obj;
		if (location != other.location)
			return false;
		if (plantType != other.plantType)
			return false;
		if (row != other.row)
			return false;
		return true;
	}

	/** The plant type. */
	public final PlantType plantType;

	/** The row. */
	public final int row;

	/** The location. */
	public final int location;

	/**
	 * Instantiates a new plant create.
	 * 
	 * @param plantType
	 *            the plant type
	 * @param row
	 *            the row
	 * @param location
	 *            the location
	 */
	public PlantCreate(PlantType plantType, int row, int location) {
		super(null, ActionType.PLANT_CREATE);
		this.plantType = plantType;
		this.row = row;
		this.location = location;
	}

}
