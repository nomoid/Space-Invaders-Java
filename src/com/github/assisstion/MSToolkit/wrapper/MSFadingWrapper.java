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
		displayForTime(millis, null);
	}
	
	public void displayForTime(long millis, Runnable finishing){
		object.show();
		final Runnable tempFinishing = finishing;
		Runnable r = new Runnable(){
			
			private Runnable finishing;
			
			{
				finishing = tempFinishing;
			}

			@Override
			public void run(){
				object.hide();
				if(finishing != null){
					new Thread(finishing).start();
				}
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
			boolean active = true;
			while(MSHelper.isSystemOn()){
				try{
					Thread.sleep(active ? DELAY : DELAY * 4);

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
					if(!iter.iterator().hasNext()){
						active = false;
					}
					else{
						active = true;
					}
					LinkedList<ScheduledRunnable> ll = new LinkedList<ScheduledRunnable>();
					for(ScheduledRunnable sr : iter){
						if(stopped){
							break;
						}
						if(sr.isStopped()){
							continue;
						}
						long time = System.currentTimeMillis() - sr.getLastStart();
						if(time > sr.getDelay()){
							new Thread(sr).start();
							sr.setLoops(sr.getLoops() - 1);
							if(sr.getLoops() <= 0){
								ll.add(sr);
								continue;
							}
						}
					}
					for(ScheduledRunnable sr : ll){
						queue.remove(sr);
					}
					ll.clear();
					stopped = false;
				}
				catch(InterruptedException e){
					e.printStackTrace();
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
