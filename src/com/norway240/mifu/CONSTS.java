package com.norway240.mifu;

import java.io.File;

public class CONSTS {
	final static int MAJOR = 7;
	final static int MINOR = 1;
	final static int PATCH = 0;
	final static String MIFUV = "v"+MAJOR+"."+MINOR+"."+PATCH; 		//MIFU version
	final static String HOMEDIR = System.getProperty("user.home"); 	//Users home dir
	final static File MIFUDIR = new File(HOMEDIR+"/.mifu"); 		//MIFU dir
}
