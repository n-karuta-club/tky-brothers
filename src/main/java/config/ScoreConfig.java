package config;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import lombok.val;

public class ScoreConfig {
    public static final int enemyBreakPoint = 100;

    public static String httpPost(String postUrl, String JSON) {
        HttpURLConnection httpURLConnection = null;
        val result = new StringBuffer();

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
                // 通信に成功した
                // テキストを取得する
                //  val inputStream = httpURLConnection.getInputStream();
                //  val encoding = httpURLConnection.getContentEncoding() == null ? httpURLConnection.getContentEncoding() : "UTF-8";
                //  val inputStreamReader = new InputStreamReader(inputStream, encoding);
                //  val bufferedReader = new BufferedReader(inputStreamReader);
                //  String line = null;
                //   // 1行ずつテキストを読み込む
                //  while ((line = bufferedReader.readLine()) != null) {
                //      result.append(line);
                //  }
                //  bufferedReader.close();
                //  inputStreamReader.close();
                //  inputStream.close();
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

        return result.toString();
    }
}