package com.github.assisstion.spaceInvaders.menu;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

import com.github.assisstion.spaceInvaders.KeyInputData;
import com.github.assisstion.spaceInvaders.ResourceManager;

@ReturnableMenu
public class OptionsMenuBuilder implements MenuBuilder {
	private Menu parent;
	private OptionsMenuBuilder instance;
	private MenuBuilder parentMenu;
	
	private JLabel topLabel;
	private JButton muteButton;
	private JButton cutsceneButton;
	//IDEA: ADD ABILITY TO CHANGE CONTROLS
	
	private Choice moveLeft;
	private Choice moveRight;
	private Choice fireKey;
	private Choice pauseKey;
	private Choice exitMenuKey;
	private Choice redeemKey;
	
	private JButton saveButton;
	
		
	
	public OptionsMenuBuilder(MenuBuilder parentMenu){
		this.parentMenu = parentMenu;
		instance = this;
		
		
	}
	
	@Override
	public void build(Menu menu) {
		parent = menu;
		
		topLabel = new JLabel("Options");
		topLabel.setForeground(Color.WHITE);
		topLabel.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 70));
		Menu.centerLabel(topLabel, 5);
		
		
		cutsceneButton = new JButton("Disable Cutscenes");
		cutsceneButton.setBounds(480-100,120,200,120);
		cutsceneButton.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 10));
		cutsceneButton.setFocusable(false);
		
		cutsceneButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (Cutscene.enabled){
					Cutscene.enabled = false;
					cutsceneButton.setText("Enable Cutscenes");
				} else if (!Cutscene.enabled){
					Cutscene.enabled = true;
					cutsceneButton.setText("Disable Cutscenes");
				}
			}
		});
		
		
		muteButton = new JButton("Mute");
		muteButton.setBounds(480-100,300,200,120);
		muteButton.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 40));
		muteButton.setFocusable(false);
		
		muteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!ResourceManager.getMuted()){
					System.out.println("Game Muted");
					ResourceManager.setMuted(true);
					muteButton.setText("Unmute");			
				}
				else{
					System.out.println("Game Unmuted");
					ResourceManager.setMuted(false);
					muteButton.setText("Mute");
				}
			}
		});
		
		
		moveLeft = new Choice();	
		KeyInputData.setChoices(KeyInputData.STANDARDKEYARRAY, moveLeft);
		moveLeft.setBounds(600,300,100,20);
		moveLeft.select("Left Arrow");
		moveLeft.setFocusable(false);
		
		moveRight = new Choice();	
		KeyInputData.setChoices(KeyInputData.STANDARDKEYARRAY, moveRight);
		moveRight.setBounds(600,350,100,20);
		moveRight.select("Right Arrow");
		moveRight.setFocusable(false);
	
		fireKey = new Choice();	
		KeyInputData.setChoices(KeyInputData.STANDARDKEYARRAY, fireKey);
		fireKey.setBounds(600,400,100,20);
		fireKey.select("Space");
		fireKey.setFocusable(false);
		
		pauseKey = new Choice();	
		KeyInputData.setChoices(KeyInputData.STANDARDKEYARRAY, pauseKey);
		pauseKey.setBounds(600,450,100,20);
		pauseKey.select("P");
		pauseKey.setFocusable(false);
		
		redeemKey = new Choice();	
		KeyInputData.setChoices(KeyInputData.STANDARDKEYARRAY, redeemKey);
		redeemKey.setBounds(600,500,100,20);
		redeemKey.select("R");
		redeemKey.setFocusable(false);
		
		exitMenuKey = new Choice();	
		KeyInputData.setChoices(KeyInputData.ACTIONKEYARRAY, exitMenuKey);
		exitMenuKey.setBounds(600,550,100,20);
		exitMenuKey.select("Escape");
		exitMenuKey.setFocusable(false);
		
		
		
		saveButton = new JButton("Save Changes");
		saveButton.setBounds(600,430,100,20);
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				KeyInputData.updateData((String) moveLeft.getSelectedItem(), (String) moveRight.getSelectedItem(),(String) fireKey.getSelectedItem(), (String) pauseKey.getSelectedItem(), (String) exitMenuKey.getSelectedItem(), (String) redeemKey.getSelectedItem());
			}
		});
		saveButton.setFocusable(false);
		
		parent.add(saveButton);
		parent.add(fireKey);
		parent.add(moveRight);
		parent.add(moveLeft);
		parent.add(pauseKey);
		parent.add(exitMenuKey);
		parent.add(redeemKey);
		
		parent.add(cutsceneButton);
		parent.add(muteButton);
		parent.add(topLabel);
	}

	@Override
	public void unBuild(Menu menu) {
		parent = menu;
		
		parent.remove(saveButton);
		parent.remove(fireKey);
		parent.remove(moveRight);
		parent.remove(moveLeft);
		parent.remove(pauseKey);
		parent.remove(exitMenuKey);
		parent.remove(redeemKey);
		
		parent.remove(cutsceneButton);
		parent.remove(muteButton);
		parent.remove(topLabel);
	}

	@Override
	public void exitMenu() {
		parent.closeMenu(instance);
		parent.addMenuBuilder(parentMenu);
	}
}
