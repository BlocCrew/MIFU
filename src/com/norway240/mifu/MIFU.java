package com.norway240.mifu;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.File;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MIFU {

	static ButtonActionListener bal = new ButtonActionListener();
	static JPanel panel = new JPanel(new GridBagLayout());
	static JButton chmods = new JButton("Choose modlist");
	static GridBagConstraints c = new GridBagConstraints();
	final static String homedir = System.getProperty("user.home");
	final static File mifudir = new File(homedir+"/.mifu");
	
	private static void addSomething(Component comp, String type, int x, int y, int width){
		if(type.equalsIgnoreCase("button")){
			((AbstractButton) comp).addActionListener(bal.act);
		}
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = x;
		c.gridy = y;
		c.gridwidth = width;
		panel.add(comp,c);
	}
	
	public static void main(String[] args){
		JFrame frame = new JFrame("MIFU");
		Download download = new Download();
		
		if (!mifudir.exists()){
			mifudir.mkdir();
			download.newDir("/modlist");
			download.downloadfile("http://deciliter.bloccrew.com/modlist.txt", "/modlist/modlist.txt");
			System.out.println("MIFU Directory created");
		}
		
		addSomething(chmods,"button",0,0,1);
		frame.add(panel);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		
		System.out.println(mifudir);
	}

}