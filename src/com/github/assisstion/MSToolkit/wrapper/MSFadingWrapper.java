package com.github.assisstion.MSToolkit.wrapper;

import java.util.HashSet;
import java.util.Iterator;

import com.github.assisstion.MSToolkit.MSComponent;
import com.github.assisstion.MSToolkit.concurrent.CollectionSynchronizer;

public class MSFadingWrapper<T extends MSComponent>{
	
	private T object;
	private CollectionSynchronizer<HashSet<FadingTimer>, FadingTimer> timers;
	
	public MSFadingWrapper(T obj){
		timers = new CollectionSynchronizer<HashSet<FadingTimer>, FadingTimer>(new HashSet<FadingTimer>());
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
		new Thread(new FadingTimer(r, millis)).start();
	}
	
	protected void override(){
		Iterable<FadingTimer> iter = new Iterable<FadingTimer>(){

			@Override
			public Iterator<FadingTimer> iterator(){
				return timers.iterator().get();
			}
		};
		for(FadingTimer timer : iter){
			timer.interrupt();
		}
	}
	
	private static class FadingTimer implements Runnable{
		
		private long length;
		private long startTime;
		private Runnable task;
		
		//Milliseconds
		public FadingTimer(Runnable task, long length){
			startTime = System.currentTimeMillis();
			this.task = task;
			this.length = length;
		}
		
		public long timeRemaining(){
			return length - (System.currentTimeMillis() - startTime);
		}

		@Override
		public void run(){
			try{
				while(timeRemaining() > 0){
					Thread.sleep(1000);
				}
				task.run();
			}
			catch(InterruptedException e){
				
			}
		}
		
		public void interrupt(){
			Thread.currentThread().interrupt();
		}
	}
}
