package com.xenondigilabs.alpha.commons.core.configuration;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface ConfigurationProvider {

	String get(String Key);  //  return the value for `key`
	
	Set<Object> keys(); // Return all keys
	
	Collection<Object> values(); // Return all values
	
}
