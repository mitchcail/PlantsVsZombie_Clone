package ca.carleton.sysc3110.project.models.actions;

import ca.carleton.sysc3110.project.models.zombies.Zombie;

// TODO: Auto-generated Javadoc
/**
 * Zombie move action class.
 * @author John Blackwood <john@xnor.ca>
 * @author Ian Wong <ian.m.y.wong@gmail.com>
 */
public class ZombieMove extends Action {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 4184715998809794849L;

	/**
	 * Instantiates a new zombie move action.
	 *
	 * @param source the source
	 */
	public ZombieMove(Zombie source) {
		super(source, ActionType.ZOMBIE_MOVE);
	}

}
