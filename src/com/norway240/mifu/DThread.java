package com.norway240.mifu;

import java.io.IOException;

public class DThread implements Runnable{
	public void run(){
		try {
			DList.dlModlist(MIFU.selectedModlist, MIFU.dlDir); //Runs the process of downloading the mods from the selected modlist
		} catch (InterruptedException | IOException e) {
			e.printStackTrace();
		}
	}
}
