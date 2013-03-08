package com.github.assisstion.spaceInvaders.gameObject;

import java.util.HashSet;

public class BulletFormations{
	public static class BulletFormationOne extends AbstractBulletFormation{

		@Override
		public HashSet<Bullet> create(int x, int y){
			HashSet<Bullet> bullets = new HashSet<Bullet>();
			return bullets;
		}

		@Override
		public HashSet<Bullet> update(int counter, int x, int y){
			HashSet<Bullet> newBullets = new HashSet<Bullet>();
			if(counter <= 10){
				
			}
			else if(counter <= 20){
				
			}
			else{
				
			}
			return newBullets;
		}
		
	}
}
