package org.nwnu.pub.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonUtil {
	
	/**
	 * 将对象转为json字符串
	 * @param object
	 * @return
	 */
	public static String toJson(Object object){
		
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();
		Gson gson = gsonBuilder.create();
		return gson.toJson(object);
	}

}
