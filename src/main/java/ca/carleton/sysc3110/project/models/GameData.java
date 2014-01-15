   package ca.carleton.sysc3110.project.models;

import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.logging.Logger;

import ca.carleton.sysc3110.project.models.actions.Action;
import ca.carleton.sysc3110.project.models.levels.Level;
import ca.carleton.sysc3110.project.models.plants.Plant;
import ca.carleton.sysc3110.project.models.shots.Shot;
import ca.carleton.sysc3110.project.models.zombies.Zombie;

// TODO: Auto-generated Javadoc
/**
 * The Class GameData.
 * 
 * @author Nikola Neskovic <nick_neskov@hotmail.com>
 */
public class GameData {

	/** The Serial ID. */
	private static final long serialVersionUID = 2242190193882299917L;

	/** The Constant log. */
	public static final Logger log = Logger.getLogger(Game.class.getCanonicalName());

	/**
	 * constants that define the speed of the game in terms of ticks and
	 * seconds.
	 */
	// TODO fix this so that instead of seconds it's milliseconds and so there's
	// no floats
	public static final int TICKS_PER_SECOND = 1;

	/** The Constant TICK_SCALE. */
	public static final float TICK_SCALE = 1 / TICKS_PER_SECOND;

	/** The Constant BASE_SUN_POINT_GENERATION. */
	private static final int BASE_SUN_POINT_GENERATION = 5;

	/** The completed actions. */
	private Map<Long,List<Action>> completedActions;
	
	/** The list of plant actions performed in the previous turns. */
	private Map<Long,List<Plant>> redoPlants;
	
	/** The boolean map to identify redo-able turns. */
	private Map<Long, Boolean> redoAvailable;

	/** The action queue. */
	private Queue<Action> actionQueue;

	/** The current level. */
	private Level currentLevel;

	/** The sun points. */
	private int sunPoints;

	/** The row data. */
	private List<List<BoardElement>> rowData;
	
	/** The redoing boolean. */
	private boolean redo;

	/** The undoing boolean. */
	private boolean undo;

	/** The plants. */
	private List<Plant> plants;

	/** The zombies. */
	private List<Zombie> zombies;

	/** The shots. */
	private List<Shot> shots;

	/** The game state. */
	private GameState gameState;

	/** The current wave. */
	private int currentWave;

	/** The current time. */
	private long currentTime;

	/** The last wave. */
	private long lastWave;

	/**
	 * Gets the completed actions.
	 *
	 * @return the completed actions
	 */
	public Map<Long, List<Action>> getCompletedActions() {
		return completedActions;
	}

	/**
	 * Sets the completed actions.
	 *
	 * @param completedActions the completed actions
	 */
	public void setCompletedActions(Map<Long, List<Action>> completedActions) {
		this.completedActions = completedActions;
	}

	/**
	 * Gets the redo plants.
	 *
	 * @return the redo plants
	 */
	public Map<Long, List<Plant>> getRedoPlants() {
		return redoPlants;
	}

	/**
	 * Sets the redo plants.
	 *
	 * @param redoPlants the redo plants
	 */
	public void setRedoPlants(Map<Long, List<Plant>> redoPlants) {
		this.redoPlants = redoPlants;
	}

	/**
	 * Gets the redo available.
	 *
	 * @return the redo available
	 */
	public Map<Long, Boolean> getRedoAvailable() {
		return redoAvailable;
	}

	/**
	 * Sets the redo available.
	 *
	 * @param redoAvailable the redo available
	 */
	public void setRedoAvailable(Map<Long, Boolean> redoAvailable) {
		this.redoAvailable = redoAvailable;
	}

	/**
	 * Gets the action queue.
	 *
	 * @return the action queue
	 */
	public Queue<Action> getActionQueue() {
		return actionQueue;
	}

	/**
	 * Sets the action queue.
	 *
	 * @param actionQueue the new action queue
	 */
	public void setActionQueue(Queue<Action> actionQueue) {
		this.actionQueue = actionQueue;
	}

	/**
	 * Gets the current level.
	 *
	 * @return the current level
	 */
	public Level getCurrentLevel() {
		return currentLevel;
	}

