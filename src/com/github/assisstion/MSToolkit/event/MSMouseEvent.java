package com.github.assisstion.MSToolkit.event;

import java.util.Set;

import com.github.assisstion.MSToolkit.MSComponent;

public class MSMouseEvent extends MSEvent{
	
	private MSMouseEventType type;
	private long time;
	private Set<MSEventModifier> modifiers;
	private int x;
	private int y;
	private int screenX;
	private int screenY;
	private int count;
	private MSMouseButtonType button;
	
	protected MSMouseEvent(){
		
	}
	
	public MSMouseEvent(MSComponent source, String message){
		super(source, message);
	}
	
	public MSMouseEvent(MSComponent source, String message, MSMouseEvent e, int x, int y){
		this(source, message, e.getType(), e.getTime(), e.getModifiers(), x, y, 
				e.getScreenX(), e.getScreenY(), e.getCount(), e.getButton());
	}
	
	public MSMouseEvent(MSComponent source, String message, MSMouseEventType type, long time, 
			Set<MSEventModifier> modifiers, int x, int y, int screenX, int screenY,
			int count, MSMouseButtonType button){
		this(source, message);
		this.type = type;
		this.time = time;
		this.modifiers = modifiers;
		this.x = x;
		this.y = y;
		this.screenX = screenX;
		this.screenY = screenY;
		this.count = count;
		this.button = button;
	}

	public MSMouseEventType getType(){
		return type;
	}

	public long getTime(){
		return time;
	}

	public Set<MSEventModifier> getModifiers(){
		return modifiers;
	}

	public int getX(){
		return x;
	}

	public int getY(){
		return y;
	}

	public int getScreenX(){
		return screenX;
	}

	public int getScreenY(){
		return screenY;
	}
	
	public int getCount(){
		return count;
	}

	public MSMouseButtonType getButton(){
		return button;
	}

	
	public enum MSMouseEventType{
		MOUSE_PRESSED, MOUSE_RELEASED, MOUSE_CLICKED, MOUSE_MOVED, MOUSE_DRAGGED, MOUSE_WHEEL;
	}
	
	public enum MSMouseButtonType{
		NO_BUTTON, BUTTON_1, BUTTON_2, BUTTON_3;
	}

	@Override
	public MSEventType getEventType(){
		return MSEventType.MOUSE;
	}
}
