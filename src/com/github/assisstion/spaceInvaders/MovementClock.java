package com.github.assisstion.spaceInvaders;

public class MovementClock implements Runnable {
	public MovementClock(){
		
	}
	public static int MovementSpeed = 3500;
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
