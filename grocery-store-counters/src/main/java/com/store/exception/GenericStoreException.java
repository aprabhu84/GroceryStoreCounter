package com.store.exception;

import com.store.logger.StoreEventLogger;

public class GenericStoreException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param Message
	 */
	public GenericStoreException(String message){
		super(message);
		StoreEventLogger.logMessage("Logging Exception with Message - " + message);
	}
	
	/**
	 * 
	 * @param message
	 * @param throwable
	 */
	public GenericStoreException(String message, Throwable cause){
		super(message, cause);
		StoreEventLogger.logMessage("Logging Exception type " + cause.getClass() + " with Message - " + message);
	}
	
	/**
	 * 
	 * @param cause
	 */
	public GenericStoreException(Throwable cause){
		super(cause);
		StoreEventLogger.logMessage("Logging Exception type " + cause.getClass());
	}
	
}
