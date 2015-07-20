package br.com.rsicarelli.weatherguard.utils;

/**
 * Created by rsicarelli on 7/20/15.
 */
public class StringUtils {

    public static int kelvinToCelcius(Double degKelvin) {
        int degCelcius;
        degCelcius = (int) (degKelvin - 273.15f);
        return degCelcius;
    }

    public static String captalize(String target) {
        StringBuilder res = new StringBuilder();

        String[] strArr = target.split(" ");
        for (String str : strArr) {
            char[] stringArray = str.trim().toCharArray();
            stringArray[0] = Character.toUpperCase(stringArray[0]);
            str = new String(stringArray);

            res.append(str).append(" ");
        }

        return res.toString().trim();
    }


}
