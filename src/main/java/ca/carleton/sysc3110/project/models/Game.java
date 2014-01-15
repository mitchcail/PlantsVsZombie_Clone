package ca.carleton.sysc3110.project.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Observable;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.logging.Logger;

import ca.carleton.sysc3110.project.Operation;
import ca.carleton.sysc3110.project.models.actions.Action;
import ca.carleton.sysc3110.project.models.actions.ActionType;
import ca.carleton.sysc3110.project.models.actions.CollisionUndo;
import ca.carleton.sysc3110.project.models.actions.CreateElement;
import ca.carleton.sysc3110.project.models.actions.PlantCreate;
import ca.carleton.sysc3110.project.models.actions.PlantRemove;
import ca.carleton.sysc3110.project.models.actions.PlantShoot;
import ca.carleton.sysc3110.project.models.actions.RecallPlantGeneration;
import ca.carleton.sysc3110.project.models.actions.ShotMove;
import ca.carleton.sysc3110.project.models.actions.ShotRemove;
import ca.carleton.sysc3110.project.models.actions.TickAction;
import ca.carleton.sysc3110.project.models.actions.UnspawnWave;
import ca.carleton.sysc3110.project.models.actions.ZombieMove;
import ca.carleton.sysc3110.project.models.actions.ZombieRemove;
import ca.carleton.sysc3110.project.models.actions.ZombieSpawn;
import ca.carleton.sysc3110.project.models.levels.Level;
import ca.carleton.sysc3110.project.models.plants.Plant;
import ca.carleton.sysc3110.project.models.plants.PlantFactory;
import ca.carleton.sysc3110.project.models.plants.PlantType;
import ca.carleton.sysc3110.project.models.plants.Potato;
import ca.carleton.sysc3110.project.models.plants.SeedSpitter;
import ca.carleton.sysc3110.project.models.plants.Sunflower;
import ca.carleton.sysc3110.project.models.shots.Shot;
import ca.carleton.sysc3110.project.models.shots.ShotFactory;
import ca.carleton.sysc3110.project.models.zombies.Zombie;
import ca.carleton.sysc3110.project.models.zombies.ZombieFactory;
import ca.carleton.sysc3110.project.models.zombies.ZombieType;

// TODO: Auto-generated Javadoc
/**
 * The Class Game. The game is the driving class that controls everything within
 * the game. It monitors the activity of all elements, actions, and game state.
 * The Game class controls the state of the board using a list of lists called
 * rowData that keeps track of the boardelements within a certain row. There is
 * also a list of all plants, zombies, and shots fired. The game state is
 * tracked using an ENUM (win, lose, progress). The current zombie wave is track
 * by the currentWave integer and the currentTime integer is used to keep track
 * of playtime/turn. The game class has the capability to add plants, zombies,
 * and shots. The method runHits assesses all of the collisions on the current
 * turn. It runs through the rows and determines if any objects have interacted
 * (zombie eating plants, shot hitting zombie). The objects in the row are
 * sorted by their location in the row. The cleanUpKills method runs through all
 * of the lists in rowData and determines which objects are dead and removes
 * them. The runQueuedActions method runs through a queue of action objects and
 * executes them all at the end of the turn. The tick method is a method that
 * deals with the turns but has potential for future change in regards to making
 * this game a real-time game. The final three methods are essentially maid
 * methods and they just serve to keep everything going. They add/subtract the
 * operating costs for the plants and progress the zombies, and spawn the
 * zombies (one in each row) at the beginning of each turn.
 * 
 * Note: This acts as the focal point of the Model class for the MVC scheme
 * 
 * @author John Blackwood <john@xnor.ca>
 * @author Nikola Neskovic <nick_neskov@hotmail.com>
 * @author Ian Wong <ian.m.y.wong@gmail.com>
 * @author Mitch Cail <mitch.cail@gmail.com>
 */
public class Game extends Observable implements Serializable {

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
	private Map<Long, List<Action>> completedActions;

	/** The list of plant actions performed in the previous turns. */
	private Map<Long, List<Plant>> redoPlants;

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

	/** The number of elements. */
	private int numElements;

	/**
	 * Instantiates a new game.
	 */
	public Game() {
		this.gameState = GameState.NEED_LEVEL;
	}

