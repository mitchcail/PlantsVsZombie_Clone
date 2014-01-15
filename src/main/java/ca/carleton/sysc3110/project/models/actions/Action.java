package ca.carleton.sysc3110.project.models.actions;

import java.io.Serializable;

import ca.carleton.sysc3110.project.models.BoardElement;

// TODO: Auto-generated Javadoc
/**
 * The Class Action. The main variables of the Action Class are the source that
 * creates the action and the type of action that was created (move, eat, shoot,
 * etc).
 * 
 * @author John Blackwood <john@xnor.ca>
 * @author Nikola Neskovic <nick_neskov@hotmail.com>
 * @author Ian Wong <ian.m.y.wong@gmail.com>
 */
public class Action implements Comparable<Action>, Serializable {

	/** The Serial ID. */
	private static final long serialVersionUID = 4076834818949508460L;

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((source == null) ? 0 : source.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Action other = (Action) obj;
		if (source == null) {
			if (other.source != null)
				return false;
		} else if (!source.equals(other.source))
			return false;
		if (type != other.type)
			return false;
		return true;
	}

	/** The source of the action. */
	private BoardElement source;

	/** The type of action. */
	private ActionType type;

	/**
	 * Instantiates a new action.
	 * 
	 * @param source
	 *            the source
	 * @param type
	 *            the type
	 */
	public Action(BoardElement source, ActionType type) {
		this.source = source;
		this.type = type;
	}

	/**
	 * Gets the source.
	 *
	 * @return the source
	 */
	public BoardElement getSource() {
		return this.source;
	}

	/**
	 * Gets the action type.
	 *
	 * @return the action type
	 */
	public ActionType getActionType() {
		return this.type;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Action o) {
		// TODO compare by time once that's implemented
		return 0;
	}

}
