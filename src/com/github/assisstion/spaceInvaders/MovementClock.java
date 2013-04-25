package com.github.assisstion.spaceInvaders;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class MovementClock implements Runnable {
	
	public static final int DEFAULT_SPEED = 1000;
	public static final int MINIMUM_SPEED = 500;
	
	public MovementClock(int serviceCounter, int id){
		this.serviceCounter = serviceCounter;
		instanceID = id;
	}
	
	private static int movementSpeed = DEFAULT_SPEED;
	private static ScheduledFuture<?> future;
	private static ScheduledExecutorService service;
	private static boolean started;
	private static int currentID;
	private int serviceCounter;
	private int instanceID;
	
	@Override
	public void run(){
		if(!Main.isRunning()){
			return;
		}
		/* Changed the implementation of the timers to
		 * use ScheduledExcecutorServices, which automatically
		 * execute tasks with a given delay.
		 * See the engine class to see the changes
		 */
		if(MainCanvas.engine != null && MainCanvas.engine.state.equalsIgnoreCase("main")){
			MainCanvas.engine.moveEnemies();
		}
		int a = Helper.serviceCounter();
		if(a == serviceCounter && currentID == instanceID){
			service.schedule(Executors.callable(this), movementSpeed, TimeUnit.MILLISECONDS);
		}
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
			future = service.schedule(Executors.callable(new MovementClock(Helper.serviceCounter(), currentID)), movementSpeed, TimeUnit.MILLISECONDS);
		}
	}
	
	public static synchronized void setService(ScheduledExecutorService ses){
		service = ses;
	}
	
	private static synchronized void stop(){
		currentID++;
		future.cancel(false);
		started = false;
	}
}
