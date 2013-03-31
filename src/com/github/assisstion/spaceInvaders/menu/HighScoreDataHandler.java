package com.github.assisstion.spaceInvaders.menu;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public final class HighScoreDataHandler {

	// array for scores
	public static final int SAVELENGTH = 10;
	public static Score[] scoreArray = new Score[SAVELENGTH];
	public static TimeTrial[] timeArray = new TimeTrial[SAVELENGTH];

	// public static int[] scoreArray =
	// {7000,6500,6200,6000,5000,3000,2000,1000,500,20};

	private static final String DATE_FORMAT = "MM/dd/yy (HH a)";

	public static void clearData() {

		for (int i = 0; i < SAVELENGTH; i++) {
			scoreArray[i] = new Score(0, null,null);
			timeArray[i] = new TimeTrial(0, null,null);
		}

	}

	// WORK ON POTENTIAL BONUSES.

	public static int[] convertTime(int seconds) {
		int[] timeArray = new int[2]; 

		if (seconds % 60 > 0) {
			timeArray[1] = seconds % 60;
		}
		
		timeArray[0] = (seconds - timeArray[1]) / 60;
		return timeArray;
	}

	private static String getTime() {
		return (new SimpleDateFormat(DATE_FORMAT).format(Calendar.getInstance()
				.getTime()));
	}

	public static void logScore(int score, String name) {

		for (int i = 0; i <= SAVELENGTH; i++) {
			if (score > scoreArray[i].score) {
				System.out.println(score + " is larger than "
						+ scoreArray[i].score
						+ " which is currently ranked at number " + (i + 1));

				if (scoreArray[i].score == 0) {
					scoreArray[i] = new Score(score, getTime(), name);
				} else {
					shiftArray(i, score);
					scoreArray[i] = new Score(score, getTime(), name);

				}

				System.out.println(formScoreString(scoreArray));

				break;

			} else if (score == scoreArray[i].score) {
				// score already exists. no point in adding a new entry
				break;
			}
		}

	}

	public static void logTime(int time, String name) {
		for (int i = 0; i <= SAVELENGTH; i++) {
			if (time < timeArray[i].time || timeArray[i].time == 0) {
				System.out.println(time + " seconds is faster than "
						+ timeArray[i].time
						+ " which is currently ranked at number " + (i + 1));

				if (timeArray[i].time == 0) {
					timeArray[i] = new TimeTrial(time, getTime(),name);
				} else {
					shiftTimeArray(i, time);
					timeArray[i] = new TimeTrial(time, getTime(),name);

				}

				System.out.println(formTimeString(timeArray));

				break;

			} else if (time == timeArray[i].time) {
				// score already exists. no point in adding a new entry
				break;
			} 
		}

	}

	public static void shiftTimeArray(int index, int newTime) {
		TimeTrial[] newArray = (TimeTrial[]) duplicateArray(false);
		for (int i = index; i < SAVELENGTH - 1; i++) {
			newArray[i + 1].time = timeArray[i].time;
		}
		timeArray = newArray;

	}

	public static String formScoreString(Score[] array) {
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
	
	public static String formTimeString(TimeTrial[] array) {
		String theString = "{";

		for (TimeTrial i : array) {

			if (i.time > 0) {
				if (theString.equals("{")) {
					theString = theString + i.time;
				} else {
					theString = theString + "," + i.time;
				}
				theString += " at " + i.date;
			}

		}

		theString = theString + "}";
		return theString;
	}

	private static Object[] duplicateArray(boolean score) {
		Object[] newArray = null;
		
		if (score) {
			newArray = new Score[SAVELENGTH];

			for (int i = 0; i < SAVELENGTH; i++) {
				newArray[i] = new Score(scoreArray[i].score, scoreArray[i].date,scoreArray[i].name);
			}

			
		} else {
			newArray = new TimeTrial[SAVELENGTH];

			for (int i = 0; i < SAVELENGTH; i++) {
				newArray[i] = new TimeTrial(timeArray[i].time, timeArray[i].date, scoreArray[i].name);
			}
		}

		return newArray;
	}

	private static void shiftArray(int index, int newScore) {
		System.out.println("Index: " + index);
		Score[] newArray = (Score[]) duplicateArray(true);

		for (int i = index; i < SAVELENGTH - 1; i++) {
			newArray[i + 1].score = scoreArray[i].score;
		}

		scoreArray = newArray;

	}

}
