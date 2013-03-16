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

public class HelpMenuBuilder implements MenuBuilder {
	private HelpMenuBuilder instance;
	private Menu parent;
	private JLabel helplabel;
	private JButton returnButton;

	public HelpMenuBuilder(){
		instance = this;
	}
	
	@Override
	public void build(Menu menu) {
		
		parent=menu;
		
		helplabel = new JLabel("Help");
		helplabel.setFont(new Font("Copperplate", Font.BOLD, 80));
		helplabel.setForeground(Color.WHITE);
		Menu.centerLabel(helplabel, 0);
		

		
		returnButton = new JButton(new ImageIcon(getReturnImage()));
		returnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Button pressed");
				parent.closeMenu(instance);
				parent.addMenuBuilder(new MainMenuBuilder());
			}
		});
		
		returnButton.setBounds(0,740-94,162,94);
		
		parent.add(returnButton);
		parent.add(helplabel);
	}

	@Override
	public void unBuild(Menu menu) {
		parent.remove(helplabel);
		parent.remove(returnButton);
	}

	private BufferedImage getReturnImage(){
		BufferedImage returnIcon = null;
		try {
			returnIcon = ResourceManager.getImageResource("resources/returnButton.png");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Error loading image!");
		}
		return returnIcon;
	}
}
