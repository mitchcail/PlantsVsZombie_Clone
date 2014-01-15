package ca.carleton.sysc3110.project.models.actions;

import org.junit.Assert;
import org.junit.Test;

import ca.carleton.sysc3110.project.models.zombies.ZombieType;


// TODO: Auto-generated Javadoc
/**
 * The Class ZombieSpawnTests.
 * @author John Blackwood <john@xnor.ca>
 */
public class ZombieSpawnTests {

	/**
	 * Test zombie spawn create.
	 */
	@Test
	public void testZombieSpawnCreate() {
		for (ZombieType t : ZombieType.values()) {
			ZombieSpawn z = new ZombieSpawn(0, 1, t);
			Assert.assertTrue(z.row == 0);
			Assert.assertTrue(z.offset == 1);
			Assert.assertTrue(z.zombieType.equals(t));
		}
	}

}
