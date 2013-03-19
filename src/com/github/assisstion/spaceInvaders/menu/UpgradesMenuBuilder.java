package com.github.assisstion.spaceInvaders.menu;

<<<<<<< HEAD
import java.awt.Canvas;
=======
import static com.github.assisstion.spaceInvaders.MainCanvas.FRAME_HEIGHT;
import static com.github.assisstion.spaceInvaders.MainCanvas.FRAME_WIDTH;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
>>>>>>> Added framework for canvases in Upgrades menu. Markus, you fix my trail of failure :D. Repainting does NOT work.
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
<<<<<<< HEAD

import com.github.assisstion.spaceInvaders.ResourceManager;

@ReturnableMenu
public class UpgradesMenuBuilder implements MenuBuilder {
=======
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.github.assisstion.spaceInvaders.Clock;
import com.github.assisstion.spaceInvaders.MainCanvas;
import com.github.assisstion.spaceInvaders.ResourceManager;

public class UpgradesMenuBuilder extends Canvas implements MenuBuilder{
	//btw wht the hell is this:
	private static final long serialVersionUID = 218162485931432439L;
>>>>>>> Added framework for canvases in Upgrades menu. Markus, you fix my trail of failure :D. Repainting does NOT work.
	private UpgradesMenuBuilder instance;
	private Menu parent;
	private LevelMenuBuilder levelScreen;
	private JButton returnButton;
<<<<<<< HEAD
	private UpgradesCanvas canvas;
=======
	private JLabel titleLabel;
	private JTabbedPane tabbedPane;
	private Canvas canvas1;
	private Canvas canvas2;
	private Canvas canvas3;
	private Graphics graphics1;
	private Graphics graphics2;
	private Graphics graphics3;
	public boolean isOn; 
>>>>>>> Added framework for canvases in Upgrades menu. Markus, you fix my trail of failure :D. Repainting does NOT work.
	
	public UpgradesMenuBuilder(LevelMenuBuilder leScreen){
		levelScreen = leScreen;
		instance = this;
	}
	
	//TABBEDVIEW 
	//CANVAS WITHIN EACH TABBEDVIEW
	
	//MAGNET BRINGS ALL RESOURCES DIRECTLY TO SHIP
	@Override
	public void build(Menu menu) {
		new Thread(new UpgradeClock(this)).start();
		isOn = true;
		parent = menu;
		parent.enableMenuKeyListener();
		parent.requestFocus();
		parent.revalidate();
		
		canvas = new UpgradesCanvas();
		
<<<<<<< HEAD
=======
		buyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Something was bought!");
			}
		});
		
		tabbedPane = new JTabbedPane();
		tabbedPane.setBounds(50, 100, MainCanvas.FRAME_WIDTH - 100, MainCanvas.FRAME_HEIGHT - 125);
	
		tabbedPane.insertTab("Weaponry", null, makePanel(1), "Weaponry", 0);
		tabbedPane.insertTab("Shielding", null, makePanel(2), "Shielding", 1);
		tabbedPane.insertTab("Misc.", null, makePanel(3), "Misc.", 2);
		
		titleLabel = new JLabel("UPGRADES");
		titleLabel.setForeground(Color.GREEN);
		titleLabel.setFont(new Font("Copperplate", Font.BOLD, 100));
		
		Menu.centerLabel(titleLabel, 20);
		
		
>>>>>>> Added framework for canvases in Upgrades menu. Markus, you fix my trail of failure :D. Repainting does NOT work.
		
		returnButton = new JButton(new ImageIcon(getImage("resources/returnButton.png")));
		returnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Button pressed");
				parent.closeMenu(instance);
				parent.addMenuBuilder(levelScreen);
			}
		});
		
		returnButton.setBounds(0,0,162,94);
<<<<<<< HEAD
=======
		
		
		parent.add(tabbedPane);
		parent.add(titleLabel);
		parent.add(returnButton);
		parent.add(buyButton);
>>>>>>> Added framework for canvases in Upgrades menu. Markus, you fix my trail of failure :D. Repainting does NOT work.
		parent.add(returnButton);
		parent.add(canvas);

	}

	public void paint(Graphics g){
		g.drawString("YOSUP", 200, 200);
	}
	
	@Override
	public void unBuild(Menu menu) {
<<<<<<< HEAD
		parent.disableMenuKeyListener();
		parent.remove(returnButton);
		parent.remove(canvas);

=======
		parent.remove(tabbedPane);
		parent.remove(titleLabel);
		parent.remove(returnButton);
		parent.remove(buyButton);
		parent.remove(returnButton);
>>>>>>> Added framework for canvases in Upgrades menu. Markus, you fix my trail of failure :D. Repainting does NOT work.
	}
	
	private JPanel makePanel(int panelNo){
		JPanel lepanel = new JPanel();
		lepanel.setLayout(null);

		if (panelNo==1){
			canvas1 = new Canvas();
			graphics1 = canvas1.getGraphics();
			graphics1.drawString("YOSUP", 200, 200);
			//BROKEN. MARKUS, U FIX
			lepanel.add(canvas1);
			canvas1.setBackground(Color.PINK);
			//NEEDS TO BE FIXED DUNNO HOW
			canvas1.setPreferredSize(new Dimension(840,570));
			canvas1.setBounds(0, 0, 840, 570);
		} else if (panelNo ==2){
			canvas2 = new Canvas();
			graphics2 = canvas2.getGraphics();
			canvas2.setBackground(Color.YELLOW);
			//NEEDS TO BE FIXED DUNNO HOW
			canvas2.setPreferredSize(new Dimension(840,570));
			canvas2.setBounds(0, 0, 840, 570);
			lepanel.add(canvas2);
		} else if (panelNo == 3){
			canvas3 = new Canvas();
			graphics3 = canvas3.getGraphics();
			canvas3.setBackground(Color.GREEN);
			//NEEDS TO BE FIXED DUNNO HOW
			canvas3.setPreferredSize(new Dimension(840,570));
			canvas3.setBounds(0, 0, 840, 570);
			lepanel.add(canvas3);
		}
		return lepanel;

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
	
	private static class UpgradesCanvas extends Canvas{
		
		private static final long serialVersionUID = 5897847762185790426L;

		@Override
		public void paint(Graphics g){
			
		}
	}
}
