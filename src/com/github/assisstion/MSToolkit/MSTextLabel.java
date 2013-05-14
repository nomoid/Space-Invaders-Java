package com.github.assisstion.MSToolkit;

import com.github.assisstion.MSToolkit.impl.MSHelper;
import com.github.assisstion.MSToolkit.style.MSMutableStyle;
import com.github.assisstion.MSToolkit.style.MSStyleManager;

public class MSTextLabel extends MSAbstractBoundedComponent implements MSGraphicContextual{
	
	protected String text;
	private MSGraphicalContext graphicsContext;
	private boolean filled;
	
	protected MSTextLabel(){
		
	}
	
	public MSTextLabel(int x, int y, String text){
		this(x, y, text, false);
	}
	
	public MSTextLabel(int x, int y, String text, boolean filled){
		super(x, y);
		style = MSStyleManager.getDefaultStyleSystem().getLabel();
		this.text = text;
		this.filled = filled;
	}
	
	public MSTextLabel(int x, int y, String text, MSColor color, MSFont font){
		this(x, y, text, false, color, MSStyleManager.getDefaultStyleSystem().getLabel().getFrontBackground(), font);
	}
	
	public MSTextLabel(int x, int y, String text, boolean filled, MSColor color, MSColor fillColor, MSFont font){
		super(x, y);
		MSMutableStyle mms = MSStyleManager.getMutableStyle(MSStyleManager.getDefaultStyleSystem().getLabel());
		mms.setForeground(color);
		mms.setFrontBackground(fillColor);
		mms.setFont(font);
		style = mms;
		this.text = text;
		this.filled = filled;
	}

	@Override
	public boolean addTo(MSContainer c){
		return true;
	}

	@Override
	public boolean removeFrom(MSContainer c){
		return true;
	}

	@Override
	public void render(MSGraphicalContext g, int x, int y){
		MSColor tempColor = g.getColor();
		MSFont tempFont = g.getFont();
		g.setFont(getStyle().getFont());
		if(filled){
			g.setColor(getStyle().getFrontBackground());
			g.fillRect(x, y, getWidth(), getHeight());
		}
		g.setColor(getStyle().getForeground());
		g.drawString(text, x + getStyle().getPaddingLeft(), y + getStyle().getPaddingTop() + getStyle().getFont().getSize());
		g.setColor(tempColor);
		g.setFont(tempFont);
	}
	
	@Override
	public int getWidth(){
		return MSHelper.getTextWidth(getStyle().getFont(), text, graphicsContext) + getStyle().getPaddingLeft() + getStyle().getPaddingRight();
	}
	
	@Override
	public int getHeight(){
		return MSHelper.getTextHeight(getStyle().getFont(), text, graphicsContext) + getStyle().getPaddingTop() + getStyle().getPaddingBottom();
	}
	
	public void setText(String text){
		this.text = text;
	}
	
	public String getText(){
		return text;
	}
	
	@Override
	public void setGraphicsContext(MSGraphicalContext graphicsContext){
		this.graphicsContext = graphicsContext;
	}
	
	@Override
	public MSGraphicalContext getGraphicsContext(){
		return graphicsContext;
	}
}
