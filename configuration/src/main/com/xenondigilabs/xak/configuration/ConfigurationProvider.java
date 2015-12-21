package com.xenondigilabs.xak.configuration;

import java.util.Collection;
import java.util.Set;

public interface ConfigurationProvider {

	String get(String Key);  //  return the value corresponding key
	
	Set<Object> keys(); // Return all keys
	
	Collection<Object> values(); // Return all values
	
}
