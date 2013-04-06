package com.github.assisstion.spaceInvaders;

public class Clock implements Runnable{
	
	public Clock(){
		
	}
	
	@Override
	public void run(){
		MainCanvas.engine.repaint();
		/*
		try{
			while(true){
				if(MainCanvas.engine == null){
					break;
				}
				if(MainCanvas.isOn){
					MainCanvas.engine.repaint();
				}
				Thread.sleep(16);
			}
			
		}
		catch(InterruptedException e){
			//TODO placeholder
			e.printStackTrace();
		}
		catch(Exception e){
			//TODO placeholder
			e.printStackTrace();
		}
		*/
	}
	
}
