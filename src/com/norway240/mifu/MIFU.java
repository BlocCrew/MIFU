package com.norway240.mifu;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class MIFU {
	
	public static void main(String[] args) throws IOException, InterruptedException {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		
		JFrame mainWindow = new JFrame();
		JPanel consoleLog = new JPanel();
		JPanel welcome = new JPanel();
		JLabel text = new JLabel();
		JTextArea log = new JTextArea(20,30);
		//JButton next = new JButton();
		JProgressBar progress = new JProgressBar();
		JScrollPane scroll = new JScrollPane(log);
		
		
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWindow.setLocation(dim.width/2-welcome.getSize().width/2, dim.height/2-welcome.getSize().height/2);
		mainWindow.add(welcome, "North");
		mainWindow.add(consoleLog, "South");
		
		welcome.add(text);
		text.setText("Welcome! This is a test window so far as it is not done yet... PLEASE WAIT UNTIL I MAKE THIS WORK C:");		
		log.setEditable(false);
		
		progress.setMinimum(0);
        progress.setValue(0);
        progress.setStringPainted(true);
        progress.setSize(300, 100);
        
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		mainWindow.getContentPane().add(scroll, BorderLayout.PAGE_END);
		mainWindow.pack();
		mainWindow.setVisible(true);
		
		
		String currdir = System.getProperty("user.dir");
		//JOptionPane.showMessageDialog(null, "Welcome to the Mod Installer From URL v5!\nThis downloads mods \nautomatically for you", "MIFU", 1);
		String DLDIR = JOptionPane.showInputDialog(null, "Enter the location of your\nminecraft installation\nLeave blank for the current dir", "MIFU", 1);
		if(DLDIR == null){DLDIR = currdir;}
		
		//JFrame gui = new JFrame("MIFU");
		//JLabel text = new JLabel("Preparing to download...            ");
		//JProgressBar progress = new JProgressBar();
		//progress.setMinimum(0);
        //progress.setValue(0);
        //progress.setStringPainted(true);
        //progress.setSize(300, 100);
        
		//gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//gui.getContentPane().setLayout(new BorderLayout ());
		//gui.getContentPane().add(text, BorderLayout.NORTH);
		//gui.getContentPane().add(progress, BorderLayout.CENTER);
		//JTextArea log = new JTextArea(20,30);
		//log.setEditable(false);
		//JScrollPane scroll = new JScrollPane(log);
		//scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		//gui.pack();
		//gui.setLocation(dim.width/2-gui.getSize().width/2-400, dim.height/2-gui.getSize().height/2);
		//gui.setVisible(true);
	    
		Thread.sleep(1000);
	    Download dlObject = new Download();
	    boolean modlistcheck = new File(currdir + "modlist.txt").isFile();
	    if(!modlistcheck){
	    	text.setText("Downloading modlist.txt");
	    	log.append("Downloading modlist.txt\n");
	    	dlObject.downloadfile("http://dl.bloccrew.com/modlist.txt", currdir, "/modlist.txt");
	    	Thread.sleep(1000);
	    }
	    
	    File modlist = new File(currdir + "/modlist.txt");
		FileReader fr = new FileReader(modlist);
		LineNumberReader lnr = new LineNumberReader(fr);
		int totalmods = 0;
        while (lnr.readLine() != null){
         	totalmods++;
        }
        lnr.close();
        text.setText("Reading modlist");
		log.append("Reading modlist\n");
        System.out.println("Total number of mods to download: " + totalmods);
	    log.append("Mods to download: " + totalmods + "\n");
	    log.append("Directory set to: "+DLDIR+"\n");
        
		try {
			BufferedReader cfgFile = new BufferedReader(new FileReader(modlist));
			String line = null;
			int currmod = 0;
			new File(DLDIR+"/mods").mkdirs();
			progress.setMaximum(totalmods);
			while ((line = cfgFile.readLine()) != null) {
			    progress.setValue(currmod);
				line.trim();
			    String [] modlst = line.split(","); 
			    String link = modlst[0];
			    String save = modlst[1];
			    if (link.equalsIgnoreCase("forge")) {
			    	dlObject.downloadfile("http://files.minecraftforge.net/minecraftforge/minecraftforge-installer-"+save+".jar", currdir, "/forge-installer-"+save+".jar");
			    	//JOptionPane.showMessageDialog(null, "MINECRAFT FORGE DETECTED\nYOU MUST INSTALL IT BEFORE YOU CAN PLAY\nTHE INSTALLER IS LOCATED AT: "+currdir+"/forge-installer-"+save+".jar", "MIFU", 1);
			    	log.append("Downloaded: Minecraft forge version: "+save+"\n");
				    log.setCaretPosition(log.getDocument().getLength());
			    } else {
			    	//log.append("Downloading: " + save + "\n");
				    log.setCaretPosition(log.getDocument().getLength());
				    dlObject.downloadfile(link, DLDIR, save);
					System.out.println("Downloaded: " + currmod + "/" + totalmods);
					text.setText("Downloaded: " + currmod + "/" + totalmods);
					log.append("Downloaded: ["+currmod+"/"+totalmods+"] " + save + "\n");
					log.setCaretPosition(log.getDocument().getLength());
			    }
			    currmod++;
			}
				cfgFile.close();
			} catch (IOException e) {
				System.out.println("Unexpected File IO Error");
				log.append("Unexpected File IO Error\n");
			}

		boolean configcheck = new File(DLDIR + "/config.zip").isFile();
	    if(configcheck) {
	    	text.setText("Extracting config.zip");
	    	log.append("Extracting config.zip\n");
	    	File configfolder = new File(DLDIR + "/minecraft/config");
	    	configfolder.mkdir();
	    	Extract extractObj = new Extract();
	    	extractObj.ExtractZipFile(DLDIR + "/config.zip", DLDIR + "/minecraft/config/");
	    	Thread.sleep(1000);
	    }
		
		text.setText("Done!");
		log.append("Done!\n");
		log.setCaretPosition(log.getDocument().getLength());
		Thread.sleep(1000);
		//gui.dispose();
		//logwindow.dispose();
		//if(!modlistcheck){JOptionPane.showMessageDialog(null, "Finished downloading mods!\nYou can play now. \nThe Official BlocBin Server\nIP is mod.bloccrew.com", "MIFU", 1);
		//}else{JOptionPane.showMessageDialog(null, "Your download is now complete!", "MIFU", 1);}
	}

}