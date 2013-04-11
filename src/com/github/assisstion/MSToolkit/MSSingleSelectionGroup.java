package com.github.assisstion.MSToolkit;

import java.util.Set;

public class MSSingleSelectionGroup<T extends MSSelectable> extends MSSelectionGroup<T>{
	
	protected T current;
	
	public MSSingleSelectionGroup(){
		
	}
	
	public MSSingleSelectionGroup(Set<T> selectables){
		super(selectables);
	}
	
	@Override
	public boolean select(T selectable){
		if(current == null){
			boolean b = super.select(selectable);
			current = selectable;
			return b;
		}
		else if(!current.equals(selectable)){
			deselect(current);
			boolean b = super.select(selectable);
			current = selectable;
			return b;
		}
		else{
			return false;
		}
	}
	
	@Override
	public boolean deselect(T selectable){
		if(current == null || !current.equals(selectable)){
			return false;
		}
		else{
			boolean b = super.deselect(selectable);
			current = null;
			return b;
		}
	}
	
	public T getCurrentlySelected(){
		return current;
	}
	
	public boolean currentlySelected(){
		if(current != null){
			return true;
		}
		else{
			return false;
		}
	}
}
