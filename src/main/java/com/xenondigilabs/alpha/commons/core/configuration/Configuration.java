package com.xenondigilabs.alpha.commons.core.configuration;

import java.util.Properties;

public class Configuration {
	
	
	 static java.util.Properties config=new  Properties();
	
	 static void add(ConfigurationProvider provider){
		for (Object key : provider.keys()) {
			String propertykey=key.toString();
			String value=provider.get(key.toString());
			config.setProperty(propertykey, value);
		}
	}
	static String get(String key )  //  return the value for `key`
	{
		 return config.getProperty(key);
	}

	 
	
		/*
	// To read configs like abc.properties

	class PropertiesConfiguration extends ConfigurationProvider{

		PropertiesConfiguation( properties_file_path )

	}

	// To read abc.yaml files

	class YAMLConfiguration extends ConfiguratonProvider{

		YAMLConfiguration( yaml_file_path )

	}

	AND SO ON


	---------------
	Use case:
	---------------


	PropertiesCOnfiguration pc = new PropertiesCOnfiguration( amc.properties )
	PropertiesCOnfiguration pc2 = new PropertiesCOnfiguration( another_file.properties )

	Configuration.add( pc )
	Configuration.add( pc2 )

	var host = Configuration.get( 'server.host' )

	//  So, we get one Configuration which reads from multiple and heterogeneous files.
*/
}
