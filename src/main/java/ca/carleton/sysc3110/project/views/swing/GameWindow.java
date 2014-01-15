package ca.carleton.sysc3110.project.views.swing;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import ca.carleton.sysc3110.project.Operation;
import ca.carleton.sysc3110.project.models.BoardElement;
import ca.carleton.sysc3110.project.models.Game;
import ca.carleton.sysc3110.project.models.levels.Level;
import ca.carleton.sysc3110.project.models.plants.Plant;
import ca.carleton.sysc3110.project.models.plants.PlantType;
import ca.carleton.sysc3110.project.models.plants.Potato;
import ca.carleton.sysc3110.project.models.plants.SeedSpitter;
import ca.carleton.sysc3110.project.models.plants.Sunflower;
import ca.carleton.sysc3110.project.models.shots.Shot;
import ca.carleton.sysc3110.project.models.shots.ShotType;
import ca.carleton.sysc3110.project.models.zombies.LumberingBrute;
import ca.carleton.sysc3110.project.models.zombies.RapidGhoul;
import ca.carleton.sysc3110.project.models.zombies.WalkingUndead;
import ca.carleton.sysc3110.project.models.zombies.Zombie;
import ca.carleton.sysc3110.project.models.zombies.ZombieType;

// TODO: Auto-generated Javadoc
/**
 * The Class GameWindow.
 * 
 * @author John Blackwood <john@xnor.ca>
 * @author Nikola Neskovic <nick_neskov@hotmail.com>
 * @author Ian Wong <ian.m.y.wong@gmail.com>
 */
public class GameWindow implements Observer, Serializable {

	/** The Serial ID. */
	private static final long serialVersionUID = 3395928687647233026L;

	/** The Constant DIRT. */
	private static final Color DIRT = new Color(0xB04521); // brown

	/** The Constant GRASS. */
	private static final Color GRASS = new Color(0x10A335); // green

	/** The Constant SUN. */
	private static final Color SUN = new Color(0xF0F00C); // yellow

	/** The Constant DEFAULT_FONT. */
	private static final Font DEFAULT_FONT = new Font("arial", Font.BOLD, 50);

	/** The Constant MAP_LENGTH. */
	private static final int MAP_LENGTH = 10;

	/** The Constant MAP_HEIGHT. */
	private static final int MAP_HEIGHT = 5;

	/** The Constant TILE_SIZE. */
	private static final int TILE_SIZE = 100;

	/** The jb. */
	private JButton[][] jb = new JButton[MAP_HEIGHT][MAP_LENGTH];

	/** The frame. */
	private JFrame frame;

	/** The pause item. */
	private JMenuItem pauseItem;

	/** The save item. */
	private JMenuItem saveItem;

	/** The load item. */
	private JMenuItem loadItem;

	/** The exit item. */
	private JMenuItem exitItem;

	/** The undo item. */
	private JMenuItem undoItem;

	/** The redo item. */
	private JMenuItem redoItem;

	/** The plant item. */
	private JMenuItem plantItem;

	/** The end item. */
	private JMenuItem endItem;

	/** The end item. */
	private JMenuItem buildItem;

	/** The level loading item. */
	private JMenuItem loadLevelItem;

	/** The lp. */
	private JLayeredPane lp;

	/** The lb. */
	private JLabel sunPointsLabel;

	/** The jp. */
	private JPanel jp;

	/** The zombie label list. */
	private Map<Integer, JLabel> zombieLabelMap;

	/** The shot label list. */
	private Map<Integer, JLabel> shotLabelMap;

	/** The background panel. */
	JPanel background;

	/**
	 * Gets the frame.
	 * 
	 * @return the frame
	 */
	public JFrame getFrame() {
		return frame;
	}

