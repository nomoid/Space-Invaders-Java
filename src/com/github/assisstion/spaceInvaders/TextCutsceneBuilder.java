package com.github.assisstion.spaceInvaders;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class TextCutsceneBuilder implements MenuBuilder {
	private TextCutsceneBuilder instance;
	private Menu parent;
	private JLabel label1;
	public boolean isOn;
	private int i = 0;
	private String leText = "";
	private String[] textToPrint = {"y","o","u"};
	
	
	public TextCutsceneBuilder(){
		instance = this;
		isOn= true;
	}
	
	@Override
	public void build(Menu menu) {
		parent = menu;
		label1 = new JLabel("PLACEHOLDER");
		label1.setForeground(Color.white);
		label1.setBounds(960/2,100,100,100);
		parent.add(label1);
		
	}

	@Override
	public void unBuild(Menu menu) {
		isOn=false;
		parent.remove(label1);
	}
	
	
	public void updateText(){
		if (i<3){
			leText += textToPrint[i];
			label1.setText(leText);
			i++;
		} else {
			parent.closeMenu(instance);
			parent.startGame();
		}
	}

}
