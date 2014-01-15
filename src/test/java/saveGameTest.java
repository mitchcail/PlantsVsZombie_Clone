import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ca.carleton.sysc3110.project.Controller;
import ca.carleton.sysc3110.project.models.levels.LevelTheFirst;


public class saveGameTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSaveGame() {
		LevelTheFirst l = new LevelTheFirst();
		Controller c = new Controller();
		String dirname = "/tmp";
	    File f1 = new File(dirname);
		
	    Assert.assertNotNull(c);
	    
	   // Assert.assertSame(c.saveGame(f1), c.loadGame(f1));
	    
	}
	
	@Test
	public void testLoadGame(){
		LevelTheFirst l = new LevelTheFirst();
		Controller c = new Controller();
		String dirname = "/tmp";
	    File f1 = new File(dirname);
	    
	}

}