	/**
	 * Instantiates a new game window.
	 */
	public GameWindow() {
		frame = new JFrame("Plants VS Zombies"); // set the name of the frame
		frame.setSize(MAP_LENGTH * 100 + 210, MAP_HEIGHT * 100 + 220); // create border between frame and button grid
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		zombieLabelMap = new HashMap<Integer, JLabel>();
		shotLabelMap = new HashMap<Integer, JLabel>();

		background = new JPanel();
		background.setSize(frame.getSize());
		background.setBackground(SUN);
		background.setVisible(true);

		sunPointsLabel = new JLabel("Sun Points: -");
		sunPointsLabel.setFont(DEFAULT_FONT);
		sunPointsLabel.setBounds(frame.getWidth() / 2 - 170, -20, 600, 150);
		sunPointsLabel.setVisible(true);

		lp = new JLayeredPane();
		lp.setSize(frame.getSize());
		lp.setVisible(true);
		lp.add(sunPointsLabel, 2);
		lp.add(background, 1);

		jp = new JPanel(null); // JPanel will be responsible for holding everything
		jp.setSize(frame.getSize()); // JPanel size = frame size
		jp.setVisible(true);
		jp.setBackground(SUN);
		jp.setOpaque(false);

		jp.add(Box.createRigidArea(frame.getSize()));
		jp.add(lp);

		frame.setJMenuBar(generateMenuBar());
		frame.getContentPane().add(jp);
		frame.repaint();

		for (int i = 0; i < MAP_HEIGHT; i++) {
			for (int k = 0; k < MAP_LENGTH; k++) {
				jb[i][k] = new JButton("");
				jb[i][k].setBounds(100 * (k + 1), 100 * (i + 1), TILE_SIZE, TILE_SIZE);
				jb[i][k].setFont(DEFAULT_FONT);
				jb[i][k].setVisible(true);
				jb[i][k].setEnabled(false);
				jb[i][k].setRolloverEnabled(false);
				jb[i][k].setBackground(DIRT);
			}
		}

	}

	/*
	 * =======================================INITIALIZATION METHODS START=======================================These methods are used to generate the game
	 * board and have been removed from constructor method for thesake of coding cohesion. There are three methods: generateMenuBar which deals with adding the
	 * menu itemsto the menu bar of the frame, generateBoard which generates 2D array of JButtons and adds it the layeredPane,and assessEnabledRows which
	 * initializes an array of booleans to determine how many rows are enabled forthe given level.
	 */

