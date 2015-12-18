package com.xenondigilabs.alpha.commons.core.logging;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class FilelogAppender implements LogAppender{

	
	String filename="";
	public void append(String log) {
		
		try {
		      File file = new File("/home/spring/Desktop/"+filename);
		      
		      // if file doesnt exists, then create it
				if (!file.exists()) {
					file.createNewFile();
				}
		      FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);
			  BufferedWriter bw = new BufferedWriter(fw);
			  bw.write(log);
		      bw.close();
		} catch (IOException e) {
		      e.printStackTrace();
		}
	}
	
	public FilelogAppender() {}
	
	public FilelogAppender(String name)
	{
		this.filename=name;
	}
}
