package com.github.assisstion.spaceInvaders;

public class MovementClock implements Runnable {
	
	public static final int DEFAULT_SPEED = 2350;
	public static final int MINIMUM_SPEED = 1500;
	
	public MovementClock(){
		
	}
	public static int MovementSpeed = DEFAULT_SPEED;
	@Override
	public void run(){
		try{
			while(MainCanvas.isOn){
				if(MainCanvas.engine.state.equalsIgnoreCase("main")){
					MainCanvas.engine.moveEnemies();
				}
				Thread.sleep(MovementSpeed);
			}
		}
		catch(InterruptedException e){
			new Thread(new MovementClock()).start();
		}
	}
	
}
