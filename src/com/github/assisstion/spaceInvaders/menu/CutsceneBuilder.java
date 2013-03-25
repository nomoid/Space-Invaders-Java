package com.github.assisstion.spaceInvaders.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import com.github.assisstion.spaceInvaders.gameObject.Sprite;

public class CutsceneBuilder implements MenuBuilder, KeyListener {
	private CutsceneBuilder instance;
	private Menu parent;

	private String lastString;
	private int y = 10;
	public boolean isOn;
	private static final String ADVANCEMENTTEXT = "Press Enter to skip";
	private static final String FASTFORWARDTEXT = "Press right arrow to fastforward";
	private static String FASTFORWARD = "";
	private static final String SPEEDNORMAL = "Press space to return speed to normal";
	private static final String SKIPPAGE = "Press down arrow to skip page";


	JLabel advancementLabel = new JLabel(ADVANCEMENTTEXT);
	JLabel fastforwardLabel = new JLabel(FASTFORWARDTEXT);
	JLabel fastforwardLabel2 = new JLabel(FASTFORWARD);
	JLabel speedNormalLabel = new JLabel(SPEEDNORMAL);
	JLabel skipPageLabel = new JLabel(SKIPPAGE);


	private JLabel lastlabel;
	private boolean notYetCreated = true;
	private LinkedList<JLabel> labelList = new LinkedList<JLabel>();
	private LinkedList<JLabel> tempLabelList = new LinkedList<JLabel>();
	public int pageNumber = 0;
	@SuppressWarnings("unused")
	private Sprite[][] sprites;
	
	public boolean justFinishedLine = false;
	private int i = 0;
	private int x = 0;
	private String leText = "";
	private CutsceneTextResource[] textRes;
	private boolean done = false;
	private boolean inUse = false;
	
	private CutsceneUpdater updater = null;
	
	int counter = 2;
	
	public void setCutsceneUpdater(CutsceneUpdater cu) {
		this.updater = cu;
	}

	public CutsceneTextResource[] getTextRes() {
		return textRes;
	}

	public CutsceneBuilder(Cutscene cutscene) {
		instance = this;
		isOn = true;
		this.sprites = cutscene.getSprites();
		this.textRes = cutscene.getTextRes();
	}

	@Override
	public void build(Menu menu) {
		parent = menu;
		parent.addKeyListener(this);
		update(parent);
		advancementLabel.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		advancementLabel.setForeground(Color.WHITE);
		advancementLabel.setBounds(650, 620, 200, 100);
		addLabel(advancementLabel);
		
		fastforwardLabel.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		fastforwardLabel.setForeground(Color.WHITE);
		fastforwardLabel.setBounds(650, 650, 300, 100);
		addLabel(fastforwardLabel);
		
		skipPageLabel.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		skipPageLabel.setForeground(Color.WHITE);
		skipPageLabel.setBounds(650, 590, 300, 100);
		addLabel(skipPageLabel);
		
		fastforwardLabel2.setFont(new Font("Arial", Font.ITALIC, 40));
		fastforwardLabel2.setForeground(Color.WHITE);
		fastforwardLabel2.setBounds(100, 645, 300, 100);
		fastforwardLabel2.setVisible(false);
		addLabel(fastforwardLabel2);
		
		speedNormalLabel.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		speedNormalLabel.setForeground(Color.WHITE);
		speedNormalLabel.setBounds(200, 650, 400, 100);
		speedNormalLabel.setVisible(false);
		addLabel(speedNormalLabel);
	}
	public void updatePage(){
		notYetCreated = true;
		y = 10;
		i = 0;
		x++;
		leText = "";
		pageNumber++;
		justFinishedLine = true;
	}
	public void update(Menu menu) {

		justFinishedLine = false;
		parent = menu;
		parent.requestFocus();
		if (pageNumber >= textRes.length) {
			parent.closeMenu(instance);
			parent.startGame();
		} else if (i < textRes[x].getText().length) {
			leText += textRes[x].getText()[i];
			buildText(leText);
			// Sprite[] array = sprites[pageNumber];
			// for(Sprite s : array){
			// buildIcon(s.getImage(), s.x, s.y);
			// } 
			i++;

		} else {			
			updatePage();
		}
	}

