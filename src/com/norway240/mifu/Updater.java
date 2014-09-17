package com.norway240.mifu;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Updater {
	public static void updateMIFU(){
		//Check latest version available
		String latest = null;
		Download.downloadfile("http://norway240.com/mifuv.txt", "/version.txt");
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
		} catch (IOException e) {
			e.printStackTrace();
		}
		//Compare versions
		String[] lversion = latest.split(".");
		System.out.println(lversion);
		
	    /*int lmajor = Integer.parseInt(lversion[0]);
	    int lminor = Integer.parseInt(lversion[1]);
	    int lpatch = Integer.parseInt(lversion[2]);
	    if(lmajor > CONSTS.MAJOR){
	    	System.out.println("new major");
	    }else if(lminor > CONSTS.MINOR){
	    	System.out.println("new major");
	    }else if(lpatch > CONSTS.PATCH){
	    	System.out.println("new major");
	    }*/
	}
}