	/**
	 * Generate menu bar.
	 * 
	 * @return the j menu bar
	 */
	private JMenuBar generateMenuBar() {

		JMenuBar menuBar = new JMenuBar(); // generate
		JMenu fileMenu = new JMenu("File");
		menuBar.add(fileMenu);
		JMenu actionMenu = new JMenu("Action");
		menuBar.add(actionMenu);
		pauseItem = new JMenuItem("Pause");
		pauseItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK));
		fileMenu.add(pauseItem);
		saveItem = new JMenuItem("Save");
		saveItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		fileMenu.add(saveItem);
		loadItem = new JMenuItem("Load");
		loadItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_MASK));
		fileMenu.add(loadItem);
		loadLevelItem = new JMenuItem("Load Level");
		loadLevelItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
		fileMenu.add(loadLevelItem);
		exitItem = new JMenuItem("Exit");
		exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_MASK));
		fileMenu.add(exitItem);
		plantItem = new JMenuItem("Plant");
		plantItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK));
		actionMenu.add(plantItem);
		endItem = new JMenuItem("End Turn");
		endItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, InputEvent.CTRL_MASK));
		actionMenu.add(endItem);
		buildItem = new JMenuItem("Build Level");
		buildItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, InputEvent.CTRL_MASK));
		actionMenu.add(buildItem);
		undoItem = new JMenuItem("Undo");
		undoItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_MASK));
		actionMenu.add(undoItem);
		redoItem = new JMenuItem("Redo");
		redoItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, InputEvent.CTRL_MASK));
		actionMenu.add(redoItem);
		return menuBar;
	}

	/**
	 * Generate board.
	 * 
	 * @param l
	 *            the l
	 */
	public void generateBoard(Level l) {
		for (int i = 0; i < MAP_HEIGHT; i++) {
			for (int k = 0; k < MAP_LENGTH; k++) {
				jb[i][k].setEnabled(l.rowEnabled[i]);
				if (jb[i][k].isEnabled())
					jb[i][k].setBackground(GRASS);
				else
					jb[i][k].setBackground(DIRT);
			}
		}
		for (int i = 0; i < MAP_HEIGHT; i++) {
			for (int k = 0; k < MAP_LENGTH; k++) {
				lp.add(jb[i][k], 1);
			}
		}
	}

	// =======================================INITIALIZATION METHODS END=======================================

	/*
	 * =======================================CONTROLLER REQUIRED METHODS START======================================= These are all methods that are used my
	 * the controller class for refreshing the board, accessing the board buttons and menu items, and adding itself as an action listener to the buttons and
	 * menu items.
	 */

	/**
	 * Gets the board.
	 * 
	 * @return the board
	 */
	public JButton[][] getBoard() {
		return jb;
	}

	/**
	 * Menu items.
	 * 
	 * @return the j menu item[]
	 */
	public JMenuItem[] menuItems() {
		return new JMenuItem[] { pauseItem, exitItem, plantItem, endItem, undoItem, redoItem, saveItem, loadItem, buildItem, loadLevelItem };
	}

	/**
	 * Reset action listeners.
	 * 
	 * @param a
	 *            the action listener
	 */
	public void resetActionListeners(ActionListener a) {
		removeActionListeners(a);
		setActionListeners(a);
	}

	/**
	 * Removes the action listeners.
	 * 
	 * @param a
	 *            the action listener to remove
	 */
	public void removeActionListeners(ActionListener a) {
		for (int i = 0; i < MAP_HEIGHT; i++) {
			for (int k = 0; k < MAP_LENGTH; k++) {
				jb[i][k].removeActionListener(a);
			}
		}
		pauseItem.removeActionListener(a);
		saveItem.removeActionListener(a);
		loadItem.removeActionListener(a);
		exitItem.removeActionListener(a);
		plantItem.removeActionListener(a);
		endItem.removeActionListener(a);
		buildItem.removeActionListener(a);
		undoItem.removeActionListener(a);
		redoItem.removeActionListener(a);
		loadLevelItem.removeActionListener(a);
	}

	/**
	 * Sets the action listeners.
	 * 
	 * @param a
	 *            the new action listeners
	 */
	public void setActionListeners(ActionListener a) {
		for (int i = 0; i < MAP_HEIGHT; i++) {
			for (int k = 0; k < MAP_LENGTH; k++) {
				jb[i][k].addActionListener(a);
			}
		}
		pauseItem.addActionListener(a);
		saveItem.addActionListener(a);
		loadItem.addActionListener(a);
		exitItem.addActionListener(a);
		plantItem.addActionListener(a);
		endItem.addActionListener(a);
		buildItem.addActionListener(a);
		undoItem.addActionListener(a);
		redoItem.addActionListener(a);
		loadLevelItem.addActionListener(a);
	}

	// =======================================CONTROLLER REQUIRED METHODS END=======================================

	/*
	 * =======================================MODEL REQUIRED (UPDATE) METHODS START=======================================These are all of the methods that will
	 * be required as a result of update arguments. These will be things likeadding and removing a shot or zombie or plant, or setting the sun point counter, or
	 * generating a victory or lossmessage to user.
	 */

	/*
	 * @param Observable o the observable object that triggered the update method
	 * 
	 * @param Object arg the Object variable on which to perform consequent actions
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	public void update(Observable o, Object arg) {
		// System.out.println("GameWindow: " + BoardElement.getNumElements());
		if (arg instanceof Operation) {
			Operation operation = (Operation) arg;
			// check to see if the operation is a Sun Points update
			if (operation.getElement() == null && operation.getGame() == null) {
				sunPointsLabel.setText("Sun Points: " + operation.sunPoints);
			} else {
				switch (operation.getOperation()) {
				case Operation.CREATE: // create operation
					determineElementToCreate(operation.getElement());
					break;
				case Operation.REMOVE: // remove operation
					determineElementToRemove(operation.getElement());
					break;
				case Operation.MOVE: // move operation
					determineElementToMove(operation.getElement());
					break;
				case Operation.LOAD: // load operation
					loadGame((Game) operation.getGame());
					break;
				}
				sunPointsLabel.setText("Sun Points: " + operation.sunPoints);
			}

		}
	}

	/**
	 * Determine element to create.
	 * 
	 * @param element
	 *            the element
	 */
	private void determineElementToCreate(BoardElement element) {
		if (element instanceof Zombie) {
			createZombieLabel((Zombie) element);
		} else if (element instanceof Shot) {
			createShotLabel((Shot) element);
		} else if (element instanceof Plant) {
			createPlant((Plant) element);
		}
	}

	/**
	 * Determine element to remove.
	 * 
	 * @param element
	 *            the element
	 */
	private void determineElementToRemove(BoardElement element) {
		if (element instanceof Zombie) {
			removeZombieLabel((Zombie) element);
		} else if (element instanceof Shot) {
			removeShotLabel((Shot) element);
		} else if (element instanceof Plant) {
			removePlant((Plant) element);
		}
	}

	/**
	 * Determine element to move.
	 * 
	 * @param element
	 *            the element
	 */
	private void determineElementToMove(BoardElement element) {
		if (element instanceof Zombie) {
			moveZombieLabel((Zombie) element);
		} else if (element instanceof Shot) {
			moveShotLabel((Shot) element);
		}

	}

	/**
	 * Move zombie label.
	 * 
	 * @param z
	 *            the z
	 */
	public void moveZombieLabel(Zombie z) {
		JLabel zLabelRef = zombieLabelMap.get(z.getId());
		zLabelRef.setLocation(z.getLocation() + 100, z.getRow() * 100);
	}

	/**
	 * Move shot label.
	 * 
	 * @param s
	 *            the s
	 */
	public void moveShotLabel(Shot s) {
		JLabel sLabelRef = shotLabelMap.get(s.getId());
		sLabelRef.setLocation(s.getLocation() + 100, s.getRow() * 100);
	}

	/**
	 * Removes the zombie label.
	 * 
	 * @param z
	 *            the z
	 */
	public void removeZombieLabel(Zombie z) {
		JLabel zLabelRef = zombieLabelMap.get(z.getId());
		zLabelRef.setIcon(null);
		zombieLabelMap.remove(z.getId());
		lp.remove(zLabelRef);
	}

	/**
	 * Removes the shot label.
	 * 
	 * @param s
	 *            the s
	 */
	public void removeShotLabel(Shot s) {
		JLabel sLabelRef = shotLabelMap.remove(s.getId());
		lp.remove(sLabelRef);
	}

	/**
	 * Removes the plant.
	 * 
	 * @param p
	 *            the p
	 */
	public void removePlant(Plant p) {
		jb[p.getRow() - 1][p.getLocation() / 100].setIcon(null);
		jb[p.getRow() - 1][p.getLocation() / 100].setEnabled(true);
	}

	/**
	 * Adds the zombie label.
	 * 
	 * @param z
	 *            the zombie to create a label for
	 * @throws IllegalArgumentException
	 *             the illegal argument exception
	 */
	public void createZombieLabel(Zombie z) throws IllegalArgumentException {
		ZombieType tempType = null;
		if (z instanceof WalkingUndead) {
			tempType = ZombieType.WALKING_UNDEAD;
		} else if (z instanceof RapidGhoul) {
			tempType = ZombieType.RAPID_GHOUL;
		} else if (z instanceof LumberingBrute) {
			tempType = ZombieType.LUMBERING_BRUTE;
		}
		JLabel zombieLabel = ZombieViewFactory.createViewZombie(tempType, z.getRow(), z.getLocation());
		addItemToBoard(zombieLabel);
		zombieLabelMap.put(new Integer(z.getId()), zombieLabel);
	}

	/**
	 * Adds the shot label.
	 * 
	 * @param s
	 *            the shot
	 * @throws IllegalArgumentException
	 *             the illegal argument exception
	 */
	public void createShotLabel(Shot s) throws IllegalArgumentException {
		JLabel shotLabel = ShotViewFactory.createViewShot(ShotType.SEED, s.getRow(), s.getLocation());
		addItemToBoard(shotLabel);
		shotLabelMap.put(new Integer(s.getId()), shotLabel); // gets the most recently added component (shot label in this case) from the list
	}

	/**
	 * Adds the item.
	 * 
	 * @param label
	 *            the label
	 */
	private void addItemToBoard(JLabel label) {
		jp.remove(lp); // remove layered pane from panel
		lp.add(label, 3);// change the layered pane by adding a zombie panel
		jp.add(lp); // re-add the the layered pane to the panel
		frame.repaint();
	}

	/**
	 * Adds the plant.
	 * 
	 * @param p
	 *            the plant
	 * @throws IllegalArgumentException
	 *             the illegal argument exception
	 */
	public void createPlant(Plant p) throws IllegalArgumentException {
		PlantType plantType = null;
		int row = p.getRow() - 1;
		int column = p.getLocation() / 100;
		ImageIcon plantImage = null;

		if (p instanceof SeedSpitter) {
			plantType = PlantType.SEED_SPITTER;
		} else if (p instanceof Sunflower) {
			plantType = PlantType.SUNFLOWER;
		} else if (p instanceof Potato) {
			plantType = PlantType.POTATO;
		}

		switch (plantType) {
		case SUNFLOWER:
			plantImage = new ImageIcon("images/SUNFLOWER.png");
			break;
		case SEED_SPITTER:
			plantImage = new ImageIcon("images/SEED_SPITTER.png");
			break;
		case POTATO:
			plantImage = new ImageIcon("images/POTATO.png");
			break;
		default:
			JOptionPane.showMessageDialog(null, "Error, please check coding.");
		}
		jb[row][column].setIcon(plantImage);
	}

	/**
	 * Sets the sun points display.
	 * 
	 * @param s
	 *            the new sun points display
	 */
	public void setSunPointsDisplay(int s) {
		sunPointsLabel.setText("Sun Points: " + s);
	}

	/**
	 * Load game.
	 * 
	 * @param g
	 *            the g
	 */
	private void loadGame(Game g) {
		// TODO get the items from a previous save to disappear when loading a new game
		lp.removeAll();
		lp.add(sunPointsLabel, 2);
		lp.add(background, 1);
		generateBoard(g.getCurrentLevel());

		setSunPointsDisplay(g.getSunPoints());

		for (int i = 0; i < MAP_HEIGHT; i++) {
			for (int k = 0; k < MAP_LENGTH; k++) {
				jb[i][k].setIcon(null);
			}
		}

		this.zombieLabelMap = null;
		this.shotLabelMap = null;

		zombieLabelMap = new HashMap<Integer, JLabel>();
		shotLabelMap = new HashMap<Integer, JLabel>();

		List<List<BoardElement>> localList = g.getBoardElements();

		for (List<BoardElement> rowElements : localList) {
			for (BoardElement currentElement : rowElements) {
				if (currentElement instanceof Zombie)
					createZombieLabel((Zombie) currentElement);
				if (currentElement instanceof Plant)
					createPlant((Plant) currentElement);
				if (currentElement instanceof Shot)
					createShotLabel((Shot) currentElement);
			}
		}

		frame.repaint();

	}
	// =======================================MODEL REQUIRED (UPDATE) METHODS END=======================================
}// END MAIN
