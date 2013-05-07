package com.github.assisstion.spaceInvaders.menu.canvas;

import java.io.IOException;
import java.util.Collections;

import com.github.assisstion.MSToolkit.MSSelectableSprite;
import com.github.assisstion.MSToolkit.MSSingleSelectionGroup;
import com.github.assisstion.spaceInvaders.gameObject.LinkHolder;

public class UpgradeIcon extends MSSelectableSprite{
	
	private static final String[] IMAGE_LOCATIONS = Collections.nCopies(UpgradeType.values().length, LinkHolder.player).toArray(new String[0]);

	private UpgradeType type;
	
	public UpgradeIcon(UpgradeType type,
			MSSingleSelectionGroup<? extends UpgradeIcon> group, int x, int y) throws IOException{
		super(group, x, y, IMAGE_LOCATIONS[type.ordinal()]);
		this.type = type;
	}
	
	public UpgradeType getType(){
		return type;
	}
	
	public String getName(){
		return type.displayName();
	}
}
