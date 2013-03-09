package com.github.assisstion.spaceInvaders.gameObject;

import java.util.Set;

public abstract class AbstractBulletFormation implements BulletFormation, Comparable<BulletFormation>{

	private static int formationCounter = 0;
	private int id;
	private int updateCounter = 0;
	private Set<Bullet> bullets;
	private boolean isDone;
	
	public AbstractBulletFormation(){
		id = formationCounter++;
	}
	
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
	
	@Override
	public boolean isDone(){
		return isDone;
	}
	
	public abstract Set<Bullet> create(int x, int y);
	
	public abstract Set<Bullet> update(int counter, int x, int y);
	
	protected Set<Bullet> getBullets(){
		return bullets;
	}

	protected void resetCounter(){
		updateCounter = 0;
	}
	
	protected void finish(){
		isDone = true;
	}
	
	@Override
	public int compareTo(BulletFormation formation){
		return new Integer(hashCode()).compareTo(formation.hashCode());
	}
	
	@Override
	public boolean equals(Object o){
		if(o instanceof AbstractBulletFormation){
			AbstractBulletFormation abf = (AbstractBulletFormation) o;
			if(getID() == abf.getID()){
				return true;
			}
		}
		return false;
	}
	
	@Override
	public int hashCode(){
		return getID();
	}
	
	public int getID(){
		return id;
	}
}
