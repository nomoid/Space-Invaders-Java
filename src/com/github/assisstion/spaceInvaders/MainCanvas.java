package com.github.assisstion.spaceInvaders;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.github.assisstion.spaceInvaders.gameObject.LinkHolder;
import com.github.assisstion.spaceInvaders.menu.HighScoreDataHandler;
import com.github.assisstion.spaceInvaders.menu.MainMenuBuilder;
import com.github.assisstion.spaceInvaders.menu.Menu;

/**
 * Main class for starting the program.
 * @author Markus Feng
 * @author Michael Man
 */
public final class MainCanvas {
	public static final int FRAME_WIDTH = 960;
	public static final int FRAME_HEIGHT = 740;
	
	public static JFrame frame;
	public static Engine engine;
	public static Random rand;
	public static boolean isOn;
	public static Menu menu;
	
	private static boolean running;

	/*
	 * There can only be one audio stream
	 * running at a time due to this lock, 
	 * and it can only run when the system
	 * is ready.
	 */
	public static ReentrantLock audioLock = new ReentrantLock();
	public static Condition looperCondition = audioLock.newCondition();
	
	//This class should not be instantiated
	private MainCanvas(){
		
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new MainRunnable());
	}
	
	private static class MainRunnable implements Runnable{
		
		@Override
		public void run(){
			running = true;
			try {
				if(System.getProperty("os.name").equalsIgnoreCase("Mac OS X")){
					// take the menu bar off the jframe
					System.setProperty("apple.laf.useScreenMenuBar", "true");
					
					// set the name of the application menu item
					System.setProperty("com.apple.mrj.application.apple.menu.about.name", "Space Invaders");
					
				}
				
				/*
				 * Create a new JFrame and set it's properties up.
				 */
				
				Runtime.getRuntime().addShutdownHook(new Thread(new ShutdownRunnable()));
				
				System.out.println("Program launch");
				frame = new JFrame("Space Invaders");
				HighScoreDataHandler.clearData();
				LinkHolder.restoreDefaults();
				
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setResizable(false);
				 
				/*
				 * Creates the engine and adds it to the frame
				 */
				
				System.out.println("Engine created");
				
				/*
				 * Pack the frame, position it in the center of the screen, and then display
				 * it, and add the menu.
				 */
				audioLock.lock();
				try{
					menu = new Menu();
					
					frame.setContentPane(menu);
					frame.setLocationRelativeTo(null);
					frame.pack();
					frame.setVisible(true);
					frame.validate();
					menu.addMenuBuilder(new MainMenuBuilder());
				}
				finally{
					audioLock.unlock();
				}
		
				System.out.println("Frame created");
			}
			catch(Exception e){
				//TODO placeholder
				e.printStackTrace();
			}
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
