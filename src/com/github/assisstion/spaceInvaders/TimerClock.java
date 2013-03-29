package com.github.assisstion.spaceInvaders;

public class TimerClock implements Runnable {

	public static int timePassed = 0; 
	public static int levelTime = 0;
	
	public TimerClock() {
		timePassed = 0;
	}

	@Override
	public void run() {
		try {
			while (MainCanvas.isOn || !(MainCanvas.engine == null)) {
				if (MainCanvas.engine.state.equalsIgnoreCase("main")) {
					Thread.sleep(1000);
					timePassed++;
					levelTime++;	
				}

			}
		} catch (InterruptedException e) {
			// TODO placeholder
			e.printStackTrace();
		} catch (Exception e) {
			// TODO placeholder
			e.printStackTrace();
		}

	}

	
}
