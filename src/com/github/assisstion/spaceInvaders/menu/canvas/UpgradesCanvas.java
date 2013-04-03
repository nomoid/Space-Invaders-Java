package com.github.assisstion.spaceInvaders.menu.canvas;

import java.awt.Color;
import java.awt.Graphics;
<<<<<<< HEAD
import java.io.IOException;
=======
>>>>>>> Lot of changes. Added ability to change controls
//import java.awt.Graphics2D;

import com.github.assisstion.MSToolkit.MSBasicFont;
import com.github.assisstion.MSToolkit.MSButton;
import com.github.assisstion.MSToolkit.MSSelectableSprite;
import com.github.assisstion.MSToolkit.MSSingleSelectionGroup;
import com.github.assisstion.MSToolkit.MSTextLabel;
import com.github.assisstion.MSToolkit.event.MSActionEvent;
import com.github.assisstion.MSToolkit.event.MSActionListener;
import com.github.assisstion.MSToolkit.impl.MSAbstractCanvas;
import com.github.assisstion.MSToolkit.impl.MSHelper;
import com.github.assisstion.MSToolkit.style.MSMutableStyle;
import com.github.assisstion.MSToolkit.style.MSStyleManager;
import com.github.assisstion.spaceInvaders.MainCanvas;

public class UpgradesCanvas extends MSAbstractCanvas{
		
		private static final long serialVersionUID = 5897847762185790426L;

		private MSButton button;
		private MSTextLabel label;
		private MSSelectableSprite upgrade1;
		private MSSingleSelectionGroup<MSSelectableSprite> group;
		
		public UpgradesCanvas(){
			RepaintingClock clock = new RepaintingClock(this);
			new Thread(clock).start();
			setBackground(Color.BLUE);
			int width;
			MSBasicFont buttonFont = new MSBasicFont("Calibri", 20);
			width = MSHelper.getTextWidth(buttonFont, "Upgrade", null);
			button = new MSButton((MainCanvas.FRAME_WIDTH - width)/2, MainCanvas.FRAME_HEIGHT*9/10, "Upgrade");
			button.addMSActionListener(new MSActionListener(){

				@Override
				public void action(MSActionEvent e){
					
				}

				@Override
				public void meaningfulAction(MSActionEvent e){
					if(group.currentlySelected()){
						if(group.getCurrentlySelected().equals(upgrade1)){
							System.out.println("Upgrade Complete!");
						}
					}
				}

				
			});
			MSBasicFont titleFont = new MSBasicFont("Calibri", 100);
			width = MSHelper.getTextWidth(titleFont, "Upgrades", null);
			label = new MSTextLabel((MainCanvas.FRAME_WIDTH - width)/2, MainCanvas.FRAME_HEIGHT/50, "Upgrades", false);
			MSMutableStyle newStyle = MSStyleManager.getMutableStyle(style);
			newStyle.setFont(titleFont);
			label.setStyle(newStyle);
			group = new MSSingleSelectionGroup<MSSelectableSprite>();
			try{
				upgrade1 = new MSSelectableSprite(group, 100, 100, "resources/SpaceShip.png");
			}
			catch(IOException e){
				e.printStackTrace();
			}
			addComponent(upgrade1);
			addComponent(button);
			addComponent(label);
		}
		
	
		@Override
		public void paint(Graphics g){
			super.paint(g);
			//Graphics2D g2d = (Graphics2D) g;
			//System.out.println("aha");
			//g2d.drawString("HELLO. BUY UPGRADES HERE", 100, 100);
		}

		@Override
		public boolean interrupted(InterruptedException e){
			e.printStackTrace();
			return false;
		}
}
	