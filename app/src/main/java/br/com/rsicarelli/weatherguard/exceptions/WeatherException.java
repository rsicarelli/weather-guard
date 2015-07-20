package br.com.rsicarelli.weatherguard.exceptions;

import android.accounts.NetworkErrorException;

import com.android.volley.VolleyError;

/**
 * Created by rsicarelli on 7/13/15.
 */
public class WeatherException extends NetworkErrorException {

    public WeatherException() {
    }

    public WeatherException(String message) {
        super(message);
    }

    public WeatherException fromVolley(VolleyError error) {
        String valueOfStatusCode = String.valueOf(error.networkResponse.statusCode);

        if (valueOfStatusCode.startsWith("4")) {
            return new WeatherException("Houve algum erro com o seu pedido");
        } else if (valueOfStatusCode.startsWith("5")) {
            return new WeatherException("Desculpe, estamos enfrentando problemas técnicos. Por favor, tente novamente mais tarde");
        } else {
            return new WeatherException("Parece que você está sem internet! Por favor, verifique a sua conexão e tente novamente");
        }

    }


}
