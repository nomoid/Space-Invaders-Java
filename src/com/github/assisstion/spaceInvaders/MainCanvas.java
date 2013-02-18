package com.github.assisstion.spaceInvaders;

import javax.swing.JFrame;

/**
 * Main class for starting the program.
 * @author Markus Feng
 * @author Michael Man
 */
public class MainCanvas {
	public static void main(String[] args) {
		 /*
		  * Create a new JFrame and set it's properties up.
		  */
		 JFrame frame = new JFrame("Space Invaders");
		 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 frame.setResizable(false);
		 
		 /*
		  * Creates the engine and adds it to the frame
		  */
		 
		 Engine engine = new Engine();
		 frame.add(engine);
		 
		 /*
		  * Pack the frame, position it in the center of the screen, and then display
		  * it.
		  */
		 
		 frame.pack();
		 frame.setLocationRelativeTo(null);
		 frame.setVisible(true);
		 
		 /*
		  * Starts the engine
		  */
		 
		 engine.start();
		 
		}
}
