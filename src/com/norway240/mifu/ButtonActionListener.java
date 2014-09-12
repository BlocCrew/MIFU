package com.norway240.mifu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonActionListener {
	ActionListener act = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			if(e.getSource()==MIFU.chmods){
				System.out.println("BUTTON");
			}
		}
	};
}
