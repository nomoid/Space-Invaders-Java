package com.github.assisstion.MSToolkit;

import java.util.HashSet;
import java.util.Set;

public class MSBasicFont implements MSFont{

	private String name;
	private int size;
	private Set<MSFontModifier> modifiers;
	
	public MSBasicFont(String name, int size){
		this(name, size, new HashSet<MSFontModifier>());
	}

	public MSBasicFont(String name, int size, Set<MSFontModifier> modifiers){
		this.name = name;
		this.size = size;
		if(modifiers != null){
			this.modifiers = modifiers;
		}
		else{
			this.modifiers = new HashSet<MSFontModifier>();
		}
	}
	
	@Override
	public int getSize(){
		return size;
	}

	@Override
	public String getName(){
		return name;
	}

	@Override
	public Set<MSFontModifier> getModifiers(){
		return modifiers;
	}

}
