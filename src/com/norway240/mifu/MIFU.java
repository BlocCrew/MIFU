package com.norway240.mifu;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.io.File;

import javax.swing.AbstractButton;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
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
	static JFrame frame = new JFrame("MIFU "+CONSTS.MIFUV);
	static JPanel panel = new JPanel(new GridBagLayout()); 												//Panel holds GUI elements
	static JTextArea list = new JTextArea("No list chosen",4,4); 													//Displays chosen modlist
	static JTextArea dir = new JTextArea("No dir chosen",4,4); 													//Displays chosen dir
	static JTextArea error = new JTextArea(2,2);
	static JButton chMods = new JButton("CHOOSE MODLIST"); 												//Choose modlist button
	static JButton chDir = new JButton("CHOOSE DIR"); 													//Choose dir button
	static JButton dlMods = new JButton("Download"); 													//Download button
	static JProgressBar progress = new JProgressBar(0,100); 											//Progressbar
	static String dlDir = CONSTS.MIFUDIR.toString(); 													//Download dir
	static JTextArea modsDisplay = new JTextArea(22,25);
	static JCheckBox cprofile = new JCheckBox("Create Minecraft Launcher Profile");
	static boolean createProfile = false;
	static String mName = "mifu";
	
	public static void addSomething(Component comp, String type, int x, int y, int width, int height){ //adds a component to the GUI
		ButtonActionListener bal = new ButtonActionListener(); 											//Button action listener
		if(type.equalsIgnoreCase("button")){ 															//If the component to be added is considered a button
			((AbstractButton) comp).addActionListener(bal.act); 										//Add the action listener to it
		}else if(type.equalsIgnoreCase("chkbox")){
			((JCheckBox) comp).addActionListener(bal.act);
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
		dlDir = CONSTS.MIFUDIR.toString();
		System.out.println("Default Selected: "+dlDir);
		dir.setText(dlDir);
		
		/*
		 * If this is the first time running MIFU, This creates the default dir
		 */
		if (!CONSTS.MIFUDIR.exists()){
			CONSTS.MIFUDIR.mkdir(); 																	//Creates the default dir
			new File(CONSTS.MIFUDIRS+"/modlist").mkdir();
			System.out.println("MIFU Directory created");
		}
		
		//Downloads our default modlist.txt
		Download.downloadfile(CONSTS.ourModlist, CONSTS.MIFUDIRS, "/modlist/modlist.txt");
		//Check for update
		Updater.checkForMIFUUpdate();

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
		
		/*
		 * Create the layout
		 */
		addSomething(mifudesc,"label",0,0,4,1);
		addSomething(Box.createHorizontalStrut(20),"spacer",1,0,1,10);
		addSomething(modslbl,"label",5,0,1,1);
		addSomething(scroll,"textbox",5,2,1,13);
		
		addSomething(Box.createVerticalStrut(10),"spacer",0,1,5,1);
		
		addSomething(listlbl,"label",0,2,4,1);
		addSomething(list,"label",0,3,3,1);
		
		addSomething(Box.createVerticalStrut(10),"spacer",0,4,4,1);
		
		addSomething(dirlbl,"label",0,5,4,1);
		addSomething(dir,"label",0,6,3,1);
		
		addSomething(Box.createVerticalStrut(30),"spacer",0,7,4,1);
		
		addSomething(error,"label",0,8,3,1);

		addSomething(Box.createVerticalStrut(30),"spacer",0,9,4,1);

		//addSomething(cprofile, "chkbox", 0,10,3,1);
		addSomething(Box.createVerticalStrut(10),"spacer",0,10,3,1);
		
		addSomething(chMods,"button",0,11,1,1);
		addSomething(Box.createHorizontalStrut(10),"spacer",1,11,1,1);
		addSomething(chDir,"button",2,11,1,1);
		addSomething(Box.createHorizontalStrut(10),"spacer",3,11,1,1);

		addSomething(Box.createVerticalStrut(10),"spacer",0,12,4,1);
		
		addSomething(dlMods,"button",0,13,3,1);
		
		addSomething(Box.createVerticalStrut(10),"spacer",0,14,4,1);

		addSomething(progress,"progress",0,15,6,1);
		
		
		frame.add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setSize(frame.getWidth()+10,frame.getHeight()+10);
		frame.setResizable(false);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
		frame.setVisible(true); 																		//Display the GUI
		
		System.out.println(CONSTS.MIFUDIR);
	}

}