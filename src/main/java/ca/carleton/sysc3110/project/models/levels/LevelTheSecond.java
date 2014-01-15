package ca.carleton.sysc3110.project.models.levels;

import ca.carleton.sysc3110.project.models.plants.PlantType;
import ca.carleton.sysc3110.project.models.zombies.ZombieType;

// TODO: Auto-generated Javadoc
/**
 *  The second level serves as a robust testing of all 5 lanes. This
 * level will contain no night time (all sunny still). There will be five waves all
 * of which will have the basic zombie type (WALKING_DEAD). The waves will
 * spawn: 1, 1, and then 2 zombies. There will be a 5 turn (START_DELAY) for the
 * first wave, 3 for the second, and 3 for the last one. The level restricts the
 * players to using a certain arsenal of plants. Because it the tutorial level
 * the players only have access to the SUNFLOWER and the SEED_SPITTER. The
 * player will start with 25 sun points at the beginning of the level.
 * @author Ian Wong <ian.m.y.wong@gmail.com>
 */
public class LevelTheSecond extends Level {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -866905231502279881L;

	/** The Constant ROWS. */
	public static final int ROWS = 3;

	// TODO: Still to be implemented
	/** The Constant HAS_NIGHTS. */
	public static final boolean HAS_NIGHTS = false;

	/** The Constant NIGHT_LENGTH. */
	public static final int NIGHT_LENGTH = 0;

	/** The Constant WAVES. */
	public static final ZombieType[] WAVES = { ZombieType.WALKING_UNDEAD, ZombieType.RAPID_GHOUL,
			ZombieType.WALKING_UNDEAD, ZombieType.RAPID_GHOUL, ZombieType.LUMBERING_BRUTE };
	
	/** The Constant WAVE_SPAWN. */
	public static final int[] WAVE_SPAWN = { 3, 2, 3, 1, 1};

	/** The Constant WAVE_DELAY. */
	public static final int[] WAVE_DELAY = { 8, 3, 4, 3, 6};

	/** The Constant AVAILABLE_PLANTS. */
	public static final PlantType[] AVAILABLE_PLANTS = { PlantType.SUNFLOWER, PlantType.SEED_SPITTER, PlantType.POTATO };

	/** The Constant INITIAL_SUN_POINTS. */
	public static final int INITIAL_SUN_POINTS = 50;
	
	/** The offset. */
	public static final int OFFSET = 2;

	/**
	 * Instantiates a new level the first.
	 */
	public LevelTheSecond() {
		super(ROWS, HAS_NIGHTS, NIGHT_LENGTH, WAVES, WAVE_SPAWN, WAVE_DELAY, AVAILABLE_PLANTS,
				INITIAL_SUN_POINTS, OFFSET);
	}

}
