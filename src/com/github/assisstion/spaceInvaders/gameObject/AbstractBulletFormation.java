package com.github.assisstion.spaceInvaders.gameObject;

import java.util.Set;

public abstract class AbstractBulletFormation implements BulletFormation, Comparable<BulletFormation>{

	private int updateCounter = 0;
	private Set<Bullet> bullets;
	
	@Override
	public Set<Bullet> createBulletFormation(int x, int y){
		bullets = create(x, y);
		return bullets;
	}

	@Override
	public Set<Bullet> updateBulletFormation(int x, int y){
		Set<Bullet> newBullets = update(updateCounter++, x, y);
		bullets.addAll(newBullets);
		return newBullets;
	}
	
	public abstract Set<Bullet> create(int x, int y);
	
	public abstract Set<Bullet> update(int counter, int x, int y);
	
	protected Set<Bullet> getBullets(){
		return bullets;
	}

	protected void resetCounter(){
		updateCounter = 0;
	}
	
	public int compareTo(BulletFormation formation){
		return new Integer(hashCode()).compareTo(formation.hashCode());
	}
}
