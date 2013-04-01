package com.github.assisstion.spaceInvaders.menu.canvas;

import java.awt.Color;
import java.awt.Graphics;
//import java.awt.Graphics2D;

import com.github.assisstion.MSToolkit.MSButton;
import com.github.assisstion.MSToolkit.MSTextLabel;
import com.github.assisstion.MSToolkit.event.MSActionEvent;
import com.github.assisstion.MSToolkit.event.MSActionListener;
import com.github.assisstion.MSToolkit.impl.MSAbstractCanvas;

public class UpgradesCanvas extends MSAbstractCanvas{
		
		private static final long serialVersionUID = 5897847762185790426L;

		private MSButton button;
		private MSTextLabel label;
		private int i = 0;
		
		public UpgradesCanvas(){
			RepaintingClock clock = new RepaintingClock(this);
			new Thread(clock).start();
			setBackground(Color.BLUE);
			button = new MSButton(100, 100, "Upgrade");
			button.addMSActionListener(new MSActionListener(){

				@Override
				public void action(MSActionEvent e){
					
				}

				@Override
				public void meaningfulAction(MSActionEvent e){
					System.out.println("Meaningful action happened: " + i++);
				}

				
			});
			label = new MSTextLabel(200, 100, "hi", false);
			addComponent(button);
			addComponent(label);
		}
		
		//MARKUS THE BUTTON SUMTIMES SITLL WORKS AFTER U PRESS IT ONCE, THEN MOVE YOUR MOUSE THEN PRESS AGAIN
		@Override
		public void paint(Graphics g){
			super.paint(g);
<<<<<<< HEAD
			//Graphics2D g2d = (Graphics2D) g;
			//System.out.println("aha");
			//g2d.drawString("HELLO. BUY UPGRADES HERE", 100, 100);
			
=======
			Graphics2D g2d = (Graphics2D) g;
			g2d.setColor(Color.WHITE);
			button.setGraphicsContext(g2d);
			label.setGraphicsContext(g2d);
			//g2d.drawString("HELLO. BUY UPGRADES HERE", 100, 100);	
>>>>>>> Many Many Many Updates. Main thing: added options menu and redesigned main page
		}

		@Override
		public boolean interrupted(InterruptedException e){
			e.printStackTrace();
			return false;
		}
}
	