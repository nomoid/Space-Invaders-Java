package com.github.assisstion.spaceInvaders.menu.canvas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import com.github.assisstion.MSToolkit.MSAbstractCanvas;
import com.github.assisstion.MSToolkit.MSButton;
import com.github.assisstion.MSToolkit.MSTextLabel;
import com.github.assisstion.MSToolkit.event.MSActionEvent;
import com.github.assisstion.MSToolkit.event.MSActionListener;

public class UpgradesCanvas extends MSAbstractCanvas{
		
		private static final long serialVersionUID = 5897847762185790426L;

		private MSButton button;
		private MSTextLabel label;
		
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
					System.out.println("Meaningful action happened");
				}

				
			});
			label = new MSTextLabel(200, 100, "hi");
			addComponent(button);
			addComponent(label);
		}
		
		
		@Override
		public void paint(Graphics g){
			super.paint(g);
			Graphics2D g2d = (Graphics2D) g;
			g2d.setColor(Color.WHITE);
			button.setGraphicsContext(g2d);
			label.setGraphicsContext(g2d);
			//g2d.drawString("HELLO. BUY UPGRADES HERE", 100, 100);
			
		}

		@Override
		public boolean interrupted(InterruptedException e){
			e.printStackTrace();
			return false;
		}
}
	