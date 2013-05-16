package com.github.assisstion.spaceInvaders.menu.canvas;

import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
//import java.awt.Graphics2D;

import com.github.assisstion.MSToolkit.MSBasicFont;
import com.github.assisstion.MSToolkit.MSButton;
import com.github.assisstion.MSToolkit.MSSingleSelectionGroup;
import com.github.assisstion.MSToolkit.MSTextLabel;
import com.github.assisstion.MSToolkit.event.MSActionEvent;
import com.github.assisstion.MSToolkit.event.MSActionListener;
import com.github.assisstion.MSToolkit.impl.MSAbstractCanvas;
import com.github.assisstion.MSToolkit.impl.MSHelper;
import com.github.assisstion.MSToolkit.style.MSMutableStyle;
import com.github.assisstion.MSToolkit.style.MSStyleManager;
import com.github.assisstion.MSToolkit.wrapper.MSFadingWrapper;
import com.github.assisstion.spaceInvaders.MainCanvas;
import com.github.assisstion.spaceInvaders.Scheduler;
import com.github.assisstion.spaceInvaders.menu.UpgradesMenuBuilder;

public class UpgradesCanvas extends MSAbstractCanvas implements Scheduler{
	
	private static final long serialVersionUID = 5897847762185790426L;
	private MSButton buttonUpgrade;
	private MSButton buttonBack;
	private MSTextLabel label;
	private MSTextLabel display;
	private MSTextLabel detailDisplay;
	private MSSingleSelectionGroup<UpgradeIcon> group;
	private ScheduledExecutorService service;
	private UpgradesMenuBuilder menuParent;
	private ArrayList<MSTextLabel> upgradeInfo;
	
