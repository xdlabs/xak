package com.xenondigilabs.xak.logging;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class FilelogAppender implements LogAppender{
	
	BufferedWriter bw =null; //To write log inside a file
	public FilelogAppender(String filepath)
	{
		try {
		      File file = new File(filepath);//Take file from filepath
		      // if file doesnt exists, then create it
				if (!file.exists()) {
					file.createNewFile();
				}
		      FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);//FileWriter to open file in  write or append model
			  bw = new BufferedWriter(fw);
		} catch (IOException e) {}
	}

	//To Write logs
	public void append(String log) {
		
		 try {
			bw.write(log);//Write logs to file 
			bw.flush();
		} catch (IOException e) {}
	}
	
	public FilelogAppender() {}
	
	
}
