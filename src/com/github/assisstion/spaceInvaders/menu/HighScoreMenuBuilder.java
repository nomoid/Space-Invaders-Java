package com.github.assisstion.spaceInvaders.menu;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.github.assisstion.spaceInvaders.AchievementMethods;
import com.github.assisstion.spaceInvaders.MainCanvas;
import com.github.assisstion.spaceInvaders.gameObject.Achievement;

@ReturnableMenu
public class HighScoreMenuBuilder implements MenuBuilder {

	private JTabbedPane tabbedPane;
	private Menu parent;
	private HighScoreMenuBuilder instance;
	private JLabel topLabel;

	
	@Override
	public void build(Menu menu) {
		AchievementMethods.redeemAchievement(new Achievement("You Smug Bastard"));
		
		instance = this;
		parent = menu;
		tabbedPane = new JTabbedPane();
		tabbedPane.setBounds(10, 85, MainCanvas.FRAME_WIDTH - 20,
				MainCanvas.FRAME_HEIGHT - 20);
		
		tabbedPane.insertTab("High Scores", null, makePanel(1), "High Scores",
				0);
		tabbedPane.insertTab("Time Trials", null, makePanel(2), "Time Trials", 1);

		topLabel = new JLabel("HIGH SCORES");
		topLabel.setForeground(Color.WHITE);
		topLabel.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 70));
		Menu.centerLabel(topLabel, 5);

		parent.add(topLabel);
		parent.add(tabbedPane);
	}

	private JPanel makePanel(int panelNo) {
		JPanel lepanel = null;

		lepanel = new JPanel();
		lepanel.setLayout(null);

		if (panelNo == 1) {
			int y = 40;
			for (int i = 0; i < HighScoreDataHandler.SAVELENGTH; i++) {
				if (HighScoreDataHandler.scoreArray[i].score > 0) {
					JLabel lelabel = new JLabel(HighScoreDataHandler.scoreArray[i].name + " at " +
							HighScoreDataHandler.scoreArray[i].date + " : "
									+ HighScoreDataHandler.scoreArray[i].score);
					lelabel.setFont(new Font("Chalkboard", Font.BOLD, 20));
					y += 30;
					lelabel.setForeground(Color.BLUE);
					lelabel.setBounds(50, y, 600, 30);
					lepanel.add(lelabel);
				}
			}
		} else if (panelNo == 2){
			int y = 40;
			for (int i = 0; i < HighScoreDataHandler.SAVELENGTH; i++) {
				if (HighScoreDataHandler.timeArray[i].time > 0) {
					JLabel lelabel = new JLabel(HighScoreDataHandler.timeArray[i].name + " at " +
							HighScoreDataHandler.timeArray[i].date + " : "
									+ HighScoreDataHandler.convertTime(HighScoreDataHandler.timeArray[i].time)[0] + " minutes " + HighScoreDataHandler.convertTime(HighScoreDataHandler.timeArray[i].time)[1] + " seconds");
					lelabel.setFont(new Font("Chalkboard", Font.BOLD, 20));
					y += 30;
					lelabel.setForeground(Color.BLUE);
					lelabel.setBounds(50, y, 600, 30);
					lepanel.add(lelabel);
				}
			}
		}
		return lepanel;
	}

	@Override
	public void unBuild(Menu menu) {
		parent.remove(tabbedPane);
		parent.remove(topLabel);

	}

	@Override
	public void exitMenu() {
		parent.closeMenu(instance);
		parent.addMenuBuilder(new MainMenuBuilder());
	}

}
