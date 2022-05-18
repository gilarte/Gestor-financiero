package com.Rafa.GestorFinanciero.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * Clase Loggers que implementa los dos métodos de los logs
 * @author Rafa Gilarte
 *
 */
public class Loggers {
	
	/**
	 * Método que muestra un log de error
	 * @param s: Frase que se muestra
	 */
	public static void LogsSevere(String s) {
		try {
			InputStream configFile = Loggers.class.getResourceAsStream("Loggin.properties");
			LogManager.getLogManager().readConfiguration(configFile);
		} catch (SecurityException | IOException | NullPointerException e) {
			System.out.println("Logging system not initialized");
		}
		Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
		logger.severe(s);
	}
	
	/**
	 * Método que muestra un log de informacion
	 * @param s: Frase que se muestra
	 */
	public static void LogsInfo(String s) {
		try {
			InputStream configFile = Loggers.class.getResourceAsStream("Loggin.properties");
			LogManager.getLogManager().readConfiguration(configFile);
		} catch (SecurityException | IOException | NullPointerException e) {
			System.out.println("Logging system not initialized");
		}
		Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
		logger.info(s);
	}
}