	/**
	 * Load saved game.
	 */
	public void loadSavedGame() {
		setChanged();
		notifyObservers(new Operation(this, Operation.LOAD, sunPoints));
	}

	/**
	 * Load the level.
	 * 
	 * @param l
	 *            the level
	 */
	public void loadlevel(Level l) {
		this.plants = new ArrayList<Plant>();
		this.zombies = new ArrayList<Zombie>();
		this.shots = new ArrayList<Shot>();
		this.currentLevel = l;
		this.actionQueue = new PriorityQueue<Action>();
		this.completedActions = new HashMap<Long, List<Action>>();
		this.redoPlants = new HashMap<Long, List<Plant>>();
		this.redoAvailable = new HashMap<Long, Boolean>();
		this.sunPoints = l.initialSunPoints;

		this.rowData = new ArrayList<List<BoardElement>>(l.rows);
		for (int i = 0; i < 5; i++)
			rowData.add(new ArrayList<BoardElement>());

		this.gameState = GameState.IN_PROGRESS;
		this.currentWave = 0;
		this.currentTime = 0;
		this.lastWave = 0;

		this.redo = false;
		this.undo = true;

		this.redoAvailable.put(currentTime, false);
		
		this.setChanged();
		this.notifyObservers(new Operation(this, Operation.LOAD, sunPoints));

	}

	/**
	 * Adds the plant.
	 * 
	 * @param plant
	 *            the plant
	 */
	private void addPlant(Plant plant) {
		this.plants.add(plant);
		this.rowData.get(plant.getRow()).add(plant);
		setChanged();
		notifyObservers(new Operation(plant, Operation.CREATE, sunPoints));
	}

	/**
	 * Adds the zombie.
	 * 
	 * @param z
	 *            the zombie
	 */
	private void addZombie(Zombie z) {
		this.zombies.add(z);
		this.rowData.get(z.getRow()).add(z);
		setChanged();
		notifyObservers(new Operation(z, Operation.CREATE, sunPoints));
	}

	/**
	 * Adds the shot.
	 * 
	 * @param s
	 *            the shot
	 */
	private void addShot(Shot s) {
		this.shots.add(s);
		this.rowData.get(s.getRow()).add(s);
		setChanged();
		notifyObservers(new Operation(s, Operation.CREATE, sunPoints));
	}

