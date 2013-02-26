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

import com.github.assisstion.spaceInvaders.EnemySquad.Direction;

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
	@SuppressWarnings("unused")
	private static final Font FONT_SMALL = new Font("Times New Roman", Font.BOLD, 20);
	
	//Unused for now
	
	private static final Font FONT_LARGE = new Font("Times New Roman", Font.BOLD, 80);
	
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
<<<<<<< HEAD
	//Direction of the enemies
	private boolean enemyMovingDown = false;
=======
>>>>>>> Fixed enemy movement by adding the EnemySquad class
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
	//set containing all enemies
	private ConcurrentSkipListSet<EnemySquad> enemySquads = new ConcurrentSkipListSet<EnemySquad>();
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
		setPreferredSize(new Dimension(960, 640));
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
			else if(state.equalsIgnoreCase("game_over")){
				updateMain(g);
				MainCanvas.isOn = false;
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
		g.setFont(FONT_LARGE);
		g.drawString(message, getWidth() / 2 - (g.getFontMetrics().stringWidth(message) / 2), 350);
		
	}
	
	/*
	 * Main update method
	 */
	public void updateMain(Graphics graphics){
		g = (Graphics2D) graphics;
		//Renders the game objects
		for(Sprite object : gameObjects){
			Helper.renderSprite(g, object);
		}
		inputUpdate();
		bulletUpdate();
		playerUpdate();
		endUpdate();
	}
	
	//map input will be developed here later
	public void constructEnemyFormation(){
		int x=10;
		EnemySquad enemies = new EnemySquad();
		for (int i=0; i<10; i++){
			enemies.direction = EnemySquad.Direction.RIGHT;
			Enemy enemy3 = new Enemy(Enemy.EnemyType.RED,x,20);
			gameObjects.add(enemy3);
			enemies.add(enemy3);
			Enemy enemy2 = new Enemy(Enemy.EnemyType.BLUE,x,60);
			gameObjects.add(enemy2);
			enemies.add(enemy2);
			Enemy enemy4 = new Enemy(Enemy.EnemyType.BLUE,x,100);
			gameObjects.add(enemy4);
			enemies.add(enemy4);
			Enemy enemy1 = new Enemy(Enemy.EnemyType.NORMAL,x,140);
			gameObjects.add(enemy1);
			enemies.add(enemy1);
			enemy1 = new Enemy(Enemy.EnemyType.NORMAL,x,180);
			gameObjects.add(enemy1);
			enemies.add(enemy1);
			enemySquads.add(enemies);
			x=x+50;
		}
	}
	
	//For cleaning up stuff
	public void endUpdate(){
		for(EnemySquad enemies : enemySquads){
			if(enemies.isEmpty()){
				enemySquads.remove(enemies);
			}
		}
	}
	
	public void inputUpdate(){
		//Bullet creation code
		if(spaceOn){
			if(player1.firingCooldown <= 0){
				int tempx = player1.x + 4;
				if (!bulletLeft){
					tempx=player1.x + 28;
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
			Helper.updateHitbox(player1);
		}
		else if(player1.currentDirection.equals(Player.Direction.RIGHT)){
			player1.x += 4;
			Helper.updateHitbox(player1);
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
		for(EnemySquad es : enemySquads){
			for (Enemy e : es){
				if (e.shootingCounter == 0){
					Bullet b = new Bullet(Bullet.BulletType.NORMAL, e.x,e.y);
					if (e.enemytype.equals(Enemy.EnemyType.RED)){
						b = new Bullet(Bullet.BulletType.RED, e.x,e.y);
					} 
					else if (e.enemytype.equals(Enemy.EnemyType.BLUE)){
						b = new Bullet(Bullet.BulletType.BLUE, e.x,e.y);
					}
					
					bullets.add(b);
					gameObjects.add(b);
					e.shootingCounter = MainCanvas.rand.nextInt(e.shootingCooldownMax - e.shootingCooldownMin) + e.shootingCooldownMin;
				}
				else{
					e.shootingCounter--;
				}
			}
		}
		
		for(Bullet b : bullets){

			if(b.direction.equals(Bullet.BulletDirection.UP)){
				b.y -= b.movementSpeed;
				Helper.updateHitbox(b);
			}
			else if(b.direction.equals(Bullet.BulletDirection.DOWN)){
				b.y += b.movementSpeed;
				Helper.updateHitbox(b);
			}
			for(Bunker k : bunkers){
				if(b.hitBox.overLaps(k.hitBox)){
					gameObjects.remove(b);
					
					k.health -= b.damage;
					bullets.remove(b);
					
				}
				if (k.health<=0){
					gameObjects.remove(k);
					bunkers.remove(k);
				} 
				else if ((k.health % 100) == 0 && k.lastImageUpdate > k.health){
					int x=k.getBunkerNum()+10;
					k.setImage(x);
					k.lastImageUpdate = k.health;
				}
			}
		
			if (b.hitBox.overLaps(player1.hitBox)){
				//CHANGE THIS LATER FOR VARYING BULLET DAMAGE
				if (b.direction.equals(Bullet.BulletDirection.DOWN) && !godmodeOn){
					player1.health-=b.damage;
					System.out.println("You've Been Hit!");
					bullets.remove(b);
					gameObjects.remove(b);
					if (player1.health<=0){
						gameObjects.remove(player1);
						System.out.println("You're Dead!");
						player1.x = MainCanvas.frame.getWidth();
						player1.y = MainCanvas.frame.getHeight();
						Helper.updateHitbox(player1);
						state = "game_over";
					}
				}
			}
			for(EnemySquad enemies : enemySquads){
				for(Enemy e : enemies){
					if(b.hitBox.overLaps(e.hitBox)){
						if (b.direction.equals(Bullet.BulletDirection.UP)){
							gameObjects.remove(b);
							bullets.remove(b);
							e.health -= b.damage;
							if(e.health <= 0){
								enemies.remove(e);
								gameObjects.remove(e);
								System.out.println("Enemy Killed");
							}
						}
					}
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
	
	public void startGame(){
		//Starts the game
		System.out.println("It's starting!");
		//Creates a new player
		new Thread(new MovementClock()).start();
		player1 = new Player("Bob");
		gameObjects.add(player1);
		//loads map plan here
		constructEnemyFormation();
		//Constructs the bunker formations
		constructBunkerFormation(64, 500);
		constructBunkerFormation(252, 500);
		constructBunkerFormation(440, 500);
		constructBunkerFormation(628, 500);
		constructBunkerFormation(816, 500);
		state = "main";
	}
	
	//Constructs a bunker formation with the top left corner at (x, y)
	public void constructBunkerFormation(int x, int y){
		int bunkerSize = Bunker.BUNKER_SIZE;
		constructBunker(4,x+bunkerSize*3,y+bunkerSize);
		constructBunker(0,x,y+bunkerSize*3);
		constructBunker(0,x,y+bunkerSize*2);
		constructBunker(0,x,y+bunkerSize);
		constructBunker(1,x,y);
		constructBunker(0,x+bunkerSize,y);
		constructBunker(0,x+bunkerSize*2,y);
		constructBunker(0,x+bunkerSize*3,y);
		constructBunker(2,x+bunkerSize*4,y);
		constructBunker(0,x+bunkerSize*4,y+bunkerSize);
		constructBunker(0,x+bunkerSize*4,y+bunkerSize*2);
		constructBunker(0,x+bunkerSize*4,y+bunkerSize*3);
		constructBunker(3,x+bunkerSize,y+bunkerSize);
	}

	@Override
	public void keyPressed(KeyEvent e){
		if (e.getKeyCode()==KeyEvent.VK_ENTER) {
			if(state.equalsIgnoreCase("ready")){
				startGame();
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
	
	private void constructBunker(int bunkerType, int x, int y){
		Bunker a = new Bunker(bunkerType,x,y);
		bunkers.add(a);
		gameObjects.add(a);
	} 
	
	public void moveEnemies(){
<<<<<<< HEAD
		for (Enemy e: enemies){
			
			if (enemyMovingDown && 1+2==5){
				e.y+=50;
				e.lastMovement=Enemy.DirectionType.DOWN;
			}
			//Situation at the very end, right hand side.
			else if (e.x+64 > MainCanvas.frame.getWidth() && e.lastMovement.equals(Enemy.DirectionType.RIGHT)){
				e.y+=50;
				e.lastMovement=Enemy.DirectionType.DOWN;
				enemyMovingDown=true;
			}
			//situation after just moving down from above situation
			else if (e.x+64 > MainCanvas.frame.getWidth() && e.lastMovement.equals(Enemy.DirectionType.DOWN)){
				e.x-=50;
				e.lastMovement=Enemy.DirectionType.LEFT;
			//situation at the very end, left hand side
			} else if (e.x-64 > MainCanvas.frame.getWidth() && e.lastMovement.equals(Enemy.DirectionType.LEFT)){
				e.y+=50;
				e.lastMovement=Enemy.DirectionType.DOWN;
				enemyMovingDown=true;
			}
			//situation after just moving down from above situation
			else if (e.x-64 > MainCanvas.frame.getWidth() && e.lastMovement.equals(Enemy.DirectionType.DOWN)){
				e.x+=50;
				e.lastMovement=Enemy.DirectionType.RIGHT;
			//normal situation, moving left.
			} else if (e.lastMovement.equals(Enemy.DirectionType.LEFT)){
				e.x-=50;
			} else if (e.lastMovement.equals(Enemy.DirectionType.RIGHT) || e.lastMovement.equals(Enemy.DirectionType.NULL)){
				e.x+=50;
				e.lastMovement=Enemy.DirectionType.RIGHT;
			}
			else {
				System.out.println("ERROR");
			}
			
			Helper.updateHitbox(e);
=======
		for(EnemySquad enemies : enemySquads){
			if(enemies.direction.equals(Direction.DOWN)){
				enemies.direction = enemies.pendingDirection;
			}
			for (Enemy e: enemies){
				if (e.x+50 >= MainCanvas.frame.getWidth() && enemies.direction.equals(EnemySquad.Direction.RIGHT)){
					enemies.direction = EnemySquad.Direction.DOWN;
					enemies.pendingDirection = EnemySquad.Direction.LEFT;
				}
				else if (e.x-50<=0 && enemies.direction.equals(EnemySquad.Direction.LEFT)){
					enemies.direction = EnemySquad.Direction.DOWN;
					enemies.pendingDirection = EnemySquad.Direction.RIGHT;
				}
			}
			for(Enemy e : enemies){
				if (enemies.direction.equals(EnemySquad.Direction.RIGHT)){
					e.x+=50;
				} 
				else if (enemies.direction.equals(EnemySquad.Direction.LEFT)){
					e.x-=50;
				} 
				else if(enemies.direction.equals(EnemySquad.Direction.DOWN)){
					e.y+=50;
				}
				Helper.updateHitbox(e);
			}
>>>>>>> Fixed enemy movement by adding the EnemySquad class
		}
	}
}

