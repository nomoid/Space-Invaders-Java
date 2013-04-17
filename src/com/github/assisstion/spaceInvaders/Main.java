package com.github.assisstion.spaceInvaders;

import javax.swing.SwingUtilities;

public class Main{
	
	private static boolean running;

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new StartupRunnable());
		
	}
	
	private static class StartupRunnable implements Runnable{
		@Override
		public void run(){
			running = true;
			Runtime.getRuntime().addShutdownHook(new Thread(new ShutdownRunnable()));
			MainCanvas.start();
		}
	}
	
	private static class ShutdownRunnable implements Runnable{

		@Override
		public void run(){
			running = false;
			ResourceManager.setMuted(true);
			System.out.println("Shutdown");
		}
		
	}
	
	public static boolean isRunning(){
		return running;
	}
}
