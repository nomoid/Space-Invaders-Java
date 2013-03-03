package com.github.assisstion.spaceInvaders;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Random;
import com.github.assisstion.spaceInvaders.MainCanvas;

public class Menu {

	
	public Menu() {
		 MainCanvas.frame.setBackground(Color.BLACK);
		 MainCanvas.frame.setPreferredSize(new Dimension(960, 760));
		 MainCanvas.frame.pack();
	}
	
	public void startGame() {
		MainCanvas.engine = new Engine();
		MainCanvas.frame.add(MainCanvas.engine);
		MainCanvas.frame.pack();
		MainCanvas.isOn = true;
		MainCanvas.rand = new Random();
		MainCanvas.engine.state = "ready";
		System.out.println("Engine starting");
	}
}
