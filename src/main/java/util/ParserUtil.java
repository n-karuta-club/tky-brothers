package util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.val;

public class ParserUtil {

    public static ArrayList<HashMap<String, String>> jsonToHashMap(String json) {
        ArrayList<HashMap<String, String>> hash = new ArrayList<>();

        json = json.replace("[", "");
        json = json.replace("]", "");
        json = json.replace("},{", "} {");
        val list = json.split(" ");

        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        ObjectMapper mapper = new ObjectMapper();

        for (String value : list) {
            try {
                map = mapper.readValue(value, new TypeReference<LinkedHashMap<String, String>>() {});
                hash.add(map);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(map);
        }

        return hash;
    }
}
