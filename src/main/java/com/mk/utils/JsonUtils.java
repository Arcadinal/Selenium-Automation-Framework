package com.mk.utils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {
	
	private JsonUtils() {}
	
	private static Map<String,String> JSONCONFIGMAP;
	
	static {
		try {
			JSONCONFIGMAP = new ObjectMapper().readValue(new File(System.getProperty("user.dir")+"/src/test/resources/json/example.json")
					,new TypeReference<HashMap<String,String>>(){});
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
