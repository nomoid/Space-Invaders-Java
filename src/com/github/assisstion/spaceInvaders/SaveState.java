package com.github.assisstion.spaceInvaders;

import java.util.concurrent.ConcurrentSkipListSet;

import com.github.assisstion.spaceInvaders.gameObject.Boss;
import com.github.assisstion.spaceInvaders.gameObject.Bullet;
import com.github.assisstion.spaceInvaders.gameObject.Bunker;
import com.github.assisstion.spaceInvaders.gameObject.EnemySquad;
import com.github.assisstion.spaceInvaders.gameObject.Explosion;
import com.github.assisstion.spaceInvaders.gameObject.Player;
import com.github.assisstion.spaceInvaders.gameObject.Powerup;
import com.github.assisstion.spaceInvaders.gameObject.Sprite;

public class SaveState {
	public int timePassed;
	public int levelTime;
	
	public int currentLevel;
	public Player player;
	public Boss boss;
	public int hitSpree;
	
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
	
	
	public SaveState(Engine engine){
		
		timePassed = new Integer(TimerClock.timePassed);
		levelTime = new Integer(TimerClock.levelTime);
		//damnit gotta figure this out
		//player = engine.player1.clone();
		//boss = engine.boss;
		
		player = engine.player1;
		currentLevel = new Integer(engine.currentLevel);
		gameObjects = engine.gameObjects.clone();
		enemySquads = engine.enemySquads.clone();
		bullets = engine.bullets.clone();
		bunkers = engine.bunkers.clone();
		powerups = engine.powerups.clone();
		overlay = engine.overlay.clone();
		explosions = engine.explosions.clone();
		
		hitSpree = new Integer(engine.hitSpree);
		
	}
	
	
	public static void load(Engine engine, SaveState state){
		TimerClock.timePassed = state.timePassed;
		TimerClock.levelTime = state.levelTime;
		engine.player1 = state.player;
		engine.currentLevel = state.currentLevel;
		engine.gameObjects = state.gameObjects.clone();
		engine.enemySquads = state.enemySquads.clone();
		engine.bullets = state.bullets.clone();
		engine.bunkers = state.bunkers.clone();
		engine.powerups = state.powerups.clone();
		engine.overlay = state.overlay.clone();
		engine.explosions = state.explosions.clone();
		engine.boss = state.boss;
		engine.hitSpree = state.hitSpree;	
	}
}
