package com.github.assisstion.spaceInvaders.menu;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Properties;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.github.assisstion.spaceInvaders.MainCanvas;

public final class TexturePackDataHandler {

	public static JFileChooser f = new JFileChooser();
	
	private static Properties p = new Properties();
	public static File file = new File("testing/TESTING.txt");
	public static LinkedList<String> nameList = new LinkedList<String>();
	
	
	public static void defaults(){
		//Property: Key - Name,  Value - Brief description
		try {
			new File("testing").mkdir();
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		p.setProperty("WeirdPack", "A weird pack - Michael Man");
		p.setProperty("BobPack", "Pack of bobs - Bob the Builder");
		p.setProperty("Default Sprites", "The default sprites - Michael Man");
		save();
	}
	
	public static String getDescription(String key){
		return p.getProperty(key);	
	}
	
	public static void newPack(){
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
		        "Bob Images", "pages");
		f.setFileFilter(filter);
		int returnVal = f.showOpenDialog(MainCanvas.menu);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = f.getSelectedFile();
            System.out.println(file);
            //This is where a real application would open the file.
        } else {
           System.out.println("No file selected");
        }
	}
	
	public static void save(){
		FileOutputStream fop = null;
		try {
			fop = new FileOutputStream(file);
			p.store(fop, "Texture Pack Data");
			fop.flush();
		} catch (IOException error) {
			error.printStackTrace();
		} finally {
			try{
				fop.close();
			} catch (IOException e){
				e.printStackTrace();
			}
		}
	}
	
	public static void newEntry(String name, String description){
		p.setProperty(name,description);
		save();
	}
	
	public static void load(){
		
		FileInputStream fip = null;
		try {
			Properties n = new Properties();
			fip = new FileInputStream(file);
			n.load(fip);
			p = n;
			
			nameList = new LinkedList<String>();
			
			for (Object s: n.keySet()){
				nameList.add((String) s);
				System.out.println(s);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			try{
				fip.close();
			} catch (IOException e){
				e.printStackTrace();
			}
		}
	}
}
