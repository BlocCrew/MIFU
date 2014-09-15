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
        System.out.println("Total number of mods to download: " + totalmods);
	    System.out.println("Directory set to: "+MIFU.dldir);
	    MIFU.progress.setMaximum(totalmods);
        
		try {
			BufferedReader cfgFile = new BufferedReader(new FileReader(modlist));
			String line = null;
			int currmod = 0;
			new File(MIFU.dldir.toString()).mkdirs();
			while ((line = cfgFile.readLine()) != null) {
				line.trim();
			    String [] modlst = line.split(","); 
			    String link = modlst[0];
			    String save = modlst[1];
			    if (link.equalsIgnoreCase("forge")) {
			    	Download.downloadfile("http://files.minecraftforge.net/minecraftforge/minecraftforge-installer-"+save+".jar", "/forge-installer-"+save+".jar");
			    	System.out.println("Downloaded: Minecraft forge version: "+save);
			    } else {
				    Download.downloadfile(link, save);
					System.out.println("Downloaded: " + currmod + "/" + totalmods);
				    MIFU.progress.setString("Downloaded: " + currmod + "/" + totalmods);
				    MIFU.progress.setValue(currmod);
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
