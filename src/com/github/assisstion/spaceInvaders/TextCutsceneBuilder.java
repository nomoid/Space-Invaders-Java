package com.github.assisstion.spaceInvaders;

import java.awt.Color;
import java.util.LinkedList;

import javax.swing.JLabel;

import static com.github.assisstion.spaceInvaders.CutsceneData.*;

public class TextCutsceneBuilder implements MenuBuilder {
	private TextCutsceneBuilder instance;
	private Menu parent;
	
	public boolean isOn;
	
	
	private LinkedList<JLabel> labelList = new LinkedList<JLabel>();
	private int pageNumber = 0;
	
	public TextCutsceneBuilder(){
		instance = this;
		isOn= true;
	}
	
	@Override
	public void build(Menu menu) {
		parent = menu;
		updateText(parent);
	}
	
	public void updateText(Menu menu){
		parent = menu;
		if(pageNumber >= CUTSCENE_1_PAGES.length){
			parent.closeMenu(instance);
			parent.startGame();
		}
		else{
			unBuildText();
			buildText(CUTSCENE_1_PAGES[pageNumber++]);
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
			constructLabel(string, 200, y);
			y += 100;
		}
		
	}

	private void constructLabel(String text, int x, int y){
		JLabel label = new JLabel(text);
		label.setForeground(Color.white);
		label.setBounds(x,y,960,200);
		parent.add(label);
		labelList.add(label);
	}
}
