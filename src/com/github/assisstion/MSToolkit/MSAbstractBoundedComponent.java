package com.github.assisstion.MSToolkit;

public abstract class MSAbstractBoundedComponent extends MSAbstractComponent implements
		MSBoundedComponent{
	
	public static final int UNDEFINED = -1;
	
	protected int width;
	protected int height;

	protected MSAbstractBoundedComponent(){
		
	}
	
	public MSAbstractBoundedComponent(int x, int y, int width, int height){
		super(x, y);
		this.width = width;
		this.height = height;
	}
	
	public MSAbstractBoundedComponent(int x, int y){
		this(x, y, -1, -1);
	}
	
	
	
	@Override
	public int getWidth(){
		return width;

	}

	@Override
	public int getHeight(){
		return height;

	}

}
