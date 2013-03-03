package com.github.assisstion.spaceInvaders;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import com.github.assisstion.spaceInvaders.MainCanvas;

public class Menu {
	
	
	public JPanel contentPane;
	
	public Menu() {
		MainCanvas.frame.setBackground(Color.BLACK);
		MainCanvas.frame.setPreferredSize(new Dimension(960, 760));

		contentPane = new JPanel();
		
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		MainCanvas.frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		MainCanvas.frame.pack();
		buildMenu();

	}

	
	public void buildMenu(){
		//menu to be built here
		BufferedImage buttonIcon = null;
		try {
			buttonIcon = ResourceHolder.getImageResource("resources/Spaceship.png");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error loading image!");
		}
		
		JButton button1 = new JButton(new ImageIcon(buttonIcon));
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Button pressed");
			}
		});
		button1.setBounds(960/2-24, 400, 48, 48);
		
		
		button1.setBorder(BorderFactory.createEmptyBorder());
		button1.setContentAreaFilled(false);
		contentPane.add(button1);
		
	}
	public void startGame() {
		MainCanvas.engine = new Engine();
		MainCanvas.frame.add(MainCanvas.engine);
		MainCanvas.frame.pack();
		MainCanvas.isOn = true;
		MainCanvas.rand = new Random();
		MainCanvas.engine.state = "ready";
		System.out.println("Engine starting");
	}
}
