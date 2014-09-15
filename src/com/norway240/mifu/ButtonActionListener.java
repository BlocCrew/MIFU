package com.norway240.mifu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ButtonActionListener {
	ActionListener act = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			if(e.getSource()==MIFU.chmods){
				System.out.println("CHOOSE");
				JFileChooser chooser = new JFileChooser("Choose a modlist");
			    FileNameExtensionFilter filter = new FileNameExtensionFilter(
			        "Modlist (.txt)", "txt");
			    chooser.setFileFilter(filter);
			    int returnVal = chooser.showOpenDialog(chooser);
			    if(returnVal == JFileChooser.APPROVE_OPTION) {
			    	MIFU.selectedModlist = chooser.getSelectedFile().getAbsoluteFile();
					System.out.println("Chosen: "+chooser.getSelectedFile().getAbsolutePath());
					MIFU.label.setText("Selected: "+chooser.getSelectedFile().getAbsolutePath());
			    }
			}else if(e.getSource()==MIFU.chdir){
				System.out.println("CHOOSEDIR");
				JFileChooser chooser = new JFileChooser("Choose a dir");
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			    int returnVal = chooser.showOpenDialog(chooser);
			    if(returnVal == JFileChooser.APPROVE_OPTION) {
			    	MIFU.dldir = chooser.getSelectedFile().getAbsolutePath();
					System.out.println("Chosen: "+chooser.getSelectedFile().getAbsolutePath());
					MIFU.label2.setText("Selected: "+chooser.getSelectedFile().getAbsolutePath());
					chooser.getSelectedFile().getAbsoluteFile().mkdirs();
					File modsfolder = new File(chooser.getSelectedFile().getAbsolutePath()+"/mods");
					modsfolder.mkdir();
			    }
			}else if(e.getSource()==MIFU.dlmods){
				System.out.println("DOWNLOAD");
				//MIFU.progress.setIndeterminate(true);
				new Thread(new DThread()).start();
			}
		}
	};
}
