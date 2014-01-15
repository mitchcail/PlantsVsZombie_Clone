package ca.carleton.sysc3110.project.models.plants;

// TODO: Auto-generated Javadoc
/**
 * A factory for creating Plant objects.
 *
 * Currently produces SUNFLOWER, and SEED_SPITTER plants
 * 
 * @author John Blackwood <john@xnor.ca>
 * @author Nikola Neskovic <nick_neskov@hotmail.com>
 * @author Ian Wong <ian.m.y.wong@gmail.com>
 */
public class PlantFactory {
	
	/**
	 * Creates a new Plant object.
	 *
	 * @param plantType the plant type
	 * @param row the row
	 * @param location the location
	 * @return the plant
	 */
	public static Plant createPlant(PlantType plantType, int row, int location) {
		switch(plantType) {
		case SUNFLOWER:
			return new Sunflower(row, location);
		case SEED_SPITTER:
			return new SeedSpitter(row, location);
		case POTATO:
			return new Potato(row, location);
		default:
			return null;
		}
	}
}
