package com.github.assisstion.spaceInvaders;

public class CutsceneUpdater implements Runnable {
	
	private TextCutsceneBuilder instance;
	private int delay;
	
	public CutsceneUpdater(TextCutsceneBuilder builder, int delay){
		instance=builder;
		this.delay = delay;
	}
	@Override
	public void run(){
		try{
			while(instance.isOn){
				Thread.sleep(delay);
				instance.updateText(MainCanvas.menu);
			}
		}
		catch(InterruptedException e){
			new Thread(new MovementClock()).start();
		}
	}
	
}
