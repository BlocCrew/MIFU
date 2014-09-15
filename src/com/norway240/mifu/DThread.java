package com.norway240.mifu;

import java.io.IOException;

public class DThread implements Runnable{
	public void run(){
		try {
			DList.dlModlist(MIFU.selectedModlist);
		} catch (InterruptedException | IOException e) {
			e.printStackTrace();
		}
	}
}
