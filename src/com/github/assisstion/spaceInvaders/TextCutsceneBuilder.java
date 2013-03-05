package com.github.assisstion.spaceInvaders;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class TextCutsceneBuilder implements MenuBuilder {
	private TextCutsceneBuilder instance;
	private Menu parent;
	private JLabel label1;
	private JLabel label2;
	private JLabel label3;
	private JLabel label4;
	private JLabel label5;
	private JLabel label6;
	
	
	public boolean isOn;
	//char counter
	private int i = 0;
	
	//string and label counter
	private int x = 0;
	
	private String leText = "";
	
	private static final String LINE1 = "Somewhere deep in Tofite-held space...";
	private static final char[] LINE1CHAR = LINE1.toCharArray();
	private static final String LINE2 = "Somewhere deep in Tofite-held space...";
	private static final char[] LINE2CHAR = LINE2.toCharArray();
	private static final String LINE3 = "Somewhere deep in Tofite-held space...";
	private static final char[] LINE3CHAR = LINE3.toCharArray();
	
	private static final char[][] COMPLETELIST = {LINE1CHAR,LINE2CHAR,LINE3CHAR};
	private JLabel[] labelList = new JLabel[6];
	
	public TextCutsceneBuilder(){
		instance = this;
		isOn= true;
	}
	
	@Override
	public void build(Menu menu) {
		int z=0;
		parent = menu;
		label1 = new JLabel("PLACEHOLDER");
		label1.setForeground(Color.white);
		label1.setBounds(200,100,960,200);
		parent.add(label1);
		labelList[z] = label1;
		z++;
		
		label2 = new JLabel("PLACEHOLDER");
		label2.setForeground(Color.white);
		label2.setBounds(200,200,960,200);
		parent.add(label2);
		labelList[z] = label2;
		z++;
		
		label3 = new JLabel("PLACEHOLDER");
		label3.setForeground(Color.white);
		label3.setBounds(200,300,960,200);
		parent.add(label3);
		labelList[z] = label3;
		z++;
		
		label4 = new JLabel("PLACEHOLDER");
		label4.setForeground(Color.white);
		label4.setBounds(200,400,960,200);
		parent.add(label4);
		labelList[z] = label4;
		z++;
		
		label5 = new JLabel("PLACEHOLDER");
		label5.setForeground(Color.white);
		label5.setBounds(200,500,960,200);
		parent.add(label5);
		labelList[z] = label5;
		z++;
		
		label6 = new JLabel("PLACEHOLDER");
		label6.setForeground(Color.white);
		label6.setBounds(200,600,960,200);
		parent.add(label6);
		labelList[z] = label6;
		z++;
	}

	@Override
	public void unBuild(Menu menu) {
		isOn=false;
		parent.remove(label1);
	}
	
	
	public void updateText(){
		System.out.println(COMPLETELIST[x].length);
		if (i<COMPLETELIST[x].length){
			leText += COMPLETELIST[x][i];
			System.out.println(labelList[x]);
			labelList[x].setText(leText);
			i++;
		} else {
			leText="";
			i=0;
			x++;
			
			if (x>COMPLETELIST.length){
				parent.closeMenu(instance);
				parent.startGame();
			}
			

		}
	}

}
