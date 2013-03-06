package com.github.assisstion.spaceInvaders.menu;

import java.awt.Color;
import java.awt.Dimension;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.JPanel;


import com.github.assisstion.spaceInvaders.Clock;
import com.github.assisstion.spaceInvaders.Engine;
import com.github.assisstion.spaceInvaders.MainCanvas;

public class Menu extends JPanel{
	
	
	private static final long serialVersionUID = 8162618142692095178L;
	private LinkedList<MenuBuilder> builders = new LinkedList<MenuBuilder>();
	
	public Menu() {
		setLayout(null);
		setBackground(Color.BLACK);
		setPreferredSize(new Dimension(960, 740));
		System.out.println("Menu built");
	}
	
	public void addMenuBuilder(MenuBuilder builder){
		builders.add(builder);
		builder.build(this);
		revalidate();
        repaint();
	}

	public void closeMenu(MenuBuilder builder){
		builders.remove(builder);
		builder.unBuild(this);
		revalidate();
        repaint();
	}
	
	public void closeAllMenus(){
		MenuBuilder builder = builders.pollLast();
		while(builder != null){
			builders.remove(builder);
			builder.unBuild(this);
			builder = builders.pollLast();
		}
		revalidate();
        repaint();
	}
	

	/*
	 * Starts the game engine
	 */
	
	
	public void startGame() {
		MainCanvas.engine = new Engine();
		add(MainCanvas.engine);
		MainCanvas.frame.pack();
		MainCanvas.isOn = true;
		MainCanvas.rand = new Random();
		MainCanvas.engine.state = "ready";
		System.out.println("Engine starting");
		new Thread(new Clock()).start();
	}
}
