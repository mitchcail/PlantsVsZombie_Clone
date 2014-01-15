package ca.carleton.sysc3110.project.models.plants;

import org.junit.Assert;
import org.junit.Test;

import ca.carleton.sysc3110.project.models.shots.ShotType;

// TODO: Auto-generated Javadoc
/**
 * The Class PlantTests.
 * @author John Blackwood <john@xnor.ca>
 */
public class PlantTests {

	/**
	 * Test plant create.
	 */
	@Test
	public void testPlantCreate() {
		for (ShotType t : ShotType.values()) {
			Plant p = new Plant(0, 1, 2, 3, 4, 5, 6, 7, t);
			Assert.assertTrue(p.getRow() == 0);
			Assert.assertTrue(p.getLocation() == 1);
			Assert.assertTrue(p.getHealth() == 2);
			Assert.assertTrue(p.getDamage() == 3);
			Assert.assertTrue(p.getSunCost() == 4);
			Assert.assertTrue(p.getCooldown() == 5);
			Assert.assertTrue(p.getShotCooldown() == 6);
			Assert.assertTrue(p.getSunUpkeep() == 7);
			Assert.assertTrue(p.getShotType().equals(t));

		}
	}

	/**
	 * Test plant shooting.
	 */
	@Test
	public void testPlantShooting() {
		for (ShotType t : ShotType.values()) {
			Plant p = new Plant(0, 1, 2, 3, 4, 5, 6, 7, t);
			
			for( int i = 0; i < p.getShotCooldown(); i++ ) {
				Assert.assertFalse(p.shouldShoot());
				p.incrementShotTimer();
			}
			
			Assert.assertTrue(p.shouldShoot());
			p.resetShotTimer();
			Assert.assertFalse(p.shouldShoot());

		}
	}


}
