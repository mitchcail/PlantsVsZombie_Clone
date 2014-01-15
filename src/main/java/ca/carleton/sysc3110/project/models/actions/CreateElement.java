package ca.carleton.sysc3110.project.models.actions;

import ca.carleton.sysc3110.project.models.BoardElement;

// TODO: Auto-generated Javadoc
/**
 * Element creation action class.
 * 
 * @author Ian Wong <ian.m.y.wong@gmail.com>
 */
public class CreateElement extends Action {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1115927073727587236L;

	/**
	 * Instantiates a new undo damage action.
	 *
	 * @param source the source
	 */
	public CreateElement(BoardElement source) {
		super(source, ActionType.CREATE_ELEMENT);
	}

}