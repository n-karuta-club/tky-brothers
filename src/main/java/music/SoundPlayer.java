package music;

import javax.sound.sampled.Clip;

public class SoundPlayer {

    /**
     * スタート画面のBGM
     *
     * @return
     */
    public static Clip playStart() {
        return SoundControl.loop(SoundPlayer.class.getResource("bgm/start.wav"));
    }

    /**
     * Main画面のBGM
     *
     * @return
     */
    public static Clip playMain() {
        return SoundControl.loop(SoundPlayer.class.getResource("bgm/BGM.wav"));
    }

    /**
     * リザルト画面のBGM
     *
     * @return
     */
    public static Clip playResult() {
        return SoundControl.loop(SoundPlayer.class.getResource("bgm/result.wav"));
    }

    /**
     * ダメージ音
     *
     * @return
     */
    public static Clip playDamage() {
        return SoundControl.play(SoundPlayer.class.getResource("bgm/damage.wav"));
    }
}
