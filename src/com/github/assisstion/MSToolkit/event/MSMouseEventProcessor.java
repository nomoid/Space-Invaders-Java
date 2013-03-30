package com.github.assisstion.MSToolkit.event;

import java.util.Iterator;
import java.util.Set;

import com.github.assisstion.MSToolkit.MSException;
import com.github.assisstion.MSToolkit.concurrent.CollectionSynchronizer;

public class MSMouseEventProcessor implements Runnable{
	
	private MSMouseEvent e;
	private CollectionSynchronizer<Set<MSMouseListener>, MSMouseListener> mouseListenerSync;
	
	public MSMouseEventProcessor(MSMouseEvent event, CollectionSynchronizer<Set<MSMouseListener>, MSMouseListener> sync){
		e = event;
		mouseListenerSync = sync;
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