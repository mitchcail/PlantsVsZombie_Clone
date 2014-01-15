package ca.carleton.sysc3110.project.views.swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ListIterator;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import ca.carleton.sysc3110.project.models.levels.CustomXMLLevel;
import ca.carleton.sysc3110.project.models.plants.PlantType;
import ca.carleton.sysc3110.project.models.zombies.ZombieType;
import ca.xnor.utils.Pair;

// TODO: Auto-generated Javadoc
/**
 * The Class LevelCreatorGUI. This class acts as
 * a self-contained GUI with the functionality to
 * create levels and save them to XML files, which
 * can then be opened.
 * 
 * @author Ian Wong
 */
public class LevelCreatorGUI{

	/** The frame. */
	private JFrame frame;
	
	/** The panels. */
	private JPanel panel1, panel2, panel3, panel4;
	
	/** The info lists that are used to populate the GUI items. */
	private ZombieType[] zombieData;
	
	/** The plant data. */
	private PlantType[] plantData;
	
	/** The row num. */
	private Integer[] rowNum;

	/** The zombie num. */
	DefaultComboBoxModel<Integer> zombieNum;
	
	/** The zombies. */
	private DefaultListModel<Pair<Integer, ZombieType>> zombies;
	
	/** The lists and input boxes. */
	private JComboBox<Integer> rowCombo;
	
	/** The zombie combo. */
	private JComboBox<ZombieType> zombieCombo;
	
	/** The num zombie combo. */
	private JComboBox<Integer> numZombieCombo;
	
	/** The zombie list. */
	private JList<Pair<Integer,ZombieType>> zombieList;
	
	/** The plant list. */
	private JList<PlantType> plantList;
	
	/** The plant scroll. */
	private JScrollPane zombieScroll, plantScroll;
	
	/** The sun text. */
	private JTextField sunText;
	
	/** Add, Remove and Save buttons. */
	private JButton addButton, removeButton, saveButton;
	
	/** The labels. */
	private JTextArea rowLabel, zombieLabel, plantLabel, sunLabel;
	
