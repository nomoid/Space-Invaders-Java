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
		
		timePassed = TimerClock.timePassed;
		levelTime = TimerClock.levelTime;
		player = engine.player1;
		currentLevel = engine.currentLevel;
		gameObjects = engine.gameObjects;
		enemySquads = engine.enemySquads;
		bullets = engine.bullets;
		bunkers = engine.bunkers;
		powerups = engine.powerups;
		overlay = engine.overlay;
		explosions = engine.explosions;
		boss = engine.boss;
		hitSpree = engine.hitSpree;
		
	}
	
	
	public static void load(Engine engine, SaveState state){
		
		TimerClock.timePassed = state.timePassed;
		TimerClock.levelTime = state.levelTime;
		engine.player1 = state.player;
		engine.currentLevel = state.currentLevel;
		engine.gameObjects = state.gameObjects;
		engine.enemySquads = state.enemySquads;
		engine.bullets = state.bullets;
		engine.bunkers = state.bunkers;
		engine.powerups = state.powerups;
		engine.overlay = state.overlay;
		engine.explosions = state.explosions;
		engine.boss = state.boss;
		engine.hitSpree = state.hitSpree;	
	}
}
