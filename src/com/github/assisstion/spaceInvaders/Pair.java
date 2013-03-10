package com.github.assisstion.spaceInvaders;

import java.io.Serializable;

public class Pair<T, S> implements Serializable{
	
	private static final long serialVersionUID = -8411360706274767599L;
	
	protected T valueOne;
	protected S valueTwo;
	
	protected Pair(){
		
	}
	
	public Pair(T valueOne, S valueTwo){
		this.valueOne = valueOne;
		this.valueTwo = valueTwo;
	}
	
	public T getValueOne(){
		return valueOne;
	}
	
	public S getValueTwo(){
		return valueTwo;
	}
	
	@Override
	public String toString(){
		String valueOneString = valueOne == null ? "null" : valueOne.toString();
		String valueTwoString = valueTwo == null ? "null" : valueTwo.toString();
		return "Pair: [" + valueOneString + "," + valueTwoString + "]";
	}
	
	@Override
	public boolean equals(Object o){
		if(o instanceof Pair){
			@SuppressWarnings("rawtypes")
			//This is necessary, as there is no way to detect types at runtime
			Pair p = (Pair) o;
			if(getValueOne().equals(p.getValueOne()) && getValueTwo().equals(p.getValueTwo())){
				return true;
			}
		}
		return false;
	}

	@Override
	public int hashCode(){
		return getValueOne().hashCode() ^ getValueTwo().hashCode();
	}
}
