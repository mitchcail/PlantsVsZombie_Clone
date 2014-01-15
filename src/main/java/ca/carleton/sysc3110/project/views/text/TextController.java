package ca.carleton.sysc3110.project.views.text;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;

import ca.carleton.sysc3110.project.models.BoardElement;
import ca.carleton.sysc3110.project.models.Game;
import ca.carleton.sysc3110.project.models.GameState;
import ca.carleton.sysc3110.project.models.actions.Action;
import ca.carleton.sysc3110.project.models.actions.PlantCreate;
import ca.carleton.sysc3110.project.models.levels.Level;
import ca.carleton.sysc3110.project.models.levels.LevelTheFirst;
import ca.carleton.sysc3110.project.models.plants.Plant;
import ca.carleton.sysc3110.project.models.plants.PlantType;
import ca.carleton.sysc3110.project.models.plants.SeedSpitter;
import ca.carleton.sysc3110.project.models.plants.Sunflower;
import ca.carleton.sysc3110.project.models.shots.Shot;
import ca.carleton.sysc3110.project.models.zombies.WalkingUndead;
import ca.carleton.sysc3110.project.models.zombies.Zombie;

// TODO: Auto-generated Javadoc
/**
 * The Class Controller.
 * @author John Blackwood <john@xnor.ca>
 * @author Ian Wong <ian.m.y.wong@gmail.com>
 */
public class TextController {
	/** The Constant PLAY_GAME. */
	private static final int PLAY_GAME = 1;

	/** The Constant QUIT. */
	private static final int QUIT = 2;

	/** The Constant ROW_SEPARATOR. */
	private static final String ROW_SEPARATOR = "=";

	/** The Constant ROW_DISPLAY_LENGTH. */
	private static final int ROW_DISPLAY_LENGTH = 100;

	/** The current level. */
	private LevelTheFirst currentLevel;

	/** The game. */
	private Game game;

	/**
	 * Instantiates a new text ui.
	 */
	public TextController() {
		this.inputScanner = new Scanner(System.in);
	}

	/**
	 * Run.
	 */
	public void run() {
		while (true) {
			displayMainMenu();

			switch (getUserInput(null)) {
			case PLAY_GAME:
				playGame();
				break;
			case QUIT:
				System.exit(0);
			}

		}
	}

	/**
	 * Play game.
	 */
	private void playGame() {
		currentLevel = new LevelTheFirst();
		game = new Game();
		game.loadlevel(currentLevel);

		while (game.getGameState() == GameState.IN_PROGRESS) {
			displayBoard();
			game.addActions(getPlayerActions());
			game.tick();
		}

	}

	/** The input scanner. */
	private Scanner inputScanner;

	/**
	 * Gets the user input.
	 *
	 * @param def the def
	 * @return the user input
	 */
	private int getUserInput(Integer def) {
		while (true) {
			String line = inputScanner.nextLine();
			if (line.equals("") && def != null)
				return def;

			try {
				return Integer.parseInt(line);
			} catch (NumberFormatException e) {
				System.out.println("Only integer inputs are allowed");
			}

		}
	}

	/**
	 * Prints the action menu.
	 */
	private void printActionMenu() {
		System.out.println("What would you like to do? You have sun " + game.getCurrentSunPoint() + " points available");
		if( game.getCurrentSunPoint() >= Sunflower.COST)
			System.out.println("1) Plant Sunflower (" + Sunflower.COST + " points)");
		if( game.getCurrentSunPoint() >= SeedSpitter.COST)
			System.out.println("2) Plant Seed Spitter (" + SeedSpitter.COST + " points )");
		System.out.println("9) Take turn");
		System.out.println("0) Run away! (quit)");
	}

	/**
	 * Gets the player actions.
	 * 
	 * @return the player actions
	 */
	private List<Action> getPlayerActions() {

		List<Action> actions = new ArrayList<Action>();

		actionInput: while (true) {
			printActionMenu();
			int choice = getUserInput(9);
			switch (choice) {
			case 1:
				if( game.getCurrentSunPoint() >= Sunflower.COST)
					actions.add(getPlantCreateAction(PlantType.SUNFLOWER));
				else
					System.out.println("Not enough sun points");
				break actionInput;
			case 2:
				if( game.getCurrentSunPoint() >= SeedSpitter.COST)
					actions.add(getPlantCreateAction(PlantType.SEED_SPITTER));
				else
					System.out.println("Not enough sun points");
				break actionInput;
			case 9:
				break actionInput;

			case 0:
				System.out.println("Wait, you coward! Now we're all gonna die!");
				System.exit(0);
			}

		}

		return actions;
	}

	/**
	 * Gets the plant create action.
	 * 
	 * @param plantType
	 *            the plant type
	 * @return the plant create action
	 */
	private Action getPlantCreateAction(PlantType plantType) {
		int row = 0; // since this milestone has only one level with one row,
						// and this UI will be abandoned for the next
						// milestone...

		System.out.println("Where would you like to place the plant in the row? [0-" + Level.LENGTH + "]");
		int location = getUserInput(null);
		return new PlantCreate(plantType, row, location);
	}

	/**
	 * Display board.
	 */
	private void displayBoard() {
		System.out.println("Current board");
		System.out.println();

		for (int row = 0; row < currentLevel.rows; row++) {
			System.out.println(StringUtils.repeat(ROW_SEPARATOR, ROW_DISPLAY_LENGTH));
			List<BoardElement> rowElements = this.game.getRowElements(row);
			int pos = 0;
			Iterator<BoardElement> it = rowElements.iterator();
			BoardElement e = null;
			while (true) {
				int location = pos * (Level.LENGTH / ROW_DISPLAY_LENGTH);

				if (e == null)
					if (it.hasNext())
						e = it.next();
					else
						break;

				if (e.getLocation() <= location) {
					System.out.print(getRepresentation(e));
					e = null;
				} else {
					pos += 1;
					System.out.print(" ");
				}

				if (pos >= ROW_DISPLAY_LENGTH) {
					break;
				}
			}
			System.out.println();
			System.out.println(StringUtils.repeat(ROW_SEPARATOR, ROW_DISPLAY_LENGTH));
		}

		System.out.println();
	}

	/**
	 * Gets the representation.
	 * 
	 * @param e
	 *            the e
	 * @return the representation
	 */
	private String getRepresentation(BoardElement e) {
		if (e instanceof Sunflower)
			return "T";
		if (e instanceof SeedSpitter)
			return "s";
		if (e instanceof Plant)
			return "p";
		if (e instanceof Zombie)
			return "Z";
		if (e instanceof WalkingUndead)
			return "W";
		if (e instanceof Shot)
			return "*";
		return "U";
	}

	/**
	 * Display main menu.
	 */
	private void displayMainMenu() {
		System.out.println("+=+=+=+ Plants Vs Zombies +=+=+=+");
		System.out.println();
		System.out.println("Main Menu");
		System.out.println("1) Play Game");
		System.out.println("2) Quit");
	}
}
