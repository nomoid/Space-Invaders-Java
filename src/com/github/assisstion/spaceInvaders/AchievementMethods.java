package com.github.assisstion.spaceInvaders;

import java.util.concurrent.ConcurrentSkipListSet;

import com.github.assisstion.spaceInvaders.gameObject.EnemySquad;


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
	private static ConcurrentSkipListSet<achievementList> achievements = new ConcurrentSkipListSet<achievementList>();
	
	public static void setEngine(Engine engine){
		instance = engine;
	}
	public static void checkName(String tempname){
		
		if (tempname.equalsIgnoreCase("god") || tempname.equalsIgnoreCase("allah") || tempname.equalsIgnoreCase("shiva") || tempname.equalsIgnoreCase("mallah")){
			instance.godmodeOn = true;
			redeemAchievement("I Gots the Power!", achievementList.IGotsThePower);
		} else if (tempname.equalsIgnoreCase("easter")
				|| tempname.equalsIgnoreCase("egg")) {
			instance.eggOn = true;
			redeemAchievement("Bunny Hop", achievementList.BunnyHop);
		} else if (tempname.equalsIgnoreCase("A113")) {
			instance.pixarOn = true;
			redeemAchievement("Save Me, Wall-E", achievementList.SaveMeWallE);
		} else if (tempname.equalsIgnoreCase("You")){
			redeemAchievement("Inception", achievementList.Inception);
		} else if (tempname.equalsIgnoreCase("Tofu")){
			redeemAchievement("Tofuception", achievementList.Tofuception);
		} else if (tempname.equalsIgnoreCase("Mick")){
			redeemAchievement("Mickception", achievementList.Mickception);
		} else if (tempname.equalsIgnoreCase("Markus")){
			redeemAchievement("Assisception", achievementList.Assisception);
		} 
		
	}
	
	public static enum achievementList{
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
		
		
		//things found in engine class
		Leeroy,
		Genocide,
		LanceArmstrong,
		TofuHull,
		
		//things found elsewhere
		Dedication,
		Modesty
		
	}
	
	public static void checkBooleans(){
		if (Abstinence){
			redeemAchievement("Abstinence", achievementList.Abstinence);
		} if (SecondAmendment){
			redeemAchievement("Second Amendment", achievementList.SecondAmendment);
		} 
	}
	
	//checks booleans for the whole game
	public static void checkWinBooleans(){
		if (thisIsSparta){
			redeemAchievement("THIS IS SPARTA!", achievementList.ThisIsSparta);
		} 
	}
	
	public static void checkFinishBooleans(){
		if (Untouchable){
			redeemAchievement("Untouchable", achievementList.Untouchable);
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
			redeemAchievement("Marksman", achievementList.Marksman);
		} if (accuracy>= 95){
			redeemAchievement("Merida", achievementList.Merida);
		} if (accuracy>= 100){
			redeemAchievement("Annie Oakley", achievementList.AnnieOakley);
		}
		
	}
	public static void checkHitstreak(int hitstreak){
		if (hitstreak >= 30){
			redeemAchievement("Streaker", achievementList.Streaker);
		} if (hitstreak >= 40){
			redeemAchievement("Streakaholic", achievementList.Streakaholic);
		} if (hitstreak >= 50){
			redeemAchievement("Streak Master", achievementList.StreakMaster);
		} if (hitstreak >= 100){
			redeemAchievement("Some Kind of Sadistic Monster", achievementList.SomeKindOfSadisticMonster);
		}
	}
	
	
	
	public static void redeemAchievement(String leName, achievementList leAchievement){
		if (!achievements.contains(leAchievement)){
			System.out.println("Achievement Unlocked: " + leName);
			achievements.add(leAchievement);
		}
	}
}
