package com.github.assisstion.MSToolkit;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

import com.github.assisstion.MSToolkit.concurrent.CollectionSynchronizer;
import com.github.assisstion.MSToolkit.event.MSActionEvent;
import com.github.assisstion.MSToolkit.event.MSActionEventProcessor;
import com.github.assisstion.MSToolkit.event.MSActionHandler;
import com.github.assisstion.MSToolkit.event.MSActionListener;
import com.github.assisstion.MSToolkit.event.MSEvent;
import com.github.assisstion.MSToolkit.event.MSMouseEvent;
import com.github.assisstion.MSToolkit.event.MSMouseEventProcessor;
import com.github.assisstion.MSToolkit.event.MSMouseHandler;
import com.github.assisstion.MSToolkit.event.MSMouseListener;
import com.github.assisstion.MSToolkit.impl.MSHelper;

public class MSSelectableSprite extends MSSprite 
		implements MSSelectable, MSMouseListener, MSMouseHandler, MSActionHandler{

	private MSSelectionGroup<MSSelectableSprite> group;
	private Set<MSMouseListener> mouseListeners;
	protected CollectionSynchronizer
			<Set<MSMouseListener>, MSMouseListener> mouseListenerSync;
	private AtomicBoolean selected = new AtomicBoolean(false);
	private Set<MSActionListener> actionListeners;
	protected CollectionSynchronizer
			<Set<MSActionListener>, MSActionListener> actionListenerSync;
	
	protected MSSelectableSprite(){
		
	}
	
	public MSSelectableSprite(MSSelectionGroup<MSSelectableSprite> group, int x, int y, String link) 
			throws IOException{
		this(group, x, y, new MSImage(MSHelper.getImage(link)));
	}
	
	public MSSelectableSprite(MSSelectionGroup<MSSelectableSprite> group, int x, int y, MSImage image){
		super(x, y, image);
		this.group = group;
		group.getSelectables().add(this);
		mouseListeners = new HashSet<MSMouseListener>();
		mouseListenerSync = new CollectionSynchronizer<Set<MSMouseListener>, 
				MSMouseListener>(mouseListeners);
		actionListeners = new HashSet<MSActionListener>();
		actionListenerSync = new CollectionSynchronizer<Set<MSActionListener>, 
				MSActionListener>(actionListeners);
	}
	
	@Override
	public void render(MSGraphicalContext g, int x, int y){
		super.render(g, x, y);
		if(selected.get()){
			MSColor tempColor = g.getColor();
			g.setColor(getStyle().getForeground());
			g.drawRect(x - getStyle().getPaddingLeft(), 
					y - getStyle().getPaddingTop(), 
					getWidth() + getStyle().getPaddingRight(), 
					getHeight() + getStyle().getPaddingBottom());
			g.setColor(tempColor);
		}
	}
	
	@Override
	public void render(MSGraphicalContext g){
		render(g, x, y);
	}
	
	@Override
	public boolean addMSActionListener(MSActionListener listener){
		if(!actionListenerSync.contains(listener).get()){
			actionListenerSync.add(listener);
			return true;
		}
		return false;
	}

	@Override
	public boolean removeMSActionListener(MSActionListener listener){
		if(actionListenerSync.contains(listener).get()){
			actionListenerSync.remove(listener);
			return true;
		}
		return false;
	}

	@Override
	public boolean addMSMouseListener(MSMouseListener listener){
		if(!mouseListenerSync.contains(listener).get()){
			mouseListenerSync.add(listener);
			return true;
		}
		return false;
	}

	@Override
	public boolean removeMSMouseListener(MSMouseListener listener){
		if(mouseListenerSync.contains(listener).get()){
			mouseListenerSync.remove(listener);
			return true;
		}
		return false;
	}
	
	private void processMouseEvent(MSMouseEvent e){
		if(MSHelper.pointIn(getX(), getY(), getX()+getWidth(), getY()+getHeight(), e.getX(), e.getY())){
			String message = e.getMessage();
			new Thread(new MSMouseEventProcessor(new MSMouseEvent(this, message, e, e.getX()-x, e.getY()-y), mouseListenerSync)).start();
		}
		
	}

	private void processActionEvent(MSActionEvent e){
		new Thread(new MSActionEventProcessor(e, actionListenerSync)).start();
	}


	@Override
	public void mousePressed(MSMouseEvent e){
		if(MSHelper.pointIn(getX(), getY(), getX()+getWidth(), getY()+getHeight(), e.getX(), e.getY())){
			processActionEvent(new MSActionEvent(this, e, false));
			processMouseEvent(e);
		}
	}

	@Override
	public void mouseReleased(MSMouseEvent e){
		if(MSHelper.pointIn(getX(), getY(), getX()+getWidth(), getY()+getHeight(), e.getX(), e.getY())){
			processActionEvent(new MSActionEvent(this, e, false));
			processMouseEvent(e);
		}
	}

	@Override
	public void mouseClicked(MSMouseEvent e){
		if(MSHelper.pointIn(getX(), getY(), getX()+getWidth(), getY()+getHeight(), e.getX(), e.getY())){
			if(isSelected()){
				getSelectionGroup().deselect(this);
			}
			else{
				getSelectionGroup().select(this);
			}
			processActionEvent(new MSActionEvent(this, e, false));
			processMouseEvent(e);
		}
	}

	@Override
	public boolean select(){
		selected.set(true);
		processActionEvent(new MSActionEvent(this, new MSEvent(this, "Selected"), true));
		return true;
	}

	@Override
	public boolean deselect(){
		selected.set(false);
		processActionEvent(new MSActionEvent(this, new MSEvent(this, "Deselected"), true));
		return true;
	}

	@Override
	public MSSelectionGroup<MSSelectableSprite> getSelectionGroup(){
		return group;
	}

	@Override
	public boolean isSelected(){
		return selected.get();
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

}
