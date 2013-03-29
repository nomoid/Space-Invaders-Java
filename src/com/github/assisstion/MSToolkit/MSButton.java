package com.github.assisstion.MSToolkit;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class MSButton extends MSAbstractBoundedComponent{
	
	
	private String text;
	private Graphics2D graphicsContext;
	private Font font;
	
	protected MSButton(){
		
	}
	
	public MSButton(String text, int x, int y, Graphics2D graphicsContext){
		super(x, y);
		this.text = text;
		this.graphicsContext = graphicsContext;
		font = MSHelper.getDefaultFont();
	}
	
	public MSButton(String text, int x, int y){
		this(text, x, y, null);
	}

	@Override
	public boolean addTo(MSContainer c){
		return true;
	}

	@Override
	public boolean removeFrom(MSContainer c){
		return true;
	}

	@Override
	public void render(Graphics g, int x, int y){
		Color tempColor = g.getColor();
		g.setColor(getStyle().getForeground());
		g.drawRect(x, y, getWidth(), getHeight());
		g.setColor(getStyle().getFrontBackground());
		g.fillRect(x, y, getWidth(), getHeight());
		g.setColor(getStyle().getForeground());
		g.drawString(text, x + getStyle().getPaddingLeft(), y + getStyle().getPaddingTop() + font.getSize());
		g.setColor(tempColor);
	}
	
	@Override
	public int getWidth(){
		return MSHelper.getTextWidth(font, text, graphicsContext) + getStyle().getPaddingLeft() + getStyle().getPaddingRight();
	}
	
	@Override
	public int getHeight(){
		return MSHelper.getTextHeight(font, text, graphicsContext) + getStyle().getPaddingTop() + getStyle().getPaddingBottom();
	}
	
	public void setGraphicsContext(Graphics2D graphicsContext){
		this.graphicsContext = graphicsContext;
	}
	
	public Graphics2D getGraphicsContext(){
		return graphicsContext;
	}
}
