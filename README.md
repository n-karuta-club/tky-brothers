# tky-brothers

## このゲームについて

アクションゲームのパロディです。
相手をファイアで倒してスコアを稼ぐゲームです。
１人でも２人でも遊ぶことができます。

## 操作方法

|動き|1Player|2Player|
|---|-------|-------|
|右移動|→|D|
|左移動|←|A|
|ジャンプ|↑|W|
|攻撃|↓|S|

## 利用技術

- Java8
- Gradle
- Lombok
- Swing

## Jarの作成方法

Eclipseのエクスポートから実行可能jarの作成を選択し、作成

## スコアサーバについて

スコアサーバのURL及びエンドポイントは`src/main/java/config/ScoreConfig.java`内の`public static final String serverApiUrl`にて変更してください。  
例) `http://localhost:8080/api/score`

## 参考URL

キー同時押し
http://www.geocities.jp/ntaka329/java/faq/ques_key.html

ジャンプ
https://bituse.info/game/22

フォントの変更
http://www1.bbiq.jp/takeharu/Applet15.html

APIドキュメント
https://docs.oracle.com/javase/jp/8/docs/api/
