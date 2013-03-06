package com.github.assisstion.spaceInvaders.gameObject;

import java.util.concurrent.ConcurrentSkipListSet;

public class EnemySquad	extends ConcurrentSkipListSet<Enemy> implements Comparable<EnemySquad>{

	private static final long serialVersionUID = 7041959444408021845L;

	//Current direction
	public Direction direction;
	//Next direction to go after this direction
	public Direction pendingDirection;
	
	public EnemySquad(){
		super();
	}
	
	public static enum Direction{
		RIGHT, LEFT, DOWN;
	}
	
	@Override
	public boolean add(Enemy e){
		e.squad = this;
		return super.add(e);
	}
	
	@Override
	public boolean remove(Object o){
		if(o instanceof Enemy){
			((Enemy)o).squad = null;
		}
		return super.remove(o);
	}

	@Override
	public int compareTo(EnemySquad o){
		return new Integer(hashCode()).compareTo(new Integer(o.hashCode()));
	}
}
