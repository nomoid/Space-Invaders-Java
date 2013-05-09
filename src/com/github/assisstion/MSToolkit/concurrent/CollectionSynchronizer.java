package com.github.assisstion.MSToolkit.concurrent;

import java.util.Collection;
import java.util.Iterator;

public class CollectionSynchronizer<T extends Collection<S>, S>{
		
		private T collection;
		private Object lock;
		
		public CollectionSynchronizer(T collection){
			this(collection, new Object());
		}
		
		public CollectionSynchronizer(T collection, Object lock){
			this.lock = lock;
			this.collection = collection;
		}
		
		public ConcurrentObject<Boolean> add(S object){
			CollectionAdder ca = new CollectionAdder(object);
			new Thread(ca).start();
			return ca.getReturnValue();
		}
		
		public ConcurrentObject<Boolean> remove(S object){
			CollectionRemover cr = new CollectionRemover(object);
			new Thread(cr).start();
			return cr.getReturnValue();
		}
		
		public ConcurrentObject<Boolean> contains(S object){
			CollectionContainer cc = new CollectionContainer(object);
			new Thread(cc).start();
			return cc.getReturnValue();
		}
		
		public ConcurrentObject<Iterator<S>> iterator(){
			CollectionIterator ci = new CollectionIterator();
			new Thread(ci).start();
			return ci.getReturnValue();
		}
		
		public void clear(){
			CollectionClearer cc = new CollectionClearer();
			new Thread(cc).start();
		}
		
		public T getCollection(){
			return collection;
		}
		
		private class CollectionAdder implements Runnable{
			
			private S object;
			private ConcurrentObject<Boolean> returnValue;

			public CollectionAdder(S object){
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

			private S object;
			private ConcurrentObject<Boolean> returnValue;
			
			public CollectionRemover(S object){
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

			private S object;
			private ConcurrentObject<Boolean> returnValue;
			
			public CollectionContainer(S object){
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
		
		private class CollectionIterator implements Runnable{
			
			private ConcurrentObject<Iterator<S>> returnValue;
			
			public CollectionIterator(){
				returnValue = new ConcurrentObject<Iterator<S>>();
			}
			
			public ConcurrentObject<Iterator<S>> getReturnValue(){
				return returnValue;
			}
			
			@Override
			public void run(){
				synchronized(lock){
					returnValue.put(collection.iterator());
				}
			}
		}
		
		private class CollectionClearer implements Runnable{
			
			@Override
			public void run(){
				synchronized(lock){
					collection.clear();
				}
			}
		}
	}