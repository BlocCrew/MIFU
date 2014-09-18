package com.norway240.mifu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ButtonActionListener {
	ActionListener act = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			JFileChooser chooser = new JFileChooser();
			chooser.setCurrentDirectory(CONSTS.MIFUDIR);
			if(e.getSource()==MIFU.chmods){ 															//If the "Choose modlist" button was clicked		
				System.out.println("CHOOSE");
				String modlistlink = JOptionPane.showInputDialog("If you want to download a modlist, you can do so here\n"
						+ "Just enter the link to that modlist.txt\n\n"
						+ "If you just want to find a modlist.txt on your computer\n"
						+ "Just click cancel and you can do that next");
				try {
					URL mlink = new URL(modlistlink);
					Download.downloadfile(mlink.toString(), CONSTS.MIFUDIRS, "/modlist/usermodlist.txt");
					MIFU.selectedModlist = new File(CONSTS.MIFUDIR+"/modlist/usermodlist.txt");
					System.out.println("Chosen: "+modlistlink);
					MIFU.list.setText(modlistlink);
				} catch (MalformedURLException e1) {
					e1.printStackTrace();
					chooser.setDialogTitle("Choose a modlist");
				    FileNameExtensionFilter filter = new FileNameExtensionFilter("Modlist (.txt)", "txt"); 	//Only allow .txt files
				    chooser.setFileFilter(filter); 															//Add the filter of only .txt files to the filechooser window
				    int returnVal = chooser.showOpenDialog(chooser); 										//Opens the dialog and waits for the user to open a file
				    if(returnVal == JFileChooser.APPROVE_OPTION) { 											//If the choose to open a file and not cancel
				    	MIFU.selectedModlist = chooser.getSelectedFile().getAbsoluteFile(); 				//Set the selected modlist to what they chose
						System.out.println("Chosen: "+MIFU.selectedModlist.toString());
						MIFU.list.setText(MIFU.selectedModlist.toString());
				    }
				}
			}else if(e.getSource()==MIFU.chdir){ 														//If the "Choose dir" button was clicked
				System.out.println("CHOOSEDIR");
				chooser.setDialogTitle("Choose a dir");
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); 							//Only allow choosing directories
			    int returnVal = chooser.showOpenDialog(chooser); 										//Opens the window and waits for the user to find a dir
			    if(returnVal == JFileChooser.APPROVE_OPTION) { 											//If they open a dir
			    	MIFU.dldir = chooser.getSelectedFile().getAbsolutePath(); 							//Set the download directory to that dir
					System.out.println("Chosen: "+chooser.getSelectedFile().getAbsolutePath());
					MIFU.dir.setText(chooser.getSelectedFile().getAbsolutePath());
					chooser.getSelectedFile().getAbsoluteFile().mkdirs();
					File modsfolder = new File(chooser.getSelectedFile().getAbsolutePath()+"/mods"); 	//Create a mods folder (There has been an issue with this not creating otherwise)
					modsfolder.mkdir(); 																//Creates the mods folder inside the dir the user selected
			    }
			}else if(e.getSource()==MIFU.dlmods){ 														//If the clicked the "Download" button
				System.out.println("DOWNLOAD");
				new Thread(new DThread()).start(); 														//Runs the process of downloading mods
			}
		}
	};
}
