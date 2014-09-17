package com.norway240.mifu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ButtonActionListener {
	ActionListener act = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			if(e.getSource()==MIFU.chmods){ 															//If the "Choose modlist" button was clicked
				System.out.println("CHOOSE");
				JFileChooser chooser = new JFileChooser("Choose a modlist"); 							//Create a new filechooser window
			    FileNameExtensionFilter filter = new FileNameExtensionFilter("Modlist (.txt)", "txt"); 	//Only allow .txt files
			    chooser.setFileFilter(filter); 															//Add the filter of only .txt files to the filechooser window
			    int returnVal = chooser.showOpenDialog(chooser); 										//Opens the dialog and waits for the user to open a file
			    if(returnVal == JFileChooser.APPROVE_OPTION) { 											//If the choose to open a file and not cancel
			    	MIFU.selectedModlist = chooser.getSelectedFile().getAbsoluteFile(); 				//Set the selected modlist to what they chose
					System.out.println("Chosen: "+chooser.getSelectedFile().getAbsolutePath());
					MIFU.list.setText("Selected: "+chooser.getSelectedFile().getAbsolutePath());
			    }
			}else if(e.getSource()==MIFU.chdir){ 														//If the "Choose dir" button was clicked
				System.out.println("CHOOSEDIR");
				JFileChooser chooser = new JFileChooser("Choose a dir"); 								//Create a new filechooser window
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); 							//Only allow choosing directories
			    int returnVal = chooser.showOpenDialog(chooser); 										//Opens the window and waits for the user to find a dir
			    if(returnVal == JFileChooser.APPROVE_OPTION) { 											//If they open a dir
			    	MIFU.dldir = chooser.getSelectedFile().getAbsolutePath(); 							//Set the download directory to that dir
			    	File chosendirfile = chooser.getSelectedFile().getAbsoluteFile();					//Creates a variable chosendirfile, that contains the File of the chosen dir
			    	String chosendir = chosendirfile.getAbsolutePath();									//Creates a variable chosendir, that contains the String of the chosen dir
			    	System.out.println("Chosen: "+chosendir);
					MIFU.dir.setText("Selected: "+chosendir);
					chosendirfile.mkdirs();																//Makes the path leading to the selected folder
					File modsfolder = new File(chosendir+"/mods"); 										//Create a mods folder (There has been an issue with this not creating otherwise)
					modsfolder.mkdir(); 																//Creates the mods folder inside the dir the user selected
			    }
			}else if(e.getSource()==MIFU.dlmods){ 														//If the clicked the "Download" button
				System.out.println("DOWNLOAD");
				//MIFU.progress.setIndeterminate(true);
				new Thread(new DThread()).start(); 														//Runs the process of downloading mods
			}
		}
	};
}
