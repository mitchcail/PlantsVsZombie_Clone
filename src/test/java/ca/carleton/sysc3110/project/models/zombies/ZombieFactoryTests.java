package ca.carleton.sysc3110.project.models.zombies;

import org.junit.Assert;
import org.junit.Test;


// TODO: Auto-generated Javadoc
/**
 * The Class ZombieFactoryTests.
 * @author John Blackwood <john@xnor.ca>
 */
public class ZombieFactoryTests {
	
	/**
	 * Test zombie factory.
	 */
	@Test
	public void testZombieFactory() {
		for (ZombieType t : ZombieType.values()) {
			Zombie z = ZombieFactory.createZombie(t, 1, 2, 0);

			Assert.assertTrue(z.getClass().equals(t.getZombieClass()));
		}
	}

}