	/**
	 * Run hits.
	 */
	private void runHits() {
		// TODO: Edit this to support realtime

		// go through the rows, anywhere where two items moving at inverse
		// speeds are reversed in the queue perform a hit
		for (int row = 0; row < rowData.size(); row++) {
			List<BoardElement> q = rowData.get(row);
			Collections.sort(q);
			final int temp = q.size() - 1;
			// TODO: change to use map with ListIterator to handle
			// OutOfBoundsException
			for (int i = temp; i >= 0; i--) {
				if (i >= q.size())
					i = q.size() - 1;
				BoardElement a = q.get(i);
				for (int j = 0; j < i; j++) {
					BoardElement b = q.get(j);

					/*
					 * If a collision has occurred, then handle the collision so
					 * long as the two items are not a plant and shot
					 */

					/*
					 * TEST STUFF
					 * 
					 * (a.getLocation() <= (b.getLocation() + b.getWidth()) ||
					 * // if the two items are overlapping (((b.getLocation() -
					 * b.getSpeed()*Game.TICK_SCALE) >= (a.getLocation() -
					 * a.getSpeed()*Game.TICK_SCALE)))
					 */

					if (!a.equals(b)) {
						/* BEGINNING OF COLLISION TESTING */

						// handle Zombie having contacted a Shot
						if ((b instanceof Zombie && a instanceof Shot)
								&& ((b.getLocation() - b.getSpeed() * Game.TICK_SCALE) > (a.getLocation() + a.getWidth() - a.getSpeed() * Game.TICK_SCALE))) {
							completedActions.get(currentTime).add(new CollisionUndo(a));
							completedActions.get(currentTime).add(new CollisionUndo(b));

							// the shot is automatically dead, so just resolve
							// the zombie
							a.hit(b.getDamage());

							if (!b.hit(a.getDamage())) {
								completedActions.get(currentTime).add(new ShotRemove((Shot) a));
								completedActions.get(currentTime).add(new ZombieRemove((Zombie) b));

								setChanged();
								notifyObservers(new Operation(a, Operation.REMOVE, sunPoints));

								setChanged();
								notifyObservers(new Operation(b, Operation.REMOVE, sunPoints));

								removeElement(row, a);
								removeElement(row, b);

								a = null;
								b = null;
								j = q.size();

							} else {
								completedActions.get(currentTime).add(new ShotRemove((Shot) a));

								setChanged();
								notifyObservers(new Operation(a, Operation.REMOVE, sunPoints));

								removeElement(row, a);

								a = null;
								j = q.size();
							}
						}

						// Handle Zombie having engaged a Plant and walked past
						// it
						else if ((b instanceof Zombie && a instanceof Plant)
								&& ((b.getLocation() - b.getSpeed() * Game.TICK_SCALE) > (a.getLocation() + a.getWidth()))) {

							completedActions.get(currentTime).add(new CollisionUndo(a));
							completedActions.get(currentTime).add(new CollisionUndo(b));

							// regardless of the result, the zombie is moved to
							// where the plant is + 1
							b.setLocation(a.getLocation() + a.getWidth() + 1);
							setChanged();
							notifyObservers(new Operation(b, Operation.MOVE, sunPoints));

							if (!a.hit(b.getDamage())) {
								completedActions.get(currentTime).add(new PlantRemove((Plant) a));

								setChanged();
								notifyObservers(new Operation(a, Operation.REMOVE, sunPoints));

								removeElement(row, a);

								a = null;
								j = q.size();
							}
						}

						// Handle Zombie having engaged a Plant
						else if ((b instanceof Plant && a instanceof Zombie) && ((b.getLocation() + b.getWidth()) >= a.getLocation())) {
							completedActions.get(currentTime).add(new CollisionUndo(a));
							completedActions.get(currentTime).add(new CollisionUndo(b));

							// regardless of the result, the zombie is moved to
							// where the plant is + 1
							a.setLocation(b.getLocation() + b.getWidth() + 1);
							setChanged();
							notifyObservers(new Operation(a, Operation.MOVE, sunPoints));

							if (!b.hit(a.getDamage())) {
								completedActions.get(currentTime).add(new PlantRemove((Plant) b));

								setChanged();
								notifyObservers(new Operation(b, Operation.REMOVE, sunPoints));

								removeElement(row, b);

								b = null;
							}
						}

						// Handle Zombie having been hit by a Shot
						else if ((b instanceof Shot && a instanceof Zombie) && ((b.getLocation() + b.getWidth()) >= a.getLocation())) {
							completedActions.get(currentTime).add(new CollisionUndo(a));
							completedActions.get(currentTime).add(new CollisionUndo(b));

							// the shot is automatically dead, so just resolve
							// the zombie
							b.hit(a.getDamage());

							if (!a.hit(b.getDamage())) {
								completedActions.get(currentTime).add(new ShotRemove((Shot) b));
								completedActions.get(currentTime).add(new ZombieRemove((Zombie) a));

								setChanged();
								notifyObservers(new Operation(a, Operation.REMOVE, sunPoints));

								setChanged();
								notifyObservers(new Operation(b, Operation.REMOVE, sunPoints));

								removeElement(row, a);
								removeElement(row, b);

								a = null;
								b = null;
								j = q.size();

							} else {
								completedActions.get(currentTime).add(new ShotRemove((Shot) b));

								setChanged();
								notifyObservers(new Operation(b, Operation.REMOVE, sunPoints));

								removeElement(row, b);

								b = null;
							}
						}

						/* END OF COLLISION HANDLING */
					}
				}

			}

		}

	}

