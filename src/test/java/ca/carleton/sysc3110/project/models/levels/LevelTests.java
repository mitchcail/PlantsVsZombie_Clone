package ca.carleton.sysc3110.project.models.levels;

import org.junit.Assert;
import org.junit.Test;

import ca.carleton.sysc3110.project.models.plants.PlantType;
import ca.carleton.sysc3110.project.models.zombies.ZombieType;

// TODO: Auto-generated Javadoc
/**
 * The Class LevelTests.
 * @author John Blackwood <john@xnor.ca>
 */
public class LevelTests {

	/**
	 * Test level constructor.
	 */
	@Test
	public void testLevelConstructor() {

		final ZombieType[] WAVES = { ZombieType.WALKING_UNDEAD, ZombieType.WALKING_UNDEAD, ZombieType.WALKING_UNDEAD, ZombieType.RAPID_GHOUL };
		final int[] WAVE_SPAWN = { 1, 1, 1, 1 };
		final int[] WAVE_DELAY = { Level.START_DELAY, 3, 3, 1 };
		final PlantType[] AVAILABLE_PLANTS = { PlantType.SUNFLOWER, PlantType.SEED_SPITTER };		

		Level l = new Level(1, true, 2, WAVES, WAVE_SPAWN, WAVE_DELAY, AVAILABLE_PLANTS, 1, 2);
		Assert.assertTrue(l.rows == 1);
		Assert.assertTrue(l.nightLength == 2);
		Assert.assertTrue(l.initialSunPoints == 1);
		Assert.assertTrue(l.offset == 2);
		Assert.assertTrue(l.hasNights == true);
		Assert.assertTrue(l.rowEnabled[0] == false);
		Assert.assertTrue(l.rowEnabled[1] == false);
		Assert.assertTrue(l.rowEnabled[2] == true);
		Assert.assertTrue(l.rowEnabled[3] == false);
		Assert.assertTrue(l.rowEnabled[4] == false);

		l = new Level(2, false, 2, WAVES, WAVE_SPAWN, WAVE_DELAY, AVAILABLE_PLANTS, 1, 2);
		Assert.assertTrue(l.hasNights == false);
		Assert.assertTrue(l.rowEnabled[0] == false);
		Assert.assertTrue(l.rowEnabled[1] == true);
		Assert.assertTrue(l.rowEnabled[2] == true);
		Assert.assertTrue(l.rowEnabled[3] == false);
		Assert.assertTrue(l.rowEnabled[4] == false);

		l = new Level(3, true, 2, WAVES, WAVE_SPAWN, WAVE_DELAY, AVAILABLE_PLANTS, 1, 2);
		Assert.assertTrue(l.rowEnabled[0] == false);
		Assert.assertTrue(l.rowEnabled[1] == true);
		Assert.assertTrue(l.rowEnabled[2] == true);
		Assert.assertTrue(l.rowEnabled[3] == true);
		Assert.assertTrue(l.rowEnabled[4] == false);

		l = new Level(4, true, 2, WAVES, WAVE_SPAWN, WAVE_DELAY, AVAILABLE_PLANTS, 1, 2);
		Assert.assertTrue(l.rowEnabled[0] == true);
		Assert.assertTrue(l.rowEnabled[1] == true);
		Assert.assertTrue(l.rowEnabled[2] == true);
		Assert.assertTrue(l.rowEnabled[3] == true);
		Assert.assertTrue(l.rowEnabled[4] == false);

		l = new Level(5, true, 2, WAVES, WAVE_SPAWN, WAVE_DELAY, AVAILABLE_PLANTS, 1, 2);
		Assert.assertTrue(l.rowEnabled[0] == true);
		Assert.assertTrue(l.rowEnabled[1] == true);
		Assert.assertTrue(l.rowEnabled[2] == true);
		Assert.assertTrue(l.rowEnabled[3] == true);
		Assert.assertTrue(l.rowEnabled[4] == true);

	}

}
