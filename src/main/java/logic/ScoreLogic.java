package logic;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.codec.digest.DigestUtils;

import config.ScoreConfig;
import lombok.val;

public class ScoreLogic {

    /**
     * スコアをサーバにPOSTするためのメソッド
     *
     * @param postUrl
     * @param JSON
     * @param headerToken
     * @return
     */
    public static boolean httpPost(String postUrl, String JSON, String headerToken) {
        HttpURLConnection httpURLConnection = null;

        try {
            val url = new URL(postUrl);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("Accept-Language", "jp");
            httpURLConnection.setRequestProperty("token", headerToken);
            httpURLConnection.setRequestProperty("Content-Type", "application/JSON; charset=utf8");
            httpURLConnection.setRequestProperty("Content-Length", String.valueOf(JSON.length()));
            httpURLConnection.setDoOutput(true);
            val outputStreamWriter = new OutputStreamWriter(httpURLConnection.getOutputStream());
            outputStreamWriter.write(JSON);
            outputStreamWriter.flush();
            httpURLConnection.connect();

            val status = httpURLConnection.getResponseCode();
            // リクエストの送信に成功したか判定
            if (status == HttpURLConnection.HTTP_OK) {
                System.out.println("success!!");
                System.out.println(status);
                return true;
            } else {
                System.out.println("failed...");
                System.out.println(status);
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        }
        return false;
    }

    /**
     * md5化するメソッド
     *
     * @param JSON
     * @return
     */
    public static String hashMd5(String score) {
        String hash = DigestUtils.md5Hex(score);
        for (int i = 1; i < ScoreConfig.md5; i++) {
            hash = DigestUtils.md5Hex(score);
        }
        return hash;
    }
}
