package com.github.assisstion.MSToolkit.impl;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.ImageObserver;

import com.github.assisstion.MSToolkit.MSBasicColor;
import com.github.assisstion.MSToolkit.MSColor;
import com.github.assisstion.MSToolkit.MSFont;
import com.github.assisstion.MSToolkit.MSGraphicalContext;
import com.github.assisstion.MSToolkit.MSImage;

class MSGraphicImpl implements MSGraphicalContext, ImageObserver{
	
	private Graphics2D graphics;
	
	public MSGraphicImpl(Graphics2D g2d){
		graphics = g2d;
	}

	@Override
	public void drawString(String str, int x, int y){
		graphics.drawString(str, x, y);
	}

	@Override
	public void drawImage(MSImage img, int x, int y){
		graphics.drawImage(img.getImage(), x, y, this);
		
	}

	@Override
	public MSColor getColor(){
		return MSConverter.fromColor(graphics.getColor());
	}

	@Override
	public MSFont getFont(){
		return MSConverter.fromFont(graphics.getFont());
	}

	@Override
	public void setColor(MSColor color){
		graphics.setColor(MSConverter.toColor(color));
	}

	@Override
	public void setFont(MSFont font){
		graphics.setFont(MSConverter.toFont(font));
	}

	@Override
	public void fillRect(int x, int y, int width, int height){
		graphics.fillRect(x, y, width, height);
	}

	@Override
	public void drawRect(int x, int y, int width, int height){
		graphics.drawRect(x, y, width, height);
	}

	@Override
	public boolean imageUpdate(Image arg0, int arg1, int arg2, int arg3,
			int arg4, int arg5){
		return false;
	}
	
	public Graphics2D getGraphics(){
		return graphics;
	}
	
	public static MSGraphicalContext emptyGraphicalContext(){
		return new MSGraphicalContext(){

			@Override
			public void drawString(String str, int x, int y){
				// TODO Auto-generated method stub
				
			}

			@Override
			public void drawImage(MSImage img, int x, int y){
				// TODO Auto-generated method stub
				
			}

			@Override
			public MSColor getColor(){
				// TODO Auto-generated method stub
				return MSBasicColor.getColor("black");
			}

			@Override
			public MSFont getFont(){
				// TODO Auto-generated method stub
				return MSHelper.getDefaultFont();
			}

			@Override
			public void setColor(MSColor color){
				// TODO Auto-generated method stub
				
			}

			@Override
			public void setFont(MSFont font){
				// TODO Auto-generated method stub
				
			}

			@Override
			public void fillRect(int x, int y, int width, int height){
				// TODO Auto-generated method stub
				
			}

			@Override
			public void drawRect(int x, int y, int width, int height){
				// TODO Auto-generated method stub
				
			}
			
		};
	}
}
