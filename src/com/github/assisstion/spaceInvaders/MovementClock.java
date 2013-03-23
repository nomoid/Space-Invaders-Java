package com.github.assisstion.spaceInvaders;

public class MovementClock implements Runnable {
	
	public static final int DEFAULT_SPEED = 3525;
	public static final int MINIMUM_SPEED = 2000;
	
	public MovementClock(){
		
	}
	public static int movementSpeed = DEFAULT_SPEED;
	@Override
	public void run(){
		try{
			while(MainCanvas.isOn){
				if(MainCanvas.engine == null){
					break;
				}
				if(MainCanvas.engine.state.equalsIgnoreCase("main")){
					MainCanvas.engine.moveEnemies();
				}
				Thread.sleep(movementSpeed);
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
	}
	
}
