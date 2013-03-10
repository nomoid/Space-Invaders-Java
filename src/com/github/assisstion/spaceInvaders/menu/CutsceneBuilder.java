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
	private JLabel lastlabel;
	private boolean notYetCreated = true;
	private LinkedList<JLabel> labelList = new LinkedList<JLabel>();
	private LinkedList<JLabel> tempLabelList = new LinkedList<JLabel>();
	public int pageNumber = 0;
	@SuppressWarnings("unused")
	private Sprite[][] sprites;
	private char[][] text;
	public double[] pageDelays;
	public boolean justFinishedLine = false;
	private int i = 0;
	private int x = 0;
	private String leText = "";
	private Font[] fonts;
	private Color[] colors;
	private Object finishLock = new Object();
	private boolean done = false;

	public CutsceneBuilder(Cutscene cutscene) {
		instance = this;
		isOn = true;
		this.sprites = cutscene.sprites;
		this.text = cutscene.pages;
		this.pageDelays = cutscene.delays;
		this.fonts = cutscene.fonts;
		this.colors = cutscene.colors;
	}

	@Override
	public void build(Menu menu) {
		parent = menu;
		parent.addKeyListener(this);
		update(parent);
		JLabel advancementLabel = new JLabel(ADVANCEMENTTEXT);
		advancementLabel.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		advancementLabel.setForeground(Color.WHITE);
		advancementLabel.setBounds(750, 650, 200, 100);
		parent.add(advancementLabel);
		labelList.add(advancementLabel);
	}

	public void update(Menu menu) {

		justFinishedLine = false;
		parent = menu;
		parent.requestFocus();
		if (pageNumber >= text.length) {
			parent.closeMenu(instance);
			parent.startGame();
		} else if (i < text[x].length) {
			leText += text[x][i];
			buildText(leText);
			// Sprite[] array = sprites[pageNumber];
			// for(Sprite s : array){
			// buildIcon(s.getImage(), s.x, s.y);
			// } 
			i++;

		} else {
			notYetCreated = true;
			y = 10;
			i = 0;
			x++;
			leText = "";
			pageNumber++;
			justFinishedLine = true;
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
			if (!label.getText().equals(ADVANCEMENTTEXT)) {
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
			lastlabel = constructLabel(colors[x], fonts[x], lastString, 25, y,
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

		parent.add(label);
		labelList.add(label);
		notYetCreated = false;
		return label;
	}

	private void updateLabel(JLabel lelabel, String text) {
		lastlabel.setText(text);
	}

	@SuppressWarnings("unused")
	private void buildIcon(BufferedImage image, int x, int y) {
		JLabel label = new JLabel(new ImageIcon(image));
		label.setBounds(x, y, image.getWidth(), image.getHeight());
		parent.add(label);
		labelList.add(label);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		try{
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				boolean b;
				synchronized(finishLock){
					b = done;
					done = true;
				}
				if(!b){
					fullUnBuildText();
					parent.closeMenu(instance);
					parent.startGame();
				}
			}
		}
		catch(Exception ex){
			//TODO placeholder
			ex.printStackTrace();
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}
}