	/**
	 * Sets the current level.
	 *
	 * @param currentLevel the new current level
	 */
	public void setCurrentLevel(Level currentLevel) {
		this.currentLevel = currentLevel;
	}

	/**
	 * Gets the sun points.
	 *
	 * @return the sun points
	 */
	public int getSunPoints() {
		return sunPoints;
	}

	/**
	 * Sets the sun points.
	 *
	 * @param sunPoints the new sun points
	 */
	public void setSunPoints(int sunPoints) {
		this.sunPoints = sunPoints;
	}

	/**
	 * Gets the row data.
	 *
	 * @return the row data
	 */
	public List<List<BoardElement>> getRowData() {
		return rowData;
	}

	/**
	 * Sets the row data.
	 *
	 * @param rowData the new row data
	 */
	public void setRowData(List<List<BoardElement>> rowData) {
		this.rowData = rowData;
	}

	/**
	 * Checks if is redo.
	 *
	 * @return true, if is redo
	 */
	public boolean isRedo() {
		return redo;
	}

	/**
	 * Sets the redo.
	 *
	 * @param redo the new redo
	 */
	public void setRedo(boolean redo) {
		this.redo = redo;
	}

	/**
	 * Checks if is undo.
	 *
	 * @return true, if is undo
	 */
	public boolean isUndo() {
		return undo;
	}

	/**
	 * Sets the undo.
	 *
	 * @param undo the new undo
	 */
	public void setUndo(boolean undo) {
		this.undo = undo;
	}

	/**
	 * Gets the plants.
	 *
	 * @return the plants
	 */
	public List<Plant> getPlants() {
		return plants;
	}

	/**
	 * Sets the plants.
	 *
	 * @param plants the new plants
	 */
	public void setPlants(List<Plant> plants) {
		this.plants = plants;
	}

	/**
	 * Gets the zombies.
	 *
	 * @return the zombies
	 */
	public List<Zombie> getZombies() {
		return zombies;
	}

	/**
	 * Sets the zombies.
	 *
	 * @param zombies the new zombies
	 */
	public void setZombies(List<Zombie> zombies) {
		this.zombies = zombies;
	}

	/**
	 * Gets the shots.
	 *
	 * @return the shots
	 */
	public List<Shot> getShots() {
		return shots;
	}

	/**
	 * Sets the shots.
	 *
	 * @param shots the new shots
	 */
	public void setShots(List<Shot> shots) {
		this.shots = shots;
	}

	/**
	 * Gets the game state.
	 *
	 * @return the game state
	 */
	public GameState getGameState() {
		return gameState;
	}

	/**
	 * Sets the game state.
	 *
	 * @param gameState the new game state
	 */
	public void setGameState(GameState gameState) {
		this.gameState = gameState;
	}

	/**
	 * Gets the current wave.
	 *
	 * @return the current wave
	 */
	public int getCurrentWave() {
		return currentWave;
	}

	/**
	 * Sets the current wave.
	 *
	 * @param currentWave the new current wave
	 */
	public void setCurrentWave(int currentWave) {
		this.currentWave = currentWave;
	}

	/**
	 * Gets the current time.
	 *
	 * @return the current time
	 */
	public long getCurrentTime() {
		return currentTime;
	}

	/**
	 * Sets the current time.
	 *
	 * @param currentTime the new current time
	 */
	public void setCurrentTime(long currentTime) {
		this.currentTime = currentTime;
	}

	/**
	 * Gets the last wave.
	 *
	 * @return the last wave
	 */
	public long getLastWave() {
		return lastWave;
	}

	/**
	 * Sets the last wave.
	 *
	 * @param lastWave the new last wave
	 */
	public void setLastWave(long lastWave) {
		this.lastWave = lastWave;
	}

	/**
	 * Gets the serialversionuid.
	 *
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * Gets the base sun point generation.
	 *
	 * @return the base sun point generation
	 */
	public static int getBaseSunPointGeneration() {
		return BASE_SUN_POINT_GENERATION;
	}

}