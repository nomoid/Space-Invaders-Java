package com.github.assisstion.MSToolkit.event;

public interface MSActionHandler extends MSEventHandler{
	boolean addMSActionListener(MSActionListener listener);
	boolean removeMSActionListener(MSActionListener listener);
}
