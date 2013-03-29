package com.github.assisstion.MSToolkit;

import java.awt.AWTEvent;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import com.github.assisstion.MSToolkit.concurrent.CollectionSynchronizer;
import com.github.assisstion.MSToolkit.events.MSAdvancedMouseListener;
import com.github.assisstion.MSToolkit.events.MSMouseEvent;
import com.github.assisstion.MSToolkit.events.MSMouseHandler;
import com.github.assisstion.MSToolkit.events.MSMouseListener;
import com.github.assisstion.MSToolkit.style.MSStyle;
import com.github.assisstion.MSToolkit.style.MSStyleManager;


public abstract class MSAbstractCanvas extends Canvas implements MSContainer, MSMouseHandler{

	private static final long serialVersionUID = -8804355855785678123L;
	private boolean on = true;
	private int delay = 16;
	private LinkedList<MSComponent> components;
	private Object listLock = new Object();
	private CollectionSynchronizer<MSComponent> cs;
	private MSComponent parent;
	protected MSStyle style;
	private Set<MSMouseListener> mouseListeners;
	private CollectionSynchronizer<MSMouseListener> mouseListenerSync;
	private AtomicInteger listeners;
	
	public MSAbstractCanvas(){
		components = new LinkedList<MSComponent>();
		cs = new CollectionSynchronizer<MSComponent>(components, listLock);
		Color foreground = getForeground();
		Color background = getBackground();
		if(foreground == null || background == null){
			style = MSStyleManager.getEmptyStyle();
		}
		else{
			Color frontBackground = getBackground().brighter();
			style = MSStyleManager.getStyle(0, 0, 0, 0, foreground, frontBackground, background);
		}
		mouseListeners = new HashSet<MSMouseListener>();
		mouseListenerSync = new CollectionSynchronizer<MSMouseListener>(mouseListeners);
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
		render(g);
	}
	
	@Override
	public void render(Graphics g){
		render(g, getX(), getY());
	}
	
	/*
	 * Subclasses of this should call super.render(g);
	 */
	@Override
	public void render(Graphics g, int x, int y){
		for(MSComponent component : components){
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
		component.addTo(this);
		cs.add(component);
		component.setComponentParent(this);
		requestFocus();
		validate();
		repaint();
	}
	
	public void removeComponent(MSComponent component){
		if(component.removeFrom(this)){
			cs.remove(component);
			component.setComponentParent(null);
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
	public void setComponentParent(MSComponent component){
		parent = component;
	}
	
	@Override
	public MSComponent getComponentParent(){
		return parent;	
	}
	
	@Override
	public MSStyle getStyle(){
		return style;
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
		new Thread(new MouseEventProcessor(new MSMouseEvent(this, e))).start();
	}
	
	private class MouseEventProcessor implements Runnable{
		
		private MSMouseEvent e;
		
		public MouseEventProcessor(MSMouseEvent event){
			e = event;
		}
		
		@Override
		public void run(){
			Iterator<MSMouseListener> listeners = mouseListenerSync.iterator().get();
			while(listeners.hasNext()){
				MSMouseListener listener = listeners.next();
				new Thread(new MouseListenerProcessor(listener)).start();
			}
		}
		
		private class MouseListenerProcessor implements Runnable{

			private MSMouseListener listener;
			
			public MouseListenerProcessor(MSMouseListener listener){
				this.listener = listener;
			}

			@Override
			public void run(){
				switch(e.getType()){
					case MOUSE_PRESSED:
						listener.mousePressed(e);
						break;
					case MOUSE_RELEASED:
						listener.mouseReleased(e);
						break;
					case MOUSE_CLICKED:
						listener.mouseClicked(e);
						break;
					case MOUSE_MOVED:
						if(listener instanceof MSAdvancedMouseListener){
							MSAdvancedMouseListener advListener = (MSAdvancedMouseListener) listener;
							advListener.mouseMoved(e);
						}
						break;
					case MOUSE_DRAGGED:
						if(listener instanceof MSAdvancedMouseListener){
							MSAdvancedMouseListener advListener = (MSAdvancedMouseListener) listener;
							advListener.mouseDragged(e);
						}
						break;
					case MOUSE_WHEEL:
						if(listener instanceof MSAdvancedMouseListener){
							MSAdvancedMouseListener advListener = (MSAdvancedMouseListener) listener;
							advListener.mouseWheel(e);
						}
						break;
					default:
						throw new MSException("Illegal Mouse Listener Type: " + e.getType().name());
				}
			}
			
		}
	}
}
