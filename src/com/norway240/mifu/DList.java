package com.norway240.mifu;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

public class DList {
	
	public static void dlModlist(File modlist, String dloc) throws InterruptedException, IOException{
		FileReader fr = new FileReader(modlist); 												//Opens the selected modlist
		LineNumberReader lnr = new LineNumberReader(fr); 										//Used to read the modlist
		int totalmods = 0; 																		//Start the mod counting at 0 of course
        while (lnr.readLine() != null){ 														//Count the amount of mods in the modlist
         	totalmods++;
        }
        lnr.close(); 																			//Closes the line number reader because it is not longer needed
        System.out.println("Reading modlist");
        System.out.println("Total number of mods to download: " + totalmods);
	    System.out.println("Directory set to: "+dloc);
	    MIFU.progress.setMaximum(totalmods); 													//Set the max of the progressbar to how many mods there are to download
        
		try {
			BufferedReader cfgFile = new BufferedReader(new FileReader(modlist)); 				//Used to read the modlist
			String line = null; 																//The line of the modlist it is on
			int currmod = 0; 																	//The current mod it is downloading
			new File(MIFU.dlDir.toString()).mkdirs();	 										//Creates the path to where it will download if it dosen't exist
			while ((line = cfgFile.readLine()) != null) { 										//This loops until it reaches the end of the modlist
				line.trim(); 																	//Gets one line at a time
			    String [] entry = line.split(","); 												//Splits the line into 2 parts
			    if (entry[0].equalsIgnoreCase("forge")) { 											//if the first part says forge
			    	Download.downloadfile("http://files.minecraftforge.net/maven/net/minecraftforge/forge/"+entry[1]+"/forge-"+entry[1]+"-installer.jar", dloc, "/forge-installer-"+entry[1]+".jar");
			    	System.out.println("Downloaded: Minecraft forge version: "+entry[1]); 			//Downloads the forge installer
			    }else if(entry[0].equalsIgnoreCase("extract")){
			    	if(entry.length>3){
			    		Extract.ExtractZipFile(dloc, entry[1], dloc+entry[2], entry[3]);
			    	}else{
			    		Extract.ExtractZipFile(dloc, entry[1], dloc+entry[2]);
			    	}
			    }else{
				    Download.downloadfile(entry[0], dloc, entry[1]); 											//Download the mod
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
		
	}

}
