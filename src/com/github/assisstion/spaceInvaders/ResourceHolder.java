package com.github.assisstion.spaceInvaders;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.ConcurrentSkipListMap;

import javax.imageio.ImageIO;

public class ResourceHolder{
	private static ConcurrentSkipListMap<String, BufferedImage>
		loadedResources = new ConcurrentSkipListMap<String, BufferedImage>();
	
	public static BufferedImage getImageResource(String location) throws IOException{
		BufferedImage image;
		if(loadedResources.containsKey(location)){
			image = loadedResources.get(location);
		}
		else{
			image = ImageIO.read(new FileInputStream(new File(location)));
			loadedResources.put(location, image);
		}
		return image;
	}
}
