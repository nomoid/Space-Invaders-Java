package com.github.assisstion.spaceInvaders;

public class MovementClock implements Runnable {
	public MovementClock(){
		
	}
	public static int MovementSpeed = 4000;
	@Override
	public void run(){
		try{
			while(MainCanvas.isOn){
				MainCanvas.engine.moveEnemies();
				Thread.sleep(MovementSpeed);
			}
		}
		catch(InterruptedException e){
			new Thread(new MovementClock()).start();
		}
	}
	
}
