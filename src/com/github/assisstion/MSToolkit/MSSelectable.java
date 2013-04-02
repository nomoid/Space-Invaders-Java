package com.github.assisstion.MSToolkit;

public interface MSSelectable<T extends MSSelectable<T>>{
	boolean select();
	boolean deselect();
	MSSelectionGroup<T> getSelectionGroup();
	boolean isSelected();
}
