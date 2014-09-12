package com.norway240.mifu;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.File;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MIFU {

	static File selectedModlist;
	static ButtonActionListener bal = new ButtonActionListener();
	static JPanel panel = new JPanel(new GridBagLayout());
	static JLabel label = new JLabel("No list chosen");
	static JButton chmods = new JButton("Choose modlist");
	static JButton dlmods = new JButton("download");
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
		
		if (!mifudir.exists()){
			mifudir.mkdir();
			Download.newDir("/modlist");
			Download.downloadfile("http://deciliter.bloccrew.com/modlist.txt", "/modlist/modlist.txt");
			System.out.println("MIFU Directory created");
		}
		
		addSomething(chmods,"button",0,0,1);
		addSomething(label,"label",0,1,1);
		addSomething(dlmods,"button",0,2,1);
		frame.add(panel);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500,100);
		frame.setVisible(true);
		
		System.out.println(mifudir);
	}

}