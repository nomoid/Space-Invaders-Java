package com.github.assisstion.spaceInvaders;

public class CutsceneUpdater implements Runnable {
	
	private TextCutsceneBuilder instance;
	
	public CutsceneUpdater(TextCutsceneBuilder hi){
		instance=hi;
	}
	@Override
	public void run(){
		try{
			while(instance.isOn){
				Thread.sleep(2000);
				instance.updateText(MainCanvas.menu);
			}
		}
		catch(InterruptedException e){
			new Thread(new MovementClock()).start();
		}
	}
	
}
