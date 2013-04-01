package com.github.assisstion.MSToolkit.impl;

import static com.github.assisstion.MSToolkit.event.MSEventModifier.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.github.assisstion.MSToolkit.MSBasicColor;
import com.github.assisstion.MSToolkit.MSBasicFont;
import com.github.assisstion.MSToolkit.MSColor;
import com.github.assisstion.MSToolkit.MSComponent;
import com.github.assisstion.MSToolkit.MSException;
import com.github.assisstion.MSToolkit.MSFont;
import com.github.assisstion.MSToolkit.MSFontModifier;
import com.github.assisstion.MSToolkit.event.MSEventModifier;
import com.github.assisstion.MSToolkit.event.MSMouseEvent;
import com.github.assisstion.MSToolkit.event.MSMouseEvent.MSMouseButtonType;
import com.github.assisstion.MSToolkit.event.MSMouseEvent.MSMouseEventType;

import static com.github.assisstion.MSToolkit.event.MSMouseEvent.MSMouseButtonType.*;
import static com.github.assisstion.MSToolkit.event.MSMouseEvent.MSMouseEventType.*;


final class MSConverter{
	private MSConverter(){
		
	}
	
	public static MSBasicColor fromColor(Color c){
		return new MSBasicColor(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha());
	}
	
	public static MSBasicFont fromFont(Font f){
		return new MSBasicFont(f.getName(), f.getSize(), getFontModifiersFromMod(f.getStyle()));
	}
	
	public static Color toColor(MSColor c){
		return new Color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha() < 0 ? 255 : c.getAlpha());
	}
	
	public static Font toFont(MSFont f){
		return new Font(f.getName(), getFontModFromModifiers(f.getModifiers()), f.getSize());
	}
	
	public static int getFontModFromModifiers(Set<MSFontModifier> set){
		int mod = 0;
		if(set.contains(MSFontModifier.BOLD)){
			mod &= Font.BOLD;
		}
		if(set.contains(MSFontModifier.ITALIC)){
			mod &= Font.ITALIC;
		}
		//Strikethrough and Underline NOT supported
		return mod;
	}
	
	public static HashSet<MSFontModifier> getFontModifiersFromMod(int mod){
		HashSet<MSFontModifier> set = new HashSet<MSFontModifier>();
		if((mod & Font.BOLD) == mod){
			set.add(MSFontModifier.BOLD);
		}
		if((mod & Font.ITALIC) == mod){
			set.add(MSFontModifier.ITALIC);
		}
		//Strikethrough and Underline NOT supported
		return set;
	}
	
	public static MSMouseEvent fromMouseEvent(MSComponent source, String message, MouseEvent e){
			return new MSMouseEvent(source, message, fromEventID(e.getID()), e.getWhen(),
					getModifiersFromMask(e.getModifiersEx()),
					e.getX(), e.getY(), e.getXOnScreen(),
					e.getYOnScreen(), e.getClickCount(),
					fromButtonID(e.getButton()));
	}
	

	public static String getMessageFromEvent(MouseEvent e){
		String name = fromEventID(e.getID()).name().replace("_", " ");
		String[] parts = name.split("_");
		String message = "";
		for(String part : parts){
			 message += part.substring(0, 1).toUpperCase() + part.substring(1).toLowerCase() + " ";
		}
		message = message.substring(0, message.length() > 0 ? message.length() - 1 : 0);
		message += "; x: " + e.getX() + ", y: " + e.getY();
		return message;
	}
	

	private static Set<MSEventModifier> getModifiersFromMask(int i){
		HashSet<MSEventModifier> modifiers = new HashSet<MSEventModifier>();
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
			modifiers.add(MSEventModifier.BUTTON_1);
		}
		if((i & MouseEvent.BUTTON2_DOWN_MASK) == i){
			modifiers.add(MSEventModifier.BUTTON_1);
		}
		if((i & MouseEvent.BUTTON3_DOWN_MASK) == i){
			modifiers.add(MSEventModifier.BUTTON_1);
		}
		if((i & MouseEvent.ALT_GRAPH_DOWN_MASK) == i){
			modifiers.add(ALT_GRAPH);
		}
		return Collections.unmodifiableSet(modifiers);
	}

	public static MSMouseButtonType fromButtonID(int id){
		switch(id){
			case MouseEvent.NOBUTTON:
				return NO_BUTTON;
			case MouseEvent.BUTTON1:
				return MSMouseEvent.MSMouseButtonType.BUTTON_1;
			case MouseEvent.BUTTON2:
				return MSMouseEvent.MSMouseButtonType.BUTTON_2;
			case MouseEvent.BUTTON3:
				return MSMouseEvent.MSMouseButtonType.BUTTON_3;
			default:
				throw new MSException("Illegal Button Type ID: " + id);
		}
	}
	

	public static MSMouseEventType fromEventID(int id){
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
