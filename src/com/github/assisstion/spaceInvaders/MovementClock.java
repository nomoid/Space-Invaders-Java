package com.github.assisstion.spaceInvaders;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class MovementClock implements Runnable {
	
	public static final int DEFAULT_SPEED = 3525;
	public static final int MINIMUM_SPEED = 2000;
	
	public MovementClock(){
		
	}
	
	private static int movementSpeed = DEFAULT_SPEED;
	private static ScheduledFuture<?> future;
	private static ScheduledExecutorService service;
	private static boolean started;
	@Override
	public void run(){
		/* Changed the implementation of the timers to
		 * use ScheduledExcecutorServices, which automatically
		 * execute tasks with a given delay.
		 * See the engine class to see the changes
		 */
		MainCanvas.engine.moveEnemies();
		service.schedule(Executors.callable(this), movementSpeed, TimeUnit.MILLISECONDS);
		/*
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
		*/
	}
	
	
	public static synchronized int getMovementSpeed(){
		return movementSpeed;
	}
	
	public static synchronized void setMovementSpeed(int speed){
		setMovementSpeed(speed, true);
	}
	
	public static synchronized void setMovementSpeed(int speed, boolean on){
		movementSpeed = speed;
		if(started){
			stop();
		}
		if(on){
			started = true;
			future = service.schedule(Executors.callable(new MovementClock()), movementSpeed, TimeUnit.MILLISECONDS);
		}
	}
	
	public static synchronized void setService(ScheduledExecutorService ses){
		service = ses;
	}
	
	private static synchronized void stop(){
		future.cancel(false);
		started = false;
	}
}
