package com.github.assisstion.spaceInvaders.menu.canvas;

import com.github.assisstion.MSToolkit.impl.MSAbstractCanvas;


public class RepaintingClock implements Runnable{
	
	private MSAbstractCanvas canvas; 
	public RepaintingClock(MSAbstractCanvas canvas){
		this.canvas = canvas;
	}
	
	@Override
	public void run(){
		canvas.repaint();
		/*
		try{
			while(true){
				try{
					if(canvas.isOn()){
						canvas.repaint();
					}
					Thread.sleep(canvas.getDelay());
				}
				catch(InterruptedException e){
					if(!canvas.interrupted(e)){
						break;
					}
				}
			}
		}
		catch(Exception e){
			//TODO placeholder
			e.printStackTrace();
		}
		*/
	}
	
}
