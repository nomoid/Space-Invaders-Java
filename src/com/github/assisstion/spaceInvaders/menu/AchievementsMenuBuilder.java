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
	private LevelMenuBuilder menubuilderparent;

	private LinkedList<JLabel> labellist = new LinkedList<JLabel>();

	private JButton returnButton;

	public AchievementsMenuBuilder(LevelMenuBuilder levelmenubuilder) {
		menubuilderparent = levelmenubuilder;
		instance = this;
	}

	@Override
	public void build(Menu menu) {
		parent = menu;
		
		returnButton = new JButton("Return");
		returnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Button pressed");
				parent.closeMenu(instance);
				parent.addMenuBuilder(menubuilderparent);
			}
		});
	
		returnButton.setBounds(0,MainCanvas.FRAME_HEIGHT - 200,MainCanvas.FRAME_WIDTH, 200);
		
		

		
		int y = 200;
		for (Achievement a: AchievementMethods.getAchievementList()){
			JLabel label = new JLabel("Achievement Unlocked: " + a.name);
			label.setForeground(Color.WHITE);
			label.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 30));
			Menu.centerLabel(label, y);
			
			
			labellist.add(label);
			parent.add(label);
			y+=70;
			
		}
		
		parent.add(returnButton);
	}

	@Override
	public void unBuild(Menu menu) {
		parent.remove(returnButton);
		
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
