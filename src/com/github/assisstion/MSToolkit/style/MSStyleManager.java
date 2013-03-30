package com.github.assisstion.MSToolkit.style;

import java.awt.Color;
import java.awt.Font;
import java.util.HashMap;
import java.util.Map;

import com.github.assisstion.MSToolkit.MSException;
import com.github.assisstion.MSToolkit.MSHelper;

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
			Color foreground, Color frontBackground, Color background, Font font){
		return new MSBasicStyle(paddingLeft, paddingRight, paddingTop, paddingBottom, 
				foreground, frontBackground, background, font);
	}
	
	public static MSStyle getStyle(MSStyle style){
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
		private Color foreground;
		private Color frontBackground;
		private Color background;
		private Font font;

		public MSBasicStyle(int paddingLeft, int paddingRight, int paddingTop, int paddingBottom,
				Color foreground, Color frontBackground, Color background, Font font){
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
		public Color getForeground(){
			return foreground;
		}
		
		@Override
		public Color getFrontBackground(){
			return frontBackground;
		}

		@Override
		public Color getBackground(){
			return background;
		}
		

		@Override
		public Font getFont(){
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
		public void setForeground(Color foreground){
			this.foreground = foreground;
		}
		
		@Override
		public void setFrontBackground(Color frontBackground){
			this.frontBackground = frontBackground;
		}

		@Override
		public void setBackground(Color background){
			this.background = background;
		}

		@Override
		public void setFont(Font font){
			this.font = font;
		}
	}
	
	private static class MSClassicStyle implements MSStyleSystem{
		private MSClassicStyle(){
			
		}
		
		private static final MSStyle BUTTON_STYLE = MSStyleManager.getStyle(
				5, 5, 5, 5, Color.BLACK, Color.GRAY, Color.WHITE, MSHelper.getDefaultFont());
		private static final MSStyle FRAME_STYLE = MSStyleManager.getStyle(
				0, 0, 0, 0, Color.BLACK, Color.GRAY, Color.WHITE, MSHelper.getDefaultFont());
		private static final MSStyle SPRITE_STYLE = MSStyleManager.getStyle(
				0, 0, 0, 0, Color.BLACK, Color.GRAY, Color.WHITE, MSHelper.getDefaultFont());
		private static final MSStyle EMPTY_STYLE = MSStyleManager.getStyle(
				0, 0, 0, 0, Color.BLACK, Color.GRAY, Color.WHITE, MSHelper.getDefaultFont());
		
		@Override
		public MSStyle getButton(){
			return getStyle(BUTTON_STYLE);
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
