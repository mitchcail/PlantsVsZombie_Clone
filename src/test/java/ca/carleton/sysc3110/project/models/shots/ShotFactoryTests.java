package ca.carleton.sysc3110.project.models.shots;

import org.junit.Assert;
import org.junit.Test;

// TODO: Auto-generated Javadoc
/**
 * The Class ShotFactoryTests.
 * @author John Blackwood <john@xnor.ca>
 */
public class ShotFactoryTests {

	/**
	 * Test shot factory.
	 */
	@Test
	public void testShotFactory() {
		for (ShotType t : ShotType.values()) {
			if( t == ShotType.NONE )
				continue;
			
			Shot s = ShotFactory.createShot(t, 1, 2, 3);

			Assert.assertTrue(s.getClass().equals(t.getShotClass()));
		}
	}

}
