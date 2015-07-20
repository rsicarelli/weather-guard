package br.com.rsicarelli.weatherguard.connection;

import android.net.Uri;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import br.com.rsicarelli.weatherguard.exceptions.WeatherException;
import br.com.rsicarelli.weatherguard.interfaces.WeatherCallback;
import br.com.rsicarelli.weatherguard.responses.WeatherResponse;
import br.com.rsicarelli.weatherguard.utils.ParserUtil;

public class WeatherRequests {

    public static void getWeathers(final WeatherCallback callback) {

        Uri.Builder uriBuilder = new Uri.Builder()
                .scheme(ApiCons.API_SCHEME).authority(ApiCons.API_AUTHORITY);

        uriBuilder.appendPath("data").appendPath("2.5").appendPath("find")
                .appendQueryParameter("lat", "-23.54")
                .appendQueryParameter("lon", "-46.73")
                .appendQueryParameter("cnt", "20");

        StringRequest stringRequest = new StringRequest(Request.Method.GET, uriBuilder.toString(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        callback.onWeatherSuccess(ParserUtil.fromJson(response, WeatherResponse.class));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.onWeatherFailed(new WeatherException().fromVolley(error));
            }
        });

        VolleySingleton.getInstance().addToRequestQueue(stringRequest);

    }

    public static void showWeatherIcon(String urlIcon, ImageView holder, DisplayImageOptions options) {
        Uri.Builder uriBuilder = new Uri.Builder()
                .scheme(ApiCons.API_SCHEME).authority(ApiCons.API_AUTHORITY).appendPath("img").appendPath("w").appendPath(urlIcon + ".png");

        ImageLoader.getInstance().displayImage(uriBuilder.toString(), holder, options);
    }
}
