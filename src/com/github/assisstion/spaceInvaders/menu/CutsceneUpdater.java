package com.github.assisstion.spaceInvaders.menu;

import com.github.assisstion.spaceInvaders.MainCanvas;

public class CutsceneUpdater implements Runnable {
	
	private CutsceneBuilder instance;
	private int delay;
	
	public CutsceneUpdater(CutsceneBuilder builder, int delay){
		instance=builder;
		this.delay = delay;
	}
	@Override
	public void run(){
		try{
			while(instance.isOn){
				if(instance.justFinishedLine){
					Thread.sleep((int) (instance.pageDelays[instance.pageNumber] * 100));
					instance.unBuildText();
				}
				else{
					Thread.sleep(delay);
				}
				instance.update(MainCanvas.menu);
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
