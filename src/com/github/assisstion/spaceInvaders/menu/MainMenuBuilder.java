package com.github.assisstion.spaceInvaders.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import com.github.assisstion.spaceInvaders.Helper;
import com.github.assisstion.spaceInvaders.MainCanvas;
import com.github.assisstion.spaceInvaders.ResourceHolder;

public class MainMenuBuilder implements MenuBuilder{

	private final static String MENUSONG = "resources/Menu Song (.wav).wav";
	private final static String STARTBUTTON = "resources/startButton.png";
	private final static String HELPBUTTON = "resources/helpButton.png";
	private final static String STORYBUTTON = "resources/storyButton.png";
	private final static String MAINLOGO = "resources/MainLogo.png";
	private final static String HSBUTTON = "resources/highscoreButton.png";
	private final static String CREDITSBUTTON = "resources/creditsButton.png";
	private MainMenuBuilder instance;
	private Menu parent;
	private JButton startButton;
	private JButton storyButton;
	private JButton helpButton;
	private JLabel logolabel;
	private JButton creditsButton;
	private JButton hscoreButton;
	private AudioLooper looper;
	
	public MainMenuBuilder(){
		instance = this;
	}
	
	@Override
	public void build(Menu menu){
		parent = menu;
		BufferedImage startbuttonIcon = null;
		BufferedImage helpbuttonIcon = null;
		BufferedImage storybuttonIcon = null;
		BufferedImage mainLogoIcon = null;
		BufferedImage creditbuttonIcon = null;
		BufferedImage hscorebuttonIcon = null;
		
		try {
			startbuttonIcon = ResourceHolder.getImageResource(STARTBUTTON);
			helpbuttonIcon = ResourceHolder.getImageResource(HELPBUTTON);
			storybuttonIcon = ResourceHolder.getImageResource(STORYBUTTON);
			mainLogoIcon = ResourceHolder.getImageResource(MAINLOGO);
			hscorebuttonIcon = ResourceHolder.getImageResource(HSBUTTON);
			creditbuttonIcon = ResourceHolder.getImageResource(CREDITSBUTTON);
			
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Error loading image!");
		}
		hscoreButton = new JButton(new ImageIcon(hscorebuttonIcon));
		creditsButton = new JButton(new ImageIcon(creditbuttonIcon));
		startButton = new JButton(new ImageIcon(startbuttonIcon));
		startButton = new JButton(new ImageIcon(startbuttonIcon));
		helpButton = new JButton(new ImageIcon(helpbuttonIcon));
		storyButton = new JButton(new ImageIcon(storybuttonIcon));
		logolabel = new JLabel(new ImageIcon(mainLogoIcon));
		
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Button pressed");
				parent.closeMenu(instance);
				CutsceneBuilder cutscenebuilder = new CutsceneBuilder(CutsceneData.Cutscene1.SCENE);
				parent.addMenuBuilder(cutscenebuilder);	
				new Thread(new CutsceneUpdater(cutscenebuilder, Cutscene.DEFAULT_DELAY)).start();
			}
		});
		
		helpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Button pressed");
				parent.closeMenu(instance);
				parent.addMenuBuilder(new HelpMenuBuilder());
			}
		});
		
		creditsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Button pressed");
				parent.closeMenu(instance);
				parent.addMenuBuilder(new CreditsMenuBuilder());
			}
		});
		
		hscoreButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Button pressed");
				parent.closeMenu(instance);
				parent.addMenuBuilder(new HighScoreMenuBuilder());
			}
		});
		
		storyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Button pressed");
				parent.closeMenu(instance);
				parent.addMenuBuilder(new PlotMenuBuilder());			
			}
		});
		

		startButton.setBounds(960/2-81, 180, 162, 79);
		helpButton.setBounds(960/2-81, 260, 162, 79);
		storyButton.setBounds(960/2-81, 340, 162, 79);
		creditsButton.setBounds(960/2-81, 420, 162, 79);
		hscoreButton.setBounds(960/2-81, 500, 162, 79);
		
		logolabel.setBounds(960/2-450,30,900,100);
		
		startButton.setBorder(BorderFactory.createEmptyBorder());
		helpButton.setBorder(BorderFactory.createEmptyBorder());
		storyButton.setBorder(BorderFactory.createEmptyBorder());
		hscoreButton.setBorder(BorderFactory.createEmptyBorder());
		creditsButton.setBorder(BorderFactory.createEmptyBorder());
		
		
		helpButton.setContentAreaFilled(false);
		storyButton.setContentAreaFilled(false);
		startButton.setContentAreaFilled(false);
		hscoreButton.setContentAreaFilled(false);
		creditsButton.setContentAreaFilled(false);
		
		parent.add(creditsButton);
		parent.add(hscoreButton);
		parent.add(storyButton);
		parent.add(helpButton);
		parent.add(startButton);
		parent.add(logolabel);
		playSound(MENUSONG);
		
	}
	

	
	
	@Override
	public void unBuild(Menu menu){
		parent = menu;
		parent.remove(storyButton);
		parent.remove(helpButton);
		parent.remove(startButton);
		parent.remove(logolabel);
		parent.remove(creditsButton);
		parent.remove(hscoreButton);
		
		looper.stop();
	}
	
	public void playSound(String location){
		looper = this.new AudioLooper(location);
		new Thread(looper).start();
	}
	
	/*
	 * Note: this inner class is NOT static
	 */
	public class AudioLooper implements Runnable{

		private String location;
		private boolean on = true;
		public boolean ready = true;
		
		public AudioLooper(String location){
			this.location = location;
		}
		
		@Override
		public void run(){
			while(on){
				if(ready){
					synchronized(MainCanvas.audioLock){
						System.out.println("Audio Loop");
						Helper.streamSound(location, this);
						ready = false;
					}
				}
			}
		}
		
		public void stop(){
			on = false;
		}
		
		public boolean isOn(){
			return on;
		}
	}
}