	/**
	 * Run queued actions.
	 */
	protected void runQueuedActions() {
		// execute all queued actions
		Action a;
		while ((a = actionQueue.peek()) != null) {
			a = actionQueue.poll();

			switch (a.getActionType()) {
			case PLANT_CREATE:
				PlantCreate pc = (PlantCreate) a;
				Plant p = PlantFactory.createPlant(pc.plantType, pc.row, pc.location);
				addPlant(p);
				this.addRedoPlantCreate(p);
				this.completedActions.get(currentTime).add(new CreateElement(p));
				break;

			case PLANT_SHOOT:
				PlantShoot ps = (PlantShoot) a;
				Shot s = ShotFactory.createShot(ps.shotType, ps.row, ps.location, ps.damage);
				addShot(s);
				this.completedActions.get(currentTime).add(new CreateElement(s));
				break;

			case ZOMBIE_SPAWN:
				ZombieSpawn zs = (ZombieSpawn) a;
				Zombie z = ZombieFactory.createZombie(zs.zombieType, zs.row, Level.LENGTH + zs.offset, zs.offset);
				addZombie(z);
				this.completedActions.get(currentTime).add(new CreateElement(z));
				break;

			case SHOT_MOVE:

			case ZOMBIE_MOVE:
				a.getSource().move();
				setChanged();
				notifyObservers(new Operation(a.getSource(), Operation.MOVE, sunPoints));
				this.completedActions.get(currentTime).add(a);
				break;
			default:
				log.warning("Encountered an unknown action type");
			}
		}
	}

	/**
	 * Simulates one tick of the game.
	 */
	public void tick() {

		currentTime += TICK_SCALE;

		// creates a new index in the map for the new currentTime
		completedActions.put(currentTime, new ArrayList<Action>());

		// puts a Tick action in the completedList for the undo function
		this.completedActions.get(currentTime).add(new TickAction());

		// if not redoing, make redoing unavailable from now on
		if (!redo) {
			this.redoAvailable.put(currentTime, false);
		}

		// add a new entry for redo functions

		// if game is not in progress then do nothing
		if (this.gameState != GameState.IN_PROGRESS)
			return;

		// handle plants ready to fire
		handlePlants();

		// handle zombies moving
		handleZombies();

		// handle shots moving
		handleShots();

		// run actions
		runQueuedActions();

		// simulate hits/kills
		runHits();

		// watch for new waves
		if (currentTime - lastWave > currentLevel.waveDelay[currentWave] && currentWave < currentLevel.waves.length) {
			spawnWave(currentLevel.waves[currentWave]);
			completedActions.get(currentTime).add(new UnspawnWave(lastWave));
			currentWave += 1;
			lastWave = currentTime;
		}

		// determine game state and return
		// 1) check for zombies win
		for (Zombie z : this.zombies) {
			if (z.getLocation() <= 0) {
				this.gameState = GameState.PLAYER_LOSES;
				return;
			}
		}
		// 2) check for player wins
		if (this.currentWave >= this.currentLevel.waves.length && this.zombies.size() <= 0) {
			this.gameState = GameState.PLAYER_WINS;
			return;
		}

		// 3) all other conditions mean game continues
		this.gameState = GameState.IN_PROGRESS;

		// handle base sun point generation
		sunPoints += BASE_SUN_POINT_GENERATION;

		undo = true;

		setChanged();
		notifyObservers(new Operation((BoardElement) null, Operation.UNKNOWN, sunPoints));

	}

	/**
	 * Handle shots.
	 */
	private void handleShots() {
		for (Shot s : this.shots)
			actionQueue.add(new ShotMove(s));
	}

	/**
	 * Handle zombies.
	 */
	private void handleZombies() {
		for (Zombie z : this.zombies)
			actionQueue.add(new ZombieMove(z));
	}

	/**
	 * Handle plants.
	 */
	private void handlePlants() {
		int upkeepCosts = 0;
		for (Plant p : plants) {
			completedActions.get(currentTime).add(new RecallPlantGeneration(p));
			upkeepCosts += p.getSunUpkeep() * TICK_SCALE;
			p.incrementShotTimer();
			if (p.shouldShoot()) {
				actionQueue.add(new PlantShoot(p));
				p.resetShotTimer();
			}
		}

		this.sunPoints -= upkeepCosts;
	}

	/**
	 * Spawn wave.
	 * 
	 * @param zombieType
	 *            the zombie type
	 */
	private void spawnWave(ZombieType zombieType) {
		// randomly spawns each zombie in one of the rows based on their offset
		for (int i = 0; i < currentLevel.waveSpawn[currentWave]; i++) {
			// integer for randomization
			int rand = (int) (Math.random() * currentLevel.rows) + currentLevel.offset;

			actionQueue.add(new ZombieSpawn(rand, i * 10, zombieType));
		}
	}

