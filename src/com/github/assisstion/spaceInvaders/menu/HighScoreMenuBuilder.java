package com.github.assisstion.spaceInvaders.menu;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JTabbedPane;

import com.github.assisstion.spaceInvaders.MainCanvas;

@ReturnableMenu
public class HighScoreMenuBuilder implements MenuBuilder{

	private JTabbedPane tabbedPane;
	private Menu parent;
	private HighScoreMenuBuilder instance;
	
	@Override
	public void build(Menu menu) {
		instance = this;
		parent = menu;
		tabbedPane = new JTabbedPane();
		tabbedPane.setBounds(10, 10, MainCanvas.FRAME_WIDTH - 20, MainCanvas.FRAME_HEIGHT - 20);
		JLabel label = new JLabel("High Scores: " + HighScoreDataHandler.formString(HighScoreDataHandler.scoreArray));
		label.setForeground(Color.WHITE);
		tabbedPane.insertTab("High Scores", null, label, "High Scores", 0);
		JLabel label2 = new JLabel("Other Crap Here");
		label2.setForeground(Color.WHITE);
		tabbedPane.insertTab("Other Crap", null, label2, "Other Crap", 1);
		parent.add(tabbedPane);
	}

	@Override
	public void unBuild(Menu menu) {
		parent.remove(tabbedPane);
		
	}

	@Override
	public void exitMenu() {
		parent.closeMenu(instance);
		parent.addMenuBuilder(new MainMenuBuilder());		
	}

}
