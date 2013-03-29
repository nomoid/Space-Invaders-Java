package com.github.assisstion.spaceInvaders.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import com.github.assisstion.spaceInvaders.AchievementMethods;
import com.github.assisstion.spaceInvaders.Engine;
import com.github.assisstion.spaceInvaders.MainCanvas;
import com.github.assisstion.spaceInvaders.ResourceManager;
import com.github.assisstion.spaceInvaders.TimerClock;

public class LevelMenuBuilder implements MenuBuilder, KeyListener {
	private LevelMenuBuilder instance;
	private Menu parent;
	private JButton nextLevelButton;
	private JLabel topLogo;
	private JLabel baseScore;
	private JLabel accuracy;
	private JLabel bonusScore;
	private JLabel totalScore;
	private JLabel lifeBonus;
	private JLabel timeLabel;
	private int baseScoreNo;
	private int shotsHitNo;
	private int totalShotsNo;
	private boolean godModeOn;
	public int totalScoreNo;
	private int livesLost;
	private JButton upgradeButton;
	private JButton achievementButton;
	private int timeTaken;
	private int levelScore;

	public LevelMenuBuilder(int baseScore, int shotsHit, int totalShots,
			boolean godModeOn, int livesLost, int score) {
		baseScoreNo = baseScore;
		levelScore = score;
		shotsHitNo = shotsHit;
		totalShotsNo = totalShots;
		this.godModeOn = godModeOn;
		this.livesLost = livesLost;
		timeTaken = TimerClock.levelTime;

		instance = this;
	}

