package com.github.assisstion.spaceInvaders;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.JFrame;

import com.github.assisstion.spaceInvaders.gameObject.LinkHolder;
import com.github.assisstion.spaceInvaders.menu.HighScoreDataHandler;
import com.github.assisstion.spaceInvaders.menu.MainMenuBuilder;
import com.github.assisstion.spaceInvaders.menu.Menu;
import com.github.assisstion.spaceInvaders.menu.TexturePackDataHandler;
import com.github.assisstion.spaceInvaders.menu.canvas.Upgrades;

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
	public static Upgrades upgrades;
	public static boolean exceptional;
	public static ObjectOutputStream output;

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
	
	@SuppressWarnings("unused")
	protected static void start(){
		try {
			if(System.getProperty("os.name").equalsIgnoreCase("Mac OS X")){
				// take the menu bar off the jframe
				System.setProperty("apple.laf.useScreenMenuBar", "true");
				
				// set the name of the application menu item
				System.setProperty("com.apple.mrj.application.apple.menu.about.name", "Space Invaders");
				
			}
			
			
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
			
			if (TexturePackDataHandler.file.exists()){
				TexturePackDataHandler.load();
			} else {
				TexturePackDataHandler.defaults();
			}
			
			upgrades = new Upgrades();
			
			/*
			 * Create a new JFrame and set it's properties up.
			 */
			
			System.out.println("Program launch");
			frame = new JFrame("Space Invaders");
			HighScoreDataHandler.clearData();
			LinkHolder.restoreDefaults();
			
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setResizable(false);
			
			/*
			 * Reads game data
			 */
			try{
				if(Data.DATA_LOCATION == "" || Data.DATA_LOCATION_FOLDER == ""){
					throw new IOException("Data IO Disabled");
				}
				File file = new File(Data.DATA_LOCATION);
				File folder = new File(Data.DATA_LOCATION_FOLDER);
				if(!folder.exists()){
					folder.mkdirs();
				}
				if(!file.exists()){
					file.createNewFile();
				}
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
				
				try{
					try{
						DataHandler.manager = DataManager.read(ois);
					}
					finally{
						ois.close();
						output = new ObjectOutputStream(new FileOutputStream(file));
					}
				}
				catch(IOException e){
					//Empty file or corrupted Stream
					DataManager.write(output, DataHandler.manager);
				}
			}
			catch(Exception e){
				e.printStackTrace();
				exceptional = true;
			}
			 
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

	public static void disableOutputs(){
		try{
			if(MainCanvas.output != null){
				MainCanvas.output.close();
			}
		}
		catch(IOException e){
			//Something wrong with Stream closure
		}
	}
}
