package com.xenondigilabs.alpha.commons.core.logging;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class FilelogAppender implements LogAppender{

	
	BufferedWriter bw =null;
	public void append(String log) {
		
		 try {
			bw.write(log);
			bw.flush();
		} catch (IOException e) {}
	}
	
	public FilelogAppender() {}
	
	public FilelogAppender(String filepath)
	{
		try {
		      File file = new File(filepath);
		      // if file doesnt exists, then create it
				if (!file.exists()) {
					file.createNewFile();
				}
		      FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);
			  bw = new BufferedWriter(fw);
		} catch (IOException e) {}
	}
}
