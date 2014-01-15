package ca.carleton.sysc3110.project.models.actions;

import org.junit.Assert;

import org.junit.Test;

import ca.carleton.sysc3110.project.models.shots.Shot;

// TODO: Auto-generated Javadoc
/**
 * The Class ShotMoveTests.
 * @author John Blackwood <john@xnor.ca>
 */
public class ShotMoveTests {

	/**
	 * Test shot create.
	 */
	@Test
	public void testShotCreate() {
		Shot shot = new Shot(0, 0, 0, 0);
		ShotMove s = new ShotMove(shot);

		Assert.assertTrue(s.getSource().equals(shot));
		Assert.assertTrue(s.getActionType().equals(ActionType.SHOT_MOVE));
	}

}
