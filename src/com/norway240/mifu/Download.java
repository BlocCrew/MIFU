package com.norway240.mifu;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class Download {
	
	public static void downloadfile(String DLURL, String DLFILE) {
		String DLOC = MIFU.dldir+DLFILE;
		
		System.out.println("URL: "+ DLURL);
		System.out.println("FILE: "+ DLOC);
		
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
	        System.out.println("Downloaded: "+DLOC+ (new Integer(totalBytesRead).toString()) + " bytes read (" + (new Long(endTime - startTime).toString()) + " millseconds).\n");
	        writer.close();
	        reader.close();
			}catch (MalformedURLException e) {
				e.printStackTrace();
			}catch (IOException e) {
				e.printStackTrace();
		}
	}
}