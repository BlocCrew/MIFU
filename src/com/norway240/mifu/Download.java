package com.norway240.mifu;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class Download {
	
	static int tries = 0;
	
	public static void downloadfile(String DLURL, String DLDIR, String DLFILE) { //This downloads a mod given the url and the location/filename
		String DLOC = DLDIR+DLFILE; //The dir to download to
		
		System.out.println("URL: "+ DLURL);
		System.out.println("FILE: "+ DLOC);
		
		MIFU.modsDisplay.append(DLFILE+"\n");
		MIFU.modsDisplay.setCaretPosition(MIFU.modsDisplay.getDocument().getLength());
		
		try {
	        long startTime = System.currentTimeMillis();
	        System.out.println("Connecting..");
	        URL url = new URL(DLURL);
	        url.openConnection();
	        InputStream reader = url.openStream();
	        FileOutputStream writer = new FileOutputStream(DLOC);
	        byte[] buffer = new byte[153600];
	        int totalBytesRead = 0;
	        int bytesRead = 0;
	        while ((bytesRead = reader.read(buffer)) > 0) {  
	           writer.write(buffer, 0, bytesRead);
	           buffer = new byte[153600];
	           totalBytesRead += bytesRead;
	        }
	        long endTime = System.currentTimeMillis();
	        System.out.println("Downloaded: "+DLOC+ " " + (new Integer(totalBytesRead).toString()) + " bytes read (" + (new Long(endTime - startTime).toString()) + " millseconds).");
	        writer.close();
	        reader.close();
	        tries=0;
			}catch (IOException e) {
				e.printStackTrace();
				MIFU.error.setText("Error downloading, Trying again...");
				try {
					Thread.sleep(5000);
					MIFU.error.setText("");
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				tries++;
				if(tries<3){
					System.out.print("Trying to download again: "+tries);
					downloadfile(DLURL,DLDIR,DLFILE);
				}else{
					System.out.println("Failed to download: "+DLOC);
					MIFU.error.setText("Failed to download: "+DLOC);
				}
			}
	}
}