package com.github.assisstion.spaceInvaders;

public class GameException extends RuntimeException{

	private static final long serialVersionUID = -3223498594775097702L;

	public GameException(Throwable cause){
		super(cause);
	}
	
	public GameException(String message){
		super(message);
	}
	
	public GameException(){
		super();
	}
	
	public GameException(String message, Throwable cause){
		super(message, cause);
	}
}
