package music;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

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
            clip = (Clip) AudioSystem.getLine(info);
            clip.open(audioInputStream);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
        return clip;
    }

    private static Clip get(URL url) {
        Clip clip = null;
        AudioInputStream audioInputStream;
        try {
            audioInputStream = AudioSystem.getAudioInputStream(url);
            AudioFormat audioFormat = audioInputStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, audioFormat);
            clip = (Clip) AudioSystem.getLine(info);
            clip.open(audioInputStream);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
        return clip;
    }

    /**
     *  ループさせて鳴らす
     * @param path
     */
    public static Clip loop(File path) {
        Clip bgm = get(path);
        bgm.loop(Clip.LOOP_CONTINUOUSLY);
        return bgm;
    }

    /**
     *  ループさせて鳴らす
     * @param path
     */
    public static Clip loop(URL url) {
        Clip bgm = get(url);
        bgm.loop(Clip.LOOP_CONTINUOUSLY);
        return bgm;
    }

    /**
     *  ループさせずに鳴らす
     * @param path
     */
    public static Clip play(File path) {
        Clip effect = get(path);
        effect.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return effect;
    }

    /**
     *  ループさせずに鳴らす
     * @param path
     */
    public static Clip play(URL url) {
        Clip effect = get(url);
        effect.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return effect;
    }
}