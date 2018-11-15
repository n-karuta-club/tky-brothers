package service;

import java.util.ArrayList;
import java.util.HashMap;

import util.ParserUtil;

public class ParserService {

    /**
     * String型のJSON形式のデータをHashMapにするメソッド
     *
     * @param json
     */
    public static ArrayList<HashMap<String, String>> jsonToHashMap(String json) {
        return ParserUtil.jsonToHashMap(json);
    }

}
