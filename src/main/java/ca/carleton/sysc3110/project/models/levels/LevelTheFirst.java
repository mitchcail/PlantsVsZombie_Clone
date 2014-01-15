package ca.carleton.sysc3110.project.models.levels;

import ca.carleton.sysc3110.project.models.plants.PlantType;
import ca.carleton.sysc3110.project.models.zombies.ZombieType;

// TODO: Auto-generated Javadoc
/**
 *  The first level serves as a simple tutorial
 * level with only one single lane of 800 units length (eight squares). This
 * level will contain no night time (all sunny). There will be three waves all
 * of which will have the basic zombie type (WALKING_DEAD). The waves will
 * spawn: 1, 1, and then 2 zombies. There will be a 5 turn (START_DELAY) for the
 * first wave, 3 for the second, and 3 for the last one. The level restricts the
 * players to using a certain arsenal of plants. Because it the tutorial level
 * the players only have access to the SUNFLOWER and the SEED_SPITTER. The
 * player will start with 25 sun points at the beginning of the level.
 * 
 * @author John Blackwood <john@xnor.ca>
 * @author Nikola Neskovic <nick_neskov@hotmail.com>
 * @author Ian Wong <ian.m.y.wong@gmail.com>
 */
public class LevelTheFirst extends Level {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 4023248814055582185L;

	/** The Constant ROWS. */
	public static final int ROWS = 1;

	// TODO: Still to be implemented
	/** The Constant HAS_NIGHTS. */
	public static final boolean HAS_NIGHTS = false;

	/** The Constant NIGHT_LENGTH. */
	public static final int NIGHT_LENGTH = 0;

	/** The Constant WAVES. */
	public static final ZombieType[] WAVES = { ZombieType.WALKING_UNDEAD, ZombieType.WALKING_UNDEAD,
			ZombieType.WALKING_UNDEAD, ZombieType.RAPID_GHOUL };
	
	/** The Constant WAVE_SPAWN. */
	public static final int[] WAVE_SPAWN = { 1, 1, 1, 1 };

	/** The Constant WAVE_DELAY. */
	public static final int[] WAVE_DELAY = { Level.START_DELAY, 3, 3, 1};

	/** The Constant AVAILABLE_PLANTS. */
	public static final PlantType[] AVAILABLE_PLANTS = { PlantType.SUNFLOWER, PlantType.SEED_SPITTER };

	/** The Constant INITIAL_SUN_POINTS. */
	public static final int INITIAL_SUN_POINTS = 50;
	
	/** The offset, directly related to number of rows. */
	public static final int OFFSET = 3;

	/**
	 * Instantiates a new level the first.
	 */
	public LevelTheFirst() {
		super(ROWS, HAS_NIGHTS, NIGHT_LENGTH, WAVES, WAVE_SPAWN, WAVE_DELAY, AVAILABLE_PLANTS,
				INITIAL_SUN_POINTS, OFFSET);
	}

}
