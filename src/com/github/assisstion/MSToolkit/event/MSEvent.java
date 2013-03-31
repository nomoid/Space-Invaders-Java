package com.github.assisstion.MSToolkit.event;

import com.github.assisstion.MSToolkit.MSComponent;

public class MSEvent{
	
	protected MSComponent source;
	protected String message;
	
	protected MSEvent(){
		
	}
	
	public MSEvent(MSComponent source){
		this(source, "");
	}
	
	public MSEvent(MSComponent source, String message){
		this.source = source;
		this.message = message;
	}
	
	public MSEventType getEventType(){
		if(getClass().equals(MSEvent.class)){
			return MSEventType.GENERIC;
		}
		else{
			return MSEventType.UNDEFINED;
		}
	}
	
	public MSComponent getSource(){
		return source;
	}
	
	public String getMessage(){
		return message;
	}
	
	@Override
	public String toString(){
		return source.getClass().getSimpleName() + " " + source.hashCode() + ": " + message;
	}
}
