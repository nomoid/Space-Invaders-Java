package com.github.assisstion.spaceInvaders.menu;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JTabbedPane;

import com.github.assisstion.spaceInvaders.MainCanvas;
@ReturnableMenu
public class HighScoreMenuBuilder implements MenuBuilder{

	private JTabbedPane tabbedPane;
	private Menu parent;
	
	@Override
	public void build(Menu menu) {
		parent = menu;
		tabbedPane = new JTabbedPane();
		tabbedPane.setBounds(10, 10, MainCanvas.FRAME_WIDTH - 20, MainCanvas.FRAME_HEIGHT - 20);
		JLabel label = new JLabel("hi");
		label.setForeground(Color.WHITE);
		tabbedPane.insertTab("hi", null, label, "epic", 0);
		JLabel label2 = new JLabel("bye");
		label2.setForeground(Color.WHITE);
		tabbedPane.insertTab("bye", null, label2, "moreEpic", 0);
		parent.add(tabbedPane);
	}

	@Override
	public void unBuild(Menu menu) {
		parent.remove(tabbedPane);
		
	}

}
