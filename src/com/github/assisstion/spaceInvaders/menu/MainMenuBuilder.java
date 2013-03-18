package com.github.assisstion.spaceInvaders.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import com.github.assisstion.spaceInvaders.AudioPlayable;
import com.github.assisstion.spaceInvaders.Helper;
import com.github.assisstion.spaceInvaders.MainCanvas;
import com.github.assisstion.spaceInvaders.ResourceManager;

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
	private static AudioLooper looper;
	
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
			startbuttonIcon = ResourceManager.getImageResource(STARTBUTTON);
			helpbuttonIcon = ResourceManager.getImageResource(HELPBUTTON);
			storybuttonIcon = ResourceManager.getImageResource(STORYBUTTON);
			mainLogoIcon = ResourceManager.getImageResource(MAINLOGO);
			hscorebuttonIcon = ResourceManager.getImageResource(HSBUTTON);
			creditbuttonIcon = ResourceManager.getImageResource(CREDITSBUTTON);
			
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
				if(looper.on){
					looper.stop();
				}
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
		ResourceManager.removeAudioPlayer(looper);
	// WORK On ENDING THE MENU MUSIC WHEN GAME STARTS

	}
	
	public void playSound(String location){
		if(looper == null || !looper.on){
			looper = this.new AudioLooper(location);
			new Thread(looper).start();
		}
	}
	
	/*
	 * Note: this inner class is NOT static
	 */
	public class AudioLooper implements Runnable, Looper{

		private String location;
		private boolean on = true;
		private boolean ready = true;
		private boolean paused = false;
		
		public AudioLooper(String location){
			this.location = location;
			ResourceManager.addAudioPlayer(this);
		}
		
		@Override
		public void run(){
			try{
				while(on){
					if(ready){
						synchronized(MainCanvas.audioLock){
							while(paused){
								MainCanvas.audioLock.wait();
							}
							Helper.streamSound(location, this);
							System.out.println("Audio Loop");
							ready = false;
						}
					}
				}
			}
			catch(Exception e){
				//TODO placeholder
				e.printStackTrace();
			}
			finally{
				ResourceManager.removeAudioPlayer(looper);
			}
		}
		
		public void stop(){
			on = false;
		}
		
		@Override
		public boolean isOn(){
			return on;
		}

		@Override
		public void ready(){
			ready = true;
		}
		
		@Override
		public boolean isPaused(){
			return paused;
		}

		@Override
		public void setPaused(boolean paused){
			if(this.paused && !paused){
				synchronized(MainCanvas.audioLock){
					MainCanvas.audioLock.notify();
				}
				
				
			}
			this.paused = paused;
		}

		@Override
		public int compareTo(AudioPlayable ap){
			return new Integer(hashCode()).compareTo(ap.hashCode());
		}
	}
}
