package com.github.assisstion.spaceInvaders.menu;

import java.awt.Color;
import java.awt.Font;

import com.github.assisstion.spaceInvaders.gameObject.Player;
import com.github.assisstion.spaceInvaders.gameObject.Sprite;

public final class CutsceneData{
	
	//This class should not be instantiated
	private CutsceneData(){
		
	}
	
	private static final Color DEFAULTCOLOR=Color.GREEN; 
	private static final Color SYSTEMCOLOR=Color.WHITE; 
	private static final Color FRIENDCOLOR=Color.YELLOW;
	private static final Color ALIENCOLOR=Color.RED;
	
	
	private static final Font DEFAULTFONT = new Font("Bank Gothic", Font.BOLD, 30);
	private static final Font SYSTEMFONT = new Font("BlairMdlTC TT", Font.BOLD, 30);
	private static final Font ALIENFONT = new Font("OCR A Std", Font.PLAIN, 30);
	
	public static final class Cutscene1{
		
		private static final String PAGE_1 = "<Somewhere deep in uncharted space...>";
		private static final String PAGE_2 = "<MISSION LOG: 0800, June 9th, 2320>";
		private static final String PAGE_3 = "<MISSION BRIEFING: CLASSIFIED>";
		private static final String PAGE_4 = "<MISSION MEMBERS: Tofu, You>";
		private static final String PAGE_5 = "You: I can't believe NASA-TD is sending us on another&mission. Haven't we fulfilled our exploration quota&for the past month?";
		private static final String PAGE_6 = "Tofu: I hear you. Apparently the Tofuland Division&of NASA is the strictest. Just our luck.";
		private static final String PAGE_7 = "You: Watch where you're driving.";
		private static final String PAGE_8 = "Tofu: Oh shut up. Where the heck are we?";
		private static final String PAGE_9 = "Tofu: Where'd these asteroids come from? This area's&more densely packed than Sector A113 of the&asteroid belt!";
		private static final String PAGE_10 = "CRASH!";
		private static final String PAGE_11 = "<System Log: Hull Integrity at 69%.>";
		private static final String PAGE_12 = "You: OH MY MICK WHAT JUST HAPPENED?!";
		private static final String PAGE_13 = "Tofu: I'M TRYING TO CONTROL THE SHIP!!";
		private static final String PAGE_14 = "CRASH!";
		private static final String PAGE_15 = "<System Log: Hull Integrity at 38%. Supplies lost.>";
		private static final String PAGE_16 = "You: DAMNIT! I'm losing control!";
		private static final String PAGE_17 = "<MISSION LOG: 1500, June 9th, 2320>";
		private static final String PAGE_18 = "You: Ugh... where are we? Passed out&for a couple of hours.";
		private static final String PAGE_19 = "Tofu: Same. Hey, _____, remember what I said about luck?";
		private static final String PAGE_20 = "You: Yeah?";
		private static final String PAGE_21 = "Tofu: Three of our subships made it.";
		private static final String PAGE_22 = "You: Great! Well, we won't even need 'em. Let's drag our&butts back to mission control and get us&some well-deserved rest.";
		private static final String PAGE_23 = "You: Why so silent?";
		private static final String PAGE_24 = "Tofu: Well, for starters, a Tofite armada&appears to be approaching.";
		private static final String PAGE_25 = "You: Crap. Can this day get any worse?";
		private static final String PAGE_26 = "Tofu: You know those subships? They're SUBSHIPS 17-19!&THE WORST ONES!";
		private static final String PAGE_27 = "[Unidentified voice]: (Placeholder for alien language)";
		private static final String PAGE_28 = "You: Siri! Translate that bugger-speak into English.";
		private static final String PAGE_29 = "<Initializing Alien Language Translation System>";
		private static final String PAGE_30 = "[Unidentified voice]: Drop any weapons you&are carrying on your personnel at&the moment. You are trespassing on&Tofite space. Surrender all ships&at once or we will gladly&send our Tofites fleet to exterminate you.";
		private static final String PAGE_31 = "Tofu: Who's that?";
		private static final String PAGE_32 = "You: The Tofites. Let's fight 'em off.";
		private static final String PAGE_33 = "Tofu: Oh no. These three subships can't do anything&good except shoot. No supplies at all. Our main&ship is falling apart too.";
		private static final String PAGE_34 = "You: Uhh... Hm... here's the plan. I'll&climb into a subship and fend them off.&I'll come back for repairs.";
		private static final String PAGE_35 = "Tofu: That's too dangerous! Are you going mad?";
		private static final String PAGE_36 = "You: Don't blame me. Blame the creators of this game.";
		private static final String PAGE_37 = "[Tofite Captain] Foolish humans. Prepare to embrace&death as reward for your stupidity. All your&base will soon belong to us!";
		private static final String PAGE_38 = "Tofu: Gah. Fine... Just... Don't die.";
		private static final String PAGE_39 = "You: I won't. Heroes never die.";
		private static final String PAGE_40 = "Tofu: But you're not a hero.";
		private static final String PAGE_41 = "You: Screw you.";
		
