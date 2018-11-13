package music;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;

public class SoundControl {
	
	/**
	 *  音楽を鳴らす準備
	 * @param path
	 * @return
	 */
	private static Clip get(File path) {
		Clip clip = null;
		AudioInputStream audioInputStream;
		try {
			audioInputStream = AudioSystem.getAudioInputStream(path);
			AudioFormat audioFormat = audioInputStream.getFormat();
			DataLine.Info info = new DataLine.Info(Clip.class, audioFormat);
			clip = (Clip)AudioSystem.getLine(info);
			clip.open(audioInputStream);
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
        return clip;
    }
	
	/**
	 *  ループさせて鳴らす
	 * @param path
	 */
	public static void loop(File path) {
		Clip bgm = get(path);
		bgm.loop(Clip.LOOP_CONTINUOUSLY);
		try {
			Thread.sleep(60000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 *  ループさせずに鳴らす
	 * @param path
	 */
	public static void play(File path) {
		Clip effect = get(path);
		effect.start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 *  止める
	 * @param path
	 */
	public static void Stop(File path) {
		Clip clip = get(path);
		clip.stop();
	}
}