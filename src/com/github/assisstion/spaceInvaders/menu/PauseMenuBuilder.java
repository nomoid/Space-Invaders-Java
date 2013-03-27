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
	private JLabel titleLabel;

	public PauseMenuBuilder(){
		instance = this;
	}
	
	@Override
	public void build(Menu menu){
		parent = menu;
		parent.requestFocus();
		parent.revalidate();
		
		pauseButton = new JButton("RESUME");
		pauseButton.setBounds(960/2-150,200,300,100);
		
		pauseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Button pressed");
				parent.closeMenu(instance);
				MainCanvas.menu.add(MainCanvas.engine);
				MainCanvas.engine.state="main";
				MainCanvas.frame.pack();
			}
		});
		
		quitButton = new JButton("RETURN TO MENU");
		quitButton.setBounds(960/2-150,500,300,100);
		
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Button pressed");
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
	}

	@Override
	public void unBuild(Menu menu){
		parent.remove(titleLabel);
		parent.remove(pauseButton);
		parent.remove(quitButton);
		
	}

	@Override
	public void exitMenu() {
		parent.closeMenu(instance);
		MainCanvas.menu.add(MainCanvas.engine);
		MainCanvas.engine.state="main";
		MainCanvas.frame.pack();	
	}

	


}
