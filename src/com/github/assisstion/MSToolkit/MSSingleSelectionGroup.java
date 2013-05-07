package com.github.assisstion.MSToolkit;

import java.util.Set;

public class MSSingleSelectionGroup<T extends MSSelectable> extends MSSelectionGroup<T>{
	
	protected T current;
	protected boolean alwaysSelected;
	
	public MSSingleSelectionGroup(){

	}
	
	public MSSingleSelectionGroup(Set<T> selectables, MSSelectable lead, boolean mustBeSelected){
		super(selectables);
		if(mustBeSelected){
			lead.select();
		}
	}
	
	@Override
	public boolean select(MSSelectable s){
		T selectable = null;
		for(T obj : selectables){
			if(obj.equals(s)){
				selectable = obj;
				break;
			}
		}
		if(selectable == null){
			throw new MSException("Selectable not in selection group");
		}
		if(current == null){
			boolean b = super.select(selectable);
			current = selectable;
			return b;
		}
		else if(!current.equals(selectable)){
			internalDeselect(current, true);
			boolean b = super.select(selectable);
			current = selectable;
			return b;
		}
		else{
			return false;
		}
	}
	
	@Override
	public boolean deselect(MSSelectable selectable){
		return internalDeselect(selectable, false);
	}
	
	protected boolean internalDeselect(MSSelectable selectable, boolean overrideAlwaysSelected){
		if(current == null || !current.equals(selectable)){
			return false;
		}
		else if(alwaysSelected && !overrideAlwaysSelected){
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
	
	public void alwaysSelected(MSSelectable lead){
		if(!currentlySelected()){
			select(lead);
		}
		alwaysSelected = true;
	}
	
	public void notAlwaysSelected(){
		alwaysSelected = false;
	}
	
	public boolean isAlwaysSelected(){
		return alwaysSelected;
	}
}
