package com.github.assisstion.spaceInvaders.menu;

public class TimeTrial {
	public int time = 0;
	public String date = null;
	public String name = null;
	
	public TimeTrial(int time, String date, String name){
		this.time = time;
		this.date = date;
		this.name = name;
		if (!(time==0)){
			//System.out.println("A time of " + HighScoreDataHandler.convertTime(time)[0] + " minutes " + HighScoreDataHandler.convertTime(time)[1] + " seconds was achieved on: " + date);
		}
	}
	//moar stuff to be added later
	
}
