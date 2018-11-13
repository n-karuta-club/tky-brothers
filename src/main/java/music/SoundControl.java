package music;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;

public class SoundControl {
	
	// 音楽を鳴らす準備
	public static Clip Get(File path) {
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
	
	// ループさせて鳴らす
	public static void Loop(Clip clip) throws Exception {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
		Thread.sleep(60000);
	}
	
	// ループさせずに鳴らす
	public static void Play(Clip clip) throws InterruptedException {
		// TODO Auto-generated method stub
		clip.start();
		Thread.sleep(3000);
	}
	
	// 止める
	public static void Stop(Clip clip) {
		clip.stop();
	}
}