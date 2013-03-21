package com.github.assisstion.spaceInvaders;


public final class AchievementMethods {
	private static Engine instance;
	
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

	
	
	public static void checkHitstreak(int hitstreak){
		if (hitstreak >= 30){
			redeemAchievement("Streaker");
		} else if (hitstreak >= 40){
			redeemAchievement("Streakaholic");
		} else if (hitstreak >= 50){
			redeemAchievement("Streak Master");
		} else if (hitstreak >= 100){
			redeemAchievement("Some Kind of Sadistic Monster");
		}
	}
	
	
	
	public static void redeemAchievement(String leName){
		System.out.println("Achievement Unlocked: " + leName);
	}
}
