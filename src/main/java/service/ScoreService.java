package service;

import config.ScoreConfig;
import lombok.val;
import util.ScoreUtil;

public class ScoreService {

    /**
     * スコアをサーバに保存するメソッド
     *
     * @param score
     * @return
     */
    public static boolean postScore(int score) {
        return ScoreUtil.postScore(score);
    }

    /**
     * サーバからスコアを取得するためのメソッド
     *
     * @return
     */
    public static String getScore() {
        val getUrl = ScoreConfig.serverApiUrl;
        return ScoreUtil.httpGet(getUrl);
    }
}
