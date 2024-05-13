package com.inetbanking.Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {

	Properties pro;
	public ReadConfig() {
		File file=new File("./Configuration\\config.properties");
		
		try {
			FileInputStream fis =new FileInputStream(file);
			pro=new Properties();
			pro.load(fis);
		}
		catch(Exception e) {
			System.out.println("Exception is "+e.getMessage());
		}
		
	}
	
	public String getApplicationUrl() {
		String url=pro.getProperty("baseURL");
		return url;
		
	}
	
	public String getUserName() {
		String userName=pro.getProperty("userName");
		return userName;
		
	}
	
	public String getPassword() {
		String passWord=pro.getProperty("password");
		return passWord;
		}
	
	public String getChromePath() {
		String chromePath=pro.getProperty("chromePath");
		return chromePath;
		
	}
	
	public String getFirefoxPath() {
		String firefoxPath=pro.getProperty("firefoxPath");
		return firefoxPath;
		
	}
	public String getIePath() {
		String iePath=pro.getProperty("iePath");
		return iePath;
		
	}
}
