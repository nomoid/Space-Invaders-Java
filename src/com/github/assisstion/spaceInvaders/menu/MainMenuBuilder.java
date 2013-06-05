package com.github.assisstion.spaceInvaders.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import com.github.assisstion.spaceInvaders.AudioPlayable;
import com.github.assisstion.spaceInvaders.Helper;
import com.github.assisstion.spaceInvaders.MainCanvas;
import com.github.assisstion.spaceInvaders.ResourceManager;

public class MainMenuBuilder implements MenuBuilder {
	
	private final static String MENUSONG = "resources/Menu Song (.wav).wav";
	private final static String STARTBUTTON = "resources/startButton.png";
	private final static String HELPBUTTON = "resources/helpButton.png";
	private final static String STORYBUTTON = "resources/storyButton.png";
	private final static String MAINLOGO = "resources/MainLogo.png";
	private final static String HSBUTTON = "resources/highscoreButton.png";
	private final static String CREDITSBUTTON = "resources/creditsButton.png";
	private final static String ACHIEVEMENTSBUTTON = "resources/creditsButton.png";
	// NEEDED

	private MainMenuBuilder instance;
	private Menu parent;
	private JButton startButton;
	private JButton storyButton;
	private JButton helpButton;
	private JButton achievementsButton;
	private JButton optionsButton;
	private JButton texturepackButton;

	private JLabel logolabel;
	private JLabel authorlabel;
	private JButton creditsButton;
	private JButton hscoreButton;
	private static AudioLooper looper;

	public MainMenuBuilder() {
		instance = this;
	}

