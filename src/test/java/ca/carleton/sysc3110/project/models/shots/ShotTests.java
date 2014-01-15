package ca.carleton.sysc3110.project.models.shots;

import org.junit.Assert;
import org.junit.Test;

// TODO: Auto-generated Javadoc
/**
 * The Class ShotTests.
 * @author John Blackwood <john@xnor.ca>
 */
public class ShotTests {

	/**
	 * Test seed constructor.
	 */
	@Test
	public void testSeedConstructor() {
		Shot s = new Shot(0, 1, 2, 3);

		Assert.assertTrue(s.getRow() == 0);
		Assert.assertTrue(s.getLocation() == 1);
		Assert.assertTrue(s.getSpeed() == 2);
		Assert.assertTrue(s.getDamage() == 3);
		Assert.assertTrue(s.getHealth() == Shot.HEALTH);
		Assert.assertTrue(s.getWidth() == Shot.WIDTH);

	}

}
