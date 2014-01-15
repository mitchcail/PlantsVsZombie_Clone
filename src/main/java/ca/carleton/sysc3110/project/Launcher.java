package ca.carleton.sysc3110.project;

import javax.swing.SwingUtilities;



// TODO: Auto-generated Javadoc
/**
 * Entry point for the application, it's only purpose is to spawn the controller.
 * @author John Blackwood <john@xnor.ca>
 * @author Nikola Neskovic <nick_neskov@hotmail.com>
 * @author Ian Wong <ian.m.y.wong@gmail.com>
 */
public class Launcher {

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater( new Runnable() {
			public void run() {
				new Controller();
			}
		});

	}

}
