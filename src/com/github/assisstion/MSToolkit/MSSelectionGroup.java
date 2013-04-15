package com.github.assisstion.MSToolkit;

import java.util.HashSet;
import java.util.Set;

public class MSSelectionGroup{
	
	protected Set<MSSelectable> selectables;
	
	public MSSelectionGroup(){
		selectables = new HashSet<MSSelectable>();
	}
	
	public MSSelectionGroup(Set<MSSelectable> selectables){
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
	
	public Set<MSSelectable> getSelectables(){
		return selectables;
	}

}
