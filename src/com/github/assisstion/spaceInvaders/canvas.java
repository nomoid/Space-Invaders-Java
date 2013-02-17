package com.github.assisstion.spaceInvaders;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;

public class canvas {
	public static void main(String[] args) {
		 /*
		  * Create a new JFrame and set it's properties up.
		  */
		 JFrame frame = new JFrame("Space Invaders");
		 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 frame.setResizable(false);
		 
		 /*
		  * Create a new canvas, and set it's properties up.
		  */
		 Canvas canvas = new Canvas();
		 canvas.setBackground(Color.BLACK);
		 canvas.setPreferredSize(new Dimension(600, 700));
		 /*
		  * Add the canvas to the frame.
		  */
		 frame.add(canvas);
		 
		 /*
		  * Pack the frame, position it in the center of the screen, and then display
		  * it.
		  */
		 frame.pack();
		 frame.setLocationRelativeTo(null);
		 frame.setVisible(true);
		 
		}
}
