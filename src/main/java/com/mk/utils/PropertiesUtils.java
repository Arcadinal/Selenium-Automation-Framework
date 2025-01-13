package com.mk.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.mk.constants.FrameworkConstants;

public final class PropertiesUtils {
	
	private PropertiesUtils() {}
	
	private static Properties p = new Properties();
	public static  Map<String,String> CONFIGMAP = new HashMap<>();
	
	static{
	
		/*
		 * Instead of using a method we are using static block Assume you have 100
		 * properties then the method will open the file 100 times but if we put it in
		 * static block it will intialized once the jvm calls the class and cache the
		 * data from properties file.
		 */
			try {
				FileInputStream file = new FileInputStream(FrameworkConstants.getConfigfilepath());
				p.load(file);
				for(Map.Entry<Object, Object> entry : p.entrySet()) {
					CONFIGMAP.put(String.valueOf(entry.getKey()),String.valueOf(entry.getValue()).trim());
				}
			} catch (IOException e) {
				throw new RuntimeException("Failed to get the driver!");
			}
		}

}
