package com.github.assisstion.spaceInvaders.gameObject;

import java.util.Set;

public interface BulletFormation{
	public Set<Bullet> createBulletFormation(int x, int y);
	public Set<Bullet> updateBulletFormation(int x, int y);
}
