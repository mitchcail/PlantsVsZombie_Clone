package ca.carleton.sysc3110.project.models;

// TODO: Auto-generated Javadoc
/**
 * Represents possible states that the game can be in.
 * 
 * @author John Blackwood <john@xnor.ca>
 * @author Ian Wong <ian.m.y.wong@gmail.com>
 */
public enum GameState {

	/** The player wins. */
	PLAYER_WINS,
	/** The player loses. */
	PLAYER_LOSES,
	/** in progress. */
	IN_PROGRESS,
	/** waiting for a level. */
	NEED_LEVEL;
}
