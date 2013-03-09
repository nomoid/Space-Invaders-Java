package com.github.assisstion.spaceInvaders;

public class Pair<T, S> {
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
		return "Pair: [" + valueOne.toString() + "," + valueTwo.toString() + "]";
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
