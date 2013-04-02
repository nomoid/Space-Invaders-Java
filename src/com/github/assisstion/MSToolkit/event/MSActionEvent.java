package com.github.assisstion.MSToolkit.event;

import com.github.assisstion.MSToolkit.MSComponent;

public class MSActionEvent extends MSEvent{
	
	private MSEvent actualEvent;
	private boolean meaningful;
	
	public MSActionEvent(MSComponent source, MSEvent actualEvent, boolean meaningful){
		super(source, actualEvent.getMessage());
		this.actualEvent = actualEvent;
		this.meaningful = meaningful;
	}
	
	protected MSActionEvent(MSComponent source){
		super(source);
	}
	
	@Override
	public MSEventType getEventType(){
		return MSEventType.ACTION;
	}
	
	public MSEvent getActualEvent(){
		return actualEvent;
	}

	public boolean isMeaningful(){
		return meaningful;
	}
}
