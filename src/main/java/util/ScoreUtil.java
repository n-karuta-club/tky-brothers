package util;

import config.ScoreConfig;
import logic.ScoreLogic;
import lombok.val;

public class ScoreUtil {

    public static boolean postScore(int score) {
        val postUrl = ScoreConfig.serverApiUrl;
        val JSON = "{\"point\": " + score + "}";
        val headerToken = ScoreLogic.hashMd5(String.valueOf(score));
        System.out.println(JSON);
        val result = ScoreLogic.httpPost(postUrl, JSON, headerToken);
        return result;
    }

    /**
     * サーバからスコアを取得するためのメソッド
     *
     * @param getUrl
     * @return
     */
    public static String httpGet(String getUrl) {
        return null;
    }

}
