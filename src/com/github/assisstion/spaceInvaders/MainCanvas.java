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
public class MainCanvas {
	
	public static JFrame frame;
	public static Engine engine;
	public static Random rand;
	public static boolean isOn;
	public static Menu menu;
	
	public static void main(String[] args) {
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
		
		menu = new Menu();
		menu.addMenuBuilder(new MainMenuBuilder());
		frame.setContentPane(menu);
		
		frame.setLocationRelativeTo(null);
		frame.pack();
		frame.setVisible(true);
		frame.validate();

		System.out.println("Frame created");
	}
}
