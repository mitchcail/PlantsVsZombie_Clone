package ca.carleton.sysc3110.project.models.actions;

import ca.carleton.sysc3110.project.models.BoardElement;

// TODO: Auto-generated Javadoc
/**
 * Damage undoing action class.
 * 
 * @author Ian Wong <ian.m.y.wong@gmail.com>
 */
public class CollisionUndo extends Action {
	
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 531113110493040631L;

	/**
	 * Instantiates a new undo damage action.
	 *
	 * @param source the source
	 */
	public CollisionUndo(BoardElement source) {
		super(source, ActionType.COLLISION_UNDO);
	}

}
