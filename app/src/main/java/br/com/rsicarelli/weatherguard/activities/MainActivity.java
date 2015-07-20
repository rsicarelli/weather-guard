package br.com.rsicarelli.weatherguard.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import br.com.rsicarelli.weatherguard.R;
import br.com.rsicarelli.weatherguard.adapters.WeatherAdapter;
import br.com.rsicarelli.weatherguard.connection.WeatherRequests;
import br.com.rsicarelli.weatherguard.exceptions.WeatherException;
import br.com.rsicarelli.weatherguard.interfaces.WeatherCallback;
import br.com.rsicarelli.weatherguard.responses.ListResult;
import br.com.rsicarelli.weatherguard.responses.WeatherResponse;

public class MainActivity extends AppCompatActivity implements WeatherCallback {

    private ListView lvWeather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvWeather = (ListView) findViewById(R.id.lv_result);
        lvWeather.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                ListResult tag = (ListResult) adapterView.getAdapter().getItem(position);
                Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                intent.putExtra("lat", tag.getCoord().getLat());
                intent.putExtra("lng", tag.getCoord().getLon());
                startActivity(intent);
            }
        });

        WeatherRequests.getWeathers(this);

    }

    @Override
    public void onWeatherSuccess(WeatherResponse response) {
        WeatherAdapter weatherAdapter = new WeatherAdapter(response.getList());
        lvWeather.setAdapter(weatherAdapter);
    }

    @Override
    public void onWeatherFailed(WeatherException e) {
        Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
    }

}
