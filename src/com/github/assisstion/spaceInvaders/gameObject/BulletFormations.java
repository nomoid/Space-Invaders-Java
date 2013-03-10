package com.github.assisstion.spaceInvaders.gameObject;

import java.util.HashSet;

import static com.github.assisstion.spaceInvaders.gameObject.Bullet.BulletType.*;

public final class BulletFormations{
	
	//This class should not be instantiated
	private BulletFormations(){
		
	}
	
	public static final int BULLET_FORMATION_AMOUNT = 2;
	
	public static class BulletFormationZero extends AbstractBulletFormation{

		@Override
		public HashSet<Bullet> create(int x, int y){
			HashSet<Bullet> bullets = new HashSet<Bullet>();
			Bullet b = new Bullet(BLUE, x, y, 250, 8, 150);
			bullets.add(b);
			return bullets;
		}

		@Override
		public HashSet<Bullet> update(int counter, int x, int y){
			HashSet<Bullet> newBullets = new HashSet<Bullet>();
			if(counter == 30){
				Bullet b = new Bullet(BLUE, x, y, 250, 8, 180);
				newBullets.add(b);
			}
			else if(counter == 60){
				Bullet b = new Bullet(BLUE, x, y, 250, 8, 210);
				newBullets.add(b);
			}
			else if(counter == 90){
				finish();
			}
			return newBullets;
		}
		
	}
	
	public static class BulletFormationOne extends AbstractBulletFormation{

		@Override
		public HashSet<Bullet> create(int x, int y){
			HashSet<Bullet> bullets = new HashSet<Bullet>();
			Bullet b = new Bullet(RED, x, y, 250, 8, 90);
			bullets.add(b);
			return bullets;
		}

		@Override
		public HashSet<Bullet> update(int counter, int x, int y){
			HashSet<Bullet> newBullets = new HashSet<Bullet>();
			if(counter % 30 == 0){
				if(counter == 210){
					finish();
					return newBullets;
				}
				Bullet b = new Bullet(RED, x, y, 250, 8, 90 + counter);
				newBullets.add(b);
			}
			return newBullets;
		}
		
	}
	
	public static BulletFormation getNewBulletFormation(int id){
		switch(id){
			case 0: return new BulletFormationZero();
			case 1: return new BulletFormationOne();
			default: throw new IllegalArgumentException("Illegal bullet formation id: " + id);
		}
	}
}
