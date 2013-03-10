package com.github.assisstion.spaceInvaders;

import java.util.Random;

import javax.swing.JFrame;

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
	
	public static Object audioLock = new Object();
	
	//This class should not be instantiated
	private MainCanvas(){
		
	}
	
	public static void main(String[] args) {
		if(System.getProperty("os.name").equalsIgnoreCase("Mac OS X")){
			// take the menu bar off the jframe
			System.setProperty("apple.laf.useScreenMenuBar", "true");
			
			// set the name of the application menu item
			System.setProperty("com.apple.mrj.application.apple.menu.about.name", "Space Invaders");
			
		}
		
		/*
		 * Create a new JFrame and set it's properties up.
		 */
		
		System.out.println("Program launch");
		frame = new JFrame("Space Invaders");
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
		synchronized(audioLock){
			menu = new Menu();
			menu.addMenuBuilder(new MainMenuBuilder());
			frame.setContentPane(menu);
			
			frame.setLocationRelativeTo(null);
			frame.pack();
			frame.setVisible(true);
			frame.validate();
		}

		System.out.println("Frame created");
	}
}
