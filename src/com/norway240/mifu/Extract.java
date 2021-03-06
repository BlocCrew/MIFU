package com.norway240.mifu;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class  Extract { //http://filez.norway240.com/ksp/kspmods.txt
	public static void ExtractZipFile(String path, String strZipFile, String destination, String specific){
		try{
	         File fSourceZip = new File(path+strZipFile);
	         String zipPath = destination;
	         File temp = new File(zipPath);
	         temp.mkdir();
	         System.out.println(zipPath + " created");
	
	         @SuppressWarnings("resource")
			 ZipFile zipFile = new ZipFile(fSourceZip);
	         @SuppressWarnings("rawtypes")
			 Enumeration e = zipFile.entries();
	        
	         while(e.hasMoreElements()){
	             ZipEntry entry = (ZipEntry)e.nextElement();
	             int slen = specific.length();
	             
	             if(entry.getName().length()>=slen){
		             if(entry.getName().substring(0, slen).equals(specific)){
			             File destinationFilePath = new File(zipPath,entry.getName().substring(slen));
			
			             //create directories if required.
			             destinationFilePath.getParentFile().mkdirs();
			            
			             //if the entry is directory, leave it. Otherwise extract it.
			             if(entry.isDirectory()){
			                     continue;
			             }else{
			                 System.out.println("Extracting " + destinationFilePath);
			                 BufferedInputStream bis = new BufferedInputStream(zipFile.getInputStream(entry));                                                  
			                 int b;
			                 byte buffer[] = new byte[1024];
			                 FileOutputStream fos = new FileOutputStream(destinationFilePath);
			                 BufferedOutputStream bos = new BufferedOutputStream(fos, 1024);
			
			                 while ((b = bis.read(buffer, 0, 1024)) != -1) {
			                                 bos.write(buffer, 0, b);
			                 }
			                 bos.flush();
			                 bos.close();
			                 bis.close();
			             }
	
		             }else{
		            	 System.out.println("Finding file...");
		             }
	             }
	         }
	         MIFU.modsDisplay.append("Extracted: "+strZipFile+"\n");
        }catch(IOException ioe){
       	 System.out.println("IOError :" + ioe);
        }
	}
	
    public static void ExtractZipFile(String path, String strZipFile, String destination) { //This extracts a zip file given to it
    	 try{
	         File fSourceZip = new File(path+strZipFile);
	         String zipPath = destination;
	         File temp = new File(zipPath);
	         temp.mkdir();
	         System.out.println(zipPath + " created");
	
	         @SuppressWarnings("resource")
			 ZipFile zipFile = new ZipFile(fSourceZip);
	         @SuppressWarnings("rawtypes")
			 Enumeration e = zipFile.entries();
	        
	         while(e.hasMoreElements()){
	             ZipEntry entry = (ZipEntry)e.nextElement();
	             File destinationFilePath = new File(zipPath,entry.getName());
	
	             //create directories if required.
	             destinationFilePath.getParentFile().mkdirs();
	            
	             //if the entry is directory, leave it. Otherwise extract it.
	             if(entry.isDirectory()){
	                     continue;
	             }else{
	                 System.out.println("Extracting " + destinationFilePath);
	                 BufferedInputStream bis = new BufferedInputStream(zipFile.getInputStream(entry));                                                  
	                 int b;
	                 byte buffer[] = new byte[1024];
	                 FileOutputStream fos = new FileOutputStream(destinationFilePath);
	                 BufferedOutputStream bos = new BufferedOutputStream(fos, 1024);
	
	                 while ((b = bis.read(buffer, 0, 1024)) != -1) {
	                                 bos.write(buffer, 0, b);
	                 }
	                 bos.flush();
	                 bos.close();
	                 bis.close();
	             }
	         }
	         MIFU.modsDisplay.append("Extracted: "+strZipFile+"\n");
         }catch(IOException ioe){
        	 System.out.println("IOError :" + ioe);
         }
    }
}