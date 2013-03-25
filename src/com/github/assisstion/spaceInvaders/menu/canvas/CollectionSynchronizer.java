package com.github.assisstion.spaceInvaders.menu.canvas;

import java.util.Collection;

public class CollectionSynchronizer<T>{
		
		private Collection<T> collection;
		private Object lock;
		
		public CollectionSynchronizer(Collection<T> collection){
			lock = new Object();
			this.collection = collection;
		}
		
		public CollectionSynchronizer(Collection<T> collection, Object lock){
			this.lock = lock;
			this.collection = collection;
		}
		
		public void add(T object){
			new Thread(new CollectionAdder(object)).start();
		}
		
		public void remove(T object){
			new Thread(new CollectionRemover(object)).start();
		}
		
		private class CollectionAdder implements Runnable{
			
			private T object;

			public CollectionAdder(T object){
				this.object = object;
			}
			
			@Override
			public void run(){
				synchronized(lock){
					collection.add(object);
				}
			}
			
		}
		
		private class CollectionRemover implements Runnable{

			private T object;
			
			public CollectionRemover(T object){
				this.object = object;
			}
			
			@Override
			public void run(){
				synchronized(lock){
					collection.remove(object);
				}
			}
		}	
	}