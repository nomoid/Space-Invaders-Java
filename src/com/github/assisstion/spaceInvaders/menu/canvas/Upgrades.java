package com.github.assisstion.spaceInvaders.menu.canvas;

import java.util.HashMap;
import java.util.Map;

public class Upgrades{
	private Map<UpgradeType, Integer> upgrades;
	
	public Upgrades(){
		upgrades = new HashMap<UpgradeType, Integer>();
	}
	
	public Upgrades(Map<UpgradeType, Integer> map){
		upgrades = map;
	}
	
	public synchronized boolean hasUpgrade(UpgradeType type){
		if(upgrades.containsKey(type)){
			return true;
		}
		return false;
	}
	
	
	public synchronized int getUpgrade(UpgradeType type){
		if(!hasUpgrade(type)){
			return 0;
		}
		return upgrades.get(type);
	}
	
	public synchronized boolean upgrade(UpgradeType type){
		boolean success;
		if(upgrades.containsKey(type)){
			success = type.isMaxUpgraded(upgrades.get(type));
		}
		else{
			success = type.isMaxUpgraded(0);
		}
		if(success){
			if(upgrades.containsKey(type)){
				upgrades.put(type, upgrades.get(type) + 1);
			}
			else{
				upgrades.put(type, 1);
			}
		}
		return success;
	}
}
