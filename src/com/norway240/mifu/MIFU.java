package com.norway240.mifu;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.File;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class MIFU {
	
	static File selectedModlist; 																		//Path to the selected modlist
	static JPanel panel = new JPanel(new GridBagLayout()); 												//Panel holds GUI elements
	static JLabel list = new JLabel("No list chosen"); 													//Displays chosen modlist
	static JLabel dir = new JLabel("No dir chosen"); 													//Displays chosen dir
	static JButton chmods = new JButton("Choose modlist"); 												//Choose modlist button
	static JButton chdir = new JButton("Choose dir"); 													//Choose dir button
	static JButton dlmods = new JButton("Download"); 													//Download button
	static JProgressBar progress = new JProgressBar(0,100); 											//Progressbar
	static String dldir = CONSTS.MIFUDIR.toString(); 													//Download dir
	
	private static void addSomething(Component comp, String type, int x, int y, int width){				//adds a component to the GUI
		if(type.equalsIgnoreCase("button")){ 															//If the component to be added is considered a button
			ButtonActionListener bal = new ButtonActionListener(); 										//Button action listener
			((AbstractButton) comp).addActionListener(bal.act); 										//Add the action listener to it
		}
		GridBagConstraints c = new GridBagConstraints(); 												//Layout stuff
		c.fill = GridBagConstraints.HORIZONTAL; 														//Fills the cell in the layout
		c.gridx = x; 																					//X position in the layout
		c.gridy = y; 																					//Y position in the layout
		c.gridwidth = width; 																			//How many cells to span across
		panel.add(comp,c); 																				//Add the component to the panel
	}
	
	public static void main(String[] args){ 															//The actual program
		JFrame frame = new JFrame("MIFU "+CONSTS.MIFUV); 												//The GUI
		progress.setStringPainted(true); 																//So the progressbar can have text on it
		progress.setString("Click download to begin"); 													//Info on the bar
		
		/*
		 * Setting the default modlist and directory
		 */
		selectedModlist = new File(CONSTS.MIFUDIR+"/modlist/modlist.txt");
		System.out.println("Default Selected: "+selectedModlist);
		list.setText("Default Selected: "+selectedModlist);
		dldir = CONSTS.MIFUDIR.toString();
		System.out.println("Default Selected: "+dldir);
		dir.setText("Default Selected: "+dldir);
		
		/*
		 * If this is the first time running MIFU, This downloads our default modlist and creates the default dir
		 */
		if (!CONSTS.MIFUDIR.exists()){
			CONSTS.MIFUDIR.mkdir(); 																	//Creates the default dir
			File modlists = new File(CONSTS.MIFUDIR.toString()+"/modlist"); 							//Defines the default modlist folder
			modlists.mkdir(); 																			//Creates the default modlist folder
			File mods = new File(CONSTS.MIFUDIR.toString()+"/mods"); 									//Defines the default mods folder
			mods.mkdir(); //Creates the default mods folder
			Download.downloadfile("http://deciliter.bloccrew.com/modlist.txt", "/modlist/modlist.txt"); //Downloads our default modlist.txt
			System.out.println("MIFU Directory created");
		}
		
		/*
		 * Add things to the GUI
		 */
		addSomething(chmods,"button",0,0,1);
		addSomething(list,"label",0,1,1);
		addSomething(chdir,"button",0,2,1);
		addSomething(dir,"label",0,3,1);
		addSomething(dlmods,"button",0,4,1);
		addSomething(progress,"progress",0,5,2);
		frame.add(panel);
		
		/*
		 * Set GUI defaults
		 */
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500,200);
		frame.setVisible(true); 																		//Display the GUI
		
		System.out.println(CONSTS.MIFUDIR);
	}

}