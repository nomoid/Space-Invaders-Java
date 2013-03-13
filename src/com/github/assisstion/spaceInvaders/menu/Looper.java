package com.github.assisstion.spaceInvaders.menu;

import com.github.assisstion.spaceInvaders.AudioPlayable;

public interface Looper extends AudioPlayable{
	void ready();
	boolean isOn();
}
