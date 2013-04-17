package com.github.assisstion.spaceInvaders.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JLabel;

import com.github.assisstion.spaceInvaders.MainCanvas;
import com.github.assisstion.spaceInvaders.gameObject.LinkHolder;

@ReturnableMenu
public class TexturePackMenuBuilder implements MenuBuilder {

	private Menu parent;
	private TexturePackMenuBuilder instance;

	private JLabel topLabel;

	private LinkedList<String> nameList = new LinkedList<String>();
	private LinkedList<JButton> buttonList = new LinkedList<JButton>();
	private LinkedList<JLabel> labelList = new LinkedList<JLabel>();

	public TexturePackMenuBuilder() {
		instance = this;
	}

	@Override
	public void build(Menu menu) {
		parent = menu;
		topLabel = new JLabel("TEXTURE PACKS");
		topLabel.setForeground(Color.WHITE);
		topLabel.setBounds(10,100,(int) topLabel.getPreferredSize().getWidth(),(int) topLabel.getPreferredSize().getHeight());
		
		TexturePackDataHandler.load();
		
		nameList = TexturePackDataHandler.nameList;
		
		int y = 200;

		for (String name : nameList) {
			JButton button = new JButton(name);
			button.setBounds(30, y, 200, 80);
			
			JLabel label = new JLabel(TexturePackDataHandler.getDescription(name));
			label.setForeground(Color.white);
			label.setBounds(250,y,500,80);
			label.setFont(new Font("Copperplate Gothic Bold",Font.ITALIC,30));
			
			button.setFocusable(false);
			
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					System.out.println(((JButton) arg0.getSource()).getText()
							+ " Texture Pack Loaded");
					LinkHolder.setSprites(((JButton) arg0.getSource())
							.getText());
				}
			});
			y += 100;

			labelList.add(label);
			parent.add(label);
			buttonList.add(button);
			parent.add(button);
		}
		
		
		JButton newPackButton = new JButton("Load Texture Pack");
		newPackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
		        TexturePackDataHandler.newPack();
			}
		});
		newPackButton.setBounds(MainCanvas.FRAME_WIDTH/2 - 250,y,300,500);
		newPackButton.setFocusable(false);
	
		buttonList.add(newPackButton);
		parent.add(newPackButton);
		parent.add(topLabel);
	}

	@Override
	public void unBuild(Menu menu) {
		parent.remove(topLabel);
		for (JButton b : buttonList) {
			parent.remove(b);
		}
		for (JLabel l : labelList){
			parent.remove(l);
		}
	}

	@Override
	public void exitMenu() {
		parent.closeMenu(instance);
		parent.addMenuBuilder(new MainMenuBuilder());
	}

}