	/**
	 * Adds the actions.
	 * 
	 * @param playerActions
	 *            the player actions
	 */
	public void addActions(List<Action> playerActions) {
		this.actionQueue.addAll(playerActions);
	}

	/**
	 * Removes the element.
	 * 
	 * @param row
	 *            the row in the list in which to remove the element
	 * @param e
	 *            the boardElement to remove
	 */
	public void removeElement(int row, BoardElement e) {
		if (e instanceof Zombie)
			zombies.remove(e);
		else if (e instanceof Plant)
			plants.remove(e);
		else
			shots.remove(e);

		rowData.get(row).remove(e);
	}

	/**
	 * Undoes the actions that occurred in the previous tick turn, including all
	 * plants planted, etc.
	 *
	 * @throws GameException the game exception
	 */
	public void undo() throws GameException {
		// if the game hasn't started or is already done, stop undoing and let
		// the user know
		if (this.gameState != GameState.IN_PROGRESS || completedActions.isEmpty() || !undoAllowed())
			throw new GameException("Not allowed to undo actions past this point");

		// get the list of actions in an iterator
		ListIterator<Action> i = completedActions.get(currentTime).listIterator(completedActions.get(currentTime).size());

		// iterate through the list in reverse an undo all actions
		while (i.hasPrevious()) {
			Action a = i.previous();

			// check to see which action was performed
			if (a.getActionType() == ActionType.PLANT_REMOVE) {
				// re-add the plant
				plants.add((Plant) a.getSource());
				rowData.get(a.getSource().getRow()).add(a.getSource());
				sunPoints -= ((Plant) a.getSource()).getSunCost();
				setChanged();
				notifyObservers(new Operation(a.getSource(), Operation.CREATE, sunPoints));
			} else if (a.getActionType() == ActionType.COLLISION_UNDO) {
				// undo any changes made to the BoardElement in rowData done in
				// a collision
				for (BoardElement element : rowData.get(a.getSource().getRow())) {
					if (element == a.getSource()) {
						element.setHealth(a.getSource().getHealth());
						element.setLocation(a.getSource().getLocation());
					}
				}

				// undo any changes made to the BoardElement in its respective
				// field
				if (a.getSource() instanceof Plant) {
					plants.remove(a.getSource());
					plants.add((Plant) a.getSource());
				} else if (a.getSource() instanceof Shot) {
					shots.remove(a.getSource());
					shots.add((Shot) a.getSource());
				} else if (a.getSource() instanceof Zombie) {
					zombies.remove(a.getSource());
					zombies.add((Zombie) a.getSource());
				}

				setChanged();
				notifyObservers(new Operation(a.getSource(), Operation.MOVE, sunPoints));
			} else if (a.getActionType() == ActionType.RECALL_PLANT) {
				// undo changes in Plants
				for (BoardElement element : rowData.get(a.getSource().getRow())) {
					if (element == a.getSource()) {
						Plant temp = (Plant) element;
						temp.unresetShotTimer();
						temp.decrementShotTimer();
						sunPoints += temp.getSunUpkeep();
					}
				}
			} else if (a.getActionType() == ActionType.SHOT_MOVE) {
				// move the shot backwards
				for (BoardElement element : rowData.get(a.getSource().getRow())) {
					if (element == a.getSource()) {
						Shot temp = (Shot) element;
						temp.setLocation(temp.getLocation() - (int) (temp.getSpeed() * Game.TICK_SCALE));
					}
				}
				shots.remove(a.getSource());
				shots.add((Shot) a.getSource());

				setChanged();
				notifyObservers(new Operation(a.getSource(), Operation.MOVE, sunPoints));
			} else if (a.getActionType() == ActionType.SHOT_REMOVE) {
				// re-add the shot
				shots.add((Shot) a.getSource());
				rowData.get(a.getSource().getRow()).add(a.getSource());

				setChanged();
				notifyObservers(new Operation(a.getSource(), Operation.CREATE, sunPoints));
			} else if (a.getActionType() == ActionType.TICK_ACTION) {
				// cancels the current actions as the undo has come to an end

				// bump all the variables that need to be changed still back and
				// remove the currentTime key instance in the Map
				completedActions.remove(currentTime);
				currentTime--;
				sunPoints -= BASE_SUN_POINT_GENERATION;

				// add a redo-able boolean to the map for redo actions
				redoAvailable.put(currentTime, true);

				setChanged();
				notifyObservers(new Operation((BoardElement) null, Operation.UNKNOWN, sunPoints));

				return;
			} else if (a.getActionType() == ActionType.UNSPAWN_WAVE) {
				// reduce the current wave # and reset the last wave value
				currentWave--;
				lastWave = ((UnspawnWave) a).lastWave;
			} else if (a.getActionType() == ActionType.CREATE_ELEMENT) {
				// delete the existing element
				rowData.get(a.getSource().getRow()).remove(a.getSource());

				setChanged();
				notifyObservers(new Operation(a.getSource(), Operation.REMOVE, sunPoints));

				// delete the BoardElement from its respective field
				if (a.getSource() instanceof Plant) {
					// if the object is a plant, give the sunPoints cost back
					sunPoints += ((Plant) a.getSource()).getSunCost();
					plants.remove(a.getSource());
				} else if (a.getSource() instanceof Shot) {
					shots.remove(a.getSource());
				} else if (a.getSource() instanceof Zombie) {
					zombies.remove(a.getSource());
				}
			} else if (a.getActionType() == ActionType.ZOMBIE_MOVE) {
				// move the zombie backwards and delete it if necessary
				for (BoardElement element : rowData.get(a.getSource().getRow())) {
					if (element == a.getSource()) {
						Zombie temp = (Zombie) element;
						temp.setLocation(temp.getLocation() - (int) (temp.getSpeed() * Game.TICK_SCALE));
						zombies.remove(a.getSource());
						if(temp.getLocation() > (Level.LENGTH + temp.getOffset()))
						{
							// handle zombies to be destroyed
							rowData.get(a.getSource().getRow()).remove(a.getSource());
							
							setChanged();
							notifyObservers(new Operation(a.getSource(), Operation.REMOVE, sunPoints));
						}
						else
						{
							zombies.add((Zombie) a.getSource());

							setChanged();
							notifyObservers(new Operation(a.getSource(), Operation.MOVE, sunPoints));
						}
					}
				}
			} else if (a.getActionType() == ActionType.ZOMBIE_REMOVE) {
				// re-add the zombie
				zombies.add((Zombie) a.getSource());
				rowData.get(a.getSource().getRow()).add(a.getSource());

				setChanged();
				notifyObservers(new Operation(a.getSource(), Operation.CREATE, sunPoints));
			} else {
				// if the action isn't on the list of actions, inform the user
				throw new GameException("There was an error undoing: an unknown action was read.");
			}
		}
	}

