package com.github.assisstion.MSToolkit;

import java.util.HashSet;
import java.util.Set;

public class MSSelectionGroup<T extends MSSelectable>{
	
	protected Set<T> selectables;
	
	public MSSelectionGroup(){
		selectables = new HashSet<T>();
	}
	
	public MSSelectionGroup(Set<T> selectables){
		this.selectables = selectables;
	}

	public boolean select(T selectable){
		if(selectables.contains(selectable)){
			return selectable.select();
		}
		else{
			throw new MSException("Selectable not in selection group");
		}
	}

	public boolean deselect(T selectable){
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

}
