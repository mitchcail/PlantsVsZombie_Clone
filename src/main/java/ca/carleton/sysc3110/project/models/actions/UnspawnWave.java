package ca.carleton.sysc3110.project.models.actions;

// TODO: Auto-generated Javadoc
/**
 * Instantiates a new action to despawn an existing Zombie Wave.
 *
 * @author Ian Wong <ian.m.y.wong@gmail.com>
 */
public class UnspawnWave extends Action 
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -8719982137824899408L;
	
	/** The last wave value. */
	public final long lastWave;
	
	/**
	 * Instantiates a new Ticking action.
	 *
	 * @param lastWave the lastWave value saved from Game class
	 */
	public UnspawnWave(long lastWave) {
		super(null, ActionType.UNSPAWN_WAVE);
		this.lastWave = lastWave;
	}
}
