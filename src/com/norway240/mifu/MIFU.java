package com.norway240.mifu;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MIFU {

	static JPanel panel = new JPanel(new GridBagLayout());
	static GridBagConstraints c = new GridBagConstraints();
	
	private static void addSomething(Component comp, int x, int y, int width){
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = x;
		c.gridy = y;
		c.gridwidth = width;
		panel.add(comp,c);
	}
	
	public static void main(String[] args){
		JFrame frame = new JFrame("MIFU");
		JButton chmods = new JButton("Choose modlist");
		String homedir = System.getProperty("user.home");
		File mifudir = new File(homedir+"/.mifu");
		Download download = new Download();
		
		if (!mifudir.exists()){
			mifudir.mkdir();
			download.newDir("/modlist");
			System.out.println("MIFU Directory created");
		}
		
		addSomething(chmods,0,0,1);
		frame.add(panel);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		
		System.out.println(mifudir);
	}

}