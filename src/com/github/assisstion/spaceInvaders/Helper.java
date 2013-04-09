package com.github.assisstion.spaceInvaders;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

import com.github.assisstion.spaceInvaders.gameObject.Box;
import com.github.assisstion.spaceInvaders.gameObject.IrregularHitbox;
import com.github.assisstion.spaceInvaders.gameObject.Sprite;
import com.github.assisstion.spaceInvaders.menu.Looper;

public final class Helper{
	
	private static ScheduledExecutorService current;
	private static int serviceCounter;
	private static ThreadGroup soundStreamerThreads;
	
	static{
		soundStreamerThreads = new ThreadGroup("SoundStreaners");
	}
	
	//This class should not be instantiated
	private Helper(){
		
	}
	
	public static void renderSprite(Graphics2D g, Sprite s){
		//Draws the Sprite s to the Graphics2D g
		AffineTransform imageRender = new AffineTransform();
		imageRender.setToTranslation(s.x, s.y);
		imageRender.rotate(Math.toRadians(s.rotation));
		g.drawImage(s.getImage(), imageRender, MainCanvas.frame);
		//g.drawImage(s.getImage(), s.x, s.y, s.getImage().getWidth(), s.getImage().getHeight(), MainCanvas.frame);
	}
	
	public static void updateHitbox(Sprite s){
		if(s instanceof IrregularHitbox){
			IrregularHitbox ih = (IrregularHitbox) s;
			ih.updateHitbox();
		}
		else{
			s.hitBox.setPos(s.x, s.y, s.getImage().getWidth(),s.getImage().getHeight(), true);
		}
	}
	
	public static boolean overlapsIrregularHitbox(Box[] a, Box[] b){
		for(Box box : a){
			for(Box boxTwo : b){
				if(box.overLaps(boxTwo)){
					return true;
				}
			}
		}
		return false;
	}
	
	public static <E> int getIndex(E[] array, E object){
		for(int i = 0; i < array.length; i++){
			if(array[i].equals(object)){
				return i;
			}
		}
		return -1;
	}
	
	public static char[] createEmptyName(char c, int length) {
		char[] ca = new char[length];
		for (int i = 0; i < ca.length; i++) {
			ca[i] = c;
		}
		return ca;
	}
	
	public static void playSound(String location){
      try {
    	  if(!ResourceManager.getMuted()){
    		  Clip clip = ResourceManager.getAudioResource(location);
    		  ClipWrapper cw = new ClipWrapper(clip);
    		  clip.start();
    		  ResourceManager.addAudioPlayer(cw);
    	  }
       } catch (UnsupportedAudioFileException e) {
          e.printStackTrace();
       } catch (IOException e) {
          e.printStackTrace();
       } catch (LineUnavailableException e) {
          e.printStackTrace();
       }
    }
	
	//Plays a sound controlled by the Looper
	public static void streamSound(String location, Looper looper){
		SoundStreamer ss = new SoundStreamer(location, looper);
		new Thread(soundStreamerThreads, ss, "SoundStreamer-"+ ss.hashCode()).start();
	}
	
	//Plays a sound once, without Looper control
	public static void streamSound(String location){
		SoundStreamer ss = new SoundStreamer(location);
		new Thread(soundStreamerThreads, ss, "SoundStreamer-"+ ss.hashCode()).start();
	}
	
	private static class SoundStreamer implements Runnable{
		private String location;
		private Looper looper;
		
		public SoundStreamer(String location, Looper looper){
			this.location = location;
			this.looper = looper;
		}
		
		public SoundStreamer(String location){
			this.location = location;
			this.looper = new Looper(){
				private boolean paused;
				
				{
					ResourceManager.addAudioPlayer(this);
				}
				
				@Override
				public void ready(){
					ResourceManager.removeAudioPlayer(this);
				}

				@Override
				public boolean isOn(){
					return true;
				}

				@Override
				public void setPaused(boolean paused){
					this.paused = paused;
				}

				@Override
				public boolean isPaused(){
					return paused;
				}

				@Override
				public int compareTo(AudioPlayable ap){
					return new Integer(hashCode()).compareTo(ap.hashCode());
				}
			};
		}
		
		@Override
		public void run(){
			MainCanvas.audioLock.lock();
			try{
				try{
					SourceDataLine sdl = null;
				    try {
				    	int bufferSize = 4096;
				    	AudioFormat format = AudioSystem.getAudioFileFormat(new File(location)).getFormat();
						sdl = AudioSystem.getSourceDataLine(format);
						sdl.open(format, bufferSize);
				        BufferedInputStream bis = new BufferedInputStream(AudioSystem.getAudioInputStream(new File(location)));
				        byte[] buffer = new byte[bufferSize];
				        int b = 0;
				        sdl.start();
				        while((b = bis.read(buffer)) >= 0 && looper.isOn()){
				        	while(looper.isPaused()){
				        		MainCanvas.looperCondition.await();
				        	}
					        sdl.write(buffer, 0, b);
				        }
				        sdl.drain();
				        sdl.stop();
				    } 
					catch(IOException e){
						e.printStackTrace();
					}
					catch(UnsupportedAudioFileException e){
						e.printStackTrace();
					}
					catch(LineUnavailableException e){
						e.printStackTrace();
					}
				    finally{
				    	if(sdl != null){
				    		sdl.close();
				    	}
				    }
				    looper.ready();
				}
				catch(Exception ex){
					ex.printStackTrace();
				}
			}
			finally{
				MainCanvas.audioLock.unlock();
			}
		}
	}

	public static synchronized ScheduledExecutorService newService(int i){
		if(current != null){
			if(!current.isShutdown()){
				current.shutdown();
				serviceCounter++;
			}
		}
		current = Executors.newScheduledThreadPool(i);
		return current;
	}
	
	public static int serviceCounter(){
		return serviceCounter;
	}
}
