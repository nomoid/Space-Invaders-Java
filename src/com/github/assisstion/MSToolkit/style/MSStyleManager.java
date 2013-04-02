package com.github.assisstion.MSToolkit.style;

import java.util.HashMap;
import java.util.Map;

import com.github.assisstion.MSToolkit.MSBasicColor;
import com.github.assisstion.MSToolkit.MSColor;
import com.github.assisstion.MSToolkit.MSException;
import com.github.assisstion.MSToolkit.MSFont;
import com.github.assisstion.MSToolkit.impl.MSHelper;

public final class MSStyleManager{
	
	private static MSStyleSystem system;
	private static Map<String, MSStyleSystem> styles;
	
	static{
		try{
			styles = new HashMap<String, MSStyleSystem>();
			addStyleSystem("classic", new MSClassicStyle());
			system = MSHelper.getDefaultStyleSystem();
		}
		catch(Exception e){
			throw new MSException("MSStyle initializing exception", e);
		}
	}
	
	private MSStyleManager(){
	}
	
	public static MSStyleSystem getDefaultStyleSystem(){
		return system;
	}
	
	public static MSStyleSystem getStyleSystem(String name){
		if(styles.containsKey(name)){
			return styles.get(name);
		}
		else{
			throw new MSException("Invalid MSStyle name: " + name);
		}
	}
	
	public static void addStyleSystem(String name, MSStyleSystem system){
		if(styles.containsKey(name)){
			throw new MSException("Style name already in use " + name);
		}
		else{
			styles.put(name, system);
		}
	}
	
	public static MSStyle getStyle(int paddingLeft, int paddingRight, int paddingTop, int paddingBottom,
			MSColor foreground, MSColor frontBackground, MSColor background, MSFont font){
		return new MSBasicStyle(paddingLeft, paddingRight, paddingTop, paddingBottom, 
				foreground, frontBackground, background, font);
	}
	
	public static MSMutableStyle getMutableStyle(int paddingLeft, int paddingRight, int paddingTop, int paddingBottom,
			MSColor foreground, MSColor frontBackground, MSColor background, MSFont font){
		return new MSBasicStyle(paddingLeft, paddingRight, paddingTop, paddingBottom, 
				foreground, frontBackground, background, font);
	}
	
	public static MSStyle getStyle(MSStyle style){
		return new MSBasicStyle(style.getPaddingLeft(), style.getPaddingRight(),
				style.getPaddingTop(), style.getPaddingBottom(),
				style.getForeground(), style.getFrontBackground(),
				style.getBackground(), style.getFont());
	}
	
	public static MSMutableStyle getMutableStyle(MSStyle style){
		return new MSBasicStyle(style.getPaddingLeft(), style.getPaddingRight(),
				style.getPaddingTop(), style.getPaddingBottom(),
				style.getForeground(), style.getFrontBackground(),
				style.getBackground(), style.getFont());
	}

	private static class MSBasicStyle implements MSMutableStyle{

		private int paddingLeft;
		private int paddingRight;
		private int paddingTop;
		private int paddingBottom;
		private MSColor foreground;
		private MSColor frontBackground;
		private MSColor background;
		private MSFont font;
		
		public MSBasicStyle(int paddingLeft, int paddingRight, int paddingTop, int paddingBottom,
				MSColor foreground, MSColor frontBackground, MSColor background, MSFont font){
			this.paddingLeft = paddingLeft;
			this.paddingRight = paddingRight;
			this.paddingTop = paddingTop;
			this.paddingBottom = paddingBottom;
			this.foreground = foreground;
			this.frontBackground = frontBackground;
			this.background = background;
			this.font = font;
		}
		
		@Override
		public int getPaddingLeft(){
			return paddingLeft;
		}

		@Override
		public int getPaddingRight(){
			return paddingRight;
		}

		@Override
		public int getPaddingTop(){
			return paddingTop;
		}

		@Override
		public int getPaddingBottom(){
			return paddingBottom;
		}

		@Override
		public MSColor getForeground(){
			return foreground;
		}
		
		@Override
		public MSColor getFrontBackground(){
			return frontBackground;
		}

		@Override
		public MSColor getBackground(){
			return background;
		}
		

		@Override
		public MSFont getFont(){
			return font;
		}


		@Override
		public void setPaddingLeft(int paddingLeft){
			this.paddingLeft = paddingLeft;
		}

		@Override
		public void setPaddingRight(int paddingRight){
			this.paddingRight = paddingRight;
		}

		@Override
		public void setPaddingTop(int paddingTop){
			this.paddingTop = paddingTop;
		}

		@Override
		public void setPaddingBottom(int paddingBottom){
			this.paddingBottom = paddingBottom;
		}

		@Override
		public void setForeground(MSColor foreground){
			this.foreground = foreground;
		}
		
		@Override
		public void setFrontBackground(MSColor frontBackground){
			this.frontBackground = frontBackground;
		}

		@Override
		public void setBackground(MSColor background){
			this.background = background;
		}

		@Override
		public void setFont(MSFont font){
			this.font = font;
		}
	}
	
	private static class MSClassicStyle implements MSStyleSystem{
		private MSClassicStyle(){
			
		}
		
		private static final MSStyle BUTTON_STYLE = MSStyleManager.getStyle(
				5, 5, 5, 5, MSBasicColor.getColor("black"),  MSBasicColor.getColor("gray"),  MSBasicColor.getColor("light-light-gray"), MSHelper.getDefaultFont());
		private static final MSStyle LABEL_STYLE = MSStyleManager.getStyle(
				0, 0, 0, 0, MSBasicColor.getColor("black"),  MSBasicColor.getColor("light-gray"),  MSBasicColor.getColor("white"), MSHelper.getDefaultFont());
		private static final MSStyle FRAME_STYLE = MSStyleManager.getStyle(
				0, 0, 0, 0, MSBasicColor.getColor("black"),  MSBasicColor.getColor("gray"),  MSBasicColor.getColor("light-light-gray"), MSHelper.getDefaultFont());
		private static final MSStyle SPRITE_STYLE = MSStyleManager.getStyle(
				2, 2, 2, 2, MSBasicColor.getColor("black"),  MSBasicColor.getColor("light-gray"),  MSBasicColor.getColor("white"), MSHelper.getDefaultFont());
		private static final MSStyle EMPTY_STYLE = MSStyleManager.getStyle(
				0, 0, 0, 0, MSBasicColor.getColor("black"),  MSBasicColor.getColor("gray"),  MSBasicColor.getColor("light-light-gray"), MSHelper.getDefaultFont());
		
		@Override
		public MSStyle getButton(){
			return getStyle(BUTTON_STYLE);
		}
		
		@Override
		public MSStyle getLabel(){
			return getStyle(LABEL_STYLE);
		}
		
		@Override
		public MSStyle getFrame(){
			return getStyle(FRAME_STYLE);
		}
		@Override
		public MSStyle getSprite(){
			return getStyle(SPRITE_STYLE);
		}
		@Override
		public MSStyle getEmpty(){
			return getStyle(EMPTY_STYLE);
		}
	}

}
