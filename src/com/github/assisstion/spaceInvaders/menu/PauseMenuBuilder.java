package com.github.assisstion.spaceInvaders.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import com.github.assisstion.spaceInvaders.MainCanvas;

@ReturnableMenu
public class PauseMenuBuilder implements MenuBuilder{

	private PauseMenuBuilder instance;
	private Menu parent;
	
	private JButton pauseButton;
	private JButton quitButton;
	private JButton optionsButton;
	
	private JLabel titleLabel;

	public PauseMenuBuilder(){
		instance = this;
	}
	
	@Override
	public void build(Menu menu){
		parent = menu;
		parent.requestFocus();
		parent.revalidate();
		
		optionsButton = new JButton("OPTIONS");
		optionsButton.setBounds(960/2-150,300,300,100);
		
		optionsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				parent.closeMenu(instance);
				parent.addMenuBuilder(new OptionsMenuBuilder(instance));
			}
		});
		
		
		
		
		pauseButton = new JButton("RESUME");
		pauseButton.setBounds(960/2-150,150,300,100);
		pauseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				parent.closeMenu(instance);
				MainCanvas.menu.add(MainCanvas.engine);
				MainCanvas.engine.state="main";
				MainCanvas.frame.pack();
			}
		});
		
		quitButton = new JButton("RETURN TO MENU");
		quitButton.setBounds(960/2-150,450,300,100);
		
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				parent.closeMenu(instance);
				MainCanvas.menu.addMenuBuilder(new MainMenuBuilder());
				MainCanvas.engine = null;
				MainCanvas.frame.pack();
				MainCanvas.menu.started=false;
			}
		});
		
		
		
		titleLabel = new JLabel("Game Paused");
		titleLabel.setForeground(Color.GREEN);
		titleLabel.setFont(new Font("Copperplate", Font.BOLD, 100));
		
		Menu.centerLabel(titleLabel, 30);
		
		parent.add(quitButton);
		parent.add(titleLabel);
		parent.add(pauseButton);
		parent.add(optionsButton);
	}

	@Override
	public void unBuild(Menu menu){
		parent.remove(titleLabel);
		parent.remove(pauseButton);
		parent.remove(quitButton);
		parent.remove(optionsButton);
		
	}

	@Override
	public void exitMenu() {
		parent.closeMenu(instance);
		MainCanvas.menu.add(MainCanvas.engine);
		MainCanvas.engine.state="main";
		MainCanvas.frame.pack();	
	}

	


}
