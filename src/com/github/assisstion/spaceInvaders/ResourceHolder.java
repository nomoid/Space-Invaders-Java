package com.github.assisstion.spaceInvaders;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.ConcurrentSkipListMap;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class ResourceHolder{
	private static ConcurrentSkipListMap<String, BufferedImage>
		loadedImageResources = new ConcurrentSkipListMap<String, BufferedImage>();
	private static ConcurrentSkipListMap<String, Clip>
		loadedAudioResources = new ConcurrentSkipListMap<String, Clip>();
	
	public static BufferedImage getImageResource(String location) throws IOException{
		BufferedImage image;
		if(loadedImageResources.containsKey(location)){
			image = loadedImageResources.get(location);
		}
		else{
			image = ImageIO.read(new FileInputStream(new File(location)));
			loadedImageResources.put(location, image);
		}
		return image;
	}
	
	public static Clip getAudioResource(String location) throws IOException, LineUnavailableException, UnsupportedAudioFileException{
		Clip audio;
		if(loadedImageResources.containsKey(location)){
			audio = loadedAudioResources.get(location);
		}
		else{
			audio = AudioSystem.getClip();
			audio.open(AudioSystem.getAudioInputStream(new File(location)));
			loadedAudioResources.put(location, audio);
		}
		return audio;
	}
	
}
