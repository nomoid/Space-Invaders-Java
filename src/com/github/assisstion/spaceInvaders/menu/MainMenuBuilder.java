package com.github.assisstion.spaceInvaders.menu;

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

	private JLabel logolabel;
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
		optionsButton = new JButton(new ImageIcon(STARTBUTTON));

		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				parent.closeMenu(instance);
				
				if (Cutscene.enabled) {
					// CUTSCENE 2 HERE 4 TESTING
					CutsceneBuilder cutscenebuilder = new CutsceneBuilder(
							CutsceneData.Cutscene1.SCENE);
					parent.addMenuBuilder(cutscenebuilder);
					new Thread(new CutsceneUpdater(cutscenebuilder,
							Cutscene.DEFAULT_DELAY)).start();
				} else {
					parent.startGame();
				}
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

		logolabel.setBounds(960 / 2 - 450, 25, 900, 100);

		parent.add(achievementsButton);
		parent.add(creditsButton);
		parent.add(hscoreButton);
		parent.add(storyButton);
		parent.add(helpButton);
		parent.add(startButton);
		parent.add(logolabel);
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

		looper.stop();
		ResourceManager.removeAudioPlayer(looper);
		looper = null;
	}

	public void playSound(String location) {
		if (looper == null || !looper.on) {
			looper = new AudioLooper(location);
			new Thread(looper).start();
		}
	}

	public static class AudioLooper implements Runnable, Looper {

		private String location;
		private boolean on = true;
		private boolean ready = true;
		private boolean paused = false;
		private Object pauseLock = new Object();
		private Object onLock = new Object();

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
						{
							MainCanvas.audioLock.unlock();
							synchronized (this) {
								while (paused) {
									wait();
								}
							}
							MainCanvas.audioLock.lock();
							Helper.streamSound(location, this);
							System.out.println("Audio Loop");
							ready = false;
						}
						MainCanvas.audioLock.unlock();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				ResourceManager.removeAudioPlayer(looper);
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
				synchronized (this) {
					notify();
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
