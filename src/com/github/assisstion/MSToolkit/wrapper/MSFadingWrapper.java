package com.github.assisstion.MSToolkit.wrapper;

import java.util.Iterator;
import java.util.LinkedList;

import com.github.assisstion.MSToolkit.MSComponent;
import com.github.assisstion.MSToolkit.concurrent.CollectionSynchronizer;
import com.github.assisstion.MSToolkit.concurrent.ScheduledRunnable;
import com.github.assisstion.MSToolkit.impl.MSHelper;

public class MSFadingWrapper<T extends MSComponent>{
	
	private static FadingTimer fader;
	private T object;
	private ScheduledRunnable sr;
	
	static{
		fader = new FadingTimer();
		new Thread(fader, "Fading-Timer").start();
	}
	
	public MSFadingWrapper(T obj){
		this.object = obj;
	}
	
	public T getObject(){
		return object;
	}
	
	public void hide(){
		override();
		object.hide();
	}
	
	public void show(){
		override();
		object.show();
	}
	
	public void displayForTime(long millis){
		object.show();
		Runnable r = new Runnable(){

			@Override
			public void run(){
				object.hide();
			}
			
		};
		
		sr = new ScheduledRunnable(r, millis);
		fader.add(sr);
	}
	
	protected void override(){
		fader.remove(sr);
	}
	
	protected static class FadingTimer implements Runnable{
		private CollectionSynchronizer<LinkedList<ScheduledRunnable>, ScheduledRunnable> queue;
		private static final long DELAY = 250;
		private boolean stopped;
		
		public FadingTimer(){
			queue = new CollectionSynchronizer<LinkedList<ScheduledRunnable>, ScheduledRunnable>(new LinkedList<ScheduledRunnable>());
		}

		@Override
		public void run(){
			while(MSHelper.isSystemOn()){
				try{
					Thread.sleep(DELAY);

					Iterable<ScheduledRunnable> iter = new Iterable<ScheduledRunnable>(){

						public Iterator<ScheduledRunnable> iterator;
						
						{
							iterator = queue.iterator().get();
						}
						
						
						@Override
						public Iterator<ScheduledRunnable> iterator(){
							return iterator;
						}
					};
					for(ScheduledRunnable sr : iter){
						if(stopped){
							break;
						}
						if(sr.isStopped()){
							continue;
						}
						long time = System.currentTimeMillis() - sr.getLastStart();
						if(time > sr.getDelay()){
							sr.setLoops(sr.getLoops() - 1);
							if(sr.getLoops() < 0){
								queue.remove(sr);
								continue;
							}
							else{
								new Thread(sr).start();
							}
						}
					}
					stopped = false;
				}
				catch(InterruptedException e){
					
				}
				catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		
		public void add(ScheduledRunnable sr){
			sr.setStopped(false);
			queue.add(sr);
		}
		
		public void remove(ScheduledRunnable sr){
			sr.setStopped(true);
			queue.remove(sr);
		}
		
		public void stopAll(){
			stopped = true;
			queue.clear();
		}
	}
	
	public static void stopAll(){
		fader.stopAll();
	}
}
