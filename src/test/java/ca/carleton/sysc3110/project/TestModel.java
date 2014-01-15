package ca.carleton.sysc3110.project;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import ca.carleton.sysc3110.project.models.BoardElementTests;
import ca.carleton.sysc3110.project.models.GameTests;
import ca.carleton.sysc3110.project.models.actions.ActionTests;
import ca.carleton.sysc3110.project.models.actions.PlantCreateTests;
import ca.carleton.sysc3110.project.models.actions.PlantShootTests;
import ca.carleton.sysc3110.project.models.actions.ShotMoveTests;
import ca.carleton.sysc3110.project.models.actions.ZombieMoveTests;
import ca.carleton.sysc3110.project.models.actions.ZombieSpawnTests;
import ca.carleton.sysc3110.project.models.levels.CustomXMLLevelTests;
import ca.carleton.sysc3110.project.models.levels.LevelTests;
import ca.carleton.sysc3110.project.models.plants.PlantFactoryTests;
import ca.carleton.sysc3110.project.models.plants.PlantTests;
import ca.carleton.sysc3110.project.models.shots.SeedTests;
import ca.carleton.sysc3110.project.models.shots.ShotFactoryTests;
import ca.carleton.sysc3110.project.models.shots.ShotTests;
import ca.carleton.sysc3110.project.models.zombies.WalkingUndeadTests;
import ca.carleton.sysc3110.project.models.zombies.ZombieFactoryTests;
import ca.carleton.sysc3110.project.models.zombies.ZombieTests;

/**
 * The Class TestModel.
 * @author John Blackwood <john@xnor.ca>
 */
@RunWith(Suite.class)
@SuiteClasses({ 
	BoardElementTests.class, 
	GameTests.class, 
	ActionTests.class, 
	PlantCreateTests.class, 
	PlantShootTests.class, 
	ShotMoveTests.class,
	ZombieMoveTests.class, 
	ZombieSpawnTests.class,
	LevelTests.class,
	CustomXMLLevelTests.class,
	PlantFactoryTests.class,
	PlantTests.class,
	SeedTests.class,
	ShotFactoryTests.class,
	ShotTests.class,
	WalkingUndeadTests.class,
	ZombieFactoryTests.class,
	ZombieTests.class,
})
public class TestModel {

}
