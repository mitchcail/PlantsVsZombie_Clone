package ca.carleton.sysc3110.project.views.swing;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import ca.carleton.sysc3110.project.models.zombies.ZombieType;

// TODO: Auto-generated Javadoc
/**
 * A factory for creating ZombieView objects.
 *
 * @author Nikola Neskovic
 * A class for producing visual zombies. Will return a disabled JButton with a 'z' to denote a zombie
 * the letter 'Z' to denote Zombie.
 * 
 * @author John Blackwood <john@xnor.ca>
 * @author Nikola Neskovic <nick_neskov@hotmail.com>
 * @author Ian Wong <ian.m.y.wong@gmail.com>
 */
public class ZombieViewFactory {
	
	/**
	 * Creates a new ZombieView object.
	 *
	 * @param zombieType the zombie type
	 * @param row the row
	 * @param location the location
	 * @return JButton a disabled JButton with z letter denoting the type of zombie
	 */
	public static JLabel createViewZombie(ZombieType zombieType, int row, int location) {
		JLabel z = null;
		ImageIcon zombieImage;
		switch (zombieType) {
		case WALKING_UNDEAD:
			z = new JLabel("Wu");
			zombieImage = new ImageIcon("images/WALKING_UNDEAD.png");
			break;
		case RAPID_GHOUL:
			z = new JLabel("Rg");
			zombieImage = new ImageIcon("images/RAPID_GHOUL.png");
			break;
		case LUMBERING_BRUTE:
			z = new JLabel("Lb");
			zombieImage = new ImageIcon("images/LUMBERING_BRUTE.png");
			break;
		default:
			return null;
		}
		setupLabelReference(zombieImage, z, row, location);
		return z;
	}
	
	/**
	 * Setup label reference.
	 *
	 * @param zombieImage the zombie image
	 * @param z the z
	 * @param row the row
	 * @param location the location
	 */
	private static void setupLabelReference (ImageIcon zombieImage, JLabel z, int row, int location){
		z.setFont(new Font("arial", Font.BOLD, 50));
		z.setBackground(Color.black);
		z.setBounds(location + 100, row * 100, 100, 100);
		z.setVisible(true);
		z.setEnabled(true);
		z.setIcon(zombieImage);
	}
}