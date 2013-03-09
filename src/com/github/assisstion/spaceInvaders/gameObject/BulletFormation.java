package com.github.assisstion.spaceInvaders.gameObject;

import java.util.Set;

public interface BulletFormation{
	Set<Bullet> createBulletFormation(int x, int y);
	Set<Bullet> updateBulletFormation(int x, int y);
	boolean isDone();
}