	public UpgradesCanvas(UpgradesMenuBuilder parent){
		try{
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
						UpgradeIcon ui = group.getCurrentlySelected() ;
						MainCanvas.upgrades.upgrade(ui.getType());
						System.out.println("Name: " + ui.getType().name() + "; Level: " + MainCanvas.upgrades.getUpgrade(ui.getType()));
						MSBasicFont font = new MSBasicFont("Calibri", 40);
						int width = MSHelper.getTextWidth(font, "Upgrade Complete!", null);
						MSTextLabel label = new MSTextLabel((MainCanvas.FRAME_WIDTH - width)/2, MainCanvas.FRAME_HEIGHT*4/5, "Upgrade Complete!", false);
						MSMutableStyle ms = MSStyleManager.getMutableStyle(style);
						ms.setFont(font);
						label.setStyle(ms);
						MSFadingWrapper<MSTextLabel> wrapper = new MSFadingWrapper<MSTextLabel>(label);
						label.hide();
						addComponent(label);
						final MSTextLabel textLabel = label;
						wrapper.displayForTime(1500, new Runnable(){
							@Override
							public void run(){
								removeComponent(textLabel);
							}
						});
						detailDisplay.setText(MainCanvas.upgrades.getDetail(group.getCurrentlySelected().getType()));
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
			group = new MSSingleSelectionGroup<UpgradeIcon>();
			addUpgrades(group);
			for(UpgradeIcon ui : group.getSelectables()){
				addComponent(ui);
			}
			for(MSTextLabel label : upgradeInfo){
				addComponent(label);
			}
			display = new MSTextLabel((MainCanvas.FRAME_WIDTH*5)/8, MainCanvas.FRAME_HEIGHT/4, "", false);
			MSBasicFont displayFont = new MSBasicFont("Calibri", 30);
			newStyle = MSStyleManager.getMutableStyle(style);
			newStyle.setFont(displayFont);
			display.setStyle(newStyle);
			display.setText(group.getCurrentlySelected().getName());
			detailDisplay = new MSTextLabel((MainCanvas.FRAME_WIDTH*5)/8, (MainCanvas.FRAME_HEIGHT*3)/8, "", false);
			MSBasicFont detailDisplayFont = new MSBasicFont("Calibri", 20);
			newStyle = MSStyleManager.getMutableStyle(style);
			newStyle.setFont(detailDisplayFont);
			detailDisplay.setStyle(newStyle);
			detailDisplay.setText(MainCanvas.upgrades.getDetail(group.getCurrentlySelected().getType()));
			
			addComponent(buttonBack);
			addComponent(buttonUpgrade);
			addComponent(label);
			addComponent(display);
			addComponent(detailDisplay);
		}
		catch(Exception e){
			e.printStackTrace();
		}
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
	
	private void addUpgrades(MSSingleSelectionGroup<UpgradeIcon> group){
		try{
			MSBasicFont font = new MSBasicFont(MSStyleManager.getDefaultStyleSystem().getLabel().getFont().getName(), 15);
			upgradeInfo = new ArrayList<MSTextLabel>();
			
			UpgradeIcon upgrade0 = new UpgradeIcon(UpgradeType.BULLET_SPEED, group, 100, 200);
			group.add(upgrade0);
			MSTextLabel text0 = new MSTextLabel(100, 250, UpgradeType.BULLET_SPEED.shortDisplayName(), MSStyleManager.getDefaultStyleSystem().getLabel().getForeground(), font);
			upgradeInfo.add(text0);
			
			
			UpgradeIcon upgrade1 = new UpgradeIcon(UpgradeType.BULLET_DAMAGE, group, 200, 200);
			group.add(upgrade1);
			MSTextLabel text1 = new MSTextLabel(200, 250, UpgradeType.BULLET_DAMAGE.shortDisplayName(), MSStyleManager.getDefaultStyleSystem().getLabel().getForeground(), font);
			upgradeInfo.add(text1);
			
			UpgradeIcon upgrade2 = new UpgradeIcon(UpgradeType.PLAYER_SPEED, group, 300, 200);
			group.add(upgrade2);
			MSTextLabel text2 = new MSTextLabel(300, 250, UpgradeType.PLAYER_SPEED.shortDisplayName(), MSStyleManager.getDefaultStyleSystem().getLabel().getForeground(), font);
			upgradeInfo.add(text2);
			
			UpgradeIcon upgrade3 = new UpgradeIcon(UpgradeType.PLAYER_FIRERATE, group, 400, 200);
			group.add(upgrade3);
			MSTextLabel text3 = new MSTextLabel(400, 250, UpgradeType.PLAYER_FIRERATE.shortDisplayName(), MSStyleManager.getDefaultStyleSystem().getLabel().getForeground(), font);
			upgradeInfo.add(text3);
			
			UpgradeIcon upgrade4 = new UpgradeIcon(UpgradeType.REWARD_REQUIREMENT, group, 150, 300);
			group.add(upgrade4);
			MSTextLabel text4 = new MSTextLabel(150, 350, UpgradeType.REWARD_REQUIREMENT.shortDisplayName(), MSStyleManager.getDefaultStyleSystem().getLabel().getForeground(), font);
			upgradeInfo.add(text4);
			
			UpgradeIcon upgrade5 = new UpgradeIcon(UpgradeType.POWERUP_LENGTH, group, 250, 300);
			group.add(upgrade5);
			MSTextLabel text5 = new MSTextLabel(250, 350, UpgradeType.POWERUP_LENGTH.shortDisplayName(), MSStyleManager.getDefaultStyleSystem().getLabel().getForeground(), font);
			upgradeInfo.add(text5);
			
			UpgradeIcon upgrade6 = new UpgradeIcon(UpgradeType.POWERUP_FREQUENCY, group, 350, 300);
			group.add(upgrade6);
			MSTextLabel text6 = new MSTextLabel(350, 350, UpgradeType.POWERUP_FREQUENCY.shortDisplayName(), MSStyleManager.getDefaultStyleSystem().getLabel().getForeground(), font);
			upgradeInfo.add(text6);
			
			group.alwaysSelected(upgrade0);
			for(UpgradeIcon upgrade : group.getSelectables()){
				upgrade.addMSActionListener(new MSActionListener(){

					@Override
					public void action(MSActionEvent e){
						
					}

					@Override
					public void meaningfulAction(MSActionEvent e){
						if(e.getMessage().equals("Selected")){
							display.setText(e.getSource().getName());
							if(e.getSource() instanceof UpgradeIcon){
								UpgradeIcon ui = (UpgradeIcon) e.getSource();
								detailDisplay.setText(MainCanvas.upgrades.getDetail(ui.getType()));
							}
							else{
								detailDisplay.setText("");
							}
						}
					}
				});
			}
		}
		catch(IOException e){
			e.printStackTrace();
		}
		
	}
}
	