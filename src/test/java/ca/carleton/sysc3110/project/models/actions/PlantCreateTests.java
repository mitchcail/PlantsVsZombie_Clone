package ca.carleton.sysc3110.project.models.actions;

import org.junit.Assert;
import org.junit.Test;

import ca.carleton.sysc3110.project.models.plants.PlantType;

// TODO: Auto-generated Javadoc
/**
 * The Class PlantCreateTests.
 * @author John Blackwood <john@xnor.ca>
 */
public class PlantCreateTests {
	
	/**
	 * Test plant create.
	 */
	@Test
	public void testPlantCreate() {

		for (PlantType t : PlantType.values()) {
			PlantCreate p = new PlantCreate(t, 1, 2);
			Assert.assertTrue(p.location == 2);
			Assert.assertTrue(p.row == 1);
			Assert.assertTrue(p.plantType.equals(t));

			PlantCreate p2 = new PlantCreate(t, 1, 2);
			Assert.assertTrue(p.equals(p2));
		}
	}
}
