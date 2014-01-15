package ca.carleton.sysc3110.project.models.actions;

import ca.carleton.sysc3110.project.models.plants.Plant;

// TODO: Auto-generated Javadoc
/**
 * Instantiates a new action to undo the amount of Sun Points removed due to plants
 * and decrement the tick timer if it didn't overflow.
 * 
 * @author Ian Wong <ian.m.y.wong@gmail.com>
 *
 */
public class RecallPlantGeneration extends Action 
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5267973655464801205L;

	/**
	 * Instantiates a new recall plant sun generation action.
	 *
	 * @param source the source
	 */
	public RecallPlantGeneration(Plant source) {
		super(source, ActionType.RECALL_PLANT);
	}

}