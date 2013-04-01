package com.github.assisstion.spaceInvaders.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

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
		
		parent.add(cutsceneButton);
		parent.add(muteButton);
		parent.add(topLabel);
	}

	@Override
	public void unBuild(Menu menu) {
		parent = menu;
		
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
