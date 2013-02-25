package com.github.assisstion.spaceInvaders;

public class MovementClock implements Runnable {
	public MovementClock(){
		
	}
	@Override
	public void run(){
		try{
			while(true){
				MainCanvas.engine.moveEnemies();
				Thread.sleep(4000);
			}
		}
		catch(InterruptedException e){
			new Thread(new MovementClock()).start();
		}
	}
	
}
