package com.github.assisstion.spaceInvaders.menu;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import com.github.assisstion.spaceInvaders.gameObject.Sprite;

public class CutsceneBuilder implements MenuBuilder {
	private CutsceneBuilder instance;
	private Menu parent;
	
	public boolean isOn;
	
	
	private LinkedList<JLabel> labelList = new LinkedList<JLabel>();
	public int pageNumber = 0;
	private Sprite[][] sprites;
	private String[] text;
	public int[] delays;
	
	public CutsceneBuilder(Cutscene cutscene){
		instance = this;
		isOn= true;
		this.sprites = cutscene.sprites;
		this.text = cutscene.pages;
		this.delays = cutscene.delays;
	}
	
	@Override
	public void build(Menu menu) {
		parent = menu;
		update(parent);
	}
	
	public void update(Menu menu){
		parent = menu;
		if(pageNumber >= text.length){
			parent.closeMenu(instance);
			parent.startGame();
		}
		else{
			unBuildText();
			buildText(text[pageNumber]);
			Sprite[] array = sprites[pageNumber];
			for(Sprite s : array){
				buildIcon(s.getImage(), s.x, s.y);
			}
			pageNumber++;
		}
	}

	@Override
	public void unBuild(Menu menu) {
		parent = menu;
		isOn=false;
		unBuildText();
	}
	
	private void unBuildText(){
		for(JLabel label : labelList){
			parent.remove(label);
		}
		labelList.clear();
		parent.revalidate();
		parent.repaint();
	}
	
	private void buildText(String text){
		String[] labels = text.split("\n");
		int y = 0;
		for(String string : labels){
			constructLabel(string, 200, y, 960, 200);
			y += 100;
		}
		
	}

	private void constructLabel(String text, int x, int y, int width, int height){
		JLabel label = new JLabel(text);
		label.setForeground(Color.white);
		label.setBounds(x,y,width,height);
		parent.add(label);
		labelList.add(label);
	}
	
	private void buildIcon(BufferedImage image, int x, int y){
		JLabel label = new JLabel(new ImageIcon(image));
		label.setBounds(x,y,image.getWidth(),image.getHeight());
		parent.add(label);
		labelList.add(label);
	}
}
