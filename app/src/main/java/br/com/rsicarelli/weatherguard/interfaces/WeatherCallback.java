package br.com.rsicarelli.weatherguard.interfaces;

import br.com.rsicarelli.weatherguard.exceptions.WeatherException;
import br.com.rsicarelli.weatherguard.responses.WeatherResponse;

/**
 * Created by rsicarelli on 7/20/15.
 */
public interface WeatherCallback {

    void onWeatherSuccess(WeatherResponse response);
    void onWeatherFailed(WeatherException e);
}
