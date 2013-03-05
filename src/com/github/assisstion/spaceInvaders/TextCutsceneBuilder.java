package com.github.assisstion.spaceInvaders;

import javax.swing.JLabel;

public class TextCutsceneBuilder implements MenuBuilder {
	private TextCutsceneBuilder instance;
	private Menu parent;
	private JLabel label1;
	public boolean isOn;
	
	
	public TextCutsceneBuilder(){
		instance = this;
		isOn= true;
	}
	
	@Override
	public void build(Menu menu) {
		

	}

	@Override
	public void unBuild(Menu menu) {
		isOn=false;
	}
	
	
	public void updateText(){
		System.out.println("hiiii");
		
	}

}
