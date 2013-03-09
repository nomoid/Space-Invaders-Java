package com.github.assisstion.spaceInvaders;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import com.github.assisstion.spaceInvaders.gameObject.Box;
import com.github.assisstion.spaceInvaders.gameObject.IrregularHitbox;
import com.github.assisstion.spaceInvaders.gameObject.Sprite;

public class Helper{
	public static void renderSprite(Graphics2D g, Sprite s){
		//Draws the Sprite s to the Graphics2D g
		AffineTransform imageRender = new AffineTransform();
		imageRender.setToTranslation(s.x, s.y);
		imageRender.rotate(Math.toRadians(s.rotation));
		g.drawImage(s.getImage(), imageRender, MainCanvas.frame);
		//g.drawImage(s.getImage(), s.x, s.y, s.getImage().getWidth(), s.getImage().getHeight(), MainCanvas.frame);
	}
	
	public static void updateHitbox(Sprite s){
		if(s instanceof IrregularHitbox){
			IrregularHitbox ih = (IrregularHitbox) s;
			ih.updateHitbox();
		}
		else{
			s.hitBox.setPos(s.x, s.y, s.getImage().getWidth(),s.getImage().getHeight(), true);
		}
	}
	
	public static boolean overlapsIrregularHitbox(Box[] a, Box[] b){
		for(Box box : a){
			for(Box boxTwo : b){
				if(box.overLaps(boxTwo)){
					return true;
				}
			}
		}
		return false;
	}
	
	public static <E> int getIndex(E[] array, E object){
		for(int i = 0; i < array.length; i++){
			if(array[i].equals(object)){
				return i;
			}
		}
		return -1;
	}
	
	public static char[] createEmptyName(char c, int length) {
		char[] ca = new char[length];
		for (int i = 0; i < ca.length; i++) {
			ca[i] = c;
		}
		return ca;
	}
}
