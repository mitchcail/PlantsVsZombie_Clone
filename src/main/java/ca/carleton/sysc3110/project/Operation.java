package ca.carleton.sysc3110.project;

import ca.carleton.sysc3110.project.models.BoardElement;
import ca.carleton.sysc3110.project.models.Game;


// TODO: Auto-generated Javadoc
/**
 * The Class Operation.
 *
 * @author Nikola Neskovic <nick_neskov@hotmail.com>
 * @author Ian Wong <ian.m.y.wong@gmail.com>
 * 
 * This class is used during the notifyObservers method in Game
 */
public class Operation 
{
	
	/** The Constant CREATE. */
	public static final int CREATE = 0;
	
	/** The Constant REMOVE. */
	public static final int REMOVE = 1;
	
	/** The Constant MOVE. */
	public static final int MOVE = 2;
	
	/** The Constant LOAD. */
	public static final int LOAD = 3;
	
	/** The Constant UNKNOWN. */
	public static final int UNKNOWN = -1;
	
	/** The game. */
	private Game game;
	
	/** The element. */
	private BoardElement element;
	
	/** The operation number. 0 is for adding elements, 1 is for removing elements, and 2 is for moving elements */
	private int operationNumber;
	
	/** The number of sunpoints currently available. */
	public final int sunPoints;
	
	/**
	 * Instantiates a new operation.
	 *
	 * @param e the e
	 * @param on the operation number (0: add, 1: remove, 2: move)
	 * @param sunPoints the sun points
	 */
	public Operation(BoardElement e, int on, int sunPoints)
	{
		element = e;
		operationNumber = on;
		this.sunPoints = sunPoints;
		game = null;
	}
	
	/**
	 * Instantiates a new operation.
	 *
	 * @param g the g
	 * @param on the on
	 * @param sunPoints the sun points
	 */
	public Operation(Game g, int on, int sunPoints)
	{
		game = g;
		operationNumber = on;
		this.sunPoints = sunPoints;
		element = null;
	}
	
	/**
	 * Gets the element.
	 *
	 * @return the element
	 */
	public BoardElement getElement()
	{
		return element;
	}
	

	/**
	 * Gets the game.
	 *
	 * @return the game
	 */
	public Game getGame()
	{
		return game;
	}
	
	/**
	 * Gets the operation.
	 *
	 * @return the operation
	 */
	public int getOperation()
	{
		return operationNumber;
	}
}
