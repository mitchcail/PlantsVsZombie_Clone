package ca.carleton.sysc3110.project.models;

import org.junit.Assert;
import org.junit.Test;

// TODO: Auto-generated Javadoc
/**
 * The Class BoardElementTests.
 * @author John Blackwood <john@xnor.ca>
 */
public class BoardElementTests {

	/**
	 * Test constructor.
	 */
	@Test
	public void testConstructor() {
		BoardElement b = new BoardElement(0, 1, 2, 3, 4, 5);

		Assert.assertTrue(b.getRow() == 0);
		Assert.assertTrue(b.getLocation() == 1);
		Assert.assertTrue(b.getWidth() == 2);
		Assert.assertTrue(b.getSpeed() == 3);
		Assert.assertTrue(b.getHealth() == 4);
		Assert.assertTrue(b.getDamage() == 5);
	}

	/**
	 * Test get id.
	 */
	@Test
	public void testGetId() {
		int nextID = BoardElement.numElements + 1;
		BoardElement b = new BoardElement(0, 1, 2, 3, 4, 5);
		Assert.assertTrue(b.getId() == nextID);
	}

	/**
	 * Test set health.
	 */
	@Test
	public void testSetHealth() {
		BoardElement b = new BoardElement(0, 1, 2, 3, 4, 5);
		b.setHealth(20);
		Assert.assertTrue(b.getHealth() == 20);
	}

	/**
	 * Test set row.
	 */
	@Test
	public void testSetRow() {
		BoardElement b = new BoardElement(0, 1, 2, 3, 4, 5);
		b.setRow(20);
		Assert.assertTrue(b.getRow() == 20);
	}

	/**
	 * Test set location.
	 */
	@Test
	public void testSetLocation() {
		BoardElement b = new BoardElement(0, 1, 2, 3, 4, 5);
		b.setLocation(20);
		Assert.assertTrue(b.getLocation() == 20);
	}

	/**
	 * Test set speed.
	 */
	@Test
	public void testSetSpeed() {
		BoardElement b = new BoardElement(0, 1, 2, 3, 4, 5);
		b.setSpeed(20);
		Assert.assertTrue(b.getSpeed() == 20);
	}

	/**
	 * Test perform action.
	 */
	@Test
	public void testPerformAction() {
		BoardElement b = new BoardElement(0, 1, 2, 3, 4, 5);
		Assert.assertNull(b.performAction(null));
	}

	/**
	 * Test compare to.
	 */
	@Test
	public void testCompareTo() {
		BoardElement b1 = new BoardElement(0, 1, 2, 3, 4, 5);
		BoardElement b2 = new BoardElement(0, 1, 2, 3, 4, 5);
		BoardElement b3 = new BoardElement(0, 0, 2, 3, 4, 5);
		BoardElement b4 = new BoardElement(0, 2, 2, 3, 4, 5);

		Assert.assertTrue(b1.compareTo(b2) == 0);
		Assert.assertTrue(b1.compareTo(b3) > 0);
		Assert.assertTrue(b1.compareTo(b4) < 0);
	}

	/**
	 * Test hit.
	 */
	@Test
	public void testHit() {
		BoardElement b = new BoardElement(0, 1, 2, 3, 4, 5);
		int health = b.getHealth();
		Assert.assertTrue(b.hit(3));
		Assert.assertTrue(b.getHealth() == health - 3);
		Assert.assertFalse(b.hit(3));
		Assert.assertTrue(b.getHealth() == health - 6);
	}

	/**
	 * Test move.
	 */
	@Test
	public void testMove() {
		BoardElement b = new BoardElement(0, 100, 2, 3, 4, 5);
		int speed = b.getSpeed();
		b.move();
		Assert.assertTrue(b.getLocation() == 100 + speed);
	}

}