	@Override
	public void build(Menu menu) {
		
		playSound(MENUSONG);
		parent = menu;

		achievementsButton = new JButton(new ImageIcon(ACHIEVEMENTSBUTTON));
		hscoreButton = new JButton(new ImageIcon(HSBUTTON));
		creditsButton = new JButton(new ImageIcon(CREDITSBUTTON));
		startButton = new JButton(new ImageIcon(STARTBUTTON));
		helpButton = new JButton(new ImageIcon(HELPBUTTON));
		storyButton = new JButton(new ImageIcon(STORYBUTTON));
		logolabel = new JLabel(new ImageIcon(MAINLOGO));
		authorlabel = new JLabel("By assisstion and michael99man (https://github.com/assisstion/Space-Invaders-Java)");
		optionsButton = new JButton(new ImageIcon(STARTBUTTON));
		//For testing
		texturepackButton = new JButton("Texture Packs");
		


		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				parent.closeMenu(instance);
				if (Cutscene.enabled) {
					// CUTSCENE 2 HERE 4 TESTING
					CutsceneBuilder cutscenebuilder = new CutsceneBuilder(
							CutsceneData.Cutscene1.SCENE);
					parent.addMenuBuilder(cutscenebuilder);
					CutsceneUpdater cu = new CutsceneUpdater(cutscenebuilder,
							Cutscene.DEFAULT_DELAY);
					CutsceneUpdater.setService(Helper.newService(3));
					cu.schedule();
				} else {
					parent.startGame();
				}
			}
		});
		
		texturepackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				parent.closeMenu(instance);
				parent.addMenuBuilder(new TexturePackMenuBuilder());
			}
		});

		helpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				parent.closeMenu(instance);
				parent.addMenuBuilder(new HelpMenuBuilder());
			}
		});

		creditsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				parent.closeMenu(instance);
				parent.addMenuBuilder(new CreditsMenuBuilder());
			}
		});

		hscoreButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				parent.closeMenu(instance);
				parent.addMenuBuilder(new HighScoreMenuBuilder());
			}
		});

		storyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				parent.closeMenu(instance);
				parent.addMenuBuilder(new PlotMenuBuilder());
			}
		});

		achievementsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				parent.closeMenu(instance);
				parent.addMenuBuilder(new AchievementsMenuBuilder(instance));
			}
		});

		optionsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				parent.closeMenu(instance);
				parent.addMenuBuilder(new OptionsMenuBuilder(instance));
			}
		});

		startButton.setBounds(960 / 2 - 81, 360, 162, 79);
		
		helpButton.setBounds(960 / 2 - 364, 260, 162, 79);
		storyButton.setBounds(960 / 2 - 364, 360, 162, 79);
		optionsButton.setBounds(960 / 2 - 364, 460, 162, 79);

		creditsButton.setBounds(684, 260, 162, 79);
		hscoreButton.setBounds(684, 360, 162, 79);
		achievementsButton.setBounds(684, 460, 162, 79);
		texturepackButton.setBounds(960 / 2 - 364, 560, 162, 79);
		
		logolabel.setBounds(960 / 2 - 450, 25, 900, 100);
		authorlabel.setBounds(960 / 2 - 450, 100, 900, 100);
		authorlabel.setForeground(Color.WHITE);
		authorlabel.setFont(new Font("Calibri", 0, 20));

		parent.add(texturepackButton);
		parent.add(achievementsButton);
		parent.add(creditsButton);
		parent.add(hscoreButton);
		parent.add(storyButton);
		parent.add(helpButton);
		parent.add(startButton);
		parent.add(logolabel);
		parent.add(authorlabel);
		parent.add(optionsButton);
	}

	@Override
	public void unBuild(Menu menu) {
		parent = menu;
		parent.remove(storyButton);
		parent.remove(helpButton);
		parent.remove(startButton);
		parent.remove(logolabel);
		parent.remove(creditsButton);
		parent.remove(hscoreButton);
		parent.remove(achievementsButton);
		parent.remove(optionsButton);
		parent.remove(texturepackButton);
		
		looper.stop();
		ResourceManager.removeAudioPlayer(looper);
		looper = null;
	}

	public void playSound(String location) {
		if (looper == null || !looper.on) {
			looper = new AudioLooper(location);
			new Thread(looper, "SoundLooper-" + looper.hashCode()).start();
		}
	}

	public static class AudioLooper implements Runnable, Looper {

		private String location;
		private boolean on = true;
		private boolean ready = true;
		private boolean paused = false;
		private Object pauseLock = new Object();
		private Object onLock = new Object();
		private Object readyLock = new Object();

		public AudioLooper(String location) {
			this.location = location;
			ResourceManager.addAudioPlayer(this);
		}

		@Override
		public void run() {
			try {
				while (on) {
					if (ready) {
						MainCanvas.audioLock.lock();
						try{
							while (paused) {
								MainCanvas.looperCondition.await();
							}
							Helper.streamSound(location, this);
							System.out.println("Audio Loop");
							ready = false;
						}
						finally{
							MainCanvas.audioLock.unlock();
						}
					}
					else{
						synchronized(readyLock){
							readyLock.wait();
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				ResourceManager.removeAudioPlayer(this);
			}
		}

		public void stop() {
			synchronized (onLock) {
				on = false;
			}
		}

		@Override
		public boolean isOn() {
			synchronized (onLock) {
				return on;
			}
		}

		@Override
		public void ready() {
			synchronized(readyLock){
				readyLock.notify();
			}
			ready = true;
		}

		@Override
		public boolean isPaused() {
			synchronized (pauseLock) {
				return paused;
			}
		}

		@Override
		public void setPaused(boolean paused) {
			boolean tempPaused;
			synchronized (pauseLock) {
				tempPaused = this.paused;
				this.paused = paused;
			}
			if (tempPaused && !paused) {
				MainCanvas.audioLock.lock();
				try{
					MainCanvas.looperCondition.notify();
				}
				finally{
					MainCanvas.audioLock.unlock();
				}
			}
			this.paused = paused;
		}

		@Override
		public int compareTo(AudioPlayable ap) {
			return new Integer(hashCode()).compareTo(ap.hashCode());
		}
	}

	@Override
	public void exitMenu() {
	}
}
