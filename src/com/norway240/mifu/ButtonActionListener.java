package com.norway240.mifu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class ButtonActionListener {
	ActionListener act = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			if(e.getSource()==MIFU.chmods){
				System.out.println("CHOOSE");
				MIFU.selectedModlist = new File(MIFU.mifudir+"/modlist/modlist.txt");
				System.out.println("Selected: "+MIFU.selectedModlist);
				MIFU.label.setText("Selected: "+MIFU.selectedModlist);
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
