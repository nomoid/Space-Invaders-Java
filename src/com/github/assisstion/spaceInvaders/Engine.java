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
import java.util.concurrent.ConcurrentSkipListSet;

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
	private String godmode = "";
	//Unused for now
	@SuppressWarnings("unused")
	private boolean godmodeOn = false;
	private Player player1;
	private boolean bulletLeft = true;
	//true if right arrow key down
	private boolean rightOn=false;
	//true if left arrow key down
	private boolean leftOn=false;
	//true if space key down
	private boolean spaceOn = false;
	//Set containing all game objects
	private ConcurrentSkipListSet<Sprite> gameObjects = new ConcurrentSkipListSet<Sprite>();
	//Set containing all bullets
	private ConcurrentSkipListSet<Bullet> bullets = new ConcurrentSkipListSet<Bullet>();
	//Set containing all bunkers
	private ConcurrentSkipListSet<Bunker> bunkers = new ConcurrentSkipListSet<Bunker>();
	
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
	 * DO NOT put direct code in here, please put methods accessing the code from here
	 */
	@Override
	public void paint(Graphics g){
		try{
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
		catch(GameException e){
			//placeholder
			e.printStackTrace();
			System.exit(1);
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
		//Renders the game objects
		for(Sprite object : gameObjects){
			RenderHelper.renderSprite(g, object);
		}
		inputUpdate();
		bulletUpdate();
		playerUpdate();
	}
	
	public void inputUpdate(){
		//Bullet creation code
		if(spaceOn){
			if(player1.firingCooldown <= 0){
				int tempx = player1.x;
				if (!bulletLeft){
					tempx=player1.x+32;
					bulletLeft = true;
				} 
				else {
					bulletLeft = false;
				}
				Bullet b = new Bullet(Bullet.BulletType.PLAYER, tempx, player1.y);
				bullets.add(b);
				gameObjects.add(b);
				player1.firingCooldown = 32;
				System.out.println("Fire!");
			}
		}
	}
	
	public void playerUpdate(){
		//Changes the player location depending on the current direction
		if(player1.currentDirection.equals(Player.Direction.LEFT)){
			player1.x -= 4;
		}
		else if(player1.currentDirection.equals(Player.Direction.RIGHT)){
			player1.x += 4;
		}
		if(player1.firingCooldown > 0){
			player1.firingCooldown--;
		}
		if(player1.x < 0){
			player1.x = 0;
		}
		else if(player1.x > MainCanvas.frame.getWidth() - player1.getImage().getWidth()){
			player1.x = MainCanvas.frame.getWidth() - player1.getImage().getWidth();
		}
	}
	
	public void bulletUpdate(){
		for(Bullet b : bullets){
			if(b.movementMode){
				if(b.movementCounter == 0){
					if(b.direction.equals(Bullet.BulletDirection.UP)){
						b.y--;
					}
					else if(b.direction.equals(Bullet.BulletDirection.DOWN)){
						b.y++;
					}
					b.movementCounter = b.movementSpeed;
				}
				else{
					b.movementCounter--;
				}
			}
			else{
				if(b.direction.equals(Bullet.BulletDirection.UP)){
					b.y -= b.movementSpeed;
				}
				else if(b.direction.equals(Bullet.BulletDirection.DOWN)){
					b.y += b.movementSpeed;
				}
			}
			if(b.y < 0 - b.getImage().getHeight() || b.x < 0 - b.getImage().getWidth() || b.x > MainCanvas.frame.getWidth() || b.y > MainCanvas.frame.getHeight()){
				bullets.remove(b);
				gameObjects.remove(b);
			}
		}
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
				player1 = new Player("Bob");
				gameObjects.add(player1);
				constructBunker(10,20);
				state = "main";
			} 
			if (godmode.equals("god")){
				godmode();
				godmode="";
				System.out.println("God Mode is starting!");
			}
		}
		else if (e.getKeyCode()==KeyEvent.VK_SPACE) {
			//tells the update loop to allow bullet firing
			if (state.equals("main")){
				spaceOn = true;
			}
		}
		else if (e.getKeyCode()==KeyEvent.VK_RIGHT) {
			//sets the direction to Right
			if (state.equals("main")){
				rightOn=true;
				player1.currentDirection = Player.Direction.RIGHT;
			}
		}
		else if (e.getKeyCode()==KeyEvent.VK_LEFT) {
			//sets the direction to Left
			if (state.equals("main")){
				leftOn=true;
				player1.currentDirection = Player.Direction.LEFT;
			}
		}

		else if (e.getKeyCode()==KeyEvent.VK_G){
			if (godmode.equals("")){
				godmode = "g";
			}
		}
		
		else if (e.getKeyCode()==KeyEvent.VK_O){
			if (godmode.equals("g")){
				godmode = "go";
			}
		}
		else if (e.getKeyCode()==KeyEvent.VK_D){
			if (godmode.equals("go")){
				godmode = "god";
				// Added God Mode
			}
			
		}
		else {
			godmode = "";
		}
	}

	@Override
	public void keyReleased(KeyEvent e){
		if (e.getKeyCode()==KeyEvent.VK_RIGHT) {
			//sets the direction to None
			if (state.equals("main")){
				rightOn=false;
				if (player1.currentDirection == Player.Direction.RIGHT){
					player1.currentDirection = Player.Direction.NONE;
					//Sees whether leftarrow is still being pressed.
					if (leftOn){
						//If it is, change direction back to left
						player1.currentDirection = Player.Direction.LEFT;
					}
				}
			}
		
		}
		else if (e.getKeyCode()==KeyEvent.VK_LEFT) {
			//sets the direction to None
			if (state.equals("main")){
				leftOn=false;
				if (player1.currentDirection == Player.Direction.LEFT){
					player1.currentDirection = Player.Direction.NONE;
					//Sees whether rightarrow is still being pressed.
					if (rightOn){
						player1.currentDirection = Player.Direction.RIGHT;
					}
					player1.currentDirection = Player.Direction.NONE;
					//Sees whether rightarrow is still being pressed.
					if (rightOn){
						//If it is, change direction back to Right
						player1.currentDirection = Player.Direction.RIGHT;
					}
				}
			}
		}
		else if (e.getKeyCode()==KeyEvent.VK_SPACE) {
			//tells the update loop to stop bullet firing
			if (state.equals("main")){
				spaceOn = false;
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e){
		
	}
	
	private void godmode(){
		godmodeOn = true;
		//Unimplemented
		//God Mode to be initiated here
	}
	
	private void constructBunker(int x, int y){
		Bunker a = new Bunker(0,x,y);
		bunkers.add(a);
		gameObjects.add(a);
	} 
}

