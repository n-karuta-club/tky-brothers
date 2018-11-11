package service;

import config.ScoreConfig;
import lombok.val;

public class ScoreService {

    /**
     * スコアをサーバに保存するメソッド
     * @param score
     * @return
     */
    public static boolean postScore(int score) {
        val postUrl = "http://localhost:8080/api/score/";
        val JSON = "{\"point\": " + score + "}";
        System.out.println(JSON);
        val result = ScoreConfig.httpPost(postUrl, JSON);
        System.out.println("============");
        System.out.println(result);
        System.out.println("============");
        return true;
    }
}
