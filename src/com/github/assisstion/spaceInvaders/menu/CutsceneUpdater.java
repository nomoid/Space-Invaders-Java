package com.github.assisstion.spaceInvaders.menu;

import com.github.assisstion.spaceInvaders.MainCanvas;

public class CutsceneUpdater implements Runnable {
	
	private CutsceneBuilder instance;
	private int delay;
	private int delayNormal;
	
	public CutsceneUpdater(CutsceneBuilder builder, int delay){
		delayNormal = delay;
		instance=builder;
		instance.setCutsceneUpdater(this);
		this.delay = delay;
	}
	
	public void speedUp(){
		delay = delay/2;
	}
	
	public void speedNormal(){
		delay = delayNormal;
	}
	@Override
	public void run(){
		try{
			while(instance.isOn){
				if(instance.justFinishedLine){
					try {
						Thread.sleep((int) (instance.getTextRes()[instance.pageNumber].getDelay() * 100));
					}catch (Exception ex) {
						ex.printStackTrace();
					}
					
						synchronized(instance) {
							instance.unBuildText();
						}
				}
				else{
					Thread.sleep(delay);
				}
				synchronized(instance) {
					instance.update(MainCanvas.menu);
				}
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
