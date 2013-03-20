package com.github.assisstion.spaceInvaders.menu;

import java.awt.Color;
import java.awt.Dimension;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.github.assisstion.spaceInvaders.Clock;
import com.github.assisstion.spaceInvaders.Engine;
import com.github.assisstion.spaceInvaders.MainCanvas;

public class Menu extends JPanel{
	
	
	public MenuBuilder currentMenu;
	private static final long serialVersionUID = 8162618142692095178L;
	private LinkedList<MenuBuilder> builders = new LinkedList<MenuBuilder>();
	MenuKeyListener keyListener;
	
	public Menu() {
		setLayout(null);
		setBackground(Color.BLACK);
		setPreferredSize(new Dimension(960, 740));
		System.out.println("Menu built");
        MainCanvas.frame.pack();
        keyListener = new MenuKeyListener(this);
		addKeyListener(keyListener);
	}
	
	public synchronized void addMenuBuilder(MenuBuilder builder){
		if(keyListener == null){
			keyListener = new MenuKeyListener(this);
			addKeyListener(keyListener);
		}
		currentMenu = builder;
		builders.add(builder);
		builder.build(this);
		requestFocus();
		revalidate();
        repaint();
	}

	public synchronized void closeMenu(MenuBuilder builder){
		builders.remove(builder);
		builder.unBuild(this);
		requestFocus();
		revalidate();
        repaint();
	}
	
	public synchronized void closeAllMenus(){
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
	
	public static void centerLabel(JLabel label, int height){
		label.setBounds( (int) (MainCanvas.FRAME_WIDTH/2 - label.getPreferredSize().getWidth()/2), height, (int) label.getPreferredSize().getWidth(), (int) label.getPreferredSize().getHeight());
	}
	
	public void startGame() {
		removeKeyListener(keyListener);
		keyListener = null;
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
