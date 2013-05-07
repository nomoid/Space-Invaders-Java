package com.github.assisstion.MSToolkit;

import java.util.HashSet;
import java.util.Set;

public class MSSelectionGroup<T extends MSSelectable> implements MSSelectableGroup{
	
	protected Set<T> selectables;
	
	public MSSelectionGroup(){
		selectables = new HashSet<T>();
	}
	
	public MSSelectionGroup(Set<T> selectables){
		this.selectables = selectables;
	}

	public boolean select(MSSelectable selectable){
		if(selectables.contains(selectable)){
			return selectable.select();
		}
		else{
			throw new MSException("Selectable not in selection group");
		}
	}

	public boolean deselect(MSSelectable selectable){
		if(selectables.contains(selectable)){
			return selectable.deselect();
		}
		else{
			throw new MSException("Selectable not in selection group");
		}
	}
	

	public Set<T> getSelectables(){
		return selectables;
	}
	
	public boolean add(T selectable){
		return selectables.add(selectable);
	}
	
	public boolean remove(T selectable){
		return selectables.remove(selectable);
	}
	
	public boolean contains(T selectable){
		return selectables.contains(selectable);
	}
}
