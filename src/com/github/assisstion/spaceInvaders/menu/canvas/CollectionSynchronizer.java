package com.github.assisstion.spaceInvaders.menu.canvas;

import java.util.Collection;

public class CollectionSynchronizer<T>{
		
		private Collection<T> collection;
		private Object lock;
		
		public CollectionSynchronizer(Collection<T> collection){
			this(collection, new Object());
		}
		
		public CollectionSynchronizer(Collection<T> collection, Object lock){
			this.lock = lock;
			this.collection = collection;
		}
		
		public ConcurrentObject<Boolean> add(T object){
			CollectionAdder ca = new CollectionAdder(object);
			new Thread(ca).start();
			return ca.getReturnValue();
		}
		
		public ConcurrentObject<Boolean> remove(T object){
			CollectionRemover cr = new CollectionRemover(object);
			new Thread(cr).start();
			return cr.getReturnValue();
		}
		
		public ConcurrentObject<Boolean> contains(T object){
			CollectionContainer cc = new CollectionContainer(object);
			new Thread(cc).start();
			return cc.getReturnValue();
		}
		
		private class CollectionAdder implements Runnable{
			
			private T object;
			private ConcurrentObject<Boolean> returnValue;

			public CollectionAdder(T object){
				this.object = object;
				returnValue = new ConcurrentObject<Boolean>();
			}
			
			public ConcurrentObject<Boolean> getReturnValue(){
				return returnValue;
			}
			
			@Override
			public void run(){
				synchronized(lock){
					returnValue.put(new Boolean(collection.add(object)));
				}
			}
			
		}
		
		private class CollectionRemover implements Runnable{

			private T object;
			private ConcurrentObject<Boolean> returnValue;
			
			public CollectionRemover(T object){
				this.object = object;
				returnValue = new ConcurrentObject<Boolean>();
			}
			
			public ConcurrentObject<Boolean> getReturnValue(){
				return returnValue;
			}
			
			@Override
			public void run(){
				synchronized(lock){
					returnValue.put(new Boolean(collection.remove(object)));
				}
			}
		}	
		
		private class CollectionContainer implements Runnable{

			private T object;
			private ConcurrentObject<Boolean> returnValue;
			
			public CollectionContainer(T object){
				this.object = object;
				returnValue = new ConcurrentObject<Boolean>();
			}
			
			public ConcurrentObject<Boolean> getReturnValue(){
				return returnValue;
			}
			
			@Override
			public void run(){
				synchronized(lock){
					returnValue.put(new Boolean(collection.contains(object)));
				}
			}
		}	
	}