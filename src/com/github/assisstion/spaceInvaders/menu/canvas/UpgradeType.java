package com.github.assisstion.spaceInvaders.menu.canvas;

public enum UpgradeType{
	BULLET_SPEED, //Implemented, *10/9 speed per upgrade
	BULLET_DAMAGE, //Implemented, *5/4 damage per upgrade
	PLAYER_SPEED,
	PLAYER_FIRERATE, //Implemented, *0.9 firerate cooldown per upgrade
	POWERUP_LENGTH,
	POWERUP_FREQUENCY;
	
	private static final int[] MAX_UPGRADES =
		{};
	

	public boolean isMaxUpgraded(int i){
		if(ordinal() < MAX_UPGRADES.length){
			return i < MAX_UPGRADES[ordinal()];
		}
		else{
			return false;
		}
	}
}
