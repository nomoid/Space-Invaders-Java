package com.github.assisstion.spaceInvaders;

import java.awt.Choice;
import java.awt.event.KeyEvent;

public final class KeyInputData {

	public static final String[] STANDARDKEYARRAY = { "Space", "Left Arrow",
			"Right Arrow", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
			"K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W",
			"X", "Y", "Z" };
	public static final String[] ACTIONKEYARRAY = { "Escape", "Shift", "Space",
			"Left Arrow", "Right Arrow" };

	public static final int[] ACTIONKEYEVENTARRAY = { KeyEvent.VK_ESCAPE,
			KeyEvent.VK_SHIFT, KeyEvent.VK_SPACE, KeyEvent.VK_LEFT,
			KeyEvent.VK_RIGHT };

	public static final int[] KEYEVENTARRAY = { KeyEvent.VK_SPACE,
			KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_A, KeyEvent.VK_B,
			KeyEvent.VK_C, KeyEvent.VK_D, KeyEvent.VK_E, KeyEvent.VK_F,
			KeyEvent.VK_G, KeyEvent.VK_H, KeyEvent.VK_I, KeyEvent.VK_J,
			KeyEvent.VK_K, KeyEvent.VK_L, KeyEvent.VK_M, KeyEvent.VK_N,
			KeyEvent.VK_O, KeyEvent.VK_P, KeyEvent.VK_Q, KeyEvent.VK_R,
			KeyEvent.VK_S, KeyEvent.VK_T, KeyEvent.VK_U, KeyEvent.VK_V,
			KeyEvent.VK_W, KeyEvent.VK_X, KeyEvent.VK_Y, KeyEvent.VK_Z };

	public static int LEFT = KeyEvent.VK_LEFT;
	public static int RIGHT = KeyEvent.VK_RIGHT;
	public static int FIRE = KeyEvent.VK_SPACE;

	public static int PAUSE = KeyEvent.VK_P;
	public static int EXIT = KeyEvent.VK_ESCAPE;
	public static int REDEEM = KeyEvent.VK_R;

	public static void setChoices(String[] choiceList, Choice choice) {
		for (int i = 0; i < choiceList.length; i++) {
			choice.addItem(choiceList[i]);
		}
	}

	private static boolean findInArray(String string, String[] array) {
		int x = 0;
		for (int i = 0; i < array.length; i++) {
			if (array[i].equals(string)) {
				x++;
			}
		}

		if (x > 1) {
			return true;
		} else {
			return false;
		}
	}

	// add moar later
	public static void updateData(String left, String right, String fire,
			String pause, String exitMenu, String redeem) {

		String[] controlList = { left, right, fire, pause, exitMenu, redeem };
		boolean conflict = false;
		Outer: for (String string : controlList) {
			for (String string2 : controlList) {
				if (string.equals(string2) && findInArray(string, controlList)) {
					System.out.println("ERROR: " + string + " and " + string2);
					conflict = true;
					break Outer;
				}
			}
		}

		if (!conflict) {
			LEFT = getKeyEventCode(left, 0);
			RIGHT = getKeyEventCode(right, 0);
			FIRE = getKeyEventCode(fire, 0);

			PAUSE = getKeyEventCode(pause, 0);
			//EXIT = getKeyEventCode(exitMenu,1);
			REDEEM = getKeyEventCode(redeem, 0);
		}

		// damnit! above system is a lot simplier
		/*
		 * for (int i = 0; i < controlList.length; i++) { int y = i + 1;
		 * 
		 * 
		 * for (int x = y; x < controlList.length; x++) {
		 * 
		 * if (!(controlList[x].equals(""))) { if
		 * (controlList[i].equals(controlList[x])) {
		 * System.out.println("Key Error: " + controlList[x]); }
		 * 
		 * } else { System.out.println(controlList[x] + " " + x); } }
		 * controlList[i] = ""; }
		 */

	}

	public static int getKeyEventCode(String key, int x) {
		if (x == 0) {
			for (int i = 0; i < STANDARDKEYARRAY.length; i++) {
				if (key.equals(STANDARDKEYARRAY[i])) {
					return KEYEVENTARRAY[i];
				}
			}
		} else {
			for (int i = 0; i < ACTIONKEYARRAY.length; i++) {
				if (key.equals(ACTIONKEYARRAY[i])) {
					return ACTIONKEYEVENTARRAY[i];
				}
			}
		}
		return 0;
	}
}
