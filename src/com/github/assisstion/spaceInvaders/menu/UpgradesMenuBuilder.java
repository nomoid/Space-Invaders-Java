package com.github.assisstion.spaceInvaders.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import com.github.assisstion.spaceInvaders.MainCanvas;
import com.github.assisstion.spaceInvaders.ResourceManager;

public class UpgradesMenuBuilder implements MenuBuilder {
	private UpgradesMenuBuilder instance;
	private Menu parent;
	private LevelMenuBuilder levelScreen;
	private JButton buyButton;
	private JButton returnButton;
	private JLabel titleLabel;
	
	public UpgradesMenuBuilder(LevelMenuBuilder leScreen){
		levelScreen = leScreen;
		instance = this;
	}
	
	@Override
	public void build(Menu menu) {
		parent = menu;
		parent.requestFocus();
		parent.revalidate();
		
		buyButton = new JButton("BUY");
		buyButton.setBounds(960/2,500,100,100);
		
		buyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Button pressed");
				parent.closeMenu(instance);
				MainCanvas.menu.add(MainCanvas.engine);
				MainCanvas.engine.state="main";
				MainCanvas.frame.pack();
			}
		});
		
		
		titleLabel = new JLabel("UPGRADES");
		titleLabel.setForeground(Color.GREEN);
		titleLabel.setFont(new Font("Copperplate", Font.BOLD, 100));
		
		Menu.centerLabel(titleLabel, 100);
		
		returnButton = new JButton(new ImageIcon(getImage("resources/returnButton.png")));
		returnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Button pressed");
				parent.closeMenu(instance);
				parent.addMenuBuilder(levelScreen);
			}
		});
		
		returnButton.setBounds(0,0,162,94);
		parent.add(titleLabel);
		parent.add(returnButton);
		parent.add(buyButton);
		parent.add(returnButton);

	}

	@Override
	public void unBuild(Menu menu) {
		parent.remove(titleLabel);
		parent.remove(returnButton);
		parent.remove(buyButton);
		parent.remove(returnButton);

	}

	private BufferedImage getImage(String filepath){
		BufferedImage leIcon = null;
		try {
			leIcon = ResourceManager.getImageResource(filepath);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Error loading image!");
		}
		return leIcon;
	}
}
