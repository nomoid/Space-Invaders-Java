package com.github.assisstion.spaceInvaders.menu;


public class Score {
	public int score = 0;
	public String date = null;
	
	public Score(int score, String date){
		this.score = score;
		this.date = date;
		if (!(score==0)){
		System.out.println("A score of " + score + " was achieved on: " + date);
		}
	}
	//moar stuff to be added later
	//MISS YOU GUYS (Mostly Markus)
	
}
