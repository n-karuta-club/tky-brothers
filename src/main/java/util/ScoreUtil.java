package util;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import lombok.val;

public class ScoreUtil {

    /**
     * スコアをサーバにPOSTするためのメソッド
     *
     * @param postUrl
     * @param JSON
     * @return
     */
    public static boolean httpPost(String postUrl, String JSON) {
        HttpURLConnection httpURLConnection = null;

        try {
            val url = new URL(postUrl);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("Accept-Language", "jp");
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
     * サーバからスコアを取得するためのメソッド
     *
     * @param getUrl
     * @return
     */
    public static String httpGet(String getUrl) {
        return null;
    }

}
