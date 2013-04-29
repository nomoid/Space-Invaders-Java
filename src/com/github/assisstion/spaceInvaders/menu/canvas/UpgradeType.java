package com.github.assisstion.spaceInvaders.menu.canvas;

public enum UpgradeType{
	BULLET_SPEED, 
	/*
	 * Implemented, *10/9 speed per upgrade
	 * Max Lvl: 10
	 */
	BULLET_DAMAGE,
	/*
	 * Implemented, *5/4 damage per upgrade
	 * Max Lvl: 10
	 */
	PLAYER_SPEED,
	/*
	 * Implemented, *10/9 speed per upgrade
	 * Max Lvl: 10
	 */
	PLAYER_FIRERATE,
	/*
	 * Implemented, *9/10 firing cooldown per upgrade
	 * Max Lvl: 10
	 */
	REWARD_REQUIREMENT,
	/*
	 * Implemented, new upgrade reward requirements per upgrade
	 * Max Lvl: 5
	 */
	POWERUP_LENGTH,
	/*
	 * Implemented, longer powerups per upgrade
	 * Max Lvl: 5
	 */
	POWERUP_FREQUENCY;
	/*
	 * Implemented, more frequent powerup drops per upgrade
	 * Max Lvl: 5
	 */
	
	private static final int[] MAX_UPGRADES =
		{10, 10, 10, 10, 5, 5, 5};
	

	public boolean isMaxUpgraded(int i){
		if(ordinal() < MAX_UPGRADES.length){
			return i < MAX_UPGRADES[ordinal()];
		}
		else{
			return false;
		}
	}
	
	public String displayName(){
		String s = "";
		char[] chars = name().toCharArray();
		boolean upNext = true;
		for(char c : chars){
			if(c == '_'){
				s += " ";
				upNext = true;
			}
			else if(upNext){
				s += String.valueOf(c).toUpperCase();
				upNext = false;
			}
			else{
				s += String.valueOf(c).toLowerCase();
			}
		}
		return s;
	}
}
