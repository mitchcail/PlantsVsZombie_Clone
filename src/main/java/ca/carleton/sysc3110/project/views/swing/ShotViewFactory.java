package ca.carleton.sysc3110.project.views.swing;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

import ca.carleton.sysc3110.project.models.shots.ShotType;

// TODO: Auto-generated Javadoc
/**
 * A factory for creating Shot objects.
 * @author John Blackwood <john@xnor.ca>
 * @author Nikola Neskovic <nick_neskov@hotmail.com>
 * @author Ian Wong <ian.m.y.wong@gmail.com>
 */
public class ShotViewFactory {

	/**
	 * Creates a new Shot object.
	 *
	 * @param shotType the shot type
	 * @param row the row
	 * @param location the location
	 * @return the shot
	 */
	public static JLabel createViewShot(ShotType shotType, int row, int location) {
		JLabel s;
		//ImageIcon shotImage;
		switch (shotType) {
		case SEED:
			s = new JLabel("o");
			s.setFont(new Font("arial", Font.BOLD, 50));
			s.setBackground(Color.black);
			s.setBounds(location + 100, row * 100, 100, 100);
			s.setVisible(true);
			s.setEnabled(true);
			return s;
		case NONE:
		default:
			return null;
		}
	}

}