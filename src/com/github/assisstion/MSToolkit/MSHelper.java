package com.github.assisstion.MSToolkit;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;

import com.github.assisstion.MSToolkit.style.MSStyleManager;
import com.github.assisstion.MSToolkit.style.MSStyleSystem;
import com.github.assisstion.spaceInvaders.ResourceManager;

public final class MSHelper{
	private MSHelper(){
	
	}
	
	public static int getTextWidth(Font font, String text, Graphics2D g){
		FontRenderContext frc = null;
		boolean b = false;
		if(g == null){
			b = true;
		}
		else{
			if(g.getFontRenderContext() == null){
				b = true;
			}
		}
		if(b){
			frc = new FontRenderContext(new AffineTransform(), false, false);
		}
		else{
			frc = g.getFontRenderContext();
		}
		return (int) font.getStringBounds(text, frc).getWidth();
	}
	
	public static int getTextHeight(Font font, String text, Graphics2D g){
		FontRenderContext frc = null;
		boolean b = false;
		if(g == null){
			b = true;
		}
		else{
			if(g.getFontRenderContext() == null){
				b = true;
			}
		}
		if(b){
			frc = new FontRenderContext(new AffineTransform(), false, false);
		}
		else{
			frc = g.getFontRenderContext();
		}
		return (int) font.getStringBounds(text, frc).getHeight();
	}

	public static BufferedImage getImage(String link){
		try{
			return ResourceManager.getImageResource(link);
		}
		catch(IOException e){
			throw new MSException("Unable to acquire image resource", e);
		}
	}
	
	
	public static Font getDefaultFont(){
		return new Font("Calibri", Font.PLAIN, 20);
	}

	public static MSStyleSystem getDefaultStyleSystem(){
		return MSStyleManager.getStyleSystem("classic");
	}
	
	public static boolean pointIn(int x1, int y1, int x2, int y2, int pointX, int pointY){
		if(pointX >= x1 && pointX <= x2 && pointY >= y1 && pointY <= y2){
			return true;
		}
		return false;
	}
}
