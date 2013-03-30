package com.github.assisstion.MSToolkit.event;

public interface MSMouseHandler extends MSEventHandler{
	boolean addMSMouseListener(MSMouseListener listener);
	boolean removeMSMouseListener(MSMouseListener listener);
}