	/**
	 * Redoes the actions that were recently undone.
	 *
	 * @throws GameException the game exception
	 */
	public void redo() throws GameException {

		// if there are no actions that have been performed before, then do not
		// perform any actions
		if (getGameState() != GameState.IN_PROGRESS || !redoAvailable.get(currentTime))
			throw new GameException("Cannot redo: no actions to perform or turn overwrote existing redo.");

		// if there are plants to place, handle them, otherwise just tick
		if(redoPlants.containsKey(currentTime))
		{
			if(!redoPlants.get(currentTime).isEmpty())
			{
				ListIterator<Plant> i = redoPlants.get(currentTime).listIterator();
		
				while (i.hasNext()) {
					Plant p = i.next();
					if (p instanceof Sunflower) {
						actionQueue.add(new PlantCreate(PlantType.SUNFLOWER, p.getRow(), p.getLocation()));
						sunPoints -= Sunflower.COST;
					} else if (p instanceof SeedSpitter) {
						actionQueue.add(new PlantCreate(PlantType.SEED_SPITTER, p.getRow(), p.getLocation()));
						sunPoints -= SeedSpitter.COST;
					} else if (p instanceof Potato) {
						actionQueue.add(new PlantCreate(PlantType.POTATO, p.getRow(), p.getLocation()));
						sunPoints -= Potato.COST;
					}
				}
			}
		}
		redo = true;
		tick();

		redo = false;
	}

