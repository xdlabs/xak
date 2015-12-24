package com.xenondigilabs.xak.configuration;

import java.util.Properties;

public class Configuration {
	
	//config attribute to add multiple configuration
	 static Properties config=new  Properties();
	
	 
	 static void add(ConfigurationProvider provider){
		 
		 //Iterate over provider,to get keys and values
		 for (Object key : provider.keys()) {
			String propertykey=key.toString();
			String value=provider.get(key.toString());//Get value corresponding key
			config.setProperty(propertykey, value);//set key and values inside config provider
		}
	}
	//Get value corresponding key of any file (.properties,.yaml) 
	static String get(String key ) 
	{
		 return config.getProperty(key);
	}
}
