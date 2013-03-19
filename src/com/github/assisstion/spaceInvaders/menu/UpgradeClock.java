package com.github.assisstion.spaceInvaders.menu;

public class UpgradeClock implements Runnable{
	
	private UpgradesMenuBuilder parent; 
	public UpgradeClock(UpgradesMenuBuilder upgradesmb){
		parent = upgradesmb;
	}
	
	@Override
	public void run(){
		try{
			while(true){
				if(parent.isOn){
					parent.repaint();
				}
				Thread.sleep(16);
			}
			
		}
		catch(InterruptedException e){
			//TODO placeholder
			e.printStackTrace();
		}
		catch(Exception e){
			//TODO placeholder
			e.printStackTrace();
		}
	}
	
}
