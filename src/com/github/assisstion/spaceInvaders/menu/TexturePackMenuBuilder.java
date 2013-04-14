package com.github.assisstion.spaceInvaders.menu;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JLabel;

import com.github.assisstion.spaceInvaders.gameObject.LinkHolder;

public class TexturePackMenuBuilder implements MenuBuilder {

	private Menu parent;
	private TexturePackMenuBuilder instance;
	
	private JLabel topLabel;
	
	private LinkedList<String> nameList = new LinkedList<String>();
	private LinkedList<JButton> buttonList = new LinkedList<JButton>();
	
	public TexturePackMenuBuilder(){
		instance = this;
	}
	
	@Override
	public void build(Menu menu) {
		parent = menu;
		
		topLabel = new JLabel("TEXTURE PACKS");
		topLabel.setForeground(Color.WHITE);
		Menu.centerLabel(topLabel, 100);
		
		nameList.add("BobPack");
		nameList.add("YoloPack");
		
		int y = 200;
		
		for (String name : nameList){
			JButton button = new JButton(name);
			button.setBounds(400,y,200, 80);
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					System.out.println(((JButton) arg0.getSource()).getText() + " Texture Pack Loaded");
					LinkHolder.setSprites(((JButton) arg0.getSource()).getText());
				}
			});
			y+=100;
			
			buttonList.add(button);
		}
		
		parent.add(topLabel);
		for (JButton b : buttonList){
			parent.add(b);
		}
	}

	@Override
	public void unBuild(Menu menu) {
		parent.remove(topLabel);
		for (JButton b : buttonList){
			parent.remove(b);
		}

	}

	@Override
	public void exitMenu() {
		parent.closeMenu(instance);
		parent.addMenuBuilder(new MainMenuBuilder());
	}

}
