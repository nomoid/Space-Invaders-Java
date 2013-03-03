package com.github.assisstion.spaceInvaders;

import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ConcurrentSkipListSet;

import com.github.assisstion.spaceInvaders.EnemySquad.Direction;
import com.github.assisstion.spaceInvaders.Powerup.PowerupType;
import com.github.assisstion.spaceInvaders.Bullet.BulletType;

import static com.github.assisstion.spaceInvaders.Data.*;

/**
 * Engine class for rendering the game. This class extends Canvas and overrides
 * the paint() method so it can be directly used to paint objects.
 * 
 * @author Markus Feng
 * @author Michael Man
 */
public class Engine extends Canvas implements KeyListener {

	/*
	 * Serial version UID, recommended for every class that implements
	 * Serializable or any class that extends something that implements
	 * Serializable
	 */
	
	private boolean mothershipOn = false;
	private Enemy mothership = null;
	private boolean readyForMothership = false;
	private boolean EggOn;
	private boolean PixarOn;
	private int deathCounter;
	private int nextReward;
	private boolean rewardAvaliable = false;
	private Powerup.PowerupType openReward;
	private int livesatlvlstart;
	private String tempname = "";
	private static final long serialVersionUID = 21816248595432439L;
	public int hitSpree = 0;
	private char[] leName = createEmptyName('-', NAME_MAX_LENGTH);
	private int nameLength;
	
	/*
	 * Update code runs according to current state of the code Possible states:
	 * not_ready: not ready to start ready: ready to start but not started yet
	 * main: started
	 */
	private boolean minigameOn = false;
	public String state = "not_ready";
	private Graphics2D g;
	private String godmode = "";
	// Unused for now
	private boolean godmodeOn = false;
	private Player player1;
	// true if right arrow key down
	private boolean rightOn = false;
	// true if left arrow key down
	private boolean leftOn = false;
	// true if space key down
	private boolean spaceOn = false;
	// Set containing all game objects
	private ConcurrentSkipListSet<Sprite> gameObjects = new ConcurrentSkipListSet<Sprite>();
	// set containing all enemies
	private ConcurrentSkipListSet<EnemySquad> enemySquads = new ConcurrentSkipListSet<EnemySquad>();
	// Set containing all bullets
	private ConcurrentSkipListSet<Bullet> bullets = new ConcurrentSkipListSet<Bullet>();
	// Set containing all bunkers
	private ConcurrentSkipListSet<Bunker> bunkers = new ConcurrentSkipListSet<Bunker>();
	// Set containing all visible powerups
	private ConcurrentSkipListSet<Powerup> powerups = new ConcurrentSkipListSet<Powerup>();
	// Set containing all objects to be rendered above gameObjects
	private ConcurrentSkipListSet<Sprite> overlay = new ConcurrentSkipListSet<Sprite>();
	// Set containing all explosions
	private ConcurrentSkipListSet<Explosion> explosions = new ConcurrentSkipListSet<Explosion>();
	// Current level
	public int currentLevel = 1;

	/*
	 * Creates a new Engine and sets up the background and dimensions
	 */
	public Engine() {
		addKeyListener(this);
		setBackground(Color.BLACK);
		setPreferredSize(new Dimension(960, 740));
	}

	/*
	 * Method for updating this canvas DO NOT put direct code in here, please
	 * put methods accessing the code from here
	 */
	@Override
	public void paint(Graphics g) {
		try {
			requestFocus();
			if (state.equalsIgnoreCase("not_ready")) {
				return;
			} else if (state.equalsIgnoreCase("ready")) {
				startGame((Graphics2D) g);
			} else if (state.equalsIgnoreCase("nametaking")) {
				renderName(g);
			} else if (state.equalsIgnoreCase("main")) {
				updateMain(g);
			} else if (state.equalsIgnoreCase("game_over")) {
				gameLost((Graphics2D) g);
			} else if (state.equalsIgnoreCase("game_won")) {
				gameWon((Graphics2D) g);
			} else if (state.equalsIgnoreCase("pause")) {
				render((Graphics2D) g);
			} else if (state.equalsIgnoreCase("just_died")) {
				deathStuff((Graphics2D) g);
				render((Graphics2D) g);
				explosionUpdate();
			} else {
				// Throws an exception if none of the states match
				throw new IllegalStateException("Illegal engine state: "
						+ state);
			}
		} catch (GameException e) {
			// placeholder
			e.printStackTrace();
			System.exit(1);
		} catch (Exception e) {
			// placeholder
			e.printStackTrace();
			System.exit(1);
		}
	}

