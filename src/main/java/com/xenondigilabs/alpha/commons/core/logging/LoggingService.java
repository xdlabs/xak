package com.xenondigilabs.alpha.commons.core.logging;

import java.util.ArrayList;
import java.util.Calendar;

public class LoggingService {

	
	static ArrayList<LogAppender> logapender=new ArrayList<LogAppender>();
	int logging_level;

	public int getLogging_level() {
		return logging_level;
	}

	public LoggingService setLogging_level(int logging_level) {
		this.logging_level = logging_level;
		return this;
	}
	public static void register(LogAppender apender)
	{
		logapender.add(apender);
	}
	
	private static void log(Class Class,int level,String message)
	{
		for (LogAppender appender : logapender) {
			if(level==0)
				appender.append("[ Error ]\t" + Calendar.getInstance().getTime() + "\t" + message + " " + Class + "\n");
			else if(level==1)
				appender.append("[ Warning ]\t" + Calendar.getInstance().getTime() + "\t" + message + " " + Class + "\n");
			else if(level==2)
				appender.append("[ Info ]\t" + Calendar.getInstance().getTime() + "\t" + message + " " + Class + "\n");
			else if(level==3)
				appender.append("[ Debug ]\t" + Calendar.getInstance().getTime() + "\t" + message + " " + Class + "\n");
		}
	}
	
	
	public static void info(Class Class,String message)
	{
		log(Class,2,message);
	}
	public static void debug(Class Class,String message)
	{
		log(Class, 3,message);
	}
	public static void warning(Class Class,String message)
	{
		log(Class, 1,message);
	}
	public static void error(Class Class,String message)
	{
		log(Class, 0,message);
	}
	
	
	
}
