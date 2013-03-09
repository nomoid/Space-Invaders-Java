package com.github.assisstion.spaceInvaders.gameObject;

import java.util.HashSet;

import static com.github.assisstion.spaceInvaders.gameObject.Bullet.BulletType.*;

public class BulletFormations{
	public static class BulletFormationZero extends AbstractBulletFormation{

		@Override
		public HashSet<Bullet> create(int x, int y){
			HashSet<Bullet> bullets = new HashSet<Bullet>();
			Bullet b = new Bullet(BLUE, x, y);
			b.rotation = 150;
			bullets.add(b);
			return bullets;
		}

		@Override
		public HashSet<Bullet> update(int counter, int x, int y){
			HashSet<Bullet> newBullets = new HashSet<Bullet>();
			if(counter == 60){
				Bullet b = new Bullet(BLUE, x, y);
				b.rotation = 180;
				newBullets.add(b);
			}
			else if(counter == 120){
				Bullet b = new Bullet(BLUE, x, y);
				b.rotation = 210;
				newBullets.add(new Bullet(BLUE, x, y));
			}
			else if(counter == 180){
				finish();
			}
			return newBullets;
		}
		
	}
	
	public static BulletFormation getNewBulletFormation(int id){
		return BossData.BOSS_FORMATIONS[id];
	}
}
