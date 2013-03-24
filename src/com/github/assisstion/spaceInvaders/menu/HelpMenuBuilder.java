package com.github.assisstion.spaceInvaders.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.github.assisstion.spaceInvaders.AchievementMethods;
import com.github.assisstion.spaceInvaders.MainCanvas;
import com.github.assisstion.spaceInvaders.ResourceManager;
import com.github.assisstion.spaceInvaders.AchievementMethods.achievementList;

@ReturnableMenu
public class HelpMenuBuilder implements MenuBuilder {
	private HelpMenuBuilder instance;
	private Menu parent;
	private JLabel helplabel;
	private JButton returnButton;

	private String[] panelLinks = {"resources/Controls.jpg","resources/Enemies.jpg","resources/Controls.jpg"};
	private JTabbedPane tabbedPane;
	
	public HelpMenuBuilder(){
		instance = this;
	}
	
	@Override
	public void build(Menu menu) {
		AchievementMethods.redeemAchievement("Modesty", achievementList.Modesty);
		parent=menu;
		
		helplabel = new JLabel("Help");
		helplabel.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 95));
		helplabel.setForeground(Color.WHITE);
		Menu.centerLabel(helplabel, 0);
		

		
		returnButton = new JButton(new ImageIcon(getImage("resources/returnButton.png")));
		returnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Button pressed");
				parent.closeMenu(instance);
				parent.addMenuBuilder(new MainMenuBuilder());
			}
		});
		returnButton.setBounds(0,0,162,94);
		
		
		tabbedPane = new JTabbedPane();
		tabbedPane.setBounds(50, 100, MainCanvas.FRAME_WIDTH - 100, MainCanvas.FRAME_HEIGHT - 125);
	
		tabbedPane.insertTab("Controls", null, makePanel(0), "General", 0);
		tabbedPane.insertTab("Enemies", null, makePanel(1), "Enemies", 1);
		tabbedPane.insertTab("Powerups", null, makePanel(2), "Powerups", 2);
		tabbedPane.insertTab("Hitstreaks", null, makePanel(2), "Hitstreaks", 3);
		tabbedPane.insertTab("OMFG", null, makePanel(4), "OMFG", 4);

		
		parent.add(tabbedPane);	
		parent.add(returnButton);
		parent.add(helplabel);
	}

	private JPanel makePanel(int panelNo){
		JPanel lepanel = null;
		if (panelNo<=3){
		lepanel = new JPanel();
		lepanel.setLayout(null);
		JLabel lelabel = new JLabel(new ImageIcon(getImage(panelLinks[panelNo])));
		lelabel.setBounds(-1,0,841,570);
		lepanel.add(lelabel);
		} else {
			lepanel = new JPanel();
			lepanel.setLayout(null);
			JLabel lelabel = new JLabel("WHEN THE HELL ARE WE EVER GONNA FINISH THIS DAMN PAGE?");
			lelabel.setBounds(-1,0,841,570);
			lepanel.add(lelabel);
		}
		return lepanel;
	}
	@Override
	public void unBuild(Menu menu) {
		parent.remove(helplabel);
		parent.remove(returnButton);
		parent.remove(tabbedPane);
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
}
