package ca.carleton.sysc3110.project.models.actions;

import ca.carleton.sysc3110.project.models.plants.Plant;

// TODO: Auto-generated Javadoc
/**
 * Plant remove action class.
 * @author John Blackwood <john@xnor.ca>
 * @author Ian Wong <ian.m.y.wong@gmail.com>
 */
public class PlantRemove extends Action {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 151267190653322689L;

	/**
	 * Instantiates a new Plant remove action.
	 *
	 * @param source the source
	 */
	public PlantRemove(Plant source) {
		super(source, ActionType.PLANT_REMOVE);
	}
}
