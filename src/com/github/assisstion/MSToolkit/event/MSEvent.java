package com.github.assisstion.MSToolkit.event;

import com.github.assisstion.MSToolkit.MSComponent;

public abstract class MSEvent{
	
	protected MSComponent source;
	
	public MSEvent(MSComponent source){
		this.source = source;
	}
	
	public abstract MSEventType getEventType();
	
	public MSComponent getSource(){
		return source;
	}
}
