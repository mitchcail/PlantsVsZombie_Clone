package ca.carleton.sysc3110.project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import ca.carleton.sysc3110.project.models.BoardElement;
import ca.carleton.sysc3110.project.models.Game;
import ca.carleton.sysc3110.project.models.GameException;
import ca.carleton.sysc3110.project.models.GameState;
import ca.carleton.sysc3110.project.models.actions.Action;
import ca.carleton.sysc3110.project.models.actions.PlantCreate;
import ca.carleton.sysc3110.project.models.levels.CustomXMLLevel;
import ca.carleton.sysc3110.project.models.levels.Level;
import ca.carleton.sysc3110.project.models.levels.LevelTheFifth;
import ca.carleton.sysc3110.project.models.levels.LevelTheFirst;
import ca.carleton.sysc3110.project.models.levels.LevelTheFourth;
import ca.carleton.sysc3110.project.models.levels.LevelTheSecond;
import ca.carleton.sysc3110.project.models.levels.LevelTheThird;
import ca.carleton.sysc3110.project.models.plants.Plant;
import ca.carleton.sysc3110.project.models.plants.PlantFactory;
import ca.carleton.sysc3110.project.models.plants.PlantType;
import ca.carleton.sysc3110.project.models.plants.Potato;
import ca.carleton.sysc3110.project.models.plants.SeedSpitter;
import ca.carleton.sysc3110.project.models.plants.Sunflower;
import ca.carleton.sysc3110.project.views.swing.GameWindow;
import ca.carleton.sysc3110.project.views.swing.LevelCreatorGUI;

// TODO: Auto-generated Javadoc
/**
 * The Class Controller.
 * 
 * @author John Blackwood <john@xnor.ca>
 * @author Nikola Neskovic <nick_neskov@hotmail.com>
 * @author Ian Wong <ian.m.y.wong@gmail.com>
 */
public class Controller implements ActionListener {

	/** The current level. */
	private int currentLevel;

	/** The game. */
	private Game game;

	/** The view. */
	private GameWindow GUI;

	/** The buttons in the view. */
	private JButton[][] buttonArray;

	/** The MenuItem array. */
	private JMenuItem[] menuItemArray;

	/** The planting. */
	private boolean planting;

	/** The plant style. */
	private PlantType plantStyle;

	/** The game levels. */
	private Level[] gameLevels;

	/**
	 * Instantiates a new controller.
	 */
	public Controller() {
		game = new Game();
		GUI = new GameWindow();
		game.addObserver(GUI);
		planting = false;
		plantStyle = PlantType.NONE;

		// get the GUI elements and add their actionListeners
		buttonArray = GUI.getBoard();
		menuItemArray = GUI.menuItems();
		GUI.setActionListeners(this);

		gameLevels = new Level[] { new LevelTheFirst(), new LevelTheSecond(), new LevelTheThird(), new LevelTheFourth(), new LevelTheFifth() };

		currentLevel = 0;
		game.loadlevel(gameLevels[currentLevel]);

	}

