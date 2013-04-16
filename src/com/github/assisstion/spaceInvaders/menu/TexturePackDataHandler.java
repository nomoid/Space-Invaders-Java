package com.github.assisstion.spaceInvaders.menu;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Properties;

public final class TexturePackDataHandler {

	private static Properties p = new Properties();
	private static File file = new File("resources/TESTING.txt");
	public static LinkedList<String> nameList = new LinkedList<String>();
	
	
	public static void save(){
		//Property: Key - Name,  Value - Brief description
		p.setProperty("WeirdPack", "A weird pack - Michael Man");
		p.setProperty("BobPack", "Pack of bobs - Bob the Builder");
		p.setProperty("Default Sprites", "The default sprites - Michael Man");
		
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
	
	public static String getDescription(String key){
		return p.getProperty(key);	
	}
	
	public static void load(){
		
		FileInputStream fip = null;
		try {
			Properties n = new Properties();
			fip = new FileInputStream(file);
			n.load(fip);
		
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
