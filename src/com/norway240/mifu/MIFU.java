package com.norway240.mifu;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.File;

import javax.swing.AbstractButton;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class MIFU {

	static int style = Font.PLAIN;
	static Font font = new Font("Verdana", style, 18);
	static File selectedModlist; 																		//Path to the selected modlist
	static JPanel panel = new JPanel(new GridBagLayout()); 												//Panel holds GUI elements
	static JTextArea list = new JTextArea("No list chosen",4,4); 													//Displays chosen modlist
	static JTextArea dir = new JTextArea("No dir chosen",4,4); 													//Displays chosen dir
	static JTextArea error = new JTextArea(2,2);
	static JButton chmods = new JButton("CHOOSE MODLIST"); 												//Choose modlist button
	static JButton chdir = new JButton("CHOOSE DIR"); 													//Choose dir button
	static JButton dlmods = new JButton("Download"); 													//Download button
	static JProgressBar progress = new JProgressBar(0,100); 											//Progressbar
	static String dldir = CONSTS.MIFUDIR.toString(); 													//Download dir
	static JTextArea modsDisplay = new JTextArea(22,25);
	
	public static void addSomething(Component comp, String type, int x, int y, int width, int height){ //adds a component to the GUI
		if(type.equalsIgnoreCase("button")){ 															//If the component to be added is considered a button
			ButtonActionListener bal = new ButtonActionListener(); 										//Button action listener
			((AbstractButton) comp).addActionListener(bal.act); 										//Add the action listener to it
		}
		GridBagConstraints c = new GridBagConstraints(); 												//Layout stuff
		c.fill = GridBagConstraints.HORIZONTAL; 														//Fills the cell in the layout
		c.anchor = GridBagConstraints.WEST;
		c.gridx = x; 																					//X position in the layout
		c.gridy = y; 																					//Y position in the layout
		c.gridwidth = width; 																			//How many cells to span horizontal
		c.gridheight = height;																			//How many cells to span vertical
		panel.add(comp,c); 																				//Add the component to the panel
	}
	
	public static void main(String[] args){ 															//The actual program
		Updater.checkForMIFUUpdate();
		JFrame frame = new JFrame("MIFU "+CONSTS.MIFUV); 												//The GUI
		progress.setStringPainted(true); 																//So the progressbar can have text on it
		progress.setString("Click download to begin"); 													//Info on the bar
		list.setEditable(false);
		list.setLineWrap(true);
		dir.setEditable(false);
		dir.setLineWrap(true);
		modsDisplay.setLineWrap(true);
		modsDisplay.setEditable(false);
		error.setEditable(false);
		error.setLineWrap(true);

		selectedModlist = new File(CONSTS.MIFUDIR+"/modlist/modlist.txt");
		System.out.println("Default Selected: "+selectedModlist);
		list.setText(selectedModlist.toString());
		dldir = CONSTS.MIFUDIR.toString();
		System.out.println("Default Selected: "+dldir);
		dir.setText(dldir);
		
		/*
		 * If this is the first time running MIFU, This downloads our default modlist and creates the default dir
		 */
		if (!CONSTS.MIFUDIR.exists()){
			CONSTS.MIFUDIR.mkdir(); 																	//Creates the default dir
			File modlists = new File(CONSTS.MIFUDIR.toString()+"/modlist"); 							//Defines the default modlist folder
			modlists.mkdir(); 																			//Creates the default modlist folder
			File mods = new File(CONSTS.MIFUDIR.toString()+"/mods"); 									//Defines the default mods folder
			mods.mkdir(); 																				//Creates the default mods folder
			Download.downloadfile("http://dl.bloccrew.com/modlist.txt", CONSTS.MIFUDIRS, "/modlist/modlist.txt"); //Downloads our default modlist.txt
			System.out.println("MIFU Directory created");
		}
		
		/*
		 * GUI
		 */
		JLabel mifudesc = new JLabel("Mod Installer From URL");
		JLabel modslbl = new JLabel("Downloads:");
		JLabel listlbl = new JLabel("Modlist:");
		JLabel dirlbl = new JLabel("Directory:");
		JScrollPane scroll = new JScrollPane(modsDisplay);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		mifudesc.setFont(font);
		modslbl.setFont(font);
		listlbl.setFont(font);
		dirlbl.setFont(font);
		Color color = new Color(frame.getBackground().getRGB());
		list.setBackground(color);
		dir.setBackground(color);
		error.setBackground(color);
		
		addSomething(mifudesc,"label",0,0,4,1);
		addSomething(Box.createHorizontalStrut(20),"spacer",1,0,1,10);
		addSomething(modslbl,"label",5,0,1,1);
		addSomething(scroll,"textbox",5,2,1,11);
		
		addSomething(Box.createVerticalStrut(10),"spacer",0,1,5,1);
		
		addSomething(listlbl,"label",0,2,4,1);
		addSomething(list,"label",0,3,3,1);
		
		addSomething(Box.createVerticalStrut(10),"spacer",0,4,4,1);
		
		addSomething(dirlbl,"label",0,5,4,1);
		addSomething(dir,"label",0,6,3,1);
		
		addSomething(Box.createVerticalStrut(30),"spacer",0,7,4,1);
		
		addSomething(error,"label",0,8,3,1);

		addSomething(Box.createVerticalStrut(30),"spacer",0,9,4,1);
		
		addSomething(chmods,"button",0,10,1,1);
		addSomething(Box.createHorizontalStrut(10),"spacer",1,10,1,1);
		addSomething(chdir,"button",2,10,1,1);
		addSomething(Box.createHorizontalStrut(10),"spacer",3,10,1,1);

		addSomething(Box.createVerticalStrut(10),"spacer",0,11,4,1);
		
		addSomething(dlmods,"button",0,12,3,1);
		
		addSomething(Box.createVerticalStrut(10),"spacer",0,13,4,1);

		addSomething(progress,"progressbar",0,14,6,1);
		
		frame.add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setSize(frame.getWidth()+10,frame.getHeight()+10);
		frame.setVisible(true); 																		//Display the GUI
		
		System.out.println(CONSTS.MIFUDIR);
	}

}