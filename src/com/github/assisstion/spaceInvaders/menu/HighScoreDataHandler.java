package com.github.assisstion.spaceInvaders.menu;

public final class HighScoreDataHandler {

	//array for scores
	public static final int SAVELENGTH = 10;
	public static int[] scoreArray = new int[SAVELENGTH];
	//public static int[] scoreArray = {7000,6500,6200,6000,5000,3000,2000,1000,500,20};
	
	
	
	public static void clearData(){
		for (int score : scoreArray){
			score = 0;
		}		
		
	}
	
	public static void logScore(int score){
		
		for (int i=0; i<=SAVELENGTH; i++){
			if (score>scoreArray[i]){
				System.out.println(score + " is larger than " + scoreArray[i] + " which is currently ranked at number " + (i+1));
				
				if (scoreArray[i] == 0){
					scoreArray[i] = score;
				} else {
					shiftArray(i, score);
				}
				
				System.out.println(formString(scoreArray));
				
				break;
				
			} else if (score == scoreArray[i]){
				//score already exists. no point in adding a new entry
				break;
			}
		}
		
		
	}
	
	public static String formString(int[] array){
		System.out.println("Length of Array: " + array.length);
		String theString = "{";
		
		for (int i: array){
			if (theString.equals("{")){
				theString = theString + i;
			} else {
				theString = theString + "," + i;
			}
		}
		
		
		theString = theString + "}";
		return theString;
	}
	
	private static int[] duplicateArray(){
		int[] newArray = new int[SAVELENGTH];
		
		for (int i=0; i<SAVELENGTH; i++){
			newArray[i] = scoreArray[i];
		}
		
		return newArray;
		
	}
	
	private static void shiftArray(int index, int newScore){
		System.out.println("Index: " + index);
		int[] newArray = duplicateArray();
		
		

		for (int i=index; i<SAVELENGTH-1; i++){
			newArray[i+1] = scoreArray[i];	
		}

		newArray[index] = newScore;
		
		scoreArray = newArray;
		
		

		
	}
	
	
	
}
