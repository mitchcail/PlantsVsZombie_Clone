package ca.carleton.sysc3110.project.models.actions;

import org.junit.Assert;
import org.junit.Test;

import ca.carleton.sysc3110.project.models.zombies.Zombie;


// TODO: Auto-generated Javadoc
/**
 * The Class ZombieMoveTests.
 * @author John Blackwood <john@xnor.ca>
 */
public class ZombieMoveTests {

	
	/**
	 * Test shot create.
	 */
	@Test
	public void testShotCreate() {
		Zombie zombie = new Zombie(0,0,0,0,0,0);
		ZombieMove zm = new ZombieMove(zombie);
		
		Assert.assertTrue(zm.getSource().equals(zombie));
		Assert.assertTrue(zm.getActionType().equals(ActionType.ZOMBIE_MOVE));
	}

}
