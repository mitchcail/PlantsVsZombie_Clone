package ca.carleton.sysc3110.project.models.shots;

import org.junit.Assert;
import org.junit.Test;

// TODO: Auto-generated Javadoc
/**
 * The Class SeedTests.
 * @author John Blackwood <john@xnor.ca>
 */
public class SeedTests {

	/**
	 * Test seed constructor.
	 */
	@Test
	public void testSeedConstructor() {
		Seed s = new Seed(0, 1, 2);

		Assert.assertTrue(s.getRow() == 0);
		Assert.assertTrue(s.getLocation() == 1);
		Assert.assertTrue(s.getDamage() == 2);
	}

}
