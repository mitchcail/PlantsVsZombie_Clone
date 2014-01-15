package ca.carleton.sysc3110.project.models.levels;

import java.io.Serializable;
import java.util.Arrays;

import ca.carleton.sysc3110.project.models.plants.PlantType;
import ca.carleton.sysc3110.project.models.zombies.ZombieType;

// TODO: Auto-generated Javadoc
/**
 * Represents the constant parameters that define a level.
 * @author John Blackwood <john@xnor.ca>
 * @author Nikola Neskovic <nick_neskov@hotmail.com>
 * @author Ian Wong <ian.m.y.wong@gmail.com>
 */
public class Level implements Serializable{

	/** The Serial ID. */
	private static final long serialVersionUID = 7577430133548920249L;

	/** The delay before the first wave starts. */
	public static final int START_DELAY = 5;

	/** The Constant LENGTH. */
	public static final int LENGTH = 1000;
	
	/** The number of rows in the level. */
	public final int rows;

	/** If the level has nights. */
	public final boolean hasNights;

	/** The night length. */
	public final int nightLength;
	
	/** The waves of zombies to be spawned. */
	public final ZombieType[] waves;

	/** The delay between waves. */
	public final int[] waveDelay;

	/** The available plant types. */
	public final PlantType[] availablePlants;

	/** the number of zombies to spawn in each wave. */
	public final int[] waveSpawn;

	/** The amount of SunPoints to start with. **/
	public final int initialSunPoints;
	
	/** The rows enabled. */
	public final boolean rowEnabled[];
	
	/** The offset. */
	public final int offset;

	
	/**
	 * Gets the row configuration.
	 *
	 * @return the row configuration
	 * @deprecated use public field instead
	 */
	@Deprecated
	public boolean[] getRowConfiguration(){return rowEnabled;}
	
	/**
	 * Instantiates a new level.
	 *
	 * @param rows the rows
	 * @param hasNights the has nights
	 * @param nightLength the night length
	 * @param waves the waves
	 * @param waveSpawn the wave spawn
	 * @param waveDelay the wave delay
	 * @param availablePlants the available plants
	 * @param initialSunPoints the initial sun points
	 * @param offset the offset
	 */
	public Level(int rows, boolean hasNights, int nightLength, ZombieType[] waves, int[] waveSpawn,
			int[] waveDelay, PlantType[] availablePlants, int initialSunPoints, int offset) {
		this.rows = rows;
		this.hasNights = hasNights;
		this.nightLength = nightLength;
		this.waves = waves;
		this.waveDelay = Arrays.copyOf(waveDelay, waveDelay.length + 1);
		this.waveDelay[waveDelay.length] = Integer.MAX_VALUE;
		this.availablePlants = availablePlants;
		this.waveSpawn = waveSpawn;
		this.initialSunPoints = initialSunPoints;
		this.offset = offset;
		
		rowEnabled = new boolean[5];
		
		for(int i = 0; i < 5;i++){
			switch(rows){
			case 1:
				if(i == 2) rowEnabled[i] = true;
			break;
			case 2:
				if(i == 1 || i == 2) rowEnabled[i] = true;
			break;
			case 3:
				if(i == 1 || i == 2 || i == 3) 	rowEnabled[i] = true;
			break;
			case 4:
				if(i == 0 || i == 1 || i == 2 || i == 3) 	rowEnabled[i] = true;
			break;
			case 5:
				rowEnabled[i] = true;
			break;
			default:
				throw new IllegalArgumentException("Invalid number of rows for level: min 1 max 5");
			}
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(availablePlants);
		result = prime * result + (hasNights ? 1231 : 1237);
		result = prime * result + initialSunPoints;
		result = prime * result + nightLength;
		result = prime * result + offset;
		result = prime * result + Arrays.hashCode(rowEnabled);
		result = prime * result + rows;
		result = prime * result + Arrays.hashCode(waveDelay);
		result = prime * result + Arrays.hashCode(waveSpawn);
		result = prime * result + Arrays.hashCode(waves);
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Level other = (Level) obj;
		if (!Arrays.equals(availablePlants, other.availablePlants))
			return false;
		if (hasNights != other.hasNights)
			return false;
		if (initialSunPoints != other.initialSunPoints)
			return false;
		if (nightLength != other.nightLength)
			return false;
		if (offset != other.offset)
			return false;
		if (!Arrays.equals(rowEnabled, other.rowEnabled))
			return false;
		if (rows != other.rows)
			return false;
		if (!Arrays.equals(waveDelay, other.waveDelay))
			return false;
		if (!Arrays.equals(waveSpawn, other.waveSpawn))
			return false;
		if (!Arrays.equals(waves, other.waves))
			return false;
		return true;
	}

}
