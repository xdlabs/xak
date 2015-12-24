package com.xenondigilabs.xak.configuration;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

import org.yaml.snakeyaml.Yaml;

public class YAMLConfiguration  implements ConfigurationProvider{
	
	//Map<Object,Object> attribute to get all keys and values of .yaml file
	private Map<Object,Object> result=null;
	
	
	public YAMLConfiguration(String properties_file_path) {
		try {
			Yaml yaml = new Yaml();
	       InputStream ios = new FileInputStream(properties_file_path);
	       	// Parse the YAML file and return the output as a series of Maps and Lists
	       	result = (Map<Object,Object>)yaml.load(ios);
	    } catch (Exception e) {
	       e.printStackTrace();
	    }
	}
	//get value from .yaml file corresponding key
	public String get(String Key) {
		return result.get(Key).toString();
	}
	//get all keys from .yaml file 
	public Set<Object> keys() {
		return result.keySet();
	}
	//get all values from .yaml file
	public Collection<Object> values() {
		return  result.values();
	}
	
	
}
