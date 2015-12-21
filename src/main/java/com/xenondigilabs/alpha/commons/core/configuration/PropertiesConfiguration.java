package com.xenondigilabs.alpha.commons.core.configuration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Properties;
import java.util.Set;

public class PropertiesConfiguration implements ConfigurationProvider{
	
	private Properties prop = null;
	
	//Open .properties file 
	public PropertiesConfiguration(String properties_file_path) {
		
	        try {
	            this.prop = new Properties();
	            InputStream  is = new FileInputStream(properties_file_path);
	            prop.load(is);//load properties file
	        } catch (FileNotFoundException e) {} 
	          catch (IOException e) {}
	}
	//get value from .properties file corresponding key
	public String get(String Key) {
		return prop.getProperty(Key);
	}
	//get all keys from .properties file 
	public Set<Object> keys() {
		return  prop.keySet();
	}
	//get all values from .properties file 
	public Collection<Object> values() {
		return  prop.values();
	}

}
