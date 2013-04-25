package com.github.assisstion.spaceInvaders.menu.canvas;

import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;
import java.util.HashSet;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
//import java.awt.Graphics2D;

import com.github.assisstion.MSToolkit.MSBasicFont;
import com.github.assisstion.MSToolkit.MSButton;
import com.github.assisstion.MSToolkit.MSSelectable;
import com.github.assisstion.MSToolkit.MSSingleSelectionGroup;
import com.github.assisstion.MSToolkit.MSTextLabel;
import com.github.assisstion.MSToolkit.event.MSActionEvent;
import com.github.assisstion.MSToolkit.event.MSActionListener;
import com.github.assisstion.MSToolkit.impl.MSAbstractCanvas;
import com.github.assisstion.MSToolkit.impl.MSHelper;
import com.github.assisstion.MSToolkit.style.MSMutableStyle;
import com.github.assisstion.MSToolkit.style.MSStyleManager;
import com.github.assisstion.spaceInvaders.MainCanvas;
import com.github.assisstion.spaceInvaders.Scheduler;
import com.github.assisstion.spaceInvaders.menu.UpgradesMenuBuilder;

public class UpgradesCanvas extends MSAbstractCanvas implements Scheduler{
	
	private static final long serialVersionUID = 5897847762185790426L;
	private MSButton buttonUpgrade;
	private MSButton buttonBack;
	private MSTextLabel label;
	private HashSet<UpgradeIcon> upgrades = new HashSet<UpgradeIcon>();
	private MSSingleSelectionGroup group;
	private ScheduledExecutorService service;
	private UpgradesMenuBuilder menuParent;
	
	public UpgradesCanvas(UpgradesMenuBuilder parent){
		menuParent = parent;
		setBackground(Color.BLUE);
		int width;
		MSBasicFont buttonFont = new MSBasicFont("Calibri", 20);
		width = MSHelper.getTextWidth(buttonFont, "Upgrade", null);
		buttonUpgrade = new MSButton((MainCanvas.FRAME_WIDTH - width)/2, MainCanvas.FRAME_HEIGHT*9/10, "Upgrade");
		buttonUpgrade.addMSActionListener(new MSActionListener(){

			@Override
			public void action(MSActionEvent e){
				
			}

			@Override
			public void meaningfulAction(MSActionEvent e){
				if(group.currentlySelected()){
					MSSelectable current = group.getCurrentlySelected() ;
					if(current instanceof UpgradeIcon){
						UpgradeIcon ui = (UpgradeIcon) current;
						MainCanvas.upgrades.upgrade(ui.getType());
						System.out.println("Name: " + ui.getType().name() + "; Level: " + MainCanvas.upgrades.getUpgrade(ui.getType()));
					}
				}
			}

			
		});
		buttonBack = new MSButton((MainCanvas.FRAME_WIDTH - width)/2, MainCanvas.FRAME_HEIGHT*19/20, "Back");
		buttonBack.addMSActionListener(new MSActionListener(){
			
			@Override
			public void action(MSActionEvent e){
				
			}
			
			@Override
			public void meaningfulAction(MSActionEvent e){
				menuParent.exitMenu();
			}
		});
		MSBasicFont titleFont = new MSBasicFont("Calibri", 100);
		width = MSHelper.getTextWidth(titleFont, "Upgrades", null);
		label = new MSTextLabel((MainCanvas.FRAME_WIDTH - width)/2, MainCanvas.FRAME_HEIGHT/50, "Upgrades", false);
		MSMutableStyle newStyle = MSStyleManager.getMutableStyle(style);
		newStyle.setFont(titleFont);
		label.setStyle(newStyle);
		group = new MSSingleSelectionGroup();
		addUpgrades(group);
		for(MSSelectable c : group.getSelectables()){
			if(c instanceof UpgradeIcon){
				UpgradeIcon ui = (UpgradeIcon) c;
				addComponent(ui);
			}
			else{
				throw new ClassCastException("Illegal Class in selection group");
			}
		}
		addComponent(buttonBack);
		addComponent(buttonUpgrade);
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


	@Override
	public ScheduledExecutorService getService(){
		return service;
	}


	@Override
	public void setService(ScheduledExecutorService ses){
		service = ses;
	}


	@Override
	public void startService(){
		RepaintingClock clock = new RepaintingClock(this);
		service.scheduleAtFixedRate(clock, 16, 16, TimeUnit.MILLISECONDS);
	}
	
	private void addUpgrades(MSSingleSelectionGroup group){
		try{
			UpgradeIcon upgrade0 = new UpgradeIcon(UpgradeType.BULLET_SPEED, group, 100, 200);
			upgrades.add(upgrade0);
			UpgradeIcon upgrade1 = new UpgradeIcon(UpgradeType.BULLET_DAMAGE, group, 200, 200);
			upgrades.add(upgrade1);
			UpgradeIcon upgrade2 = new UpgradeIcon(UpgradeType.PLAYER_SPEED, group, 300, 200);
			upgrades.add(upgrade2);
			UpgradeIcon upgrade3 = new UpgradeIcon(UpgradeType.PLAYER_FIRERATE, group, 400, 200);
			upgrades.add(upgrade3);
			group.alwaysSelected(upgrade0);
		}
		catch(IOException e){
			e.printStackTrace();
		}
		
	}
}
	