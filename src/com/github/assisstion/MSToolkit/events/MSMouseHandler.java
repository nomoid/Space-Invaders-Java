package com.github.assisstion.MSToolkit.events;

public interface MSMouseHandler extends MSEventHandler{
	boolean addMSMouseListener(MSMouseListener listener);
	boolean removeMSMouseListener(MSMouseListener listener);
}
