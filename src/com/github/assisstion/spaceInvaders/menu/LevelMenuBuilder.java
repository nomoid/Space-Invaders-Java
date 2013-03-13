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
	private int baseScoreNo;
	private int shotsHitNo;
	private int totalShotsNo;
	private boolean godModeOn;
	
	public LevelMenuBuilder(int baseScore,int shotsHit,int totalShots,boolean godModeOn){
		baseScoreNo = baseScore;
		shotsHitNo = shotsHit;
		totalShotsNo = totalShots;
		this.godModeOn=godModeOn;
		
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
		
		
		topLogo = new JLabel("Level Completed!");
		topLogo.setForeground(Color.GREEN);
		topLogo.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 90));
 	 	topLogo.setBounds(30,20,960,65);
 	 	
		int temp1=100;
		int y=170;
		int temp2=600;
		int temp3=150;
		
		Font basefont = new Font("Impact", Font.BOLD, 50);
		baseScore = new JLabel("Score: " + (godModeOn ? "°" : baseScoreNo));
		baseScore.setForeground(Color.WHITE);
		baseScore.setFont(basefont);
		baseScore.setBounds(temp1,y,temp2,temp3);
		
// NEED TO ADD LIFE BONUS THINGY
		
		double accuracyBonus = (double) shotsHitNo/ (double)(totalShotsNo==0 ? 1 : totalShotsNo);
		double accuracyPercentage = Math.round(accuracyBonus * 10000)/100;
		

		accuracy = new JLabel("Accuracy: " + (!godModeOn ? (shotsHitNo + "/" + totalShotsNo + " (" + (int) accuracyPercentage + "%)"): "N/A"));
		accuracy.setForeground(Color.WHITE);
		accuracy.setFont(basefont);
		accuracy.setBounds(temp1,y+60,temp2,temp3);
		
		accuracyBonus = (int) (accuracyBonus * baseScoreNo);
		bonusScore = new JLabel("Bonus: " + (!godModeOn ? (int) accuracyBonus : "N/A"));
		bonusScore.setForeground(Color.WHITE);
		bonusScore.setFont(basefont);
		bonusScore.setBounds(temp1,y+120,temp2,temp3);
		
		totalScore = new JLabel("Total Score: " + (!godModeOn ? (accuracyBonus+ (int) baseScoreNo): "°" ));
		totalScore.setForeground(Color.WHITE);
		totalScore.setFont(basefont);
		totalScore.setBounds(temp1,y+180,temp2,temp3);
		
		parent.add(nextLevelButton);
		parent.add(topLogo);
		parent.add(baseScore);
		parent.add(accuracy);
		parent.add(bonusScore);
		parent.add(totalScore);
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
