package ca.carleton.sysc3110.project.models.shots;


// TODO: Auto-generated Javadoc
/**
 * A factory for creating Shot objects.
 * 
 * @author John Blackwood <john@xnor.ca>
 * @author Nikola Neskovic <nick_neskov@hotmail.com>
 * @author Ian Wong <ian.m.y.wong@gmail.com>
 */
public class ShotFactory {

	/**
	 * Creates a new Shot object.
	 * 
	 * @param shotType
	 *            the shot type
	 * @param row
	 *            the row
	 * @param location
	 *            the location
	 * @param damage
	 *            the damage
	 * @return the shot
	 */
	public static Shot createShot(ShotType shotType, int row, int location, int damage) {
		switch (shotType) {
		case SEED:
			return new Seed(row, location, damage);
		case NONE:
		default:
			return null;
		}
	}

}
