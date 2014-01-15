package ca.carleton.sysc3110.project.models.actions;

// TODO: Auto-generated Javadoc
/**
 * The Enum ActionType.
 * @author John Blackwood <john@xnor.ca>
 * @author Ian Wong <ian.m.y.wong@gmail.com>
 */
public enum ActionType {

	/** create a plant. */
	PLANT_CREATE,
	
	/** a plant shoots. */
	PLANT_SHOOT,
	
	/** a zombie moves. */
	ZOMBIE_MOVE,
	
	/** a zombie spawns. */
	ZOMBIE_SPAWN,
	
	/** a shot moves. */
	SHOT_MOVE,
	
	/** a zombie dies. */
	ZOMBIE_REMOVE,
	
	/** a plant dies. */
	PLANT_REMOVE,
	
	/** a shot dies. */
	SHOT_REMOVE,
	
	/** tick action. */
	TICK_ACTION,
	
	/** unspawn a spawned wave. */
	UNSPAWN_WAVE, 
	
	/** recall the amount of Sun lost due to plants. */
	RECALL_PLANT,
	
	/** undo the damage dealt to a boardElement. */
	COLLISION_UNDO,
	
	/** undoes the creation of an element. */
	CREATE_ELEMENT;
}