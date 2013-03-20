package com.github.assisstion.spaceInvaders.menu;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import com.github.assisstion.spaceInvaders.ResourceManager;

@ReturnableMenu
public class UpgradesMenuBuilder implements MenuBuilder {
	private UpgradesMenuBuilder instance;
	private Menu parent;
	private LevelMenuBuilder levelScreen;
	private JButton returnButton;
	private UpgradesCanvas canvas;
	
	public UpgradesMenuBuilder(LevelMenuBuilder leScreen){
		levelScreen = leScreen;
		instance = this;
	}
	
	@Override
	public void build(Menu menu) {
		parent = menu;
		parent.enableMenuKeyListener();
		parent.requestFocus();
		parent.revalidate();
		
		canvas = new UpgradesCanvas();
		
		
		returnButton = new JButton(new ImageIcon(getImage("resources/returnButton.png")));
		returnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Button pressed");
				parent.closeMenu(instance);
				parent.addMenuBuilder(levelScreen);
			}
		});
		
		returnButton.setBounds(0,0,162,94);
		parent.add(returnButton);
		parent.add(canvas);

	}

	@Override
	public void unBuild(Menu menu) {
		parent.disableMenuKeyListener();
		parent.remove(returnButton);
		parent.remove(canvas);

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
	
	private static class UpgradesCanvas extends Canvas{
		
		private static final long serialVersionUID = 5897847762185790426L;

		@Override
		public void paint(Graphics g){
			
		}
	}
}
