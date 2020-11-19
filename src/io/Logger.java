package io;

import java.util.logging.Level;
import java.util.logging.LogRecord;

import client.Client;

public class Logger {
	
	public static void log(Object o) {
		System.out.println(o);//more rebust later
		if(Client.getConsole() != null)
        Client.getConsole().log(new LogRecord(Level.INFO, o.toString()));
	}

}
