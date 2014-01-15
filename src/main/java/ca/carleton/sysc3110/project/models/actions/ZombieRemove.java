package ca.carleton.sysc3110.project.models.actions;

import ca.carleton.sysc3110.project.models.zombies.Zombie;

// TODO: Auto-generated Javadoc
/**
 * Zombie remove action class.
 * @author Ian Wong <ian.m.y.wong@gmail.com>
 */
public class ZombieRemove extends Action {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -6673304969616450013L;

	/**
	 * Instantiates a new zombie remove action.
	 *
	 * @param source the source
	 */
	public ZombieRemove(Zombie source) {
		super(source, ActionType.ZOMBIE_REMOVE);
	}

}
