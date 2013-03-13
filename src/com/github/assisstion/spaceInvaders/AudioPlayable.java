package com.github.assisstion.spaceInvaders;

public interface AudioPlayable extends Comparable<AudioPlayable>{
	void setPaused(boolean paused);
	boolean isPaused();
}