		private static final Font DEFAULTFONT = new Font("Bank Gothic", Font.BOLD, 30);
		private static final Font SYSTEMFONT = new Font("BlairMdlTC TT", Font.BOLD, 30);
		private static final Font ALIENFONT = new Font("Wingdings 2", Font.PLAIN, 30);
		private static final Font[] FONTLIST =  {new Font("Helvetica", Font.PLAIN, 45), SYSTEMFONT, SYSTEMFONT, SYSTEMFONT, DEFAULTFONT,DEFAULTFONT,DEFAULTFONT,DEFAULTFONT,DEFAULTFONT,new Font("Copperplate Gothic Bold", Font.BOLD, 50),SYSTEMFONT, DEFAULTFONT,DEFAULTFONT,new Font("Copperplate Gothic Bold", Font.BOLD, 50),SYSTEMFONT, DEFAULTFONT, SYSTEMFONT,DEFAULTFONT,DEFAULTFONT,DEFAULTFONT,DEFAULTFONT,DEFAULTFONT,DEFAULTFONT,DEFAULTFONT,DEFAULTFONT, ALIENFONT, DEFAULTFONT, SYSTEMFONT, SYSTEMFONT, DEFAULTFONT, DEFAULTFONT, DEFAULTFONT, DEFAULTFONT, DEFAULTFONT, DEFAULTFONT, DEFAULTFONT, DEFAULTFONT, DEFAULTFONT,DEFAULTFONT,DEFAULTFONT, DEFAULTFONT};
		private static final Color OTHERCOLOR=Color.GRAY;
		
		
		private static final Color[] COLORLIST = {SYSTEMCOLOR, SYSTEMCOLOR, SYSTEMCOLOR, SYSTEMCOLOR, DEFAULTCOLOR, FRIENDCOLOR, DEFAULTCOLOR, FRIENDCOLOR, FRIENDCOLOR, OTHERCOLOR, SYSTEMCOLOR, DEFAULTCOLOR, FRIENDCOLOR, OTHERCOLOR, SYSTEMCOLOR, DEFAULTCOLOR,SYSTEMCOLOR, DEFAULTCOLOR, FRIENDCOLOR, DEFAULTCOLOR, FRIENDCOLOR, DEFAULTCOLOR, DEFAULTCOLOR, FRIENDCOLOR, DEFAULTCOLOR, FRIENDCOLOR, ALIENCOLOR, DEFAULTCOLOR, SYSTEMCOLOR, ALIENCOLOR, FRIENDCOLOR, DEFAULTCOLOR, FRIENDCOLOR, DEFAULTCOLOR, FRIENDCOLOR, DEFAULTCOLOR, ALIENCOLOR, FRIENDCOLOR, DEFAULTCOLOR, FRIENDCOLOR, DEFAULTCOLOR};
		
		
		private static final char[][] PAGES = {PAGE_1.toCharArray(), PAGE_2.toCharArray(), PAGE_3.toCharArray(), PAGE_4.toCharArray(), PAGE_5.toCharArray(), PAGE_6.toCharArray(), PAGE_7.toCharArray(), PAGE_8.toCharArray(), PAGE_9.toCharArray(), PAGE_10.toCharArray(), PAGE_11.toCharArray(), PAGE_12.toCharArray(), PAGE_13.toCharArray(), PAGE_14.toCharArray(), PAGE_15.toCharArray(), PAGE_16.toCharArray(), PAGE_17.toCharArray(), PAGE_18.toCharArray(), PAGE_19.toCharArray(), PAGE_20.toCharArray(), PAGE_21.toCharArray(), PAGE_22.toCharArray(), PAGE_23.toCharArray(), PAGE_24.toCharArray(), PAGE_25.toCharArray(), PAGE_26.toCharArray(), PAGE_27.toCharArray(), PAGE_28.toCharArray(), PAGE_29.toCharArray(), PAGE_30.toCharArray(), PAGE_31.toCharArray(), PAGE_32.toCharArray(), PAGE_33.toCharArray(), PAGE_34.toCharArray(), PAGE_35.toCharArray(), PAGE_36.toCharArray(), PAGE_37.toCharArray(), PAGE_38.toCharArray(),PAGE_39.toCharArray(),PAGE_40.toCharArray(),PAGE_41.toCharArray()};
		
		private static final Sprite[][] SPRITES = {{new Player(0, "hi")},{}};
		private static final double[] DELAYLIST = {10,10,10,10,20,10,10,10,10,10,10,10,10,10,15,20,15,15,20,15,20,20,20,15,20,15,10,15,15,15,10,25,25,15,15,20,20,15,20,20,20,20};		
		public static Cutscene SCENE = null;
		
		public static CutsceneTextResource[] textRes;
		static {
			textRes = new CutsceneTextResource[40];
			for (int i = 0; i < textRes.length; i ++) {
				textRes[i] = new CutsceneTextResource(PAGES[i], FONTLIST[i], COLORLIST[i], DELAYLIST[i]);
			}
			SCENE = new Cutscene(SPRITES, textRes);
		}
		
	}
	
	
	public static final class Cutscene2{
		
