package com.spring.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class test {
	public static void main(String[] args) {
		Properties prop = new Properties();
		InputStream in = Object.class.getResourceAsStream("/sys.properties");   
		 try {   
			 prop.load(in); 
			 System.out.println(2);
		 } catch (IOException e) {   
			 e.printStackTrace();   
		 }  
	}
}
