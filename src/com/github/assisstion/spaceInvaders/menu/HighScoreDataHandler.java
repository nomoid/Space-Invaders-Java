package com.github.assisstion.spaceInvaders.menu;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public final class HighScoreDataHandler {

	// array for scores
	public static final int SAVELENGTH = 10;
	public static Score[] scoreArray = new Score[SAVELENGTH];
	// public static int[] scoreArray =
	// {7000,6500,6200,6000,5000,3000,2000,1000,500,20};

	private static final String DATE_FORMAT = "MM/dd/yy (HH a)";

	public static void clearData() {

		for (int i = 0; i < SAVELENGTH; i++) {
			scoreArray[i] = new Score(0, null);
		}

	}

	//WORK ON POTENTIAL BONUSES.
	
	public static int[] convertTime(int seconds){
		int[] timeArray = new int[2];
				
		if (seconds % 60 > 0){
			timeArray[1] = seconds % 60;
		}
		
		timeArray[0] = (seconds-timeArray[1])/60;
		
		return timeArray;
	}
	
	
	private static String getTime() {
		return (new SimpleDateFormat(DATE_FORMAT).format(Calendar.getInstance()
				.getTime()));
	}

	public static void logScore(int score) {

		for (int i = 0; i <= SAVELENGTH; i++) {
			if (score > scoreArray[i].score) {
				System.out.println(score + " is larger than "
						+ scoreArray[i].score
						+ " which is currently ranked at number " + (i + 1));

				if (scoreArray[i].score == 0) {
					scoreArray[i] = new Score(score, getTime());
				} else {
					shiftArray(i, score);
					scoreArray[i] = new Score(score, getTime());

				}

				System.out.println(formString(scoreArray));

				break;

			} else if (score == scoreArray[i].score) {
				// score already exists. no point in adding a new entry
				break;
			}
		}

	}

	public static String formString(Score[] array) {
		String theString = "{";

		for (Score i : array) {

			if (i.score > 0) {

				if (theString.equals("{")) {
					theString = theString + i.score;
				} else {
					theString = theString + "," + i.score;
				}
				theString += " at " + i.date;
			}

		}

		theString = theString + "}";
		return theString;
	}

	private static Score[] duplicateArray() {
		Score[] newArray = new Score[SAVELENGTH];

		for (int i = 0; i < SAVELENGTH; i++) {
			newArray[i] = new Score(scoreArray[i].score, scoreArray[i].date);
		}

		return newArray;

	}

	private static void shiftArray(int index, int newScore) {
		System.out.println("Index: " + index);
		Score[] newArray = duplicateArray();

		for (int i = index; i < SAVELENGTH - 1; i++) {
			newArray[i + 1].score = scoreArray[i].score;
		}

		scoreArray = newArray;

	}

}
