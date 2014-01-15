package ca.carleton.sysc3110.project.models.actions;

import ca.carleton.sysc3110.project.models.shots.Shot;

// TODO: Auto-generated Javadoc
/**
 * The Class ShotMove.
 * @author John Blackwood <john@xnor.ca>
 * @author Ian Wong <ian.m.y.wong@gmail.com>
 */
public class ShotMove extends Action {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2244946401414702343L;

	/**
	 * Instantiates a new shot move.
	 *
	 * @param s the s
	 */
	public ShotMove(Shot s) {
		super(s, ActionType.SHOT_MOVE);
	}

}
