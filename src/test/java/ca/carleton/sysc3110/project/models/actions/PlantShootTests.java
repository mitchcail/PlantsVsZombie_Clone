package ca.carleton.sysc3110.project.models.actions;

import org.junit.Assert;
import org.junit.Test;

import ca.carleton.sysc3110.project.models.plants.Plant;
import ca.carleton.sysc3110.project.models.plants.PlantFactory;
import ca.carleton.sysc3110.project.models.plants.PlantType;

// TODO: Auto-generated Javadoc
/**
 * The Class PlantShoot.
 * @author John Blackwood <john@xnor.ca>
 */
public class PlantShootTests {

	
	
	/**
	 * Test plant shoot.
	 */
	@Test
	public void testPlantShoot() {

		for (PlantType t : PlantType.values()) {
			if( t == PlantType.NONE )
				continue;
			
			Plant plant = PlantFactory.createPlant(t, 1, 2);
			PlantShoot s = new PlantShoot(plant);
			
			Assert.assertTrue(s.location == Plant.PLANT_WIDTH + 2);
			Assert.assertTrue(s.row == 1);
			Assert.assertTrue(s.damage == plant.getDamage());
			Assert.assertTrue(s.shotType.equals(plant.getShotType()));
		}
	}

}
