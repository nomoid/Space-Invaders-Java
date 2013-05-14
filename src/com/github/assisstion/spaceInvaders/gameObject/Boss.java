package com.github.assisstion.spaceInvaders.gameObject;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArraySet;

import com.github.assisstion.spaceInvaders.GameException;
import com.github.assisstion.spaceInvaders.MainCanvas;
import com.github.assisstion.spaceInvaders.Pair;

@Hostile
public class Boss extends Sprite implements IrregularHitbox{
	
	private double tempX;
	private double tempY;
	public double movementSpeed;
	public int health;
	public CopyOnWriteArraySet<Box> hitBox;
	protected ConcurrentSkipListSet<BulletFormation> formations
		= new ConcurrentSkipListSet<BulletFormation>();
	public int formationCount = 0;
	/*
	 * Note: boss actually appears to move left
	 * when the Direction is 90 (right) as it moves
	 * right relative to the rotation of the sprite
	 */
	public double movementDirection = 90;
	
	public static int BOSS_IMAGE_WIDTH = 32;
	public static int BOSS_IMAGE_HEIGHT = 32;
	private static String BOSS_IMAGE =
			LinkHolder.player;
	public static final int[][] 
			BOSS_HITBOX_FORMATION = {
			{0, 0, BOSS_IMAGE_WIDTH, BOSS_IMAGE_HEIGHT},
			
		};
	
	
	
	
	protected Boss() {

	}

	public Boss(int x, int y) throws GameException {
		super(BOSS_IMAGE, x, y);
		health = 1000;
		rotation = 180;
		movementSpeed = 2;
		tempX = x;
		tempY = y;
		createHitbox();
	}
	
	public Set<Bullet> addBulletFormation(BulletFormation formation){
		formationCount++;
		Set<Bullet> bullets = formation.createBulletFormation(x, y);
		for(Bullet b : bullets){
			b.owner = this;
		}
		formations.add(formation);
		return bullets;
	}
	
	public Pair<Boolean, Set<Bullet>> update(){
		HashSet<Bullet> bullets = new HashSet<Bullet>();
		boolean done = false;
		for(BulletFormation formation : formations){
			Set<Bullet> newBullets = formation.updateBulletFormation(x, y);
			for(Bullet b : newBullets){
				b.owner = this;
			}
			bullets.addAll(newBullets);
			if(formation.isDone()){
				formations.remove(formation);
				done = true;
				formationCount--;
			}
		}
		return new Pair<Boolean, Set<Bullet>>(new Boolean(done), bullets);
	}
	
	private void createHitbox(){
		HashSet<Box> tempSet = new HashSet<Box>();
		for(int i = 0; i < BOSS_HITBOX_FORMATION.length; i++){
			Box b = new Box(BOSS_HITBOX_FORMATION[i][0], BOSS_HITBOX_FORMATION[i][0], 
					BOSS_HITBOX_FORMATION[i][0], BOSS_HITBOX_FORMATION[i][0], true);
			tempSet.add(b);
		}
		hitBox = new CopyOnWriteArraySet<Box>(tempSet);
	}

	@Override
	public void updateHitbox(){
		int i = 0;
		for(Box b : hitBox){
			b.setPos(x + BOSS_HITBOX_FORMATION[i][0], y + BOSS_HITBOX_FORMATION[i][1], 
					 BOSS_HITBOX_FORMATION[i][2], BOSS_HITBOX_FORMATION[i][3], true);
			i++;
		}
	}
	
	//BOSS HITBOX IS CURRENTLY BROKEN
	
	
	public void updateLocation(){
		if(x < 0 || x > MainCanvas.FRAME_WIDTH - BOSS_IMAGE_WIDTH){
			movementDirection = -movementDirection;
		}
		tempX += movementSpeed * Math.sin(Math.toRadians(rotation + movementDirection));
		tempY -= movementSpeed * Math.cos(Math.toRadians(rotation + movementDirection));
		x = (int) tempX;
		y = (int) tempY;
	}
}
