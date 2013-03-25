package com.github.assisstion.spaceInvaders;

import java.util.concurrent.ConcurrentSkipListSet;

import com.github.assisstion.spaceInvaders.gameObject.Achievement;



public final class AchievementMethods {
	private static Engine instance = null;
	
	
	public static boolean achievementUnlocked;
	//Booleans for achievements
	
	public static boolean Headhunter;
	public static boolean SecondAmendment; 
	public static boolean Abstinence;
	public static boolean firstLevel;
	public static boolean enemyKilled;
	public static boolean thisIsSparta;
	public static boolean Untouchable;
	private static ConcurrentSkipListSet<Achievement> achievements = new ConcurrentSkipListSet<Achievement>();
	
	public static void setEngine(Engine engine){
		instance = engine;
	}
	public static void checkName(String tempname){
		
		if (tempname.equalsIgnoreCase("god") || tempname.equalsIgnoreCase("allah") || tempname.equalsIgnoreCase("shiva") || tempname.equalsIgnoreCase("mallah")){
			instance.godmodeOn = true;
			redeemAchievement(new Achievement("I Gots the Power!"));
		} else if (tempname.equalsIgnoreCase("easter")	|| tempname.equalsIgnoreCase("egg")) {
			instance.eggOn = true;
			redeemAchievement(new Achievement("Bunny Hop"));
		} else if (tempname.equalsIgnoreCase("A113")) {
			instance.pixarOn = true;
			redeemAchievement(new Achievement("Save Me, Wall-E"));
		} else if (tempname.equalsIgnoreCase("You")){
			redeemAchievement(new Achievement("Inception"));
		} else if (tempname.equalsIgnoreCase("Tofu")){
			redeemAchievement(new Achievement("Tofuception"));
		} else if (tempname.equalsIgnoreCase("Mick")){
			redeemAchievement(new Achievement("Mickception"));
		} else if (tempname.equalsIgnoreCase("Markus")){
			redeemAchievement(new Achievement("Assisception"));
		} 
		
	}
	
	/* public static enum achievementList{
		IGotsThePower,
		BunnyHop,
		SaveMeWallE,
		Inception,
		Tofuception,
		Mickception,
		Assisception,
		
		Abstinence,
		SecondAmendment,
		ThisIsSparta,
		Untouchable,
		Marksman,
		Merida,
		AnnieOakley,
		Streaker,
		Streakaholic,
		StreakMaster,
		SomeKindOfSadisticMonster,
		
		OhtheHugeManatee,
		OhtheUnhumanity, 
		OhtheHumanity, 
		BlindFaith, 
		FernandoTorres,
		
		
		//things found in engine class
		Leeroy,
		Genocide,
		LanceArmstrong,
		TofuHull,
		
		//things found elsewhere
		Dedication,
		Modesty,
		
	} */
	
	public static void checkBooleans(){
		if (Abstinence){
			redeemAchievement(new Achievement("Abstinence"));
		} if (SecondAmendment){
			redeemAchievement(new Achievement("Second Amendment"));
		} 
	}
	
	//checks booleans for the whole game
	public static void checkWinBooleans(){
		if (thisIsSparta){
			redeemAchievement(new Achievement("THIS IS SPARTA!"));
		} 
	}
	
	public static void checkFinishBooleans(){
		if (Untouchable){
			redeemAchievement(new Achievement("Untouchable"));
		} 
	}
	
	
	
	//clears booleans associated with a single level
	public static void clearBooleans(){
		Abstinence = true;
		firstLevel=false;
		enemyKilled = false;
		
	}
	
	public static void checkAccuracy(int accuracy){
		if (accuracy>= 100){
			redeemAchievement(new Achievement("Annie Oakley"));
		} else if (accuracy>= 95){
			redeemAchievement(new Achievement("Merida"));
		} else if (accuracy>= 90){
			redeemAchievement(new Achievement("Marksman"));
		} else if (accuracy<=10){
			redeemAchievement(new Achievement("Blind Faith"));
		} else if (accuracy<=5){
			redeemAchievement(new Achievement("Fernando Torres"));
		}
		
	}
	public static void checkHitstreak(int hitstreak){
		if (hitstreak >= 100){
			redeemAchievement(new Achievement("Some Kind of Sadistic Monster"));
		} else if (hitstreak >= 50){
			redeemAchievement(new Achievement("Streak Master"));
		} else if (hitstreak >= 40){
			redeemAchievement(new Achievement("Streakaholic"));
		} else if (hitstreak >= 30){
			redeemAchievement(new Achievement("Streaker"));
		} 
	}
	
	public static void lostHitstreak(int hitstreak){
		if (hitstreak >= 50){
			redeemAchievement(new Achievement("Oh the Huge Manatee"));
		} else if (hitstreak >= 40){
			redeemAchievement(new Achievement("Oh the Unhumanity"));
		} else if (hitstreak >= 30){
			redeemAchievement(new Achievement("Oh the Humanity"));
		} 
	}	
	
	
	
	public static void redeemAchievement(Achievement leAchievement){
		if (instance==null && !achievements.contains(leAchievement)|| !achievements.contains(leAchievement) && !instance.godmodeOn){
			System.out.println("Achievement Unlocked: " + leAchievement.name);
			achievements.add(leAchievement);
			achievementUnlocked = true; 
		}
	}
	public static void reset() {
		Headhunter = false;
		SecondAmendment = true; 
		Abstinence = true;
		firstLevel = true;
		enemyKilled = false;
		thisIsSparta = true;
		Untouchable = true;
		
	}
}
