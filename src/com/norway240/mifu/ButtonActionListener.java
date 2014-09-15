package com.norway240.mifu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ButtonActionListener {
	ActionListener act = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			if(e.getSource()==MIFU.chmods){
				System.out.println("CHOOSE");
				MIFU.selectedModlist = new File(MIFU.mifudir+"/modlist/modlist.txt");
				System.out.println("Default Selected: "+MIFU.selectedModlist);
				MIFU.label.setText("Default Selected: "+MIFU.selectedModlist);
				
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
				
			}else if(e.getSource()==MIFU.dlmods){
				System.out.println("DOWNLOAD");
				try {
					DList.dlModlist(MIFU.selectedModlist);
				} catch (InterruptedException | IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	};
}
