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
	public PropertiesConfiguration(String properties_file_path) {
		
	        try {
	            this.prop = new Properties();
	            InputStream  is = new FileInputStream(properties_file_path);
	            prop.load(is);
	        } catch (FileNotFoundException e) {} 
	          catch (IOException e) {}
	        
	}
	public String get(String Key) {
		return prop.getProperty(Key);
	}

	public Set<Object> keys() {
		return  prop.keySet();
	}

	public Collection<Object> values() {
		return  prop.values();
	}

}
