package com.github.assisstion.spaceInvaders.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.github.assisstion.spaceInvaders.MainCanvas;
import com.github.assisstion.spaceInvaders.ResourceManager;

@ReturnableMenu
public class HelpMenuBuilder implements MenuBuilder {
	private HelpMenuBuilder instance;
	private Menu parent;
	private JLabel helplabel;
	private JButton returnButton;

	private JTabbedPane tabbedPane;
	
	public HelpMenuBuilder(){
		instance = this;
	}
	
	@Override
	public void build(Menu menu) {
		
		parent=menu;
		
		helplabel = new JLabel("Help");
		helplabel.setFont(new Font("Copperplate", Font.BOLD, 95));
		helplabel.setForeground(Color.WHITE);
		Menu.centerLabel(helplabel, 0);
		

		
		returnButton = new JButton(new ImageIcon(getReturnImage()));
		returnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Button pressed");
				parent.closeMenu(instance);
				parent.addMenuBuilder(new MainMenuBuilder());
			}
		});
		returnButton.setBounds(0,0,162,94);
		
		
		tabbedPane = new JTabbedPane();
		tabbedPane.setBounds(50, 100, MainCanvas.FRAME_WIDTH - 100, MainCanvas.FRAME_HEIGHT - 125);
		System.out.println(tabbedPane.getWidth() + " " + tabbedPane.getHeight());
		
		JLabel label = new JLabel("YO SUP HOMIE BRO");
		label.setForeground(Color.WHITE);
		
		tabbedPane.insertTab("General", null, label, "General", 0);
		
		JLabel label4 = new JLabel("YO SasdfasdfasdfadfUP HOMIE BRO");
		label4.setForeground(Color.WHITE);
		
		tabbedPane.insertTab("Controls", null, label4, "General", 1);
		
		JLabel label2 = new JLabel("NI HAO HOMIE BRO");
		label2.setForeground(Color.WHITE);
		
		
		tabbedPane.insertTab("Boosts", null, label2, "Boosts", 2);
		
		JLabel label3 = new JLabel("YOYOYOYOY");
		JPanel panel1 = new JPanel();
		panel1.setLayout( null );
		panel1.add(label3);
		
		label3.setForeground(Color.WHITE);
		tabbedPane.insertTab("Enemies", null, panel1, "Boosts", 3);
		
		
		
		parent.add(tabbedPane);	
		parent.add(returnButton);
		parent.add(helplabel);
	}

	@Override
	public void unBuild(Menu menu) {
		parent.remove(helplabel);
		parent.remove(returnButton);
		parent.remove(tabbedPane);
	}

	private BufferedImage getReturnImage(){
		BufferedImage returnIcon = null;
		try {
			returnIcon = ResourceManager.getImageResource("resources/returnButton.png");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Error loading image!");
		}
		return returnIcon;
	}
}
