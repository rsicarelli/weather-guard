package br.com.rsicarelli.weatherguard.utils;


import com.google.gson.Gson;

public class ParserUtil {

    private static Gson gson;

    public static Gson getGson() {
        if (gson == null) {
            gson = new Gson();
        }
        return gson;
    }

    public static <T> T fromJson(String json, Class<T> classOfT) {
        return getGson().fromJson(json, classOfT);
    }

    public static String toJson(Object src) {
        return getGson().toJson(src);
    }

}
