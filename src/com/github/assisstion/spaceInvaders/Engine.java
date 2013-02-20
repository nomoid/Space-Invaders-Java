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
import java.io.IOException;

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
	
	/*
	 *  Update code runs according to current state of the code
	 *  Possible states:
	 *  	not_ready: not ready to start
	 *  	ready: ready to start but not started yet
	 *  	main: started
	 */
	private String state = "not_ready";
	private Graphics2D g;
	private Player player1;
	
	/*
	 * Creates a new Engine and sets up the background and dimensions
	 */
	public Engine() {
		addKeyListener(this);
		setBackground(Color.PINK);
		setPreferredSize(new Dimension(600, 700));
	}
	
	/*
	 * Method for updating this canvas
	 * DO NOT put direct code in here, please put methods accessing the code from here
	 */
	@Override
	public void paint(Graphics g){
		requestFocus();
		if(state.equalsIgnoreCase("not_ready")){
			return;
		}
		else if(state.equalsIgnoreCase("ready")){
			startGame(g);
		}
		else if(state.equalsIgnoreCase("main")){
			updateMain(g);
		}
		else{
			//Throws an exception if none of the states match
			throw new IllegalStateException("Illegal engine state: " + state);
		}
	}
	
	/*
	 * Starts the game
	 */
	public void startGame(Graphics graphics) {
		g = (Graphics2D)graphics;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(Color.BLUE);
		String message = new String("Press Enter To Start");
		g.setFont(FONT_SMALL);
		g.drawString(message, getWidth() / 2 - (g.getFontMetrics().stringWidth(message) / 2), 350);
		
	}
	
	/*
	 * Main update method
	 */
	public void updateMain(Graphics graphics){
		g = (Graphics2D) graphics;
		//Renders the player
		RenderHelper.renderSprite(g, player1);
		//Changes the player location depending on the current direction
		if(player1.currentDirection.equals(Player.Direction.LEFT)){
			player1.x--;
		}
		else if(player1.currentDirection.equals(Player.Direction.RIGHT)){
			player1.x++;
		}
		else if(player1.currentDirection.equals(Player.Direction.UP)){
			player1.y--;
		}
		else if(player1.currentDirection.equals(Player.Direction.DOWN)){
			player1.y++;
		}
		//Reloops this
		repaint();
	}
		
	/*
	 * Called by the main method when the game wants to start
	 */
	public void start(){
		state = "ready";
	}

	@Override
	public void keyPressed(KeyEvent e){
		if (e.getKeyCode()==KeyEvent.VK_ENTER) {
			if(state.equalsIgnoreCase("ready")){
				//Starts the game
				System.out.println("It's starting!");
				//Creates a new player
				try{
					player1 = new Player("Bob");
				}
				catch(IOException e1){
					e1.printStackTrace();
					//Placeholder
				}
				state = "main";
				
				//Always call repaint when something changes
				repaint();
			}
		}
		else if (e.getKeyCode()==KeyEvent.VK_RIGHT) {
			//sets the direction to Right
			if (state.equals("main")){
				player1.currentDirection = Player.Direction.RIGHT;
			}
		}
		else if (e.getKeyCode()==KeyEvent.VK_LEFT) {
			//sets the direction to Left
			if (state.equals("main")){
				player1.currentDirection = Player.Direction.LEFT;
			}
		}
		else if (e.getKeyCode()==KeyEvent.VK_UP) {
			//sets the direction to Up
			if (state.equals("main")){
				player1.currentDirection = Player.Direction.UP;
			}
		}
		else if (e.getKeyCode()==KeyEvent.VK_DOWN) {
			//sets the direction to Down
			if (state.equals("main")){
				player1.currentDirection = Player.Direction.DOWN;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e){
		if (e.getKeyCode()==KeyEvent.VK_RIGHT) {
			//sets the direction to None
			if (state.equals("main")){
				player1.currentDirection = Player.Direction.NONE;
			}
		}
		else if (e.getKeyCode()==KeyEvent.VK_LEFT) {
			//sets the direction to None
			if (state.equals("main")){
				player1.currentDirection = Player.Direction.NONE;
			}
		}
		else if (e.getKeyCode()==KeyEvent.VK_UP) {
			//sets the direction to None
			if (state.equals("main")){
				player1.currentDirection = Player.Direction.NONE;
			}
		}
		else if (e.getKeyCode()==KeyEvent.VK_DOWN) {
			//sets the direction to None
			if (state.equals("main")){
				player1.currentDirection = Player.Direction.NONE;
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e){
		//No use yet
	}
	
	
}

