package com.github.assisstion.spaceInvaders;

import javax.swing.JButton;

public class PauseMenuBuilder implements MenuBuilder{

	private PauseMenuBuilder instance;
	private Menu parent;
	
	private JButton helplabel;

	public PauseMenuBuilder(){
		instance = this;
	}
	
	@Override
	public void build(Menu menu){
		parent = menu;
		helplabel = new JButton("hi");
		helplabel.setBounds(960/2,100,100,100);
		parent.add(helplabel);
	}

	@Override
	public void unBuild(Menu menu){
		parent.remove(helplabel);
	}

}
