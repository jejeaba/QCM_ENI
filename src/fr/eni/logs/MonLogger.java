package fr.eni.logs;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;


public class MonLogger 
{
	public static Logger getLogger(String className)
	{
		Logger monLogger = Logger.getLogger(className);
		monLogger.setLevel(Level.FINEST);
		monLogger.setUseParentHandlers(false);
		
		ConsoleHandler ch = new ConsoleHandler();
		ch.setLevel(Level.FINEST);
		
		FileHandler fh = null;
		try {
			fh = new FileHandler("C:/Users/administrateur/Desktop/logs/qcm_eni.log",200,3);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		fh.setLevel(Level.ALL);
		fh.setFormatter(new SimpleFormatter());
		
		
		monLogger.addHandler(ch);
		monLogger.addHandler(fh);
		
		return monLogger;
	}
}
