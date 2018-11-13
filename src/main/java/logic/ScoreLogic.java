package logic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
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
            httpURLConnection.setConnectTimeout(100000);
            httpURLConnection.connect();

            val outputStreamWriter = new OutputStreamWriter(httpURLConnection.getOutputStream());
            outputStreamWriter.write(JSON);
            outputStreamWriter.flush();

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
     * スコアをサーバから取得するメソッド
     *
     * @param getUrl
     * @return
     */
    public static String httpGet(String getUrl) {
        HttpURLConnection httpURLConnection = null;
        // int responseCode = 0;
        String responseData = "";

        try {
            val url = new URL(getUrl);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(false);
            httpURLConnection.connect();

            // responseCode = httpURLConnection.getResponseCode();
            responseData = convertToString(httpURLConnection.getInputStream());

            val status = httpURLConnection.getResponseCode();
            // リクエストの送信に成功したか判定
            if (status == HttpURLConnection.HTTP_OK) {
                System.out.println("success!!");
                System.out.println(status);
            } else {
                System.out.println("failed...");
                System.out.println(status);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        }

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println(responseData);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>");

        return responseData;
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

    /**
     * GETリクエストで受け取ったデータを文字列型に変換するメソッド
     *
     * @param stream
     * @return
     * @throws UnsupportedEncodingException
     */
    private static String convertToString(InputStream stream) throws UnsupportedEncodingException {
        StringBuffer stringBuffer = new StringBuffer();
        String line = "";
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
            while ((line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line);
            }
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuffer.toString();
    }
}