	/**
	 * Adds the redo action.
	 * 
	 * @param p
	 *            the plant
	 */
	public void addRedoPlantCreate(Plant p) {
		// add the plantCreate action to the map of actions to be redone in a
		// particular time
		if (!redoPlants.containsKey(currentTime - 1)) {
			redoPlants.put(currentTime - 1, new ArrayList<Plant>());
		}

		// add the plant
		if (!redoPlants.get(currentTime - 1).contains(p)) {
			redoPlants.get(currentTime - 1).add(p);
		}
	}

	/**
	 * Set undo to false.
	 */
	public void setUndoFalse() {
		undo = false;
	}

	/**
	 * Check to see if undo is allowed.
	 * 
	 * @return true, if successful
	 */
	public boolean undoAllowed() {
		// test as to whether undo is allowed at the moment
		if (undo) {
			return true;
		}
		return false;
	}

	/**
	 * Update gui.
	 *
	 * @param p the p
	 */
	public void updateGUI(Plant p) {
		// updates the UI with the creation
		setChanged();
		notifyObservers(new Operation(p, Operation.CREATE, sunPoints));

	}

	// ====================getters/setters=================================================================

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
	 * @param completedActions
	 *            the completed actions
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
	 * @param redoPlants
	 *            the redo plants
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
	 * @param redoAvailable
	 *            the redo available
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
	 * @param actionQueue
	 *            the new action queue
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
	 * @param currentLevel
	 *            the new current level
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
	 * @param sunPoints
	 *            the new sun points
	 */
	public void setSunPoints(int sunPoints) {
		this.sunPoints = sunPoints;
	}

	/**
	 * Sets the row data.
	 * 
	 * @param rowData
	 *            the new row data
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
	 * @param redo
	 *            the new redo
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
	 * @param undo
	 *            the new undo
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
	 * @param plants
	 *            the new plants
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
	 * @param zombies
	 *            the new zombies
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
	 * @param shots
	 *            the new shots
	 */
	public void setShots(List<Shot> shots) {
		this.shots = shots;
	}

	/**
	 * Sets the game state.
	 * 
	 * @param gameState
	 *            the new game state
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
	 * @param currentWave
	 *            the new current wave
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
	 * @param currentTime
	 *            the new current time
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
	 * @param lastWave
	 *            the new last wave
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

	/**
	 * Gets the redo.
	 * 
	 * @return the redo
	 */
	public boolean getRedo() {
		return this.redo;
	}

	/**
	 * Gets the undo.
	 * 
	 * @return the undo
	 */
	public boolean getUndo() {
		return this.undo;
	}

	/**
	 * Gets the level state.
	 * 
	 * @return PLAYER_WINS if all zombies are dead and there are no more waves,
	 *         PLAYER_LOSES if a zombie has reached a location <= 0, otherwise
	 *         IN_PROGRESS
	 */
	public GameState getGameState() {
		return this.gameState;
	}

	/**
	 * Gets the board elements.
	 * 
	 * @return the board elements
	 */
	public List<List<BoardElement>> getBoardElements() {
		return rowData;
	}

	/**
	 * Gets the ticks.
	 * 
	 * @return the ticks
	 */
	public long getTicks() {
		return currentTime;
	}

	/**
	 * Gets the row elements.
	 * 
	 * @param row
	 *            the row
	 * @return the row elements
	 */
	public List<BoardElement> getRowElements(int row) {
		return this.rowData.get(row);
	}

	/**
	 * Gets the current sun point.
	 * 
	 * @return the current sun point
	 */
	public int getCurrentSunPoint() {
		return this.sunPoints;
	}

	/**
	 * Sets the current sun point.
	 * 
	 * @param sun
	 *            The number of sun points to set the current value to
	 */
	public void setCurrentSunPoint(int sun) {
		sunPoints = sun;
	}

	/**
	 * Gets the num elements.
	 * 
	 * @return the num elements
	 */
	public int getNumElements() {
		return numElements;
	}

	/**
	 * Sets the num elements.
	 * 
	 * @param numElements
	 *            the new num elements
	 */
	public void setNumElements(int numElements) {
		this.numElements = numElements;
	}

	/**
	 * Gets the available plant types.
	 *
	 * @return the available plant types
	 */
	public PlantType[] getAvailablePlantTypes() {
		return this.currentLevel.availablePlants;
	}
}
