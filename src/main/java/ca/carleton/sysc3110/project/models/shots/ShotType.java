package ca.carleton.sysc3110.project.models.shots;

// TODO: Auto-generated Javadoc
/**
 * The Enum ShotType.
 * 
 * @author John Blackwood <john@xnor.ca>
 * @author Nikola Neskovic <nick_neskov@hotmail.com>
 * @author Ian Wong <ian.m.y.wong@gmail.com>
 */
public enum ShotType {

	/** The none. */
	NONE,
	/** The seed. */
	SEED;

	/**
	 * Gets the shot class.
	 *
	 * @return the shot class
	 */
	public Class<? extends Shot> getShotClass() {
		switch (this) {
		case NONE:
			return Shot.class;
		case SEED:
			return Seed.class;
		default:
			return null;
		}
	}
}
