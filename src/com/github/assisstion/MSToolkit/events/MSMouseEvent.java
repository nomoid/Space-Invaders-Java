package com.github.assisstion.MSToolkit.events;

import java.awt.event.MouseEvent;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.github.assisstion.MSToolkit.MSComponent;
import com.github.assisstion.MSToolkit.MSException;

import static com.github.assisstion.MSToolkit.events.MSModifier.*;

public class MSMouseEvent{
	
	private MSComponent source;
	private MSMouseEventType type;
	private long time;
	private Set<MSModifier> modifiers;
	private int x;
	private int y;
	private int screenX;
	private int screenY;
	private int count;
	private MSMouseButtonType button;
	
	protected MSMouseEvent(){
		
	}
	
	public MSMouseEvent(MSComponent source, MouseEvent e){
		this.source = source;
		type = MSMouseEventType.fromID(e.getID());
		time = e.getWhen();
		modifiers = getModifiersFromMask(e.getModifiersEx());
		x = e.getX();
		y = e.getY();
		screenX = e.getXOnScreen();
		screenY = e.getYOnScreen();
		count = e.getClickCount();
		button = MSMouseButtonType.fromID(e.getButton());
	}

	public MSComponent getSource(){
		return source;
	}

	public MSMouseEventType getType(){
		return type;
	}

	public long getTime(){
		return time;
	}

	public Set<MSModifier> getModifiers(){
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

	private Set<MSModifier> getModifiersFromMask(int i){
		HashSet<MSModifier> modifiers = new HashSet<MSModifier>();
		if((i & MouseEvent.ALT_DOWN_MASK) == i){
			modifiers.add(ALT);
		}
		if((i & MouseEvent.CTRL_DOWN_MASK) == i){
			modifiers.add(CTRL);
		}
		if((i & MouseEvent.META_DOWN_MASK) == i){
			modifiers.add(META);
		}
		if((i & MouseEvent.SHIFT_DOWN_MASK) == i){
			modifiers.add(SHIFT);
		}
		if((i & MouseEvent.BUTTON1_DOWN_MASK) == i){
			modifiers.add(BUTTON_1);
		}
		if((i & MouseEvent.BUTTON2_DOWN_MASK) == i){
			modifiers.add(BUTTON_1);
		}
		if((i & MouseEvent.BUTTON3_DOWN_MASK) == i){
			modifiers.add(BUTTON_1);
		}
		if((i & MouseEvent.ALT_GRAPH_DOWN_MASK) == i){
			modifiers.add(ALT_GRAPH);
		}
		return Collections.unmodifiableSet(modifiers);
	}

	
	public enum MSMouseEventType{
		MOUSE_PRESSED, MOUSE_RELEASED, MOUSE_CLICKED, MOUSE_MOVED, MOUSE_DRAGGED, MOUSE_WHEEL;
		
		public static MSMouseEventType fromID(int id){
			switch(id){
				case MouseEvent.MOUSE_PRESSED:
					return MOUSE_PRESSED;
				case MouseEvent.MOUSE_RELEASED:
					return MOUSE_RELEASED;
				case MouseEvent.MOUSE_CLICKED:
					return MOUSE_CLICKED;
				case MouseEvent.MOUSE_MOVED:
					return MOUSE_MOVED;
				case MouseEvent.MOUSE_DRAGGED:
					return MOUSE_DRAGGED;
				case MouseEvent.MOUSE_WHEEL:
					return MOUSE_WHEEL;
				default:
					throw new MSException("Illegal Event Type ID: " + id);
			}
		}
	}
	
	public enum MSMouseButtonType{
		NO_BUTTON, BUTTON_1, BUTTON_2, BUTTON_3;
		
		public static MSMouseButtonType fromID(int id){
			switch(id){
				case MouseEvent.NOBUTTON:
					return NO_BUTTON;
				case MouseEvent.BUTTON1:
					return BUTTON_1;
				case MouseEvent.BUTTON2:
					return BUTTON_2;
				case MouseEvent.BUTTON3:
					return BUTTON_3;
				default:
					throw new MSException("Illegal Button Type ID: " + id);
			}
		}
	}
}