	@Override
	public void unBuild(Menu menu) {
		parent = menu;
		isOn = false;
		fullUnBuildText();
	}

	public void unBuildText() {
		for (JLabel label : labelList) {
			if (!label.getText().equals(ADVANCEMENTTEXT) && !label.getText().equals(FASTFORWARDTEXT) && !label.equals(fastforwardLabel2) && !label.equals(speedNormalLabel) && !label.equals(skipPageLabel)) {
				if (justFinishedLine) {
					parent.remove(label);
				}
			} else {
				tempLabelList.add(label);
			}
		}
		labelList.clear();
		for (JLabel label : tempLabelList) {
			labelList.add(label);
		}
		tempLabelList.clear();
		parent.revalidate();
		parent.repaint();
	}

	public void fullUnBuildText() {
		for (JLabel label : labelList) {
			parent.remove(label);
		}
		labelList.clear();
		parent.revalidate();
		parent.repaint();
	}

	private void buildText(String text) {
		String[] labels = text.split("&");
		if (labels.length > 1) {
			if (lastString.equals(labels[labels.length - 2])) {
				notYetCreated = true;
			}
		}
		lastString = labels[labels.length - 1];

		if (notYetCreated) {
			lastlabel = constructLabel(textRes[x].getColor(), textRes[x].getFont(), lastString, 25, y,
					960, 40);
			y += 40;
		} else {
			updateLabel(lastlabel, lastString);
		}
	}

	private JLabel constructLabel(Color tempcolor, Font tempfont, String text,
			int x, int y, int width, int height) {
		JLabel label = new JLabel(text);
		label.setFont(tempfont);
		label.setForeground(tempcolor);
		label.setBounds(x, y, width, height);
		addLabel(label);
		notYetCreated = false;
		return label;
	}

	@SuppressWarnings("unused")
	private void buildIcon(BufferedImage image, int x, int y) {
		JLabel label = new JLabel(new ImageIcon(image));
		label.setBounds(x, y, image.getWidth(), image.getHeight());
		addLabel(label);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyReleased(KeyEvent e) {
		try{
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				boolean b;
				synchronized(this){
					b = inUse;
					inUse = true;
					done = true;
				}
				if(!b){
					fullUnBuildText();
					parent.closeMenu(instance);
					parent.startGame();
				}
			}else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
				boolean b;
				synchronized(this){
					b = inUse;
					inUse = true;
				}
				if(!b){
					for(int i = 0; i<textRes.length; i++){
						textRes[i].speedUp();
					}
					updater.speedUp();
					fastforwardLabel2.setText("x" + counter);
					fastforwardLabel2.setVisible(true);
					speedNormalLabel.setVisible(true);
					System.out.println("STUFF");
					
					counter = counter*2;
					inUse = false;
				}

			}else if(e.getKeyCode() == KeyEvent.VK_SPACE){
				boolean b;
				synchronized(this){
					b = inUse;
					inUse = true;
				}
				if(!b){
					updater.speedNormal();
					fastforwardLabel2.setVisible(false);
					speedNormalLabel.setVisible(false);

					counter = 2;
					
					for(int i = 0; i<textRes.length; i++){
						textRes[i].speedNormal();
					}
				}
				inUse = false;
			}else if(e.getKeyCode() == KeyEvent.VK_DOWN){
				boolean b;
				synchronized(this){
					b = inUse;
					inUse = true;
				}
				if(!b){
					updatePage();
					unBuildText();		
				}
				inUse = false;

			}
		}
		catch(Exception ex){
			//TODO placeholder
			ex.printStackTrace();
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}
	
	public synchronized void addLabel(JLabel label){
		if(done){
			return;
		}
		parent.add(label);
		labelList.add(label);
	}
	
	private synchronized void updateLabel(JLabel lelabel, String text) {
		if(done){
			return;
		}
		lastlabel.setText(text);
	}
}
