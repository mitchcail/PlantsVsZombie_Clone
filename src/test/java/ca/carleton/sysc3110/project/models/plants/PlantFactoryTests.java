package ca.carleton.sysc3110.project.models.plants;

import org.junit.Assert;
import org.junit.Test;

// TODO: Auto-generated Javadoc
/**
 * The Class PlantFactoryTests.
 * @author John Blackwood <john@xnor.ca>
 */
public class PlantFactoryTests {

	/**
	 * Test plant factory.
	 */
	@Test
	public void testPlantFactory() {
		for (PlantType t : PlantType.values()) {
			if( t == PlantType.NONE )
				continue;
			
			Plant p = PlantFactory.createPlant(t, 1, 2);
			Assert.assertTrue(p.getClass().equals(t.getPlantClass()));
		}
	}
}
