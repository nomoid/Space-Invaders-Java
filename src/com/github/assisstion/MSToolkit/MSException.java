package com.github.assisstion.MSToolkit;

public class MSException extends RuntimeException{

	private static final long serialVersionUID = 2410128558483011362L;

	public MSException(){
		super();
	}
	
	public MSException(Throwable cause){
		super(cause);
	}
	
	public MSException(String message){
		super(message);
	}
	
	public MSException(String message, Throwable cause){
		super(message, cause);
	}
}
