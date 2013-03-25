package com.github.assisstion.spaceInvaders.menu.canvas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;


public class UpgradesCanvas extends AbstractCanvas{
		
		private static final long serialVersionUID = 5897847762185790426L;

		public UpgradesCanvas(){
			RepaintingClock clock = new RepaintingClock(this);
			new Thread(clock).start();
			setBackground(Color.BLUE);
		}
		
		
		@Override
		public void paint(Graphics g){
			super.paint(g);
			Graphics2D g2d = (Graphics2D) g;
			g2d.setColor(Color.WHITE);
			g2d.drawString("HELLO. BUY UPGRADES HERE", 100, 100);
		}

		@Override
		public boolean interrupted(InterruptedException e){
			e.printStackTrace();
			return false;
		}
}
	