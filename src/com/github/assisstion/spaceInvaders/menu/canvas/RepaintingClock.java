package com.github.assisstion.spaceInvaders.menu.canvas;


public class RepaintingClock implements Runnable{
	
	private AbstractCanvas canvas; 
	public RepaintingClock(AbstractCanvas canvas){
		this.canvas = canvas;
	}
	
	@Override
	public void run(){
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
	}
	
}
