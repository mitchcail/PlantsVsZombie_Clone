package ca.carleton.sysc3110.project.models;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ca.carleton.sysc3110.project.models.levels.LevelTheFirst;
import ca.carleton.sysc3110.project.models.levels.LevelTheSecond;

// TODO: Auto-generated Javadoc
/**
 * The Class GameTests.
 * 
 * @author John Blackwood <john@xnor.ca>
 * @author Mitch Cail <mitch.cail@gmail.com>
 */
public class GameTests {

	/**
	 * Sets the up.
	 *
	 * @throws Exception the exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * Tear down.
	 *
	 * @throws Exception the exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test game.
	 */
	@Test
	public void testGame() {
		Game g = new Game();
		Assert.assertTrue(g.getGameState().equals(GameState.NEED_LEVEL));
		Assert.assertTrue(g.getCurrentSunPoint() == 0);
		
		
	}

	/**
	 * Test loadlevel.
	 */
	@Test
	public void testLoadlevel() {
		Game g = new Game();
		LevelTheFirst l = new LevelTheFirst();
		g.loadlevel(l);
		
		Assert.assertTrue(g.getGameState().equals(GameState.IN_PROGRESS));
		Assert.assertTrue(g.getCurrentSunPoint() == 50);
		
		Game g2 = new Game();
		LevelTheSecond level2 = new LevelTheSecond();
		g2.loadlevel(level2);
		
		Assert.assertTrue(g2.getCurrentSunPoint() == 50);
		Assert.assertTrue(g2.getGameState().equals(GameState.IN_PROGRESS));
		
	}

	/**
	 * Test get game state.
	 */
	@Test
	public void testGetGameState() {
		Game g = new Game();
		LevelTheFirst l = new LevelTheFirst();
		g.loadlevel(l);
		
		Assert.assertTrue(g.getGameState().equals(GameState.IN_PROGRESS));
	}

	/**
	 * Test get board elements.
	 */
	@Test
	public void testGetBoardElements() {
		Game g = new Game();
		Assert.assertTrue(g.getBoardElements() == null);
	}

	/**
	 * Test tick.
	 */
	@Test
	public void testTick() {
		Game g = new Game();
		Assert.assertTrue(g.getTicks() == 0);
		
		//g.tick();
		//Assert.assertTrue(g.getTicks() == 1);

	}

	/**
	 * Test add actions.
	 */
	@Test
	public void testAddActions() {
		
	}

	/**
	 * Test get row elements.
	 */
	@Test
	public void testGetRowElements() {
		//Game g = new Game();
		//Assert.assertTrue(g.getRowElements(4) == null);
	}

	
	/**
	 * Test set current sun point.
	 */
	@Test
	public void testSetCurrentSunPoint() {
		Game g = new Game();
		g.setCurrentSunPoint(100);
		
		Assert.assertTrue(g.getCurrentSunPoint() == 100);
	}

	/**
	 * Test remove element.
	 */
	@Test
	public void testRemoveElement() {
		//Game g = new Game();
		//BoardElement b = new BoardElement(5,5,5,5,5,5);
		//g.addElement(1,b);
	}
	
	@Test
	public void testSerialUID(){
		Game g = new Game();
		LevelTheFirst l = new LevelTheFirst();
		
		Assert.assertEquals(g.getSerialversionuid(), 2242190193882299917L);
	}
	
	public void testRedo(){
		Game g = new Game();
		LevelTheFirst l = new LevelTheFirst();
		
		g.loadlevel(l);
		
		Assert.assertFalse(g.getRedo());
	}
	
	public void testUndo(){
		Game g = new Game();
		LevelTheFirst l = new LevelTheFirst();
		
		g.loadlevel(l);

		Assert.assertTrue(g.getUndo());
	}

}
