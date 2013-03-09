package com.github.assisstion.spaceInvaders.gameObject;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArraySet;

import com.github.assisstion.spaceInvaders.GameException;
import com.github.assisstion.spaceInvaders.Pair;

import static com.github.assisstion.spaceInvaders.gameObject.BossData.*;

public class Boss extends Sprite implements Hostile, IrregularHitbox{
	
	public int health;
	public CopyOnWriteArraySet<Box> hitBox;
	
	private static String BOSS_IMAGE =
			"resources/boss.png";
	
	protected ConcurrentSkipListSet<BulletFormation> formations
		= new ConcurrentSkipListSet<BulletFormation>();
	
	public boolean readyForFormation = true;
	
	protected Boss() {

	}

	public Boss(int x, int y) throws GameException {
		super(BOSS_IMAGE, x, y);
		createHitbox();
	}
	
	public Set<Bullet> addBulletFormation(BulletFormation formation){
		Set<Bullet> bullets = formation.createBulletFormation(x, y);
		formations.add(formation);
		return bullets;
	}
	
	public Pair<Boolean, Set<Bullet>> update(){
		HashSet<Bullet> bullets = new HashSet<Bullet>();
		boolean done = false;
		for(BulletFormation formation : formations){
			bullets.addAll(formation.updateBulletFormation(x, y));
			if(formation.isDone()){
				formations.remove(formation);
				done = true;
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
			b.setPos(x + BOSS_HITBOX_FORMATION[i][0], y + BOSS_HITBOX_FORMATION[i][0], 
					 BOSS_HITBOX_FORMATION[i][0], BOSS_HITBOX_FORMATION[i][0], true);
			i++;
		}
	}
}
