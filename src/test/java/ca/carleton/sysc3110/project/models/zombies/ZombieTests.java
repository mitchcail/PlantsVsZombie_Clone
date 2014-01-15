package ca.carleton.sysc3110.project.models.zombies;

import org.junit.Assert;
import org.junit.Test;


// TODO: Auto-generated Javadoc
/**
 * The Class ZombieTests.
 * @author John Blackwood <john@xnor.ca>
 */
public class ZombieTests {
	
	/**
	 * Test zombie constructor.
	 */
	@Test
	public void testZombieConstructor() {
		Zombie w = new Zombie(0, 1, 2, 3, 4, 0);
		Assert.assertTrue(w.getRow() == 0);
		Assert.assertTrue(w.getLocation() == 1);
		Assert.assertTrue(w.getSpeed() == 2);
		Assert.assertTrue(w.getHealth() == 3);
		Assert.assertTrue(w.getDamage() == 4);
		Assert.assertTrue(w.getWidth() == Zombie.WIDTH);
	}

}
