package com.norway240.mifu;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Updater {
	private static void restartMIFU(){
		try {
			@SuppressWarnings("unused")
			Process proc = Runtime.getRuntime().exec("java -jar "+System.getProperty("user.dir")+"/MIFU.jar");
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.exit(0);
	}
	
	private static void updateMIFU(){
		Download.downloadfile("http://filez.norway240.com/MIFU.jar", System.getProperty("user.dir"), "/MIFU.jar");
		restartMIFU();
	}
	
	public static void checkForMIFUUpdate(){
		//Check latest version available
		String latest = null;
		Download.downloadfile("http://filez.norway240.com/mifuv.txt", CONSTS.MIFUDIRS, "/version.txt");
		try {
			File file = new File(CONSTS.MIFUDIR+"/version.txt");
			FileReader fileReader = new FileReader(file);
			StringBuffer stringBuffer = new StringBuffer();
			int numCharsRead;
			char[] charArray = new char[1024];
			while ((numCharsRead = fileReader.read(charArray)) > 0) {
				stringBuffer.append(charArray, 0, numCharsRead);
			}
			fileReader.close();
			latest = stringBuffer.toString();
			System.out.println("Latest version: v"+latest);
		} catch(IOException e) {
			e.printStackTrace();
		}
		//Compare versions
		String[] lversion = latest.split("\\.");
		int lmajor = Integer.parseInt(lversion[0]);
	    int lminor = Integer.parseInt(lversion[1]);
	    int lpatch = Integer.parseInt(lversion[2]);
	    if(lmajor > CONSTS.MAJOR){
	    	System.out.println("NEW MAJOR");
	    	updateMIFU();
	    }else if(lminor > CONSTS.MINOR){
	    	System.out.println("NEW MINOR");
	    	updateMIFU();
	    }else if(lpatch > CONSTS.PATCH){
	    	System.out.println("NEW PATCH");
	    	updateMIFU();
	    }else{
	    	System.out.println("MIFU IS UP TO DATE");
	    }
	}
}