	/**
	 * Save game.
	 * 
	 * @param f
	 *            the f
	 */
	public void saveGame(File f) {
		try {
			FileOutputStream ostream = new FileOutputStream(f);
			ObjectOutputStream p = new ObjectOutputStream(ostream);
			game.setNumElements(BoardElement.getNumElements());
			p.writeObject(this.game);
			p.close();
			ostream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Load game.
	 * 
	 * @param f
	 *            the f
	 */
	public void loadGame(File f) {
		try {
			FileInputStream ostream = new FileInputStream(f);
			ObjectInputStream p = new ObjectInputStream(ostream);
			Game g = (Game) p.readObject();

			this.game = g;
			game.addObserver(GUI);
			BoardElement.setNumElements(game.getNumElements());
			game.loadSavedGame();
			p.close();
			ostream.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * The responsive function to an action in the UI.
	 * 
	 * @param e
	 *            the Action that occurred to trigger actionPerformed
	 */
	public void actionPerformed(ActionEvent e) {
		// If an action is performed, check whether it is a menuitem or button,
		// and act accordingly
		if (e.getSource() instanceof JButton) {
			// buttonArray
			JButton tempButton = (JButton) e.getSource();

			if (planting && game.getGameState() == GameState.IN_PROGRESS) {
				for (int i = 0; i < buttonArray.length; i++) {
					for (int j = 0; j < buttonArray[i].length; j++) {
						if (buttonArray[i][j].equals(tempButton)) {
							// i is row, j is location
							List<Action> actions = new ArrayList<Action>();
							Action tempAction = (Action) new PlantCreate(plantStyle, i + 1, (j * 100));
							actions.add(tempAction);
							game.addActions(actions);
							
							// manually creates a plant for  instantaneous GUI updating
							PlantCreate pc = (PlantCreate) tempAction;
							Plant p = PlantFactory.createPlant(pc.plantType, pc.row, pc.location);
							game.updateGUI(p);
							planting = false;

							// indicate that an action is planned for this place
							//tempButton.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 4));

							plantStyle = PlantType.NONE;
							game.setUndoFalse();
						}
					}
				}
			}
			// else statement for additional buttons goes here
		} // end if statement
		else if (e.getSource() instanceof JMenuItem) {
			JMenuItem tempMenuItem = (JMenuItem) e.getSource();
			// pause, exit, plant, end
			for (int k = 0; k < menuItemArray.length; k++) {
				if (menuItemArray[k].equals(tempMenuItem)) {
					switch (k) {
					case 0:
						// Unimplemented pause function for realtime
						break;
					case 1:
						// exit from game
						GUI = null;
						System.exit(0);
					case 2:
						if (game.getGameState() == GameState.IN_PROGRESS) {
							// plant a plant
							PlantType[] plantTypes = game.getAvailablePlantTypes();
							String[] listOfPlantTypes = new String[plantTypes.length];

							int i = 0;
							for (PlantType p : plantTypes) {
								int plantCost = 0;
								// must be added in the order they appear in the
								// ENUM
								switch (p) {
								case SUNFLOWER:
									plantCost = Sunflower.COST;
									break;
								case SEED_SPITTER:
									plantCost = SeedSpitter.COST;
									break;
								case POTATO:
									plantCost = Potato.COST;
									break;
								default:
									break;
								}

								if (p != PlantType.NONE) {
									listOfPlantTypes[i++] = plantCost + " - " + PlantType.values()[i];
								}
							}
							String result = (String) JOptionPane.showInputDialog(null, "Choose a type of plant to use:\n", "Spawn a plant",
									JOptionPane.PLAIN_MESSAGE, null, listOfPlantTypes, listOfPlantTypes[0]);

							// if the result is null, then nothing happens
							if (result == null) {
								return;
							}

							// if the selected string matches one of the
							for (int j = 0; j < listOfPlantTypes.length; j++) {
								if (result.equals(listOfPlantTypes[j])) {
									plantStyle = plantTypes[j];
								}
							}

							// check for whether there is enough money for the
							// plant
							if (plantStyle.equals(PlantType.SUNFLOWER) && game.getCurrentSunPoint() >= Sunflower.COST && !planting) {
								planting = true;
								game.setCurrentSunPoint(game.getCurrentSunPoint() - Sunflower.COST);
							} else if (plantStyle.equals(PlantType.SEED_SPITTER) && game.getCurrentSunPoint() >= SeedSpitter.COST && !planting) {
								planting = true;
								game.setCurrentSunPoint(game.getCurrentSunPoint() - SeedSpitter.COST);
							} else if (plantStyle.equals(PlantType.POTATO) && game.getCurrentSunPoint() >= Potato.COST && !planting) {
								planting = true;
								game.setCurrentSunPoint(game.getCurrentSunPoint() - Potato.COST);
							} else
								// if sun point measure is not met
								JOptionPane.showMessageDialog(null, "You do not have enough Sun Points to afford this plant.\n");
						}
						break;
					case 3:
						// end your turn (tick)
						if (game.getGameState() == GameState.IN_PROGRESS) {
							game.tick();

							// TODO put game ending detection here, next level
							switch (game.getGameState()) {
							case IN_PROGRESS:
								break;
							case PLAYER_LOSES:
								JOptionPane.showMessageDialog(null, "You have lost!");
								break;
							case PLAYER_WINS:

								// load next level
								currentLevel += 1;
								if (currentLevel > gameLevels.length)
									JOptionPane.showMessageDialog(null, "You have beaten the game!");
								else {
									JOptionPane.showMessageDialog(null, "You have beaten the level!");
									game.loadlevel(gameLevels[currentLevel]);
								}

							case NEED_LEVEL:
								break;
							default:
								break;
							}
						}
						break;
					case 4:
						// undo an full tick action if it is the beginning of a turn
						try {
							// as long as the user is not trying to plant something, undo
							if(!planting)
								game.undo();
							else
								JOptionPane.showMessageDialog(null, "You must first place the plant before undoing.");
						} catch (GameException e1) {
							// TODO make me an error dialog
							JOptionPane.showMessageDialog(null, e1.getMessage());
						}

						break;
					case 5:
						// redo an action so long as new actions have not been taken
						try {
							if(!planting)
								game.redo();
							else
								JOptionPane.showMessageDialog(null, "You must first place the plant before redoing.");
						} catch (GameException e1) {
							// TODO make me an error dialog
							JOptionPane.showMessageDialog(null, e1.getMessage());
						}

						break;
					case 6:
						// save game
						final JFileChooser fc = new JFileChooser();

						int returnVal = fc.showSaveDialog(null);

						File file = null;

						if (returnVal == JFileChooser.APPROVE_OPTION) {
							file = fc.getSelectedFile();
						}

						if (file != null)
							saveGame(file);
						else
							JOptionPane.showMessageDialog(null, "Invalid file input.");
						break;
					case 7:
						// load game
						final JFileChooser fc2 = new JFileChooser();

						int returnVal2 = fc2.showOpenDialog(null);

						File file2 = null;

						if (returnVal2 == JFileChooser.APPROVE_OPTION) {
							file2 = fc2.getSelectedFile();
						}

						if (file2 != null)
							loadGame(file2);
						else
							JOptionPane.showMessageDialog(null, "Invalid file input.");

						break;
					case 8:
						// create a level!
						new LevelCreatorGUI();
						break;
					case 9:
						// load a level into a new GUI
						final JFileChooser fc3 = new JFileChooser();

						int returnVal3 = fc3.showOpenDialog(null);

						File file3 = null;

						if (returnVal3 == JFileChooser.APPROVE_OPTION) {
							file3 = fc3.getSelectedFile();
						}

						if (file3 != null) {
							try {
								Level l = CustomXMLLevel.loadFromFile(file3);
								game.loadlevel(l);
							} catch (ParserConfigurationException e1) {
								JOptionPane.showMessageDialog(null, "Parser Error.");
							} catch (SAXException e1) {
								JOptionPane.showMessageDialog(null, "Sax Parsing Error.");
							} catch (IOException e1) {
								JOptionPane.showMessageDialog(null, "IO Exception.");
							}
						} else {
							JOptionPane.showMessageDialog(null, "Invalid file input.");
						}

						break;
					}
				}
			}
		}
	}
}
