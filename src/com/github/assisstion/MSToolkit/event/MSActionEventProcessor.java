package com.github.assisstion.MSToolkit.event;

import java.util.Iterator;
import java.util.Set;

import com.github.assisstion.MSToolkit.concurrent.CollectionSynchronizer;
import com.github.assisstion.MSToolkit.impl.MSHelper;

public class MSActionEventProcessor implements Runnable{
	
	private MSActionEvent e;
	private CollectionSynchronizer<Set<MSActionListener>, MSActionListener> listenerSync;

	public MSActionEventProcessor(MSActionEvent event, CollectionSynchronizer<Set<MSActionListener>, MSActionListener> sync){
		e = event;
		listenerSync = sync;
	}

	@Override
	public void run(){
		if(!e.isMeaningful() && !MSHelper.unmeaningfulActionEventsEnabled()){
			return;
		}
		Iterator<MSActionListener> listeners = listenerSync.iterator().get();
		while(listeners.hasNext()){
			MSActionListener listener = listeners.next();
			new Thread(new ActionListenerProcessor(listener)).start();
		}
	}
	
	private class ActionListenerProcessor implements Runnable{
		private MSActionListener listener;
		
		public ActionListenerProcessor(MSActionListener listener){
			this.listener = listener;
		}

		@Override
		public void run(){
			listener.action(e);
			if(e.isMeaningful()){
				listener.meaningfulAction(e);
			}
		}
		
	}
	
}
