package com.github.assisstion.spaceInvaders.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import com.github.assisstion.spaceInvaders.ResourceManager;

public class PlotMenuBuilder implements MenuBuilder {
	
	private PlotMenuBuilder instance;
	private Menu parent;
	private JLabel mainlabel;
	private JButton returnButton;

	public PlotMenuBuilder(){
		instance = this;
	}
	
	@Override
	public void build(Menu menu) {
		System.out.println("asdfasdfasdf");
		parent=menu;
		mainlabel = new JLabel(new ImageIcon("resources/Spaceship.png"));
		mainlabel.setBounds(960/2,100,100,100);
		parent.add(mainlabel);

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
		
	}

	@Override
	public void unBuild(Menu menu) {
		parent.remove(mainlabel);
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
