package ca.carleton.sysc3110.project.models.actions;

import ca.carleton.sysc3110.project.models.shots.Shot;

// TODO: Auto-generated Javadoc
/**
 * Shot remove action class.
 * @author Ian Wong <ian.m.y.wong@gmail.com>
 */
public class ShotRemove extends Action {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 960657747851317491L;

	/**
	 * Instantiates a new Shot remove action.
	 *
	 * @param source the source
	 */
	public ShotRemove(Shot source) {
		super(source, ActionType.SHOT_REMOVE);
	}

}