	public void deathStuff(Graphics2D g) {
		deathCounter--;
		String secondsLeft = "3";
		Font leFont = new Font("Copperplate", Font.BOLD, 100);
		g.setFont(leFont);
		g.setColor(Color.RED);

		if (deathCounter <= 125) {
			secondsLeft = "2";
		}
		if (deathCounter <= 63) {
			secondsLeft = "1";
		}
		if (deathCounter == 0) {
			secondsLeft = "0";
			state = "main";
			gameObjects.add(player1);
			return;
		}
		String message = "Respawn in " + secondsLeft;

		g.drawString(message,
				getWidth() / 2 - (g.getFontMetrics().stringWidth(message) / 2),
				570);

	}

	/*
	 * Starts the game
	 */
	public void renderName(Graphics gOld) {
		g = (Graphics2D) gOld;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_OFF);

		startGame(g);

		Font lefont = new Font("Copperplate", Font.PLAIN, 200);
		g.setFont(lefont);
		g.setColor(Color.RED);

		tempname = String.copyValueOf(removeChar(leName, '-'));

		String message = String.copyValueOf(leName);
		g.drawString(message,
				getWidth() / 2 - (g.getFontMetrics().stringWidth(message) / 2),
				500);

		// implement countdown? Hi, Name! 3... 2... 1... EPIC
	}

	private char[] removeChar(char[] ca, char c) {
		char[] transferArray = new char[ca.length];
		int j = 0;
		for (int i = 0; i < ca.length; i++) {
			if (ca[i] != c) {
				transferArray[j++] = ca[i];
			}
		}
		char[] returnArray = new char[j];
		System.arraycopy(transferArray, 0, returnArray, 0, j);
		return returnArray;
	}

	public void startGame(Graphics2D g) {
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_OFF);
		g.setColor(Color.BLUE);
		String message = new String("Press Enter To Start");
		g.setFont(FONT_MEDIUM);
		g.drawString(message,
				getWidth() / 2 - (g.getFontMetrics().stringWidth(message) / 2),
				700);

		g.setFont(FONT_LARGE);
		g.setColor(Color.WHITE);
		message = "Welcome to";
		g.drawString(message,
				getWidth() / 2 - (g.getFontMetrics().stringWidth(message) / 2),
				80);
		message = "Space Invaders!";
		g.drawString(message,
				getWidth() / 2 - (g.getFontMetrics().stringWidth(message) / 2),
				150);
		Font fonttiny = new Font("Copperplate", Font.ITALIC, 18);
		g.setFont(fonttiny);
		message = "Created by Markus Feng and Michael Man (2013)";
		g.drawString(message,
				getWidth() / 2 - (g.getFontMetrics().stringWidth(message) / 2),
				180);

		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, 200, 960, 450);

		Font lefont = new Font("Capitals", Font.ROMAN_BASELINE, 50);
		g.setFont(lefont);
		g.setColor(Color.GREEN);
		message = "What is your name?";
		g.drawString(message,
				getWidth() / 2 - (g.getFontMetrics().stringWidth(message) / 2),
				260);

		state = "nametaking";
	}

	public void redeem() {
		rewardAvaliable = false;
		hitSpree = 0;
		processPowerup(player1, openReward);
	}

	/*
	 * Main update method
	 */
	public void updateMain(Graphics graphics) {
		g = (Graphics2D) graphics;
		// Renders the game objects
		render(g);
		inputUpdate();
		bulletUpdate();
		playerUpdate();
		powerupUpdate();
		enemyUpdate();
		hitSpreeUpdate();
		explosionUpdate();
		endUpdate();
		motherShip();

	}

	public void motherShip() {
		if (readyForMothership) {
			if (!mothershipOn) {
				if (MainCanvas.rand.nextInt(MOTHERSHIP_CHANCE) == 0) {

					mothership = new Enemy(Enemy.EnemyType.MOTHERSHIP, 10, 80);
					for (EnemySquad enemies : enemySquads) {
						enemies.add(mothership);
						Helper.updateHitbox(mothership);
					}
					gameObjects.add(mothership);
					System.out.println("Mothership Deployed!");
					mothershipOn = true;
				}
			} else {
				mothership.x += MOTHERSHIP_SPEED;
				Helper.updateHitbox(mothership);
			}
		}
	}

	public void render(Graphics2D g) {
		for (Sprite object : gameObjects) {
			Helper.renderSprite(g, object);
		}
		for (Sprite object : overlay) {
			Helper.renderSprite(g, object);
		}
		drawMenu(g);
	}

	public void gameLost(Graphics2D g) {
		g.clearRect(0, 0, 960, 740);
		String gameOver = new String("Game Over!");
		String yourScore = new String("Final Score: "
				+ (godmodeOn ? "°" : player1.score));
		g.setColor(Color.RED);
		g.setFont(FONT_HUGE);
		g.drawString(gameOver, getWidth() / 2
				- (g.getFontMetrics().stringWidth(gameOver) / 2), 370);
		g.setColor(Color.WHITE);
		g.setFont(FONT_LARGE);
		g.drawString(yourScore, getWidth() / 2
				- (g.getFontMetrics().stringWidth(yourScore) / 2), 450);
	}

	public void gameWon(Graphics2D g) {
		g.fillRect(0, 0, 960, 740);
		String gameWon = new String("You've Won!");
		String yourScore = new String("Final Score: "
				+ (godmodeOn ? "°" : player1.score));
		g.setColor(Color.BLUE);
		g.setFont(FONT_HUGE);
		g.drawString(gameWon,
				getWidth() / 2 - (g.getFontMetrics().stringWidth(gameWon) / 2),
				370);
		g.setColor(Color.WHITE);
		g.setFont(FONT_LARGE);
		g.drawString(yourScore, getWidth() / 2
				- (g.getFontMetrics().stringWidth(yourScore) / 2), 450);

	}

	public void gameCleanup() {
		if (state.equalsIgnoreCase("game_won")) {
			player1.score += player1.livesRemaining
					* Player.PLAYER_DEFAULT_HEALTH + player1.health;
		}
		for (Sprite s : gameObjects) {
			gameObjects.remove(s);
		}
		for(EnemySquad enemies : enemySquads){
			gameObjects.remove(enemies);
		}
		for(Bullet b : bullets){
			gameObjects.remove(b);
		}
		for(Bunker k : bunkers){
			gameObjects.remove(k);
		}
		for(Powerup p : powerups){
			gameObjects.remove(p);
		}
		for (Sprite s : overlay) {
			gameObjects.remove(s);
		}
		for (Explosion ex : explosions) {
			gameObjects.remove(ex);
		}
	}

	public void drawMenu(Graphics2D g) {

		// message = Score Display
		// message2=Health Display
		// message3=Life Display
		// message4=GOD MODE ON CONFIRMER
		// message5=Level Display

		g.setFont(FONT_SMALL);
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, 960, 70);
		g.setColor(Color.BLACK);
		String message = null;
		String message3 = null;
		if (!godmodeOn) {
			message = new String("Score: " + player1.score);
			message3 = new String("Lives Left: " + player1.livesRemaining);
		} else {
			g.setColor(Color.RED);
			message = new String("Score: °");
			message3 = new String("Lives Left: °");

		}
		g.drawString(message, 10, 25);
		g.drawString(message3, 710, 25);

		if (godmodeOn) {
			g.setColor(Color.RED);
			g.setFont(FONT_MEDIUM);
			String message4 = new String("GOD MODE ON");
			g.drawString(message4, getWidth() / 2
					- (g.getFontMetrics().stringWidth(message4) / 2), 570);
			g.setFont(FONT_SMALL);
		}

		String message2 = new String("Health: " + player1.health + "/"
				+ Player.PLAYER_DEFAULT_HEALTH);

		Color tempColor = null;
		if (godmodeOn) {
			tempColor = Color.red;
			message2 = ("Health: INFINITE");
		} else if (player1.health > Player.PLAYER_DEFAULT_HEALTH * 3 / 4) {
			tempColor = Color.green;
		} else if (player1.health > Player.PLAYER_DEFAULT_HEALTH / 2) {
			tempColor = Color.yellow;
		} else if (player1.health > Player.PLAYER_DEFAULT_HEALTH / 4) {
			tempColor = Color.orange;
		} else {
			tempColor = Color.red;
		}

		g.setColor(tempColor);

		g.drawString(message2, 10, 60);

		g.setColor(Color.BLACK);
		String message5 = new String("Level: " + currentLevel + "/5");
		
		if (minigameOn){
			message5 = new String("Level:Bonus");
		}
		g.drawString(message5, 710, 60);

		g.setColor(Color.RED);
		if (rewardAvaliable) {
			g.setColor(Color.GREEN);
		}

		Font lefont = new Font("Copperplate", Font.BOLD, 30);
		g.setFont(lefont);
		g.drawString("Hitstreak: " + hitSpree + "/" + nextReward, 355, 27);
		g.drawString("Reward: " + (rewardAvaliable ? openReward : "N/A"), 355,
				55);

		Stroke oldStroke = g.getStroke();
		g.setStroke(new BasicStroke(7));
		g.drawRect(350, 1, 330, 64);
		g.setStroke(oldStroke);

	}

	public void hitSpreeUpdate() {
		for (int i = 0; i < REWARDS_REQUIREMENTS.length; i++) {
			nextReward = REWARDS_REQUIREMENTS[i];
			if (hitSpree >= REWARDS_REQUIREMENTS[i]) {
				openReward = REWARDS_LIST[i];
				rewardAvaliable = true;
			} else {
				i--;
				break;
			}
		}

	}

	public void explosionUpdate() {
		for (Explosion ex : explosions) {
			if (ex.explosionTimer <= 0) {
				overlay.remove(ex);
				explosions.remove(ex);
			} else {
				ex.explosionTimer--;
			}
		}
	}

	// map input will be developed here later
	public void constructEnemyFormation(int lvlnum) {
		int enemyWidth = LEVELS[lvlnum - 1][0];
		int x = 10;
		EnemySquad enemies = new EnemySquad();

		for (int i = 0; i < enemyWidth; i++) {
			Enemy.EnemyType[] EnemyData = null;
			switch (lvlnum) {
			case 1:
				EnemyData = LEVEL1DATA;
				break;
			case 2:
				//EnemyData = LEVEL2DATA;
				break;
			case 3:
				EnemyData = LEVEL3DATA;
				break;
			case 4:
				EnemyData = LEVEL4DATA;
				break;
			case 5:
				EnemyData = LEVEL5DATA;
				break;
			case 6: 
				EnemyData = MINIGAMEDATA;
				minigameOn=true;
				break;
			default:
				throw new IllegalArgumentException("Level Number Error!");
			}
			enemies.direction = EnemySquad.Direction.RIGHT;

			Enemy enemy1 = new Enemy(EnemyData[0], x, 80);
			Enemy enemy2 = new Enemy(EnemyData[1], x, 120);
			Enemy enemy3 = new Enemy(EnemyData[2], x, 160);
			Enemy enemy4 = new Enemy(EnemyData[3], x, 200);
			Enemy enemy5 = new Enemy(EnemyData[4], x, 240);

			gameObjects.add(enemy1);
			gameObjects.add(enemy2);
			gameObjects.add(enemy3);
			gameObjects.add(enemy4);
			gameObjects.add(enemy5);
			enemies.add(enemy1);
			enemies.add(enemy2);
			enemies.add(enemy3);
			enemies.add(enemy4);
			enemies.add(enemy5);

			if (lvlnum >= 2) {
				Enemy enemy6 = new Enemy(EnemyData[5], x, 280);
				Enemy enemy7 = new Enemy(EnemyData[6], x, 320);
				gameObjects.add(enemy6);
				enemies.add(enemy6);
				gameObjects.add(enemy7);
				enemies.add(enemy7);
			}
			if (lvlnum >= 3) {
				Enemy enemy8 = new Enemy(EnemyData[7], x, 360);
				gameObjects.add(enemy8);
				enemies.add(enemy8);
			}
			if (lvlnum >= 4) {
				Enemy enemy9 = new Enemy(EnemyData[8], x, 400);
				gameObjects.add(enemy9);
				enemies.add(enemy9);
				Enemy enemy10 = new Enemy(EnemyData[9], x, 440);
				gameObjects.add(enemy10);
				enemies.add(enemy10);
			}

			enemySquads.add(enemies);
			if (lvlnum <= 2) {
				x += 70;
			} else if (lvlnum <= 4) {
				x += 60;
			} else {
				x += 50;
			}
		}
	}

	// For cleaning up stuff
	public void endUpdate() {
		for (EnemySquad enemies : enemySquads) {
			if (enemies.isEmpty()) {
				enemySquads.remove(enemies);
			}
			if (enemySquads.isEmpty()) {
				System.out.println("Level Complete!");
				nextLevel();
			}
		}
	}

	
	public void nextLevel() {
		// STUFF TO DO HERE: display info to player.
		if (currentLevel > 5) {
			gameCleanup();
			state = "game_won";
		} else {
			if (livesatlvlstart == player1.livesRemaining) {
				player1.livesRemaining++;
			}
			livesatlvlstart = player1.livesRemaining;
			currentLevel += 1;
			constructEnemyFormation(currentLevel);
			MovementClock.MovementSpeed = MovementClock.DEFAULT_SPEED;
			readyForMothership=false;
		}
		
		
	}

	public void inputUpdate() {
		// Bullet creation code
		if (spaceOn) {
			if (player1.firingCooldown <= 0) {
				int extraDamage = 1;
				if (player1.powerups.containsKey(PowerupType.DAMAGE)) {
					extraDamage = 3;
				}
				BulletType tempType = BulletType.PLAYER;
				if (PixarOn) {
					// sumthing
				} else if (EggOn) {
					tempType = BulletType.EGG;
				}
				Bullet b = new Bullet(tempType, player1.x + 16, player1.y,
						Bullet.BULLET_DAMAGE[BulletType.PLAYER.ordinal()]
								* extraDamage,
						Bullet.BULLET_MOVEMENT_SPEED[BulletType.PLAYER
								.ordinal()]);

				b.owner = player1;
				bullets.add(b);
				gameObjects.add(b);
				if (player1.powerups.containsKey(PowerupType.FIRERATE)) {
					player1.firingCooldown = Player.PLAYER_DEFAULT_FIRING_COOLDOWN / 3;
				} else {
					player1.firingCooldown = Player.PLAYER_DEFAULT_FIRING_COOLDOWN;
				}
			}
		}

	}

	public void playerUpdate() {
		// Changes the player location depending on the current direction
		int extraSpeed = 1;
		if (player1.powerups.containsKey(PowerupType.SPEED)) {
			extraSpeed = 3;
		}
		if (player1.currentDirection.equals(Player.Direction.LEFT)) {
			player1.x -= 4 * extraSpeed;
			Helper.updateHitbox(player1);
		} else if (player1.currentDirection.equals(Player.Direction.RIGHT)) {
			player1.x += 4 * extraSpeed;
			Helper.updateHitbox(player1);
		}
		if (player1.firingCooldown > 0) {
			if (godmodeOn) {
				player1.firingCooldown -= 10;
			} else {
				player1.firingCooldown--;
			}
		}
		if (player1.x < 0) {
			player1.x = 0;
		} else if (player1.x > MainCanvas.frame.getWidth()
				- player1.getImage().getWidth()) {
			player1.x = MainCanvas.frame.getWidth()
					- player1.getImage().getWidth();
		}
	}

	public void bulletUpdate() {
		for (EnemySquad es : enemySquads) {
			for (Enemy e : es) {
				if (!e.enemytype.equals(Enemy.EnemyType.MOTHERSHIP)) {
					if (e.shootingCounter <= 0) {
						Bullet b = new Bullet(BulletType.NORMAL, e.x, e.y);

						if (e.enemytype.equals(Enemy.EnemyType.RED)) {
							b = new Bullet(BulletType.RED, e.x, e.y);
						} else if (e.enemytype.equals(Enemy.EnemyType.BLUE)) {
							b = new Bullet(BulletType.BLUE, e.x, e.y);
						}

						b.owner = e;

						bullets.add(b);
						gameObjects.add(b);
						e.shootingCounter = MainCanvas.rand
								.nextInt(e.shootingCooldownMax
										- e.shootingCooldownMin)
								+ e.shootingCooldownMin;
					} else {
						e.shootingCounter--;
					}
				}
			}
		}

		for (Bullet b : bullets) {
			b.tempX += b.movementSpeed * Math.sin(Math.toRadians(b.rotation));
			b.tempY -= b.movementSpeed * Math.cos(Math.toRadians(b.rotation));
			b.updateLocation();
			Helper.updateHitbox(b);
			for (Bunker k : bunkers) {
				if (b.hitBox.overLaps(k.hitBox)) {
					gameObjects.remove(b);

					k.health -= b.damage;

					if (godmodeOn && b.owner instanceof Player) {
						k.health = 0;
					} else {
						gameObjects.remove(b);
						bullets.remove(b);
					}

					if (b.owner instanceof Player) {
						hitSpree = 0;
						rewardAvaliable = false;
					}
				}
				if (k.health <= 0) {
					gameObjects.remove(k);
					bunkers.remove(k);
				} else if ((k.health % 100) == 0
						&& k.lastImageUpdate > k.health) {
					int x = k.getBunkerNum() + 10;
					k.setImage(x);
					k.lastImageUpdate = k.health;
				}
			}

			if (b.hitBox.overLaps(player1.hitBox)) {
				if ((b.owner instanceof Enemy) && !godmodeOn) {
					player1.health -= b.damage;		
					hitSpree = 0;
					rewardAvaliable = false;
					bullets.remove(b);
					gameObjects.remove(b);
					if (player1.health <=0) {
						player1.livesRemaining--;
						playerDeath();

					}
					if (player1.livesRemaining <= 0) {
						System.out.println("You're Dead!");
						player1.x = MainCanvas.frame.getWidth();
						player1.y = MainCanvas.frame.getHeight();
						Helper.updateHitbox(player1);
						gameCleanup();
						state = "game_over";
					}
					
					if (minigameOn){
						nextLevel();
					}
				}
			}
			for (EnemySquad enemies : enemySquads) {
				for (Enemy e : enemies) {
					if (b.hitBox.overLaps(e.hitBox)) {
						if (b.owner instanceof Player) {
							e.health -= b.damage;
							hitSpree += 1;
							if (!godmodeOn) {
								bullets.remove(b);
								gameObjects.remove(b);
							}
							if (e.health <= 0 || godmodeOn || minigameOn) {
								dropPowerup(e, e.x, e.y);
								removeEnemy(enemies, e);								
								if (e.equals(mothership)) {
									System.out.println("Mothership Destroyed!");
									mothershipOn = false;
								}
								if (minigameOn){
									player1.score+= 500;
								} else {
								player1.score += e.scoreReward;
								}

							}

						}

						if (e.y > player1.y) {
							gameCleanup();
							state = "game_over";
							System.out.println("Game Over!");
						}
					}
				}
			}
			if (b.y < 0 - b.getImage().getHeight()
					|| b.x < 0 - b.getImage().getWidth()
					|| b.x > MainCanvas.frame.getWidth()
					|| b.y > MainCanvas.frame.getHeight()) {
				if (b.owner instanceof Player) {
					hitSpree = 0;
					rewardAvaliable = false;
				}
				bullets.remove(b);
				gameObjects.remove(b);

			}
		}
	}

	public void enemyUpdate() {
		for (EnemySquad enemies : enemySquads) {
			for (Enemy e : enemies) {
				if (e.hitBox.overLaps(player1.hitBox)) {
					if (!godmodeOn) {
						player1.livesRemaining--;
						playerDeath();
					} if (minigameOn){
						nextLevel();
					}
					/*
					for (Enemy a : enemies) {
						a.x = a.startingX;
						a.y = a.startingY;
						Helper.updateHitbox(a);
					}
					*/
				} if (e.x>getWidth()){
					System.out.println("Annddd it's gone!");
					removeEnemy(enemies, e);
					mothershipOn=false;
					mothership=null;     
				}
				for (Bunker k : bunkers) {
					if (e.hitBox.overLaps(k.hitBox)) {
						bunkers.remove(k);
						gameObjects.remove(k);
					}
				}
			}
		}
	}

	public void powerupUpdate() {
		for (Powerup p : powerups) {
			p.y += p.movementSpeed;
			Helper.updateHitbox(p);
			if (p.hitBox.overLaps(player1.hitBox)) {
				processPowerup(player1, p.type);
				gameObjects.remove(p);
				powerups.remove(p);
			}
		}
		for (PowerupType p : player1.powerups.keySet()) {
			int n = player1.powerups.get(p);
			n--;
			if (n > 0) {
				player1.powerups.put(p, n);
			} else {
				player1.powerups.remove(p);
				System.out.println("Powerup Removed");
			}
		}
	}

	public void dropPowerup(Enemy e, int x, int y) {
		int randint = MainCanvas.rand.nextInt(1000);
		PowerupType fillerType = null;
		int numeral = Helper.getIndex(Powerup.ENEMY_POWERUP_TABLE, e.enemytype);
		if (randint < Powerup.POWERUP_CHANCES[numeral][0]) {
			int type = MainCanvas.rand
					.nextInt(Powerup.POWERUP_CHANCES[numeral][Powerup.POWERUP_CHANCES[numeral].length - 1]);
			if (type < Powerup.POWERUP_CHANCES[numeral][1]) {
				fillerType = PowerupType.HEALTH;
			} else if (type < Powerup.POWERUP_CHANCES[numeral][2]) {
				fillerType = PowerupType.SPEED;
			} else if (type < Powerup.POWERUP_CHANCES[numeral][3]) {
				fillerType = PowerupType.DAMAGE;
			} else if (type < Powerup.POWERUP_CHANCES[numeral][4]) {
				fillerType = PowerupType.FIRERATE;
			}
			Powerup a = new Powerup(fillerType, x, y);
			gameObjects.add(a);
			powerups.add(a);
		}
	}

	public void processPowerup(Player player, PowerupType p) {
		switch (p) {
		case HEALTH:
			player.health += Player.PLAYER_DEFAULT_HEALTH / 2;
			if (player.health > Player.PLAYER_DEFAULT_HEALTH) {
				player.health = Player.PLAYER_DEFAULT_HEALTH;
			}
			break;
		case FIRERATE:
			player.powerups.put(PowerupType.FIRERATE,
					Powerup.DEFAULT_POWERUP_FRAMES);
			break;
		case DAMAGE:
			player.powerups.put(PowerupType.DAMAGE,
					Powerup.DEFAULT_POWERUP_FRAMES);
			break;
		case XTRALIFE:
			player.livesRemaining++;
			break;
		case SPEED:
			player.powerups.put(PowerupType.SPEED,
					Powerup.DEFAULT_POWERUP_FRAMES);
			break;
		case STEROIDS:
			processPowerup(player, Powerup.PowerupType.HEALTH);
			processPowerup(player, Powerup.PowerupType.SPEED);
			processPowerup(player, Powerup.PowerupType.FIRERATE);
			processPowerup(player, Powerup.PowerupType.DAMAGE);
			break;
		case BUNKER:
			for (Bunker k : bunkers) {
				k.health = Bunker.BUNKER_DEFAULT_HEALTH;
				k.setImage(k.originalBunker);
			}
			break;
		}
	}

	/*
	 * Called by the main method when the game wants to start
	 */

	public void startGame() {
		// Starts the game
		System.out.println("It's starting!");
		
		new Thread(new MovementClock()).start();
		int type = 0;
		if (tempname.equalsIgnoreCase("god")) {
			godmodeOn = true;
		} else if (tempname.equalsIgnoreCase("easter")
				|| tempname.equalsIgnoreCase("egg")) {
			EggOn = true;
			type = 1;
		} else if (tempname.equalsIgnoreCase("A113")) {
			PixarOn = true;
			type = 2;
		}
		// Creates a new player
		player1 = new Player(type, tempname);
		gameObjects.add(player1);
		// loads map plan here
		constructEnemyFormation(1);
		livesatlvlstart = player1.livesRemaining;
		// Constructs the bunker formations
		constructBunkerFormation(64, 600);
		constructBunkerFormation(252, 600);
		constructBunkerFormation(440, 600);
		constructBunkerFormation(628, 600);
		constructBunkerFormation(816, 600);
		state = "main";

	}

	// Constructs a bunker formation with the top left corner at (x, y)
	public void constructBunkerFormation(int x, int y) {
		int bunkerSize = Bunker.BUNKER_SIZE;
		constructBunker(4, x + bunkerSize * 3, y + bunkerSize);
		constructBunker(0, x, y + bunkerSize * 3);
		constructBunker(0, x, y + bunkerSize * 2);
		constructBunker(0, x, y + bunkerSize);
		constructBunker(1, x, y);
		constructBunker(0, x + bunkerSize, y);
		constructBunker(0, x + bunkerSize * 2, y);
		constructBunker(0, x + bunkerSize * 3, y);
		constructBunker(2, x + bunkerSize * 4, y);
		constructBunker(0, x + bunkerSize * 4, y + bunkerSize);
		constructBunker(0, x + bunkerSize * 4, y + bunkerSize * 2);
		constructBunker(0, x + bunkerSize * 4, y + bunkerSize * 3);
		constructBunker(3, x + bunkerSize, y + bunkerSize);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if ((e.getKeyCode() == KeyEvent.VK_ENTER)
				&& state.equals("justfinished")) {
			state = "ready";
			g = null;
		} else if (rewardAvaliable && e.getKeyCode() == KeyEvent.VK_R) {
			redeem();
		} else if (state.equals("main")) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				if (godmode.equals("god")) {
					godmodeOn = true;
					godmode = "";
					System.out.println("God Mode is starting!");
				} else {
					godmode = "";
				}
			} else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				// tells the update loop to allow bullet firing
				spaceOn = true;
			} else if (e.getKeyCode() == KeyEvent.VK_SPACE && rewardAvaliable) {
				redeem();
			} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				// sets the direction to Right
				rightOn = true;
				player1.currentDirection = Player.Direction.RIGHT;
			} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				// sets the direction to Left
				if (state.equals("main")) {
					leftOn = true;
					player1.currentDirection = Player.Direction.LEFT;
				}
			}

			else if (e.getKeyCode() == KeyEvent.VK_G) {
				if (godmode.equals("")) {
					godmode = "g";
				} else {
					godmode = "";
				}
			}

			else if (e.getKeyCode() == KeyEvent.VK_O) {
				if (godmode.equals("g")) {
					godmode = "go";
				} else {
					godmode = "";
				}
			} else if (e.getKeyCode() == KeyEvent.VK_D) {
				if (godmode.equals("go")) {
					godmode = "god";
					// Added God Mode
				} else {
					godmode = "";
				}

			} else {
				godmode = "";
			}
			// note shift fails
		} else if (state.equals("nametaking")) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				// fix this. in case tempname=""
				System.out.println("Enter Pressed");
				startGame();
				state = "main";

			} else if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
				if (nameLength > 0) {
					nameLength--;
					leName[nameLength] = '-';
				}
			} else if (Character.isLetterOrDigit(e.getKeyChar())) {
				if (nameLength < NAME_MAX_LENGTH) {
					nameLength++;
					leName[nameLength - 1] = e.getKeyChar();
				}
			} else {
				System.out.println("action key");
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			// sets the direction to None
			if (state.equals("main")) {
				rightOn = false;
				if (player1.currentDirection == Player.Direction.RIGHT) {
					player1.currentDirection = Player.Direction.NONE;
					// Sees whether leftarrow is still being pressed.
					if (leftOn) {
						// If it is, change direction back to left
						player1.currentDirection = Player.Direction.LEFT;
					}
				}
			}

		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			// sets the direction to None
			if (state.equals("main")) {
				leftOn = false;
				if (player1.currentDirection == Player.Direction.LEFT) {
					player1.currentDirection = Player.Direction.NONE;
					// Sees whether rightarrow is still being pressed.
					if (rightOn) {
						player1.currentDirection = Player.Direction.RIGHT;
					}
					player1.currentDirection = Player.Direction.NONE;
					// Sees whether rightarrow is still being pressed.
					if (rightOn) {
						// If it is, change direction back to Right
						player1.currentDirection = Player.Direction.RIGHT;
					}
				}
			}
		} else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			// tells the update loop to stop bullet firing
			if (state.equals("main")) {
				spaceOn = false;
			}
		} else if (e.getKeyCode() == KeyEvent.VK_P) {
			if (state.equals("main")) {
				state = "pause";
			} else if (state.equals("pause")) {
				state = "main";
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	private void constructBunker(int bunkerType, int x, int y) {
		Bunker a = new Bunker(bunkerType, x, y);
		bunkers.add(a);
		gameObjects.add(a);
	}

	public void moveEnemies() {
		for (EnemySquad enemies : enemySquads) {
			if (enemies.direction.equals(Direction.DOWN)) {
				enemies.direction = enemies.pendingDirection;
			}
			for (Enemy e : enemies) {
				if (e.x + 50 >= MainCanvas.frame.getWidth()
						&& enemies.direction.equals(EnemySquad.Direction.RIGHT)) {
					enemies.direction = EnemySquad.Direction.DOWN;
					int speed = (int) (MovementClock.MovementSpeed * (4.0 / 5.0));
					MovementClock.MovementSpeed = speed > MovementClock.MINIMUM_SPEED ? speed
							: MovementClock.MINIMUM_SPEED;
					enemies.pendingDirection = EnemySquad.Direction.LEFT;
				} else if (e.x - 50 <= 0
						&& enemies.direction.equals(EnemySquad.Direction.LEFT)) {
					enemies.direction = EnemySquad.Direction.DOWN;
					int speed = (int) (MovementClock.MovementSpeed * (4.0 / 5.0));
					MovementClock.MovementSpeed = speed > MovementClock.MINIMUM_SPEED ? speed
							: MovementClock.MINIMUM_SPEED;
					enemies.pendingDirection = EnemySquad.Direction.RIGHT;
				}
			}
			for (Enemy e : enemies) {
				if (!e.enemytype.equals(Enemy.EnemyType.MOTHERSHIP)) {
					if (enemies.direction.equals(EnemySquad.Direction.RIGHT)) {
						e.x += 40;
					} else if (enemies.direction
							.equals(EnemySquad.Direction.LEFT)) {
						e.x -= 40;
					} else if (enemies.direction
							.equals(EnemySquad.Direction.DOWN)) {
						e.y += 25;
						if (e.y - e.startingY >= 50) {
							readyForMothership = true;
						}
					}
				}
				Helper.updateHitbox(e);
			}
		}
	}

	public static char[] createEmptyName(char c, int length) {
		char[] ca = new char[length];
		for (int i = 0; i < ca.length; i++) {
			ca[i] = c;
		}
		return ca;
	}

	public void removeEnemy(EnemySquad enemies, Enemy e) {
		gameObjects.remove(e);
		enemies.remove(e);
		Explosion ex = new Explosion(e, 0);
		overlay.add(ex);
		explosions.add(ex);
	}

	public void playerDeath() {
		state = "just_died";
		player1.health = Player.PLAYER_DEFAULT_HEALTH;
		deathCounter = 188;
		Explosion ex = new Explosion(player1, 1);
		player1.x = 432;
		player1.y = 680;
		player1.powerups = new ConcurrentSkipListMap<PowerupType, Integer>();
		overlay.add(ex);
		explosions.add(ex);
		gameObjects.remove(player1);
	}
}
