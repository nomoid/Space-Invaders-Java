package com.github.assisstion.MSToolkit.concurrent;

import java.util.ConcurrentModificationException;

public class ConcurrentObject<T>{
	
	private T object = null;
	private Object lock = new Object();
	private Object blockingLock = new Object();
	private boolean blocked = false;
	
	public ConcurrentObject(T object){
		this.object = object;
	}
	
	public ConcurrentObject(){
		setBlocked(true);
	}
	
	public T get(){
		T object;
		synchronized(lock){
			object = poll();
			setBlocked(false);
			lock.notify();
		}
		return object;
	}
	
	public T take(){
		T object;
		synchronized(lock){
			object = poll();
		}	
		object = null;
		return object;
	}
	
	private T poll(){
		T object = null;
		synchronized(lock){
			try{
				if(isBlocked()){
					lock.wait();
				}
				object = this.object;
			}
			catch(InterruptedException e){
				throw new ConcurrentModificationException(
						"caused by InterruptedException: " + e.getMessage());
			}
		}
		return object;
	}
	
	public boolean isBlocked(){
		synchronized(blockingLock){
			return blocked;
		}
	}
	
	private void setBlocked(boolean blocked){
		synchronized(blockingLock){
			this.blocked = blocked;
		}
	}
	
	public T getUnblocked(){
		if(isBlocked()){
			return null;
		}
		else{
			return get();
		}
	}
	
	public void put(T object){
		synchronized(lock){
			this.object = object;
			lock.notify();
		}
	}
}
