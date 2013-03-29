package com.github.assisstion.MSToolkit.style;

import java.awt.Color;

public final class MSStyleManager{
	private MSStyleManager(){
		
	}
	
	public static MSStyle getEmptyStyle(){
		return new MSBasicStyle(0, 0, 0, 0, Color.BLACK, Color.GRAY, Color.WHITE);
	}
	
	public static MSStyle getStyle(int paddingLeft, int paddingRight, int paddingTop, int paddingBottom,
			Color foreground, Color frontBackground, Color background){
		return new MSBasicStyle(paddingLeft, paddingRight, paddingTop, paddingBottom, 
				foreground, frontBackground, background);
	}

	private static class MSBasicStyle implements MSStyle{

		private int paddingLeft;
		private int paddingRight;
		private int paddingTop;
		private int paddingBottom;
		private Color foreground;
		private Color frontBackground;
		private Color background;

		public MSBasicStyle(int paddingLeft, int paddingRight, int paddingTop, int paddingBottom,
				Color foreground, Color frontBackground, Color background){
			this.paddingLeft = paddingLeft;
			this.paddingRight = paddingRight;
			this.paddingTop = paddingTop;
			this.paddingBottom = paddingBottom;
			this.foreground = foreground;
			this.frontBackground = frontBackground;
			this.background = background;
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
	}
}
