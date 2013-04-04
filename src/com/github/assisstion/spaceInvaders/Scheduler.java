package com.github.assisstion.spaceInvaders;

import java.util.concurrent.ScheduledExecutorService;

public interface Scheduler{
	ScheduledExecutorService getService();
	void setService(ScheduledExecutorService ses);
	void startService();
}
