package com.github.assisstion.MSToolkit.impl;

import java.awt.AWTEvent;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import com.github.assisstion.MSToolkit.MSComponent;
import com.github.assisstion.MSToolkit.MSContainer;
import com.github.assisstion.MSToolkit.MSGraphicContextual;
import com.github.assisstion.MSToolkit.MSGraphicalContext;
import com.github.assisstion.MSToolkit.concurrent.CollectionSynchronizer;
import com.github.assisstion.MSToolkit.event.MSMouseEventProcessor;
import com.github.assisstion.MSToolkit.event.MSMouseListener;
import com.github.assisstion.MSToolkit.style.MSStyle;
import com.github.assisstion.MSToolkit.style.MSStyleManager;


public abstract class MSAbstractCanvas extends Canvas implements MSContainer{

	private static final long serialVersionUID = -8804355855785678123L;
	private boolean on = true;
	private int delay = 16;
	private LinkedList<MSComponent> components;
	private Object listLock = new Object();
	private CollectionSynchronizer<LinkedList<MSComponent>, MSComponent> cs;
	protected MSStyle style;
	private Set<MSMouseListener> mouseListeners;
	private CollectionSynchronizer<Set<MSMouseListener>, MSMouseListener> mouseListenerSync;
	private AtomicInteger listeners = new AtomicInteger(0);
	
	public MSAbstractCanvas(){
		components = new LinkedList<MSComponent>();
		cs = new CollectionSynchronizer<LinkedList<MSComponent>, MSComponent>(components, listLock);
		Color foreground = getForeground();
		Color background = getBackground();
		if(foreground == null || background == null){
			style = MSStyleManager.getDefaultStyleSystem().getFrame();
		}
		else{
			Color frontBackground = getBackground().brighter();
			style = MSStyleManager.getStyle(0, 0, 0, 0, 
					MSConverter.fromColor(foreground), 
					MSConverter.fromColor(frontBackground), 
					MSConverter.fromColor(background), 
					MSHelper.getDefaultFont());
		}
		mouseListeners = new HashSet<MSMouseListener>();
		mouseListenerSync = new CollectionSynchronizer<Set<MSMouseListener>, MSMouseListener>(mouseListeners);
	}
	
	public boolean isOn(){
		return on;
	}
	
	public void setOn(boolean b){
		on = b;
	}

	public int getDelay(){
		return delay;
	}
	
	public void setDelay(int i){
		delay = i;
	}
	
	/*
	 * Subclasses of this should call super.paint(g);
	 */
	@Override
	public void paint(Graphics g){
		render(new MSGraphicImpl((Graphics2D)g));
	}
	
	@Override
	public void render(MSGraphicalContext g){
		render(g, getX(), getY());
	}
	
	/*
	 * Subclasses of this should call super.render(g);
	 */
	@Override
	public void render(MSGraphicalContext g, int x, int y){
		for(MSComponent component : components){
			if(component instanceof MSGraphicContextual){
				((MSGraphicContextual) component).setGraphicsContext(g);
			}
			component.render(g, component.getX() + x, component.getY() + y);
		}
	}
	
	/*
	 * This must be implemented in the subclass
	 * The subclass should return false if the class wants to
	 * stop the thread whenever an interrupted exception 
	 * is caught.
	 * The subclass should return true if the class
	 * wants to keep the thread going whenever an
	 * interrupted exception is caught.
	 */
	public abstract boolean interrupted(InterruptedException e);
	
	@Override
	public Collection<MSComponent> getComponentView(){
		synchronized(listLock){
			return Collections.unmodifiableCollection(components);
		}
	}
	
	public void addComponent(MSComponent component){
		if(component.addTo(this)){
			cs.add(component);
			requestFocus();
			validate();
			repaint();
		}
	}
	
	public void removeComponent(MSComponent component){
		if(component.removeFrom(this)){
			cs.remove(component);
			requestFocus();
			validate();
			repaint();
		}
	}
	
	@Override
	public boolean addTo(MSContainer container){
		return true;
	}
	
	@Override
	public boolean removeFrom(MSContainer container){
		return true;
	}
	
	@Override
	public MSStyle getStyle(){
		return style;
	}
	
	@Override
	public void setStyle(MSStyle style){
		this.style = style;
	}
	
	@Override
	public boolean addMSMouseListener(MSMouseListener listener){
		if(!mouseListeners.contains(listener)){
			listeners.incrementAndGet();
			enableEvents(AWTEvent.MOUSE_EVENT_MASK);
			mouseListenerSync.add(listener);
			return true;
		}
		return false;
	}
	
	@Override
	public boolean removeMSMouseListener(MSMouseListener listener){
		if(mouseListeners.contains(listener)){
			if(listeners.decrementAndGet() <= 0){
				disableEvents(AWTEvent.MOUSE_EVENT_MASK);
			}
			mouseListenerSync.remove(listener);
			return true;
		}
		return false;
	}
	
	@Override
	public void processMouseEvent(MouseEvent e){
		super.processMouseEvent(e);
		if(e.getID() == MouseEvent.MOUSE_ENTERED || e.getID() == MouseEvent.MOUSE_EXITED){
			return;
		}
		String message = MSConverter.getMessageFromEvent(e);
		new Thread(new MSMouseEventProcessor(MSConverter.fromMouseEvent(this, message, e), mouseListenerSync)).start();
	}
}
