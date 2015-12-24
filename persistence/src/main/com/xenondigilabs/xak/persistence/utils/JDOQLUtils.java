package com.xenondigilabs.xak.persistence.utils;

import java.util.Map;

/**
 * Generate JDOQL queries from different data structures.
 */
public class JDOQLUtils {

    /**
     * Generate a JDOQL string from key-value Map.
     * Parse a JSONStore like query Map and generates JDOQL string
     * @param mapping
     * @return
     */
    public static String mapToJDOQL(Map<String, Object> mapping){
        // Initialize empty query string
        String jdoql_string = "";
        // Read all key-value pairs in map
        for(String key: mapping.keySet()){
            // Append all key-value pairs with AND operator
            jdoql_string += " && " + key + " == \"" + mapping.get(key) + "\"";
        }
        // Trim extra operators appended
        jdoql_string = jdoql_string.replaceFirst("&&", "");

        // Returns JDOQL string
        return jdoql_string;

    }

}