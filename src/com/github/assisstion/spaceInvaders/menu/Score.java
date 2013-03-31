package com.github.assisstion.spaceInvaders.menu;


public class Score {
	public int score = 0;
	public String date = null;
	public String name = null;
	
	public Score(int score, String date, String name){
		this.score = score;
		this.date = date;
		this.name = name;
		if (!(score==0)){
		System.out.println("A score of " + score + " was achieved on: " + date);
		}
	}
	//moar stuff to be added later
	
}
