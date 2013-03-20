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

import com.github.assisstion.spaceInvaders.MainCanvas;
import com.github.assisstion.spaceInvaders.ResourceManager;


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
	private int baseScoreNo;
	private int shotsHitNo;
	private int totalShotsNo;
	private boolean godModeOn;
	public int totalScoreNo;
	private int livesLost;
	private JButton upgradeButton;
	
	public LevelMenuBuilder(int baseScore,int shotsHit,int totalShots,boolean godModeOn, int livesLost){
		baseScoreNo = baseScore;
		shotsHitNo = shotsHit;
		totalShotsNo = totalShots;
		this.godModeOn=godModeOn;
		this.livesLost = livesLost;
		
		instance = this;
	}
	@Override
	public void build(Menu menu) {
		
		parent = menu;
		parent.addKeyListener(this);
		parent.requestFocus();
		parent.revalidate();
		
		nextLevelButton = new JButton(new ImageIcon(getImage()));
		nextLevelButton.setBounds(0,640,960,100);
		
		nextLevelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Button pressed");
				finish();
			}
		});
		
		nextLevelButton.setBorder(BorderFactory.createEmptyBorder());
		nextLevelButton.setContentAreaFilled(false);
		
		upgradeButton = new JButton("UPGRADES");
		upgradeButton.setBounds(0,400,150,150);
		
		upgradeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				parent.closeMenu(instance);
				parent.addMenuBuilder(new UpgradesMenuBuilder(instance));
			}
		});
		
		
		topLogo = new JLabel("Level Completed!");
		topLogo.setForeground(Color.GREEN);
		topLogo.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 90));
 	 	topLogo.setBounds(30,20,960,65);
 	 	

		int x = 250;
		Font basefont = new Font("Impact", Font.BOLD, 50);
		baseScore = new JLabel("Score: " + (godModeOn ? "°" : baseScoreNo));
		baseScore.setForeground(Color.WHITE);
		baseScore.setFont(basefont);
		Menu.centerLabel(baseScore,x);
		
// NEED TO ADD LIFE BONUS THINGY
		
		double accuracyBonus = (double) shotsHitNo/ (double)(totalShotsNo==0 ? 1 : totalShotsNo);
		double accuracyPercentage = Math.round(accuracyBonus * 10000)/100;
		
		accuracy = new JLabel("Accuracy: " + (!godModeOn ? (shotsHitNo + "/" + totalShotsNo + " (" + (int) accuracyPercentage + "%)"): "N/A"));
		accuracy.setForeground(Color.WHITE);
		accuracy.setFont(basefont);
		Menu.centerLabel(accuracy, x+60);
		

		accuracyBonus = (int) (accuracyBonus * baseScoreNo);
		bonusScore = new JLabel("Bonus: " + (!godModeOn ? (int) accuracyBonus : "N/A"));
		bonusScore.setForeground(Color.WHITE);
		bonusScore.setFont(basefont);
		Menu.centerLabel(bonusScore, x+120);
		totalScoreNo = (int) (accuracyBonus + baseScoreNo);
		String leMessage = null;
		if (livesLost == 0){
			totalScoreNo= (int)(totalScoreNo* 1.5);
			leMessage = "Survival Boost: 1.5x";
		} else if (livesLost == 2) {
			totalScoreNo= (int)(totalScoreNo* 0.8);
			leMessage = "Survival Boost: 0.8x";
		} else if (livesLost >= 3){
			totalScoreNo= (int)(totalScoreNo* 0.5);
			leMessage = "Survival Boost: 0.5x";
		}
		
		lifeBonus = new JLabel(godModeOn ? "Survival Boost: °": leMessage);
		lifeBonus.setForeground(Color.WHITE);
		lifeBonus.setFont(basefont);
		Menu.centerLabel(lifeBonus,x+180);
		
		
		totalScore = new JLabel("Total Score: " + (!godModeOn ? (int) (accuracyBonus + baseScoreNo): "°" ));
		totalScore.setForeground(Color.WHITE);
		totalScore.setFont(basefont);
		Menu.centerLabel(totalScore,x+240);
		
		parent.add(nextLevelButton);
		parent.add(topLogo);
		parent.add(baseScore);
		parent.add(accuracy);
		parent.add(bonusScore);
		parent.add(totalScore);
		parent.add(lifeBonus);
		parent.add(upgradeButton);
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
		parent.remove(upgradeButton);
	
	}

	private BufferedImage getImage(){
		BufferedImage returnIcon = null;
		try {
			returnIcon = ResourceManager.getImageResource("resources/nextlevelButton.png");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Error loading image!");
		}
		return returnIcon;
	}
	@Override
	public void keyPressed(KeyEvent e){
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyReleased(KeyEvent e){
		try{
			if(e.getKeyCode() == KeyEvent.VK_ENTER){
				finish();
				System.out.println("Finish");
			}
		}
		catch(Exception ex){
			//TODO placeholder
			ex.printStackTrace();
		}
	}
	@Override
	public void keyTyped(KeyEvent e){
		// TODO Auto-generated method stub
		
	}
	
	private void finish(){
		parent.closeMenu(instance);
		MainCanvas.menu.add(MainCanvas.engine);
		MainCanvas.engine.state="main";
		MainCanvas.frame.pack();
	}
}
