package com.github.assisstion.spaceInvaders;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.swing.SwingUtilities;

import com.github.assisstion.MSToolkit.impl.MSHelper;

public class Main{
	
	private static boolean running;
	private static ExceptionHandlerWrapper ehw;
	private static Object startupLock = new Object();

	public static void main(String[] args) {
		synchronized(startupLock){
			LinkedList<Thread.UncaughtExceptionHandler> ll = new LinkedList<Thread.UncaughtExceptionHandler>();
			ll.add(Thread.getDefaultUncaughtExceptionHandler());
			ll.add(new GameExceptionHandler());
			ehw = new ExceptionHandlerWrapper(ll);
			Thread.setDefaultUncaughtExceptionHandler(ehw);
			Runtime.getRuntime().addShutdownHook(new Thread(new ShutdownRunnable()));
			SwingUtilities.invokeLater(new StartupRunnable());
			
		}
	}
	
	private static class StartupRunnable implements Runnable{
		@Override
		public void run(){
			synchronized(startupLock){
				Thread.currentThread().setUncaughtExceptionHandler(ehw);
				running = true;
				MainCanvas.start();
			}
		}
	}
	
	private static class ShutdownRunnable implements Runnable{

		@Override
		public void run(){
			try{
				MainCanvas.disableOutputs();
				running = false;
				MSHelper.disableSystem();
				ResourceManager.setMuted(true);
			}
			catch(Exception e){
				System.out.println("Exception Occured During Shutdown");
			}
			finally{
				System.out.println("Shutdown");
			}
		}
	}
	
	private static class ExceptionHandlerWrapper implements Thread.UncaughtExceptionHandler{

		private List<Thread.UncaughtExceptionHandler> list;
		
		@Override
		public void uncaughtException(Thread t, Throwable e){
			for(Thread.UncaughtExceptionHandler tueh : list){
				tueh.uncaughtException(t, e);
			}
		}
		
		public ExceptionHandlerWrapper(List<Thread.UncaughtExceptionHandler> list){
			this.list = Collections.unmodifiableList(list);
		}
	}
	
	private static class GameExceptionHandler implements Thread.UncaughtExceptionHandler{

		@Override
		public void uncaughtException(Thread t, Throwable e){
			System.out.print("uncaught exception handled!");
		}
	}
	
	public static boolean isRunning(){
		return running;
	}
}
