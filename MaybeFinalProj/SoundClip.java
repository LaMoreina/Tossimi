package MaybeFinalProj;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
//the textbook code wasn't running smoothly
//but this tutorial used a similar method,
//so the code below was based on this site:
//http://www3.ntu.edu.sg/home/ehchua/programming/java/J8c_PlayingSound.html


public class SoundClip {
	
	
	public SoundClip() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
		
	      try {
	         // Open an audio input stream.	   
	    	  File soundFile = new File("music/lazy_susan.wav");
	    	  AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
	        
	    	  // Get a sound clip resource.
	         Clip clip = AudioSystem.getClip();
	         
	         // Open audio clip and load samples from the audio input stream.
	         clip.open(audioIn);
	         clip.start();
	         clip.loop(Clip.LOOP_CONTINUOUSLY);
	         
	      } catch (UnsupportedAudioFileException e) {
	         e.printStackTrace();
	      } catch (IOException e) {
	         e.printStackTrace();
	      } catch (LineUnavailableException e) {
	         e.printStackTrace();
	      }
        
	}
}
