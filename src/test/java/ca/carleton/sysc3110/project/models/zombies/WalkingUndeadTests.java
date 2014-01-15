package ca.carleton.sysc3110.project.models.zombies;

import org.junit.Assert;
import org.junit.Test;

// TODO: Auto-generated Javadoc
/**
 * The Class WalkingUndeadTests.
 * @author John Blackwood <john@xnor.ca>
 */
public class WalkingUndeadTests {

	/**
	 * Test walking undead constructor.
	 */
	@Test
	public void testWalkingUndeadConstructor() {
		WalkingUndead w = new WalkingUndead(0, 1,0);
		Assert.assertTrue(w.getRow() == 0);
		Assert.assertTrue(w.getLocation() == 1);
		Assert.assertTrue(w.getDamage() == WalkingUndead.DAMAGE);
		Assert.assertTrue(w.getSpeed() == WalkingUndead.SPEED);
		Assert.assertTrue(w.getHealth() == WalkingUndead.HEALTH);
	}

}
