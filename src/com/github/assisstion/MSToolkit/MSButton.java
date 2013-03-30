package com.github.assisstion.MSToolkit;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.HashSet;
import java.util.Set;

import com.github.assisstion.MSToolkit.concurrent.CollectionSynchronizer;
import com.github.assisstion.MSToolkit.event.MSMouseEvent;
import com.github.assisstion.MSToolkit.event.MSMouseHandler;
import com.github.assisstion.MSToolkit.event.MSMouseListener;
import com.github.assisstion.MSToolkit.style.MSStyleManager;

public class MSButton extends MSAbstractBoundedComponent implements MSMouseListener, MSMouseHandler{
	
	
	private String text;
	private Graphics2D graphicsContext;
	private Set<MSMouseListener> mouseListeners;
	private CollectionSynchronizer<Set<MSMouseListener>, MSMouseListener> mouseListenerSync;
	
	protected MSButton(){
		
	}
	
	public MSButton(String text, int x, int y, Graphics2D graphicsContext){
		super(x, y);
		style = MSStyleManager.getDefaultStyleSystem().getButton();
		this.text = text;
		this.graphicsContext = graphicsContext;
		mouseListeners = new HashSet<MSMouseListener>();
		mouseListenerSync = new CollectionSynchronizer<Set<MSMouseListener>, MSMouseListener>(mouseListeners);
	}
	
	public MSButton(String text, int x, int y){
		this(text, x, y, null);
	}

	@Override
	public boolean addTo(MSContainer c){
		c.addMSMouseListener(this);
		return true;
	}

	@Override
	public boolean removeFrom(MSContainer c){
		c.removeMSMouseListener(this);
		return true;
	}

	@Override
	public void render(Graphics g, int x, int y){
		Color tempColor = g.getColor();
		Font tempFont = g.getFont();
		g.setFont(getStyle().getFont());
		g.setColor(getStyle().getFrontBackground());
		g.fillRect(x, y, getWidth(), getHeight());
		g.setColor(getStyle().getForeground());
		g.drawRect(x, y, getWidth(), getHeight());
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

	@Override
	public void mousePressed(MSMouseEvent e){
		processEvent(e);
	}

	@Override
	public void mouseReleased(MSMouseEvent e){
		processEvent(e);
	}

	@Override
	public void mouseClicked(MSMouseEvent e){
		processEvent(e);
	}

	@Override
	public boolean addMSMouseListener(MSMouseListener listener){
		if(!mouseListeners.contains(listener)){
			mouseListenerSync.add(listener);
			return true;
		}
		return false;
	}
	
	@Override
	public boolean removeMSMouseListener(MSMouseListener listener){
		if(mouseListeners.contains(listener)){
			mouseListenerSync.remove(listener);
			return true;
		}
		return false;
	}
	
	public void processEvent(MSMouseEvent e){
		if(MSHelper.pointIn(getX(), getY(), getX()+getWidth(), getY()+getHeight(), e.getX(), e.getY())){
			new Thread(new MSMouseEventProcessor(new MSMouseEvent(this, e, e.getX()-x, e.getY()-y), mouseListenerSync)).start();
		}
	}
}
