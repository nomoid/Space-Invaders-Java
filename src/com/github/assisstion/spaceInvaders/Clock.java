package com.github.assisstion.spaceInvaders;

public class Clock implements Runnable{
	
	public Clock(){
		
	}
	
	@Override
	public void run(){
		try{
			while(true){
				if(MainCanvas.isOn){
					MainCanvas.engine.repaint();
				}
				Thread.sleep(16);
			}
		}
		catch(InterruptedException e){
			e.printStackTrace();
			new Thread(new Clock()).start();
		}
	}
	
}
