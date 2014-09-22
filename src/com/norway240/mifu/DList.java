package com.norway240.mifu;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

import javax.swing.JOptionPane;

public class DList {
	
	public static void dlModlist(File modlist) throws InterruptedException, IOException{
		FileReader fr = new FileReader(modlist); 												//Opens the selected modlist
		LineNumberReader lnr = new LineNumberReader(fr); 										//Used to read the modlist
		int totalmods = 0; 																		//Start the mod counting at 0 of course
        while (lnr.readLine() != null){ 														//Count the amount of mods in the modlist
         	totalmods++;
        }
        lnr.close(); 																			//Closes the line number reader because it is not longer needed
        System.out.println("Reading modlist");
        System.out.println("Total number of mods to download: " + totalmods);
	    System.out.println("Directory set to: "+MIFU.dlDir);
	    MIFU.progress.setMaximum(totalmods); 													//Set the max of the progressbar to how many mods there are to download
        
		try {
			BufferedReader cfgFile = new BufferedReader(new FileReader(modlist)); 				//Used to read the modlist
			String line = null; 																//The line of the modlist it is on
			int currmod = 0; 																	//The current mod it is downloading
			new File(MIFU.dlDir.toString()).mkdirs();	 										//Creates the path to where it will download if it dosen't exist
			while ((line = cfgFile.readLine()) != null) { 										//This loops until it reaches the end of the modlist
				line.trim(); 																	//Gets one line at a time
			    String [] modlst = line.split(","); 											//Splits the line into 2 parts
			    String link = modlst[0]; 														//The link
			    String save = modlst[1]; 														//The location/filename
			    if (link.equalsIgnoreCase("forge")) { 											//if the first part says forge
			    	Download.downloadfile("http://files.minecraftforge.net/minecraftforge/minecraftforge-installer-"+save+".jar", MIFU.dlDir, "/forge-installer-"+save+".jar");
			    	System.out.println("Downloaded: Minecraft forge version: "+save); 			//Downloads the forge installer
			    }else if(link.equalsIgnoreCase("extract")){
			    	//TODO: Extract any specified zip file
			    }else if(link.equalsIgnoreCase("thanks")){
			    	String [] authorsArr = save.split("\\|");
			    	String authors = authorsArr[0] + ", ";
			    	for(int i=1;i<authorsArr.length-1;i++){
			    		authors = authors + authorsArr[i] + ", ";
			    	}
			    	authors = authors + "and " + authorsArr[authorsArr.length-1];
			    	String thanks = "Thanks to: " + authors + " for making these mods possible!";
			    	JOptionPane.showMessageDialog(MIFU.frame, "<html><body><p style='width: 400px;text-align:center;'>"+thanks+"</body></html>", "Thanks!", JOptionPane.PLAIN_MESSAGE);
			    }else{
				    Download.downloadfile(link, MIFU.dlDir, save); 											//Download the mod
				}
			    currmod++; 																		//Go on to the next mod
			    MIFU.progress.setValue(currmod); 												//Set the progressbar to how many mods have been downloaded
				System.out.println("Downloaded: " + currmod + "/" + totalmods);
			    MIFU.progress.setString("Downloaded: " + currmod + "/" + totalmods); 			//Sets the text on the progressbar
			    if(currmod==totalmods){
			    	MIFU.progress.setString("Done!");
			    }
			}
			cfgFile.close(); 																	//Close the file
		} catch (IOException e) {
			System.out.println("Unexpected File IO Error");
		}

		boolean configcheck = new File(MIFU.dlDir+"/config.zip").isFile(); 					//Check if there is a config.zip
	    if(configcheck) { 																		//If there is
	    	System.out.println("Extracting config.zip");
	    	File configfolder = new File(MIFU.dlDir+"/config"); 							//Define a config folder
	    	configfolder.mkdir(); 																//Create the config folder
	    	Extract.ExtractZipFile(MIFU.dlDir+"/config.zip", MIFU.dlDir+"/config/"); 	//Extract the zip to it
	    	Thread.sleep(1000);
	    }
	}

}
