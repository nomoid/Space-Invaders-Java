package com.github.assisstion.spaceInvaders;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;

public class DataManager implements Serializable{

	private static final long serialVersionUID = 5056965394926216130L;
	//ID: 1
	private HashMap<String, Long> numberMap = new HashMap<String, Long>();
	//ID: 2
	private HashMap<String, String> stringMap = new HashMap<String, String>();
	//ID: 3
	private HashMap<String, Double> doubleMap = new HashMap<String, Double>();
	private HashMap<String, Integer> keys = new HashMap<String, Integer>();

	public DataManager(){
		
	}
	
	public boolean putNumber(String key, long value){
		Integer i = keys.get(key);
		if(i != null){
			if(i != 1){
				switch(i){
					case 2:
						stringMap.remove(key);
						break;
					case 3:
						doubleMap.remove(key);
						break;
					default:
						return false;
				}
			}
		}
		keys.put(key, 1);
		numberMap.put(key, value);
		return true;
	}
	
	public boolean putString(String key, String value){
		Integer i = keys.get(key);
		if(i != null){
			if(i != 2){
				switch(i){
					case 1:
						numberMap.remove(key);
						break;
					case 3:
						doubleMap.remove(key);
						break;
					default:
						return false;
				}
			}
		}
		keys.put(key, 2);
		stringMap.put(key, value);
		return true;
	}
	
	public boolean putDouble(String key, double value){
		Integer i = keys.get(key);
		if(i != null){
			if(i != 3){
				switch(i){
					case 1:
						numberMap.remove(key);
						break;
					case 2:
						stringMap.remove(key);
						break;
					default:
						return false;
				}
			}
		}
		keys.put(key, 3);
		doubleMap.put(key, value);
		return true;
	}
	
	public boolean removeKey(String key){
		Integer i = keys.get(key);
		if(i == null){
			return false;
		}
		switch(i){
			case 1:
				numberMap.remove(key);
				break;
			case 2:
				stringMap.remove(key);
				break;
			case 3:
				doubleMap.remove(key);
				break;
			default:
				return false;
		}
		keys.remove(key);
		return true;
	}
	
	
	public static void write(ObjectOutputStream oos, DataManager dm) throws IOException{
		oos.writeObject(dm);
	}
	
	public static DataManager read(ObjectInputStream ois) throws IOException, ClassNotFoundException{
		return (DataManager) ois.readObject();
	}
}
