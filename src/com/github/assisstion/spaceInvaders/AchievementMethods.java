package com.github.assisstion.spaceInvaders;


public final class AchievementMethods {
	private static Engine instance;
	
	
	
	//Booleans for achievements
	
	public static boolean Headhunter = false;
	public static boolean SecondAmendment = true; 
	public static boolean Abstinence = true;
	public static boolean firstLevel = true;
	public static boolean enemyKilled = false;
	public static boolean thisIsSparta = true;
	public static boolean Untouchable = true;
	
	public static void setEngine(Engine engine){
		instance = engine;
	}
	public static void checkName(String tempname){
		
		if (tempname.equalsIgnoreCase("god") || tempname.equalsIgnoreCase("allah") || tempname.equalsIgnoreCase("shiva") || tempname.equalsIgnoreCase("mallah")){
			instance.godmodeOn = true;
			redeemAchievement("I Gots the Power!");
		} else if (tempname.equalsIgnoreCase("easter")
				|| tempname.equalsIgnoreCase("egg")) {
			instance.eggOn = true;
			redeemAchievement("Bunny Hop");
		} else if (tempname.equalsIgnoreCase("A113")) {
			instance.pixarOn = true;
			redeemAchievement("Save Me, Wall-E");
		} else if (tempname.equalsIgnoreCase("You")){
			redeemAchievement("Inception");
		} else if (tempname.equalsIgnoreCase("Tofu")){
			redeemAchievement("Tofuception");
		} else if (tempname.equalsIgnoreCase("Mick")){
			redeemAchievement("Mickception");
		} else if (tempname.equalsIgnoreCase("Markus")){
			redeemAchievement("Assisception");
		} 
		
	}
	
	public static void checkBooleans(){
		if (Abstinence){
			redeemAchievement("Abstinence");
		} if (SecondAmendment){
			redeemAchievement("Second Amendment");
		} 
	}
	
	//checks booleans for the whole game
	public static void checkWinBooleans(){
		if (thisIsSparta){
			redeemAchievement("THIS IS SPARTA!");
		} if (SecondAmendment){
			redeemAchievement("Second Amendment");
		} 
	}
	
	public static void checkFinishBooleans(){
		if (Untouchable){
			redeemAchievement("Untouchable");
		} 
	}
	
	
	
	//clears booleans associated with a single level
	public static void clearBooleans(){
		Abstinence = true;
		firstLevel=false;
		enemyKilled = false;
		
	}
	
	public static void checkAccuracy(int accuracy){
		if (accuracy>= 90){
			redeemAchievement("Marksman");
		} if (accuracy>= 95){
			redeemAchievement("Merida");
		} if (accuracy>= 100){
			redeemAchievement("Annie Oakley");
		}
		
	}
	public static void checkHitstreak(int hitstreak){
		if (hitstreak >= 30){
			redeemAchievement("Streaker");
		} if (hitstreak >= 40){
			redeemAchievement("Streakaholic");
		} if (hitstreak >= 50){
			redeemAchievement("Streak Master");
		} if (hitstreak >= 100){
			redeemAchievement("Some Kind of Sadistic Monster");
		}
	}
	
	
	
	public static void redeemAchievement(String leName){
		System.out.println("Achievement Unlocked: " + leName);
	}
}
