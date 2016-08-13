package com.mystore.business.common;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import org.apache.log4j.Logger;

public class ConfigReader {
	
	private static Logger logger = Logger.getLogger(ConfigReader.class);
	
	private static String path_pic_service = "http://localhost:8080/picService";
	
	private static String path_pic_upload= "H:/workspaces_jdk6/mystore_upload";
	
	static {
		logger.info("The file \"config.properties\" analyse start!");
		try{
			InputStream is = ConfigReader.class.getClassLoader().getResourceAsStream("config.properties"); 
			BufferedReader br= new BufferedReader(new InputStreamReader(is));  
	        Properties props = new Properties();  
	        props.load(br);  
	        path_pic_service = (String)props.get("path_pic_service"); 
	        path_pic_upload = (String)props.get("path_pic_upload"); 
		}catch(Exception e){
			logger.error("The file \"config.properties\" analyse  failure !");
		}
		logger.info("The file \"config.properties\" analyse end!");
	}

	public static String getPath_pic_service() {
		return path_pic_service;
	}

	public static String getPath_pic_upload() {
		return path_pic_upload;
	}

}
