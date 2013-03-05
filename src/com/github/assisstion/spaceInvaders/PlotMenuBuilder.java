package com.github.assisstion.spaceInvaders;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

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
		
		
		returnButton = new JButton("placeholdertext");
		
		returnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Button pressed");
				parent.closeMenu(instance);
				parent.addMenuBuilder(new MainMenuBuilder());
			}
		});
		
		returnButton.setBounds(960/2,500,50,50);
		parent.add(returnButton);
		
	}

	@Override
	public void unBuild(Menu menu) {
		parent.remove(mainlabel);
		parent.remove(returnButton);
	}

}
