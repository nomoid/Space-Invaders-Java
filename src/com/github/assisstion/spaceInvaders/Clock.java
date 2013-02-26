package com.github.assisstion.spaceInvaders;

public class Clock implements Runnable{
	
	public Clock(){
		
	}
	
	@Override
	public void run(){
		try{
			while(MainCanvas.isOn){
				MainCanvas.engine.repaint();
				Thread.sleep(16);
			}
		}
		catch(InterruptedException e){
			new Thread(new Clock()).start();
		}
	}
	
}
