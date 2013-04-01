package com.github.assisstion.MSToolkit;

import java.util.HashMap;
import java.util.Map;

public class MSBasicColor implements MSColor, Cloneable{
	
	private int red;
	private int green;
	private int blue;
	private int alpha;
	
	private static Map<String, MSBasicColor> predefinedColors;
	
	static{
		predefinedColors = new HashMap<String, MSBasicColor>();
		predefinedColors.put("black", new MSBasicColor(0, 0, 0));
		predefinedColors.put("white", new MSBasicColor(255, 255, 255));
		predefinedColors.put("light-gray", new MSBasicColor(192, 192, 192));
		predefinedColors.put("light-light-gray", new MSBasicColor(224, 224, 224));
		predefinedColors.put("dark-gray", new MSBasicColor(64, 64, 64));
		predefinedColors.put("dark-dark-gray", new MSBasicColor(32, 32, 32));
		predefinedColors.put("gray", new MSBasicColor(128, 128, 128));
		predefinedColors.put("medium-dark-gray", new MSBasicColor(96, 96, 96));
		predefinedColors.put("medium-light-gray", new MSBasicColor(160, 160, 160));
		
		predefinedColors.put("red", new MSBasicColor(255, 0, 0));
		predefinedColors.put("green", new MSBasicColor(0, 255, 0));
		predefinedColors.put("blue", new MSBasicColor(0, 0, 255));
		predefinedColors.put("yellow", new MSBasicColor(255, 255, 0));
		predefinedColors.put("magenta", new MSBasicColor(255, 0, 255));
		predefinedColors.put("cyan", new MSBasicColor(0, 255, 255));
	
		predefinedColors.put("pink", new MSBasicColor(255, 175, 175));
		predefinedColors.put("orange", new MSBasicColor(255, 200, 0));
	}
	
	public MSBasicColor(int r, int g, int b){
		this(r, g, b, -1);
	}
	
	public MSBasicColor(int r, int g, int b, int a){
		if(r > 255 || r < 0){
			throw new IllegalArgumentException("Invalid argument for red: " + r);
		}
		else if(g > 255 || g < 0){
			throw new IllegalArgumentException("Invalid argument for green: " + g);
		}
		else if(b > 255 || b < 0){
			throw new IllegalArgumentException("Invalid argument for blue: " + b);
		}
		else if(a > 255 || a < -1){
			throw new IllegalArgumentException("Invalid argument for alpha: " + a);
		}
		red = r;
		green = g;
		blue = b;
		alpha = a;
	}
	
	@Override
	public int getRed(){
		return red;
	}
	
	@Override
	public int getGreen(){
		return green;
	}
	
	@Override
	public int getBlue(){
		return blue;
	}

	@Override
	public int getAlpha(){
		return alpha;
	}
	
	@Override
	public boolean hasAlpha(){
		if(alpha >= 0){
			return true;
		}
		return false;
	}
	
	@Override
	public MSBasicColor clone(){
		return new MSBasicColor(getRed(), getGreen(), getBlue(), getAlpha());
	}
	
	@Override
	public boolean equals(Object o){
		if(o instanceof MSColor){
			MSBasicColor c = (MSBasicColor) o;
			if(c.getRed() == getRed() && c.getBlue() == getBlue() && c.getGreen() == getGreen() && c.getAlpha() == getAlpha()){
				return true;
			}
		}
		return false;
	}
	
	@Override
	public int hashCode(){
		return (getAlpha() << 24) ^ (getBlue() << 16) ^ (getGreen() << 8) ^ getRed();
	}
	
	@Override
	public String toString(){
		return "Color: " + " red: " + getRed() + "; green: " + getGreen() + "; blue: " + getBlue() + (hasAlpha() ? ("; alpha: " + getAlpha() + ";" ) : ";");
	}
	
	@Override
	public MSColor darker(){
		return new MSBasicColor(Math.max((int)(getRed() * 0.7), 0), Math.max((int)(getGreen() * 0.7), 0), Math.max((int)(getBlue() * 0.7), 0), getAlpha());
	}

	@Override
	public MSColor brighter(){
		return new MSBasicColor(Math.min((int)((getRed() + 1) / 0.7), 255), Math.min((int)((getGreen() + 1) / 0.7), 255), Math.min((int)((getBlue() + 1) / 0.7), 255), getAlpha());
	}

	/*
	 * Colors CURRENTLY supported:
	 * black
	 * gray
	 * light-gray
	 * light-light-gray
	 * white
	 */
	public static MSBasicColor getColor(String string){
		String name = string.toLowerCase();
		if(predefinedColors.keySet().contains(name)){
			return predefinedColors.get(name);
		}
		return null;
	}
}
