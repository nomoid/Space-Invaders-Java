package com.github.assisstion.spaceInvaders;

import javax.sound.sampled.Clip;

public class ClipWrapper implements AudioPlayable{

	private Clip clip;
	private boolean paused;
	private boolean done;
	
	public ClipWrapper(Clip clip){
		this.clip = clip;
	}
	
	protected ClipWrapper(){
		
	}
	
	@Override
	public synchronized void setPaused(boolean paused){
		if(paused && !this.paused){
			if(clip.isRunning()){
				clip.stop();
			}
			else{
				done = true;
			}
		}
		else if(!paused && this.paused){
			if(!done){
				clip.start();
			}
		}
		if(done){
			ResourceManager.removeAudioPlayer(this);
		}
		this.paused = paused;
	}

	@Override
	public boolean isPaused(){
		return paused;
	}
	
	public Clip getClip(){
		return clip;
	}
	
	@Override
	public boolean equals(Object o){
		if(o instanceof ClipWrapper){
			ClipWrapper cw = (ClipWrapper) o;
			if(getClip().equals(cw.getClip())){
				return true;
			}
		}
		if(o instanceof Clip){
			Clip c = (Clip) o;
			if(getClip().equals(c)){
				return true;
			}
		}
		return false;
	}
	
	@Override
	public int hashCode(){
		return getClip().hashCode();
	}

	@Override
	public int compareTo(AudioPlayable ap){
		return new Integer(hashCode()).compareTo(ap.hashCode());
	}

}
