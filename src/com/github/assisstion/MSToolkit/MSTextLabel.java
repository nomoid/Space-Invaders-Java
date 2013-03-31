package com.github.assisstion.MSToolkit;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import com.github.assisstion.MSToolkit.style.MSStyleManager;

public class MSTextLabel extends MSAbstractBoundedComponent{
	
	protected String text;
	private Graphics2D graphicsContext;
	
	protected MSTextLabel(){
		
	}
	
	public MSTextLabel(int x, int y, String text){
		super(x, y);
		style = MSStyleManager.getDefaultStyleSystem().getLabel();
		this.text = text;
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
		Font tempFont = g.getFont();
		g.setFont(getStyle().getFont());
		/*
		g.setColor(getStyle().getFrontBackground());
		g.fillRect(x, y, getWidth(), getHeight());
		*/
		g.setColor(getStyle().getForeground());
		g.drawString(text, x + getStyle().getPaddingLeft(), y + getStyle().getPaddingTop() + getStyle().getFont().getSize());
		g.setColor(tempColor);
		g.setFont(tempFont);
	}
	
	@Override
	public int getWidth(){
		return MSHelper.getTextWidth(getStyle().getFont(), text, graphicsContext) + getStyle().getPaddingLeft() + getStyle().getPaddingRight();
	}
	
	@Override
	public int getHeight(){
		return MSHelper.getTextHeight(getStyle().getFont(), text, graphicsContext) + getStyle().getPaddingTop() + getStyle().getPaddingBottom();
	}
	
	public void setGraphicsContext(Graphics2D graphicsContext){
		this.graphicsContext = graphicsContext;
	}
	
	public Graphics2D getGraphicsContext(){
		return graphicsContext;
	}
}