	@Override
	public void build(Menu menu) {

		parent = menu;
		parent.addKeyListener(this);
		parent.requestFocus();
		parent.revalidate();

		nextLevelButton = new JButton(new ImageIcon(getImage()));
		nextLevelButton.setBounds(0, 640, 960, 100);

		nextLevelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Button pressed");
				AchievementMethods.achievementUnlocked = false;
				finish();
			}
		});

		nextLevelButton.setBorder(BorderFactory.createEmptyBorder());
		nextLevelButton.setContentAreaFilled(false);

		upgradeButton = new JButton("UPGRADES");
		upgradeButton.setBounds(0, 400, 150, 150);

		upgradeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				parent.closeMenu(instance);
				parent.addMenuBuilder(new UpgradesMenuBuilder(instance));
			}
		});

		achievementButton = new JButton("ACHIEVEMENTS");
		achievementButton.setBounds(400, 100, 150, 150);

		achievementButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				parent.closeMenu(instance);
				parent.addMenuBuilder(new AchievementsMenuBuilder(instance));
			}
		});

		topLogo = new JLabel("Level Completed!");
		topLogo.setForeground(Color.GREEN);
		topLogo.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 90));
		topLogo.setBounds(30, 20, 960, 65);

		int x = 250;
		Font basefont = new Font("Impact", Font.BOLD, 40);
		Font largefont = new Font("Impact", Font.BOLD, 60);

		baseScore = new JLabel("Score: " + (godModeOn ? "°" : baseScoreNo));
		baseScore.setForeground(Color.WHITE);
		baseScore.setFont(basefont);
		Menu.centerLabel(baseScore, x);

		// NEED TO ADD LIFE BONUS THINGY

		double accuracyBonus = (double) shotsHitNo
				/ (double) (totalShotsNo == 0 ? 1 : totalShotsNo);
		double accuracyPercentage = Math.round(accuracyBonus * 10000) / 100;

		AchievementMethods.checkAccuracy((int) accuracyPercentage);

		accuracy = new JLabel("Accuracy: "
				+ (!godModeOn ? (shotsHitNo + "/" + totalShotsNo + " ("
						+ (int) accuracyPercentage + "%)") : "N/A"));
		accuracy.setForeground(Color.WHITE);
		accuracy.setFont(basefont);
		Menu.centerLabel(accuracy, x + 40);

		accuracyBonus = (int) (accuracyBonus * levelScore);

		String leMessage = null;
		if (livesLost == 0) {
			accuracyBonus = (int) (accuracyBonus * 1.5);
			leMessage = "Survival Reward: 1.5x";
		} else if (livesLost == 1) {
			accuracyBonus = (int) (accuracyBonus * 0.85);
			leMessage = "Death Penalty: 0.85x";
		} else if (livesLost == 2) {
			accuracyBonus = (int) (accuracyBonus * 0.65);
			leMessage = "Death Penalty: 0.65";
		} else if (livesLost == 3) {
			accuracyBonus = (int) (accuracyBonus * 0.5);
			leMessage = "Death Penalty: 0.5x";
		}

		lifeBonus = new JLabel(godModeOn ? "Survival Boost: °" : leMessage);
		lifeBonus.setForeground(Color.WHITE);
		lifeBonus.setFont(basefont);
		Menu.centerLabel(lifeBonus, x + 80);

		timeLabel = new JLabel("Time Taken: "
				+ (!godModeOn ? (int) timeTaken : "N/A"));
		timeLabel.setForeground(Color.WHITE);
		timeLabel.setFont(basefont);
		Menu.centerLabel(timeLabel, x + 120);

		bonusScore = new JLabel("Total Bonus: "
				+ (!godModeOn ? (int) accuracyBonus : "N/A"));
		bonusScore.setForeground(Color.WHITE);
		bonusScore.setFont(basefont);
		Menu.centerLabel(bonusScore, x + 160);

		totalScoreNo = (int) (accuracyBonus + baseScoreNo);
		totalScore = new JLabel("Total Score: "
				+ (!godModeOn ? ((int) totalScoreNo) : "°"));
		totalScore.setForeground(Color.WHITE);
		totalScore.setFont(largefont);
		Menu.centerLabel(totalScore, x + 220);

		parent.add(nextLevelButton);
		parent.add(topLogo);
		parent.add(baseScore);
		parent.add(accuracy);
		parent.add(bonusScore);
		parent.add(totalScore);
		parent.add(lifeBonus);
		parent.add(upgradeButton);
		parent.add(timeLabel);
		if (AchievementMethods.achievementUnlocked) {
			parent.add(achievementButton);
		}
	}

	@Override
	public void unBuild(Menu menu) {

		parent = menu;
		parent.removeKeyListener(this);
		parent.remove(nextLevelButton);
		parent.remove(topLogo);
		parent.remove(baseScore);
		parent.remove(accuracy);
		parent.remove(bonusScore);
		parent.remove(totalScore);
		parent.remove(lifeBonus);
		parent.remove(timeLabel);
		parent.remove(upgradeButton);
		parent.remove(achievementButton);
	}

	private BufferedImage getImage() {
		BufferedImage returnIcon = null;
		try {
			returnIcon = ResourceManager
					.getImageResource("resources/nextlevelButton.png");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Error loading image!");
		}
		return returnIcon;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		try {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				finish();
				System.out.println("Finish");
			}
		} catch (Exception ex) {
			// TODO placeholder
			ex.printStackTrace();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	private void finish() {
		if (Engine.currentLevel == 6) {
			parent.closeMenu(instance);
			CutsceneBuilder cutscenebuilder = new CutsceneBuilder(
					CutsceneData.Cutscene2.SCENE);
			MainCanvas.menu.addMenuBuilder(cutscenebuilder);
			new Thread(new CutsceneUpdater(cutscenebuilder,
					Cutscene.DEFAULT_DELAY)).start();
			MainCanvas.frame.pack();

		} else {
			parent.closeMenu(instance);
			MainCanvas.menu.add(MainCanvas.engine);
			MainCanvas.engine.state = "main";
			MainCanvas.frame.pack();
		}

	}

	@Override
	public void exitMenu() {
		System.out.println("Button pressed");
		AchievementMethods.achievementUnlocked = false;
		finish();
	}
}
