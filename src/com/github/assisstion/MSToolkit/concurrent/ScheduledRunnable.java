package com.github.assisstion.MSToolkit.concurrent;

public class ScheduledRunnable implements Runnable{
	
	private static final long MINIMUM_DELAY = 1;
	
	private Runnable runnable;
	private long delay;
	private int loops;
	private long lastStart;
	private boolean stopped = false;
	
	public ScheduledRunnable(Runnable runnable, long delay){
		this(runnable, delay, 1);
	}
	
	public ScheduledRunnable(Runnable runnable, long delay, boolean forever){
		this(runnable, delay, forever ? -1 : 1);
	}
	
	public ScheduledRunnable(Runnable runnable, long delay, int loops){
		updateLastStart();
		if(delay < MINIMUM_DELAY || delay < 0){
			throw new IllegalArgumentException("Illegal Delay Number");
		}
		if(loops < -1){
			throw new IllegalArgumentException("Illegal Loop Number");
		}
		this.runnable = runnable;
		this.delay = delay;
		this.loops = loops;
	}
	
	public int getLoops(){
		return loops;
	}
	
	public void setLoops(int loops){
		this.loops = loops;
	}
	
	public long getDelay(){
		return delay;
	}
	
	public Runnable getRunnable(){
		return runnable;
	}
	
	public long getLastStart(){
		return lastStart;
	}
	
	@Override
	public void run(){
		updateLastStart();
		runnable.run();
	}
	
	public void updateLastStart(){
		lastStart = System.currentTimeMillis();
	}
	
	public boolean isStopped(){
		return stopped;
	}
	
	public void setStopped(boolean stopped){
		this.stopped = stopped;
	}
}