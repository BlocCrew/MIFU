package com.norway240.mifu.v5;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class  Extract {
    public void ExtractZipFile(String filename, String destinationname) {
        try {
            byte[] buf = new byte[1024];
            ZipInputStream zipinputstream = null;
            ZipEntry zipentry;
            zipinputstream = new ZipInputStream(new FileInputStream(filename));
            zipentry = zipinputstream.getNextEntry();
            while (zipentry != null) { 
                String entryName = zipentry.getName();
                System.out.println("entryname "+entryName);
                int n;
                FileOutputStream fileoutputstream;
                File newFile = new File(entryName);
                String directory = newFile.getParent();
                if(directory == null) {
                    if(newFile.isDirectory())
                        break;
                }
                fileoutputstream = new FileOutputStream(destinationname+entryName);             
                while ((n = zipinputstream.read(buf, 0, 1024)) > -1)
                	fileoutputstream.write(buf, 0, n);
                	fileoutputstream.close(); 
                	zipinputstream.closeEntry();
                	zipentry = zipinputstream.getNextEntry();
            	}

        	zipinputstream.close();
        } catch (Exception e) {
        	e.printStackTrace();
        }
    }
}