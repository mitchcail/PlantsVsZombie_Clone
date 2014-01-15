package ca.carleton.sysc3110.project.models.actions;


import org.junit.Assert;
import org.junit.Test;

import ca.carleton.sysc3110.project.models.BoardElement;


// TODO: Auto-generated Javadoc
/**
 * The Class ActionTests.
 * @author John Blackwood <john@xnor.ca>
 */
public class ActionTests {

	
	/**
	 * Test action.
	 */
	@Test
	public void testAction() {
		BoardElement s = new BoardElement(0, 0, 0, 0, 0, 0);
		for( ActionType t : ActionType.values() ) {
			Action a = new Action(s, t);
			Assert.assertTrue(a.getSource().equals(s));
			Assert.assertTrue(a.getActionType().equals(t));
			
			Action b = new Action(s, t);
			Assert.assertTrue(a.compareTo(b) == 0);
		}
		
	}
}
