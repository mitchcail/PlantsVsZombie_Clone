package ca.carleton.sysc3110.project.models.zombies;

// TODO: Auto-generated Javadoc
/**
 * Zombie Type.
 * 
 * @author John Blackwood <john@xnor.ca>
 * @author Ian Wong <ian.m.y.wong@gmail.com>
 */
public enum ZombieType {

	/** The walking undead. */
	WALKING_UNDEAD, /** The rapid ghoul. */
 RAPID_GHOUL, /** The lumbering brute. */
 LUMBERING_BRUTE;

	/**
	 * Gets the zombie class.
	 *
	 * @return the zombie class
	 */
	public Class<? extends Zombie> getZombieClass() {
		switch (this) {
		case WALKING_UNDEAD:
			return WalkingUndead.class;
		case RAPID_GHOUL:
			return RapidGhoul.class;
		case LUMBERING_BRUTE:
			return LumberingBrute.class;
		default:
			return null;
		}
	}

}
