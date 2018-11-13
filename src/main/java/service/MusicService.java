package service;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;
import javafx.scene.media.AudioClip;

public class MusicService {
	
	public static Clip bgm(File path) {
		Clip clip = null;
		AudioInputStream audioInputStream;
		try {
			audioInputStream = AudioSystem.getAudioInputStream(path);
			AudioFormat audioFormat = audioInputStream.getFormat();
			DataLine.Info info = new DataLine.Info(Clip.class, audioFormat);
			clip = (Clip)AudioSystem.getLine(info);
			clip.open(audioInputStream);
			return clip;
		}
		catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		catch (LineUnavailableException e) {
			e.printStackTrace();
		}
        return null;
    }
	
	public static void loopbgm(Clip clip) throws Exception {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
		Thread.sleep(60000);
	}
		
	public static AudioClip getsound () {
		return new AudioClip(System.getProperty("user.dir") + "\\src\\bgm\\damage.wav");
	}
	public static void playsound(AudioClip audio) {
		audio.play();
	}
}
