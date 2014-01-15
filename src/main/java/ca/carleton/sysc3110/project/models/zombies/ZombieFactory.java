package ca.carleton.sysc3110.project.models.zombies;

// TODO: Auto-generated Javadoc
/**
 * A factory for creating Zombie objects.
 * 
 * @author John Blackwood <john@xnor.ca>
 * @author Nikola Neskovic <nick_neskov@hotmail.com>
 * @author Ian Wong <ian.m.y.wong@gmail.com>
 */
public class ZombieFactory {

	/**
	 * Creates a new Zombie object.
	 *
	 * @param zombieType the zombie type
	 * @param row the row
	 * @param location the location
	 * @param offset the offset
	 * @return the zombie
	 */
	public static Zombie createZombie(ZombieType zombieType, int row, int location, int offset) {
		switch (zombieType) {
		case WALKING_UNDEAD:
			return new WalkingUndead(row, location, offset);
		case RAPID_GHOUL:
			return new RapidGhoul(row, location, offset);
		case LUMBERING_BRUTE:
			return new LumberingBrute(row, location, offset);
		default:
			return null;
		}
	}

}
