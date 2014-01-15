package ca.carleton.sysc3110.project.models.plants;

// TODO: Auto-generated Javadoc
/**
 * The Enum PlantType.
 * 
 * @author John Blackwood <john@xnor.ca>
 * @author Ian Wong <ian.m.y.wong@gmail.com>
 */
public enum PlantType {
	
	/** The none. */
	NONE, 
 /** The sunflower. */
 SUNFLOWER, 
 /** The seed spitter. */
 SEED_SPITTER, 
 /** The potato. */
 POTATO;

	/**
	 * Gets the plant class.
	 *
	 * @return the plant class
	 */
	public Class<? extends Plant> getPlantClass() {
		switch (this) {
		case SUNFLOWER:
			return Sunflower.class;
		case SEED_SPITTER:
			return SeedSpitter.class;
		case POTATO:
			return Potato.class;
		default:
			return null;
		}
	}
}
