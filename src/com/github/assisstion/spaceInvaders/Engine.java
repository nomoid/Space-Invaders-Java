package com.github.assisstion.spaceInvaders;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Engine implements KeyListener{
	
	private static final Font FONT_SMALL = new Font("Times New Roman", Font.BOLD, 20);
	
	private static final Font FONT_LARGE = new Font("Times New Roman", Font.BOLD, 40);
		
	private Canvas canvas;
	
	public Engine(Canvas canvas) {
		this.canvas = canvas;
		canvas.addKeyListener(this);
	}

	public void startGame() {
		
		canvas.createBufferStrategy(2);
		Graphics2D g = (Graphics2D)canvas.getBufferStrategy().getDrawGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(Color.WHITE);
		String message = new String("Press Enter To Start");
		g.setFont(FONT_SMALL);
		g.drawString(message, canvas.getWidth() / 2 - (g.getFontMetrics().stringWidth(message) / 2), 350);
		
	}

	@Override
	public void keyPressed(KeyEvent e){
		
	}

	@Override
	public void keyReleased(KeyEvent e){
		
	}

	@Override
	public void keyTyped(KeyEvent e){
		
	}
	
	
}

