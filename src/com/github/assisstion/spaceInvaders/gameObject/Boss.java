package com.github.assisstion.spaceInvaders.gameObject;

import java.util.concurrent.ConcurrentSkipListSet;

import com.github.assisstion.spaceInvaders.GameException;

public class Boss extends Sprite implements Hostile{
	
	private static String BOSS_IMAGE =
			"resources/boss.png";
	
	private ConcurrentSkipListSet<BulletFormation> formations
		= new ConcurrentSkipListSet<BulletFormation>();
	
	protected Boss() {

	}

	public Boss(int x, int y) throws GameException {
		super(BOSS_IMAGE, x, y);
	}
	
	public void addBulletFormation(BulletFormation formation){
		formation.createBulletFormation(x, y);
		formations.add(formation);
	}
	
	public void update(){
		for(BulletFormation formation : formations){
			formation.updateBulletFormation(x, y);
		}
	}
}
