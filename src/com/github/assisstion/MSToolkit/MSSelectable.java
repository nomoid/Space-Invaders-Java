package com.github.assisstion.MSToolkit;

public interface MSSelectable{
	boolean select();
	boolean deselect();
	MSSelectionGroup<?> getSelectionGroup();
	boolean isSelected();
}
