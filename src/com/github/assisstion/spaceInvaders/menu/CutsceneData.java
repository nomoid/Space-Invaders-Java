package com.github.assisstion.spaceInvaders.menu;

import java.awt.Color;
import java.awt.Font;

import com.github.assisstion.spaceInvaders.gameObject.Player;
import com.github.assisstion.spaceInvaders.gameObject.Sprite;

public final class CutsceneData{
	public static final class Cutscene1{
		
		private static final String PAGE_1 = "<Somewhere deep in uncharted space...>";
		private static final String PAGE_2 = "<MISSION LOG: 0800, June 9th, 2320>";
		private static final String PAGE_3 = "<MISSION DETAIL: CLASSIFIED>";
		private static final String PAGE_4 = "<MISSION MEMBERS: Tofu, You>";
		private static final String PAGE_5 = "You: I can't believe NASA-TD is sending us on another mission. Haven't we fulfilled our exploration quota for the past month?";
		private static final String PAGE_6 = "Tofu: I hear you. I hear the Tofuland Division of NASA is the strictest. Just our luck.";
		private static final String PAGE_7 = "You: Watch where you're driving.";
		private static final String PAGE_8 = "Tofu: You watch where you're driving.";
		private static final String PAGE_9 = "Tofu: Where'd these asteroids come from? This area's more densely packed than Sector A113 of the asteroid belt!";
		private static final String PAGE_10 = "CRASH!";
		private static final String PAGE_11 = "<System Log: Hull Integrity at 48%. ";
		private static final String PAGE_12 = "You: OH MY MICK WHAT JUST HAPPENED?!";
		private static final String PAGE_13 = "Tofu: I'M TRYING TO CONTROL THE SHIP!!";
		private static final String PAGE_14 = "CRASH!";
		private static final String PAGE_15 = "<System Log: Hull Integrity at 30%. Supplies lost.>";
		private static final String PAGE_16 = "You: DAMNIT! I'm losing control!";
		private static final String PAGE_17 = "<MISSION LOG: 1500, June 9th, 2320>";
		private static final String PAGE_18 = "You: Ugh... where are we? Passed out for a couple of hours.";
		private static final String PAGE_19 = "Tofu: Hey, _____, remember what I said about luck?";
		private static final String PAGE_20 = "You: Yeah?";
		private static final String PAGE_21 = "Tofu: Three of our subships made it.";
		private static final String PAGE_22 = "You: Ugh... where are we?";
		private static final String PAGE_23 = "You: Great! Well, we won't even need 'em. Let's drag our butts back to mission control and get us some well-deserved rest.";
		private static final String PAGE_24 = "Tofu: Well, a Tofite armada appears to be approaching.";
		private static final String PAGE_25 = "You: Crap. Can this day get any worse?";
		private static final String PAGE_26 = "Tofu: You know those subships? They're SUBSHIPS 17-19! THE WORST ONES!";
		private static final String PAGE_27 = "[Unidentified voice]: (Placeholder for alien language)";
		private static final String PAGE_28 = "You: Siri! Translate that bugger-speak into English.";
		private static final String PAGE_29 = "<Initializing Alien Language Translation System>";
		private static final String PAGE_30 = "[Unidentified voice]: Drop any weapons you are carrying on your personnel at the moment. You are trespassing on Tofite space. Surrender all subships at once or we will gladly send our Tofites fleet to exterminate you.";
		private static final String PAGE_31 = "Tofu: Who's that?";
		private static final String PAGE_32 = "You: The Tofites. Let's fight 'em off.";
		private static final String PAGE_33 = "Tofu: Oh no. Those three subships can't do anything good except shoot. No supplies at all. Our main ship is falling apart too.";
		private static final String PAGE_34 = "You: Uhh... Hm... here's the plan. I'll climb into a subship and fend them off. I'll come back for repairs.";
		private static final String PAGE_35 = "Tofu: That's too dangerous! Are you going mad?";
		private static final String PAGE_36 = "You: Don't blame me. Blame the creators of this game.";
		private static final String PAGE_39 = "[Tofite Captain] Foolish humans. Prepare to embrace death as reward for your stupidity. All your base will soon belong to us!";
		private static final String PAGE_37 = "Tofu: Gah. Fine... Just... Don't die.";
		private static final String PAGE_38 = "You: I won't. Heroes never die.";
		
		private static final Font DEFAULTFONT = new Font("Bank Gothic", Font.BOLD, 20);
		private static final Font SYSTEMFONT = new Font("BlairMdlTC TT", Font.BOLD, 30);
		private static final Font ALIENFONT = new Font("Wingdings 2", Font.PLAIN, 30);
		private static final Font[] FONTLIST =  {new Font("Helvetica", Font.PLAIN, 20), SYSTEMFONT, SYSTEMFONT, SYSTEMFONT, DEFAULTFONT,DEFAULTFONT,DEFAULTFONT,DEFAULTFONT,DEFAULTFONT,new Font("Copperplate Gothic Bold", Font.BOLD, 50),SYSTEMFONT, DEFAULTFONT,DEFAULTFONT,new Font("Copperplate Gothic Bold", Font.BOLD, 50),DEFAULTFONT, SYSTEMFONT, DEFAULTFONT,DEFAULTFONT,DEFAULTFONT,DEFAULTFONT,DEFAULTFONT,DEFAULTFONT,DEFAULTFONT,DEFAULTFONT,DEFAULTFONT, ALIENFONT, DEFAULTFONT, SYSTEMFONT, SYSTEMFONT, DEFAULTFONT, DEFAULTFONT, DEFAULTFONT, DEFAULTFONT, DEFAULTFONT, DEFAULTFONT, DEFAULTFONT, DEFAULTFONT, DEFAULTFONT};
		private static final Color DEFAULTCOLOR=null; 
		private static final Color SYSTEMCOLOR=null; 
		private static final Color OTHERCOLOR=null;
		private static final Color FRIENDCOLOR=null;
		private static final Color ALIENCOLOR=null;
		
		private static final Color[] COLORLIST = {};
		
		
		private static final char[][] PAGES = {PAGE_1.toCharArray(), PAGE_2.toCharArray(), PAGE_3.toCharArray(), PAGE_4.toCharArray(), PAGE_5.toCharArray(), PAGE_6.toCharArray(), PAGE_7.toCharArray(), PAGE_8.toCharArray(), PAGE_9.toCharArray(), PAGE_10.toCharArray(), PAGE_11.toCharArray(), PAGE_12.toCharArray(), PAGE_13.toCharArray(), PAGE_14.toCharArray(), PAGE_15.toCharArray(), PAGE_16.toCharArray(), PAGE_17.toCharArray(), PAGE_18.toCharArray(), PAGE_19.toCharArray(), PAGE_20.toCharArray(), PAGE_21.toCharArray(), PAGE_22.toCharArray(), PAGE_23.toCharArray(), PAGE_24.toCharArray(), PAGE_25.toCharArray(), PAGE_26.toCharArray(), PAGE_27.toCharArray(), PAGE_28.toCharArray(), PAGE_29.toCharArray(), PAGE_30.toCharArray(), PAGE_31.toCharArray(), PAGE_32.toCharArray(), PAGE_33.toCharArray(), PAGE_34.toCharArray(), PAGE_35.toCharArray(), PAGE_36.toCharArray(), PAGE_37.toCharArray(), PAGE_38.toCharArray()};
		
		private static final Sprite[][] SPRITES = {{new Player(0, "hi")},{}};

		public static final Cutscene SCENE = new Cutscene(PAGES, SPRITES,FONTLIST);
	}
}