package com.github.assisstion.spaceInvaders.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JLabel;

import com.github.assisstion.spaceInvaders.AchievementMethods;
import com.github.assisstion.spaceInvaders.MainCanvas;
import com.github.assisstion.spaceInvaders.gameObject.Achievement;
@ReturnableMenu
public class AchievementsMenuBuilder implements MenuBuilder {

	private Menu parent;
	private AchievementsMenuBuilder instance;
	private MenuBuilder menubuilderparent;
	private boolean inLevel = true;
	
	
	private LinkedList<JLabel> labellist = new LinkedList<JLabel>();

	private JButton returnButton;
	private JLabel topLabel;

	public AchievementsMenuBuilder(LevelMenuBuilder levelmenubuilder) {
		menubuilderparent = levelmenubuilder;
		instance = this;
	}

	public AchievementsMenuBuilder(MainMenuBuilder mainmenubuilder) {
		menubuilderparent = mainmenubuilder;
		inLevel = false;
		instance = this;
	}
	
	@Override
	public void build(Menu menu) {
		parent = menu;
		
		topLabel = new JLabel("ACHIEVEMENTS");
		topLabel.setForeground(Color.WHITE);
		topLabel.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 70));
		Menu.centerLabel(topLabel, 5);
		
		returnButton = new JButton("Return");
		returnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				parent.closeMenu(instance);
				parent.addMenuBuilder(menubuilderparent);
			}
		});
	
		returnButton.setBounds(0,MainCanvas.FRAME_HEIGHT - 80,MainCanvas.FRAME_WIDTH, 80);
		
		

		
		int y = 100;
		for (Achievement a: AchievementMethods.getAchievementList(inLevel)){
			JLabel label = new JLabel("Achievement Unlocked: " + a.name);
			label.setForeground(Color.BLUE);
			label.setFont(new Font("Hobo Std", Font.ITALIC, 30));
			Menu.centerLabel(label, y);
			
			labellist.add(label);
			parent.add(label);
			y+=40;
			
		}
		if (y == 100){
			JLabel label = new JLabel("NO ACHIEVEMENTS UNLOCKED");
			label.setForeground(Color.BLUE);
			label.setFont(new Font("Helvetica", Font.BOLD, 50));
			Menu.centerLabel(label, 350);
			
			labellist.add(label);
			parent.add(label);
		}
		
		parent.add(topLabel);
		parent.add(returnButton);
	}

	@Override
	public void unBuild(Menu menu) {
		parent.remove(returnButton);
		parent.remove(topLabel);
		
		for (JLabel l : labellist){
			parent.remove(l);
		}
	}

	@Override
	public void exitMenu() {
		parent.closeMenu(instance);
		parent.addMenuBuilder(menubuilderparent);
		
	}

}
