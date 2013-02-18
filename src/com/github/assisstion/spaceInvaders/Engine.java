package com.github.assisstion.spaceInvaders;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Engine class for rendering the game. This class extends Canvas and overrides the paint()
 * method so it can be directly used to paint objects.
 * @author Markus Feng
 * @author Michael Man
 */
public class Engine extends Canvas implements KeyListener{
	
	/*
	 * Serial version UID, recommended for every class that implements Serializable
	 * or any class that extends something that implements Serializable
	 */
	private static final long serialVersionUID = 21816248595432439L;

	private static final Font FONT_SMALL = new Font("Times New Roman", Font.BOLD, 20);
	
	//Unused for now
	@SuppressWarnings("unused")
	private static final Font FONT_LARGE = new Font("Times New Roman", Font.BOLD, 40);
	
	//Set to true by the main method when the game is ready to render
	private boolean readyToStart = false;
	//Set to true by this class when the game starting script starts running
	private boolean started = false;
	
	/*
	 * Creates a new Engine and sets up the background and dimensions
	 */
	public Engine() {
		addKeyListener(this);
		setBackground(Color.BLACK);
		setPreferredSize(new Dimension(600, 700));
	}
	
	/*
	 * Method for updating this canvas
	 */
	@Override
	public void paint(Graphics g){
		if(!readyToStart){
			return;
		}
		if(!started){
			started = true;
			startGame(g);
		}
		
	}
	
	/*
	 * Starts the game
	 */
	public void startGame(Graphics graphics) {
		Graphics2D g = (Graphics2D)graphics;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(Color.WHITE);
		String message = new String("Press Enter To Start");
		g.setFont(FONT_SMALL);
		g.drawString(message, getWidth() / 2 - (g.getFontMetrics().stringWidth(message) / 2), 350);
		
	}
	
	/*
	 * Called by the main method when the game wants to start
	 */
	public void start(){
		readyToStart = true;
	}

	@Override
	public void keyPressed(KeyEvent e){
		if (e.getKeyCode()==KeyEvent.VK_ENTER) {
			System.out.println("It's starting!");
		}
	}

	@Override
	public void keyReleased(KeyEvent e){
		
	}

	@Override
	public void keyTyped(KeyEvent e){
		
	}
	
	
}

