package com.github.assisstion.spaceInvaders.menu;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.github.assisstion.spaceInvaders.MainCanvas;

public class CutsceneUpdater implements Runnable {
	
	private CutsceneBuilder instance;
	private int delay;
	private int delayNormal;
	private int currentDelay;
	private static ScheduledExecutorService service;
	private String next;
	
	public CutsceneUpdater(CutsceneBuilder builder, int delay){
		next = "n";
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
		if(instance.justFinishedLine && !next.equals("ja") && !next.equals("jo")){
			currentDelay = (int) (instance.getTextRes()[instance.pageNumber].getDelay() * 100);
			next = "ja";
		}
		else if(next.equals("n")){
			currentDelay = delay;
			next = "o";
		}
		else if(next.equals("ja")){
			synchronized(instance) {
				instance.unBuildText();
			}
			currentDelay = 0;
			next = "jo";
		}
		else if(next.equals("o") || next.equals("jo")){
			synchronized(instance) {
				instance.update(MainCanvas.menu);
			}
			currentDelay = delay;
			next = "o";
		}
		schedule();
		
		/*
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
		*/
	}
	
	public static synchronized void setService(ScheduledExecutorService ses){
		service = ses;
	}
	
	public void schedule(){
		service.schedule(Executors.callable(this), currentDelay, TimeUnit.MILLISECONDS);
	}
}
