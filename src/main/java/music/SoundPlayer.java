package music;

import javax.sound.sampled.Clip;

public class SoundPlayer {

	public static Clip playStart() {
		return SoundControl.loop(SoundPlayer.class.getResource("../resource/sound/start.wav"));

	}

	public static Clip playMain() {
		return SoundControl.loop(SoundPlayer.class.getResource("../resource/sound/BGM.wav"));

	}

	public static Clip playResult() {
		return SoundControl.loop(SoundPlayer.class.getResource("../resource/sound/result.wav"));

	}

	public static Clip playDamage() {
		return SoundControl.play(SoundPlayer.class.getResource("../resource/sound/damage.wav"));

	}

}
