package com.github.assisstion.spaceInvaders.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import com.github.assisstion.spaceInvaders.MainCanvas;
import com.github.assisstion.spaceInvaders.gameObject.Sprite;

public class CutsceneBuilder implements MenuBuilder, KeyListener{
	private CutsceneBuilder instance;
	private Menu parent;

	public boolean isOn;

	private JLabel lastlabel;
	private boolean notYetCreated = true;
	private LinkedList<JLabel> labelList = new LinkedList<JLabel>();
	public int pageNumber = 0;
	private Sprite[][] sprites;
	private char[][] text;
	public double[] pageDelays;
	public boolean justFinishedLine = false;;
	private int i = 0;
	private int x = 0;
	private String leText = "";
	private Font[] fonts;
	private Color[] colors;

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
		update(parent);
	}

	public void update(Menu menu) {
		justFinishedLine = false;
		parent = menu;
		if (pageNumber >= text.length) {
			parent.closeMenu(instance);
			parent.startGame();
		} else if (i < text[x].length) {
			leText += text[x][i];
			unBuildText();
			buildText(leText);
			// Sprite[] array = sprites[pageNumber];
			// for(Sprite s : array){
			// buildIcon(s.getImage(), s.x, s.y);
			// }
			i++;

		} else {
			justFinishedLine = true;
			i = 0;
			x++;
			leText = "";
			pageNumber++;
			notYetCreated = true;
		}
	}

	@Override
	public void unBuild(Menu menu) {
		parent = menu;
		isOn = false;
		unBuildText();
	}

	private void unBuildText() {
		for (JLabel label : labelList) {
			parent.remove(label);
		}
		labelList.clear();
		parent.revalidate();
		parent.repaint();
	}

	private void buildText(String text) {
		String[] labels = text.split("&");
		
		int y = 10;
		
		for (int i = 0; i < labels.length; i++) {
			constructLabel(colors[x], fonts[x], labels[i], 25, y, 960, 40);
			y += 40;
		}

	}

	private void constructLabel(Color tempcolor, Font tempfont, String text,
		int x, int y, int width, int height) {
		
		if (notYetCreated){
			JLabel label = new JLabel(text);
			label.setFont(tempfont);
			label.setForeground(tempcolor);
			label.setBounds(x, y, width, height);
			lastlabel=label;
			parent.add(label);
			labelList.add(label);
		} else {
			lastlabel.setText(text);
		}
	}

	private void buildIcon(BufferedImage image, int x, int y) {
		JLabel label = new JLabel(new ImageIcon(image));
		label.setBounds(x, y, image.getWidth(), image.getHeight());
		parent.add(label);
		labelList.add(label);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER){
			parent.closeMenu(instance);
			parent.startGame();
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