	/**
	 * Instantiates a new view.
	 */
	public LevelCreatorGUI()
	{
		// initializes the labels
		rowLabel = new JTextArea("Select the number of rows: ");
		rowLabel.setLineWrap(true);
		rowLabel.setWrapStyleWord(true);
		rowLabel.setBorder(BorderFactory.createLineBorder(Color.black));
		zombieLabel = new JTextArea("Select the zombie that you want to spawn and a number of zombies to "
				+ "spawn in the order that each wave will appear: ");
		zombieLabel.setLineWrap(true);
		zombieLabel.setWrapStyleWord(true);
		zombieLabel.setBorder(BorderFactory.createLineBorder(Color.black));
		plantLabel = new JTextArea("Select the plants that should be available for the player to purchase: ");
		plantLabel.setLineWrap(true);
		plantLabel.setWrapStyleWord(true);
		plantLabel.setBorder(BorderFactory.createLineBorder(Color.black));
		sunLabel = new JTextArea("Enter the number of Sun Points the player should start with (0-300): ");
		sunLabel.setLineWrap(true);
		sunLabel.setWrapStyleWord(true);
		sunLabel.setBorder(BorderFactory.createLineBorder(Color.black));

		// initialize the actual data fields
		rowNum = new Integer[]{1,2,3,4,5};
		zombieNum = new DefaultComboBoxModel<Integer>(new Integer[]{1});
		zombieData = ZombieType.values();
		int n = 0;
		plantData = new PlantType[PlantType.values().length - 1];
		for(int i = 0; i < PlantType.values().length; i++)
		{
			if(PlantType.values()[i] != PlantType.NONE)
			{
				plantData[n] = PlantType.values()[i];
				n++;
			}
		}
		zombies = new DefaultListModel<Pair<Integer, ZombieType>>();

		// set up the inputs
		zombieCombo = new JComboBox<ZombieType>(zombieData);
		zombieCombo.setSelectedIndex(0);
		
		numZombieCombo = new JComboBox<Integer>(zombieNum);
		numZombieCombo.setSelectedIndex(0);
		zombieNum.addListDataListener(numZombieCombo);
		
		rowCombo = new JComboBox<Integer>(rowNum);
		rowCombo.setSelectedIndex(0);
		rowCombo.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				 // get the current value of row to populate the zombieNum and zombieCombo
				zombieNum.removeAllElements();
				
				// populates the Integer array
				for(int i = 0; i < (Integer)rowCombo.getSelectedItem(); i++)
				{
					zombieNum.addElement(i + 1);;
					// repopulates the zombieCombo
				}
				numZombieCombo.setSelectedIndex(0);
				
			}
		});
		
		
		zombieList = new JList<Pair<Integer, ZombieType>>(zombies);
		zombieList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		zombieScroll = new JScrollPane(zombieList);
		
		plantList = new JList<PlantType>(plantData);
		plantScroll = new JScrollPane(plantList);
		
		sunText = new JTextField();
		
		addButton = new JButton("ADD");
		addButton.setVisible(true);
		addButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				 // add the entry to the zombie list
				Pair<Integer, ZombieType> tempPair = new Pair<Integer, ZombieType>((Integer)numZombieCombo.getSelectedItem(), (ZombieType)zombieCombo.getSelectedItem());
				zombies.addElement(tempPair);
			}
		});
		
		removeButton = new JButton("REMOVE");
		removeButton.setVisible(true);
		removeButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				// remove any selected indices
				if(!zombies.isEmpty() && zombieList.getSelectedIndex() >= 0)
					zombies.removeElementAt(zombieList.getSelectedIndex());
			}
		});
		
		saveButton = new JButton("SAVE");
		saveButton.setVisible(true);
		saveButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					 // if there are no zombie waves, error
			         if(zombies.isEmpty())
			         {
			        	 JOptionPane.showMessageDialog(null, "You must have at least one zombie wave in a level.");
			         }
			         // if there are no plants chosen, error
			         else if(plantList.getSelectedIndices().length <= 0)
			         {
			        	 JOptionPane.showMessageDialog(null, "You must have at least one available plant in a level.");
			         }
			         // otherwise create the xml file if the textfield is fine
			         else
			         {
			        	 int temp = 0;
			        	 try
			        	 {
			        		 temp = Integer.parseInt(sunText.getText());
			        	 }
			        	 catch(NumberFormatException r)
			        	 {
			        		 JOptionPane.showMessageDialog(null, "The textfield must be filled with a number.");
			        		 return;
			        	 }
			        	 if(temp < 0 || temp > 300)
			        	 {
			        		 JOptionPane.showMessageDialog(null, "The amount of Sun Points must be between 0 and 300.");
			        	 }
			        	 // parse a file
			        	 else
			        	 {
			        		 ZombieType[] zombieArray = new ZombieType[zombies.size()];
			        		 int[] waveArray = new int[zombies.size()];
			        		 int[] delay = new int[zombies.size()];
			        		 for(int i = 0; i < zombies.size(); i++)
			        		 {
			        			 zombieArray[i] = zombies.elementAt(i).second;
			        			 waveArray[i] = Integer.valueOf(zombies.elementAt(i).first);
			        			 delay[i] = 5;
			        		 }
			        		 
			        		 PlantType[] plants = new PlantType[plantList.getSelectedValuesList().size()];
			        		 int j = 0;
			        		 ListIterator<PlantType> iter = plantList.getSelectedValuesList().listIterator();
			        		 while(iter.hasNext())
			        		 {
			        			 plants[j] = iter.next();
			        			 j++;
			        		 }
			        		 
			        		 int rows = (Integer)rowCombo.getSelectedItem();
			        		 int offset = 0;
			        		 switch(rows)
			        		 {
			        		 case 1:
			        			 offset = 3;
			        			 break;
			        		 case 2:
			        		 case 3:
			        			 offset = 2;
			        			 break;
			        		 case 4:
			        		 case 5:
			        			 offset = 1;
			        			 break;
			        		 }
			        		 
			        		 CustomXMLLevel manMadeLevel = new CustomXMLLevel(rows, false, 0, zombieArray, waveArray, delay, plants, temp, offset);
			        		 
			        		 final JFileChooser fc = new JFileChooser();
			        		 
			        		 int returnVal = fc.showSaveDialog(null);

			        		 if (returnVal == JFileChooser.APPROVE_OPTION) 
			        		 {
			        			 File file = fc.getSelectedFile();
				        		 try 
				        		 {
				        			 manMadeLevel.saveToFile(file);
				        			 frame.dispose();
				        		 } catch (FileNotFoundException e1) {
				        			 JOptionPane.showMessageDialog(null, "File not found.");
				        		 } catch (ParserConfigurationException e1) {
				        			 JOptionPane.showMessageDialog(null, "Parsing error.");
				        		 } catch (TransformerException e1) {
				        			 JOptionPane.showMessageDialog(null, "Transformers, robots in disguise.");
				        		 }
			        		 }
			        	 }
			         }
				}
		}); 
		
		// intializes the frame with a BoxLayout in the center of the screen
		frame = new JFrame();
		frame.setSize(600, 600);
		
		// initializes the panel
		panel1 = new JPanel(new GridLayout(0,2));
		panel1.setVisible(true);
		panel3 = new JPanel(new GridLayout(0,2));
		panel3.setVisible(true);
		panel2 = new JPanel(new GridLayout(0,3));
		panel2.setVisible(true);
		panel4 = new JPanel();
		panel4.setVisible(true);
		
		// add the textfield, lists and labels to the panel
		panel1.add(rowLabel);
		panel1.add(rowCombo);
		panel1.add(sunLabel);
		panel1.add(sunText);
		panel3.add(plantLabel);
		panel3.add(plantScroll);
		zombieLabel.setPreferredSize(new Dimension(300, 100));
		panel2.add(zombieLabel);
		panel2.add(zombieCombo);
		panel2.add(numZombieCombo);
		panel2.add(addButton);
		panel2.add(removeButton);
		panel2.add(saveButton);
		zombieScroll.setPreferredSize(new Dimension(590, 140));
		panel4.add(zombieScroll);
		
		// initializes the last of the frame
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// set the contentPane and layout
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.PAGE_AXIS));
		frame.getContentPane().add(panel1);
		frame.getContentPane().add(panel3);
		frame.getContentPane().add(panel2);
		frame.getContentPane().add(panel4);
		frame.setVisible(true);
		
	}
}