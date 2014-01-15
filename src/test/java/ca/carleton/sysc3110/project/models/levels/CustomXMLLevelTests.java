package ca.carleton.sysc3110.project.models.levels;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.junit.Assert;
import org.junit.Test;
import org.xml.sax.SAXException;

import ca.carleton.sysc3110.project.models.plants.PlantType;
import ca.carleton.sysc3110.project.models.zombies.ZombieType;

// TODO: Auto-generated Javadoc
/**
 * The Class LevelTests.
 * @author John Blackwood <john@xnor.ca>
 */
public class CustomXMLLevelTests {

	/**
	 * Test level constructor.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ParserConfigurationException the parser configuration exception
	 * @throws TransformerException the transformer exception
	 * @throws SAXException the sAX exception
	 */
	@Test
	public void testCustomXMLLevelSaveLoad() throws IOException, ParserConfigurationException, TransformerException, SAXException {

		final ZombieType[] WAVES = { ZombieType.WALKING_UNDEAD, ZombieType.WALKING_UNDEAD, ZombieType.WALKING_UNDEAD, ZombieType.RAPID_GHOUL };
		final int[] WAVE_SPAWN = { 1, 1, 1, 1 };
		final int[] WAVE_DELAY = { Level.START_DELAY, 3, 3, 1 };
		final PlantType[] AVAILABLE_PLANTS = { PlantType.SUNFLOWER, PlantType.SEED_SPITTER };		

		CustomXMLLevel l = new CustomXMLLevel(1, true, 2, WAVES, WAVE_SPAWN, WAVE_DELAY, AVAILABLE_PLANTS, 1, 2);
		
		File f = File.createTempFile("customXMLLevel", ".xml");
		f.deleteOnExit();
		l.saveToFile(f);
		System.out.println("Save file: " + f.getAbsolutePath());
		
		CustomXMLLevel nl = CustomXMLLevel.loadFromFile(f);
		
		Assert.assertTrue(l.equals(nl));
		
		

	}

}
