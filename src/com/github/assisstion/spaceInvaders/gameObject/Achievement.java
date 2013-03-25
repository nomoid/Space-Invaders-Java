package com.github.assisstion.spaceInvaders.gameObject;

public class Achievement implements Comparable<Achievement> {
	public String name;
	//I did this for MOAR FUNCTIONALITY
	//e.g. public Sprite image;
	//or even public AchievementClass class;
	//Work on this in Beta
	
	public Achievement(String name){
		this.name = name;
	}

	@Override
	public int compareTo(Achievement arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
}