		private static final String PAGE_1 = "<MISSION LOG: 0800, June 9th, 2320>";
		private static final String PAGE_2 = "<MISSION LOCATION: Deep Within Tofuite Space. At&Temporary Base>";
		private static final String PAGE_3 = "You: Tofu! Tofu! I'm back!";
		private static final String PAGE_4 = "Tofu: That's great...";
		private static final String PAGE_5 = "You: You sound strange... is something wrong?";
		private static final String PAGE_6 = "Tofu: I think I might know why the Tofites retreated.";
		private static final String PAGE_7 = "You: Really? That's great! Why do you think - Wait...&How do you know they've retreated?";
		private static final String PAGE_8 = "Tofu: Simple. You, as the lolmany others who invaded&our territory have been, are a trespasser. And as&we have stated before, trespassers must.&be.&executed.";
		private static final String PAGE_9 = "You: Tofu... Hold on. You're not Tofu...";
		private static final String PAGE_10 = "Possessed Tofu: Do not refer to me by that ridiculous name anymore. I am the Supreme Lord of the Tofites, a manifestation of the most developed psychic and athletic prowess on this side of the galaxy, the master and controller of all in this vicinity! I! AM! SHERIDAN!";
		private static final String PAGE_11 = "You: What the... Sheridan? That's a dumber name&than Tofu!";
		private static final String PAGE_12 = "Sheridan: Wha- No it's not! I'll have you know, 'Sheridan' is a name accepted and revered in intergalactic proportions! From the Michael Sheridan of Earth to Blitzkreig Sheridan of the Blitzkreig Quadrant, the name Sheridan commands respect throughout all corners of the universe! Which is a very large feat because the universe, as it exists now, does not actually have corners.";
		private static final String PAGE_13 = "You: *yawns* That's cool. But... can you EAT a&Sheridan?";
		private static final String PAGE_14 = "Sheridan: IRRELEVANT!";
		private static final String PAGE_15 = "You: Well, you can eat Tofu, which gives you&nutrition, which then helps sustain life. Without the sustainment of life, a thriving ecosystem, such as the one you currently live in, cannot hope to exist, rendering your argument null and void.";
		private static final String PAGE_16 = "Sheridan: Wha... no... who... THAT'S IT! PREPARE TO BE EXECUTED!";
		private static final String PAGE_17 = "<Siri: Sir, enemy weapons systems warming. Cation advised>";
		private static final String PAGE_18 = "You: Suit yourself, Your Inedibleness.";
	
		private static final Font[] FONTLIST =  {SYSTEMFONT, SYSTEMFONT, DEFAULTFONT, DEFAULTFONT, DEFAULTFONT, DEFAULTFONT, DEFAULTFONT, DEFAULTFONT, DEFAULTFONT, ALIENFONT, DEFAULTFONT, ALIENFONT, DEFAULTFONT, ALIENFONT, DEFAULTFONT, ALIENFONT, SYSTEMFONT, DEFAULTFONT};
		

		private static final Color[] COLORLIST = {SYSTEMCOLOR, SYSTEMCOLOR, DEFAULTCOLOR, FRIENDCOLOR, DEFAULTCOLOR, FRIENDCOLOR, DEFAULTCOLOR, FRIENDCOLOR, DEFAULTCOLOR, ALIENCOLOR, DEFAULTCOLOR, ALIENCOLOR, DEFAULTCOLOR, ALIENCOLOR, DEFAULTCOLOR, ALIENCOLOR, SYSTEMCOLOR, DEFAULTCOLOR};
		
		
		private static final char[][] PAGES = {PAGE_1.toCharArray(), PAGE_2.toCharArray(), PAGE_3.toCharArray(), PAGE_4.toCharArray(), PAGE_5.toCharArray(), PAGE_6.toCharArray(), PAGE_7.toCharArray(), PAGE_8.toCharArray(), PAGE_9.toCharArray(), PAGE_10.toCharArray(), PAGE_11.toCharArray(), PAGE_12.toCharArray(), PAGE_13.toCharArray(), PAGE_14.toCharArray(), PAGE_15.toCharArray(), PAGE_16.toCharArray(), PAGE_17.toCharArray(), PAGE_18.toCharArray()};
		private static final Sprite[][] SPRITES = {{new Player(0, "hi")},{}};
		
		private static final double[] DELAYLIST = {10,10,10,10,10,10,15,20,10,30,15,50,15,10,25,10,15,10};	
		public static Cutscene SCENE = null;
		
		public static CutsceneTextResource[] textRes;
		static {
			textRes = new CutsceneTextResource[17];
			for (int i = 0; i < textRes.length; i ++) {
				textRes[i] = new CutsceneTextResource(PAGES[i], FONTLIST[i], COLORLIST[i], DELAYLIST[i]);
			}
			SCENE = new Cutscene(SPRITES, textRes);
		}
	}
}