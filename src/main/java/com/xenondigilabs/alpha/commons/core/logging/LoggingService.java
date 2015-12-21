package com.xenondigilabs.alpha.commons.core.logging;

import java.util.ArrayList;
import java.util.Calendar;

public class LoggingService {

	//logappender to add multiple type of logs
	static ArrayList<LogAppender> logapender=new ArrayList<LogAppender>();
	int logging_level;// To get appropriate message for logs

	//To get logging_level
	public int getLogging_level() {
		return logging_level;
	}

	//To set logging_level
	public void setLogging_level(int logging_level) {
		this.logging_level = logging_level;
	}
	
	//To Register multiple LogAppender like jdbcappender,fileappender,KafkaAppender
	public static void register(LogAppender apender)
	{
		logapender.add(apender);// Add Appender to arraylist of LogAppender type
	}
	
	//To create logs
	private static void log(Class Class,int level,String message)
	{
		//Iterate over appender and send log to append method to write the logs in a file
		for (LogAppender appender : logapender) {
			if(level==0)//Error Logs
				appender.append("[ Error ]\t" + Calendar.getInstance().getTime() + "\t" + message + " " + Class + "\n");
			else if(level==1)//Warning Logs
				appender.append("[ Warning ]\t" + Calendar.getInstance().getTime() + "\t" + message + " " + Class + "\n");
			else if(level==2)//Info Logs
				appender.append("[ Info ]\t" + Calendar.getInstance().getTime() + "\t" + message + " " + Class + "\n");
			else if(level==3)//Debug Logs
				appender.append("[ Debug ]\t" + Calendar.getInstance().getTime() + "\t" + message + " " + Class + "\n");
		}
	}
	public static void info(Class Class,String message)
	{
		log(Class,2,message);//forward to log method ,to create message
	}
	public static void debug(Class Class,String message)
	{
		log(Class, 3,message);//forward to log method ,to create message
	}
	public static void warning(Class Class,String message)
	{
		log(Class, 1,message);//forward to log method ,to create message
	}
	public static void error(Class Class,String message)
	{
		log(Class, 0,message);//forward to log method ,to create message
	}
	
	
	
}
