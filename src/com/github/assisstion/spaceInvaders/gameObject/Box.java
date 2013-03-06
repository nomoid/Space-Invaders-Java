package com.github.assisstion.spaceInvaders.gameObject;

public class Box{
	
	protected int x1;
	protected int x2;
	protected int y1;
	protected int y2;
	
	protected Box(){
		
	}
	
	/*
	 * Creates a box
	 * Automatically makes x1 <= x2 and y1 <= y2
	 */
	public Box(int x1, int x2, int y1, int y2){
		this.x1 = x1 <= x2 ? x1 : x2;
		this.x2 = x2 >= x1 ? x2 : x1;
		this.y1 = y1 <= y2 ? y1 : y2;
		this.y2 = y2 >= y1 ? y2 : y1;
	}
	
	/*
	 * Creates a box
	 * The variable a is not used, just to determine the difference
	 * between this constructor and the other constructor
	 */
	public Box(int x, int y, int width, int height, boolean a){
		x1 = x;
		x2 = x + Math.abs(width);
		y1 = y;
		y2 = y + Math.abs(height);
	}
	
	/*
	 * Checks if Box b is within this box
	 */
	public boolean within(Box b){
		if(pointIn(b.x1, b.y1) && pointIn(b.x2, b.y2)){
			return true;
		}
		return false;
	}
	
	/*
	 * Checks if point (x, y) is within this box
	 */
	public boolean pointIn(int x, int y){
		if(x >= this.x1() && x <= this.x2() && y >= this.y1() && y <= this.y2()){
			return true;
		}
		return false;
	}
	
	/*
	 * Checks if Box b overlaps this box
	 */
	public boolean overLaps(Box b){
		if(this.halfOverLaps(b) || b.halfOverLaps(this)){
			return true;
		}
		return false;
	}
	
	private boolean halfOverLaps(Box b){
		if(this.pointIn(b.x1(), b.y1()) || this.pointIn(b.x1(), b.y2()) || this.pointIn(b.x2(), b.y1()) || this.pointIn(b.x2(), b.y2())){
			return true;
		}
		return false;
	}
	
	/*
	 * Sets the position of this box
	 * Automatically makes x1 <= x2 and y1 <= y2
	 */
	public void setPos(int x1,int x2,int y1,int y2){
		this.x1 = x1 <= x2 ? x1 : x2;
		this.x2 = x2 >= x1 ? x2 : x1;
		this.y1 = y1 <= y2 ? y1 : y2;
		this.y2 = y2 >= y1 ? y2 : y1;
	}
	
	public void setPos(int x, int y, int width, int height, boolean a){
		x1 = x;
		x2 = x + Math.abs(width);
		y1 = y;
		y2 = y + Math.abs(height);
	}
	
	public int x1(){
		return x1;
	}
	public int x2(){
		return x2;
	}
	public int y1(){
		return y1;
	}
	public int y2(){
		return y2;
	}
	
	@Override
	public boolean equals(Object o){
		if(o instanceof Box){
			Box b = (Box) o;
			if(b.x1() == x1() && b.x2() == x2() && b.y1() == y1() && b.y2() == y2()){
				return true;
			}
		}
		return false;
		
	}
	
	@Override
	public int hashCode(){
		char[] x = {(char) this.x1, (char) this.x2, (char) this.y1, (char) this.y2};
		String s = String.copyValueOf(x);
		return s.hashCode();
	}
}
