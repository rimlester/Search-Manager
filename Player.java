
import javax.sound.sampled.*;
import java.io.*;
import java.applet.*;

public class Player {
	private double currentTime = 0;
	private AudioClip mu;
	public Player(File f){
		try{
			mu = Applet.newAudioClip(f.toURL());
		}catch(Exception e){
		System.out.println("Invalid File Type");
		}
	}
	
	
	public void pause(){
		try{
			mu.wait();
		}catch(Exception e){}
		
	}
	public void play(){
		mu.play();
	}
	public void jumpTo(double time){
		
	}
	public void stop(){
		mu.stop();
	}
	
}
