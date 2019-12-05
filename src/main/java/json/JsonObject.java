package json;

import java.util.*;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class JsonObject extends Json {
    private final HashMap<String, Json> jsonPairs = new HashMap<>();

    public JsonObject(JsonPair... jsonPairs) {
        for (JsonPair pair: jsonPairs) {
            this.jsonPairs.put(pair.key, pair.value);
        }
    }

    @Override
    public String toJson() {
        return "{" + getJsonObjectBody() + "}";
    }

    private String getJsonObjectBody() {
        String objBodyStr = "";
        Iterator jsonIterator = jsonPairs.entrySet().iterator();
        while (jsonIterator.hasNext()) {
            Map.Entry pair = (Map.Entry) jsonIterator.next();
            JsonPair jPair = new JsonPair((String) pair.getKey(), (Json) pair.getValue());
            objBodyStr += jPair.toJson();
            if (jsonIterator.hasNext())
                objBodyStr += ", ";
        }
        return objBodyStr;
    }

    public void add(JsonPair jsonPair) {
        if (contains(jsonPair.key)) {
            jsonPairs.replace(jsonPair.key, jsonPair.value);
        } else {
            jsonPairs.put(jsonPair.key, jsonPair.value);
        }
    }

    private boolean contains(String name) {
        return jsonPairs.containsKey(name);
    }

    public Json find(String name) {
        return jsonPairs.get(name);
    }

    public JsonObject projection(String... names) {

        JsonObject jsonObjectProjection = new JsonObject();
        for (String name: names) {
            Json value = find(name);
            if (value != null) {
                jsonObjectProjection.add(new JsonPair(name, value));
            }
        }
        return jsonObjectProjection;

    }
}
