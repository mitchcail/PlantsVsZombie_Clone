package ca.carleton.sysc3110.project.models.levels;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import ca.carleton.sysc3110.project.models.plants.PlantType;
import ca.carleton.sysc3110.project.models.zombies.ZombieType;

// TODO: Auto-generated Javadoc
/**
 * A custom level defined by XML.
 *
 * @author John Blackwood <john@xnor.ca>
 */
public class CustomXMLLevel extends Level {

	
	/** The Constant WAVE_NODE. */
	private static final String WAVE_NODE = "wave";
	
	/** The Constant PLANT. */
	private static final String PLANT = "plant";
	
	/** The Constant NIGHT_LENGTH. */
	private static final String NIGHT_LENGTH = "nightLength";
	
	/** The Constant LEVEL. */
	private static final String LEVEL = "level";
	
	/** The Constant INITIAL_SUN_POINTS. */
	private static final String INITIAL_SUN_POINTS = "initialSunPoints";
	
	/** The Constant OFFSET. */
	private static final String OFFSET = "offset";
	
	/** The Constant HAS_NIGHTS. */
	private static final String HAS_NIGHTS = "hasNights";
	
	/** The Constant ROWS. */
	private static final String ROWS = "rows";
	
	/** The Constant DELAY. */
	private static final String DELAY = "delay";
	
	/** The Constant SPAWN. */
	private static final String SPAWN = "spawn";
	
	/** The Constant ZOMBIE. */
	private static final String ZOMBIE = "zombie";
	
	/** The Constant TYPE. */
	private static final String TYPE = "type";
	
	/** The Constant AVAILABLEPLANTS. */
	private static final String AVAILABLEPLANTS = "availableplants";
	
	/** The Constant WAVES. */
	private static final String WAVES = "waves";
	
	/** The Constant PROPERTIES. */
	private static final String PROPERTIES = "properties";
	

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 3000155949404585725L;

	/**
	 * Instantiates a new custom XML level.
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
	public CustomXMLLevel(int rows, boolean hasNights, int nightLength, ZombieType[] waves, int[] waveSpawn, int[] waveDelay, PlantType[] availablePlants,
			int initialSunPoints, int offset) {
		super(rows, hasNights, nightLength, waves, waveSpawn, waveDelay, availablePlants, initialSunPoints, offset);

	}

	/**
	 * Load a level from a file.
	 *
	 * @param f the file to load the level from
	 * @return the custom level
	 * @throws ParserConfigurationException the parser configuration exception
	 * @throws SAXException the sAX exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static CustomXMLLevel loadFromFile(File f) throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder d = factory.newDocumentBuilder();
		Document doc = d.parse(f);
		
		Element root = doc.getDocumentElement();
		Element properties = null;
		Element waves = null;
		Element availablePlants = null;
		NodeList rootChildren = root.getChildNodes();
		for( int i = 0; i < rootChildren.getLength(); i++ ) {
			Element e = (Element) rootChildren.item(i);
			if( e.getNodeName().equals(PROPERTIES))
				properties = e;
			else if( e.getNodeName().equals(WAVES))
				waves = e;
			else if( e.getNodeName().equals(AVAILABLEPLANTS))
				availablePlants = e;
		}
		
		// get plant types
		List<PlantType> plantTypes = new ArrayList<PlantType>();
		NodeList pts = availablePlants.getChildNodes();
		for( int i = 0; i < pts.getLength(); i++ ) {
			Element n = (Element) pts.item(i);
			PlantType pt = PlantType.valueOf(n.getAttribute(TYPE));
			plantTypes.add(pt);
		}
		
		PlantType[] ap = plantTypes.toArray(new PlantType[0]);
		
		// get number of waves
		NodeList wc = waves.getChildNodes();
		ZombieType[] zwaves = new ZombieType[wc.getLength()];
		int[] waveSpawn = new int[wc.getLength()];
		int[] waveDelay = new int[wc.getLength()];
		for( int i = 0; i < wc.getLength(); i++ ) {
			Element n = (Element) wc.item(i);
			zwaves[i] = ZombieType.valueOf(n.getAttribute(ZOMBIE));
			waveSpawn[i] = Integer.parseInt(n.getAttribute(SPAWN));
			waveDelay[i] = Integer.parseInt(n.getAttribute(DELAY));
		}
		
		// get propertiers
		int rows = Integer.parseInt(properties.getAttribute(ROWS));
		boolean hasNights = Boolean.parseBoolean(properties.getAttribute(HAS_NIGHTS));
		int nightLength = Integer.parseInt(properties.getAttribute(NIGHT_LENGTH));
		int initialSunPoints = Integer.parseInt(properties.getAttribute(INITIAL_SUN_POINTS));
		int offset = Integer.parseInt(properties.getAttribute(OFFSET));
		
		return new CustomXMLLevel(rows, hasNights, nightLength, zwaves, waveSpawn, waveDelay, ap, initialSunPoints, offset);
	}

	/**
	 * Save this level to a file.
	 *
	 * @param f the file to save the level to
	 * @throws ParserConfigurationException the parser configuration exception
	 * @throws TransformerException the transformer exception
	 * @throws FileNotFoundException the file not found exception
	 */
	public void saveToFile(File f) throws ParserConfigurationException, TransformerException, FileNotFoundException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder d = factory.newDocumentBuilder();
		Document doc = d.newDocument();

		Element root = doc.createElement(LEVEL);
		doc.appendChild(root);

		Element properties = doc.createElement(PROPERTIES);
		properties.setAttribute(ROWS, Integer.toString(this.rows));
		properties.setAttribute(HAS_NIGHTS, Boolean.toString(this.hasNights));
		properties.setAttribute(NIGHT_LENGTH, Integer.toString(this.nightLength));
		properties.setAttribute(INITIAL_SUN_POINTS, Integer.toString(this.initialSunPoints));
		properties.setAttribute(OFFSET, Integer.toString(this.offset));
		root.appendChild(properties);

		Element availablePlants = doc.createElement(AVAILABLEPLANTS);
		for (PlantType pt : this.availablePlants) {
			Element apt = doc.createElement(PLANT);
			apt.setAttribute(TYPE, pt.name());
			availablePlants.appendChild(apt);
		}
		root.appendChild(availablePlants);

		// ZombieType[] waves, int[] waveSpawn, int[] waveDelay
		Element waves = doc.createElement(WAVES);
		for (int i = 0; i < this.waves.length; i++) {
			Element wave = doc.createElement(WAVE_NODE);
			wave.setAttribute(ZOMBIE, this.waves[i].name());
			wave.setAttribute(SPAWN, Integer.toString(this.waveSpawn[i]));
			wave.setAttribute(DELAY, Integer.toString(this.waveDelay[i]));
			waves.appendChild(wave);
		}

		root.appendChild(waves);

		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new FileOutputStream(f));
		transformer.transform(source, result);
	}

}
