package com.norway240.mifu;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

public class DList {
	
	public static void dlModlist(File modlist) throws InterruptedException, IOException{
		FileReader fr = new FileReader(modlist);
		LineNumberReader lnr = new LineNumberReader(fr);
		int totalmods = 0;
        while (lnr.readLine() != null){
         	totalmods++;
        }
        lnr.close();
        System.out.println("Reading modlist");
        System.out.println("Reading modlist\n");
        System.out.println("Total number of mods to download: " + totalmods);
	    System.out.println("Directory set to: "+MIFU.mifudir);
        
		try {
			BufferedReader cfgFile = new BufferedReader(new FileReader(modlist));
			String line = null;
			int currmod = 0;
			new File(MIFU.mifudir.toString()).mkdirs();
			while ((line = cfgFile.readLine()) != null) {
				line.trim();
			    String [] modlst = line.split(","); 
			    String link = modlst[0];
			    String save = modlst[1];
			    if (link.equalsIgnoreCase("forge")) {
			    	Download.downloadfile("http://files.minecraftforge.net/minecraftforge/minecraftforge-installer-"+save+".jar", MIFU.mifudir+"/forge-installer-"+save+".jar");
			    	//JOptionPane.showMessageDialog(null, "MINECRAFT FORGE DETECTED\nYOU MUST INSTALL IT BEFORE YOU CAN PLAY\nTHE INSTALLER IS LOCATED AT: "+currdir+"/forge-installer-"+save+".jar", "MIFU", 1);
			    	System.out.println("Downloaded: Minecraft forge version: "+save);
			    } else {
				    Download.downloadfile(link, save);
					System.out.println("Downloaded: " + currmod + "/" + totalmods);
			    }
			    currmod++;
			}
				cfgFile.close();
			} catch (IOException e) {
				System.out.println("Unexpected File IO Error");
			}

		boolean configcheck = new File(MIFU.mifudir+"/config.zip").isFile();
	    if(configcheck) {
	    	System.out.println("Extracting config.zip");
	    	File configfolder = new File(MIFU.mifudir+"/config");
	    	configfolder.mkdir();
	    	Extract.ExtractZipFile(MIFU.mifudir+"/config.zip", MIFU.mifudir+"/config/");
	    	Thread.sleep(1000);
	    }
	}

}
