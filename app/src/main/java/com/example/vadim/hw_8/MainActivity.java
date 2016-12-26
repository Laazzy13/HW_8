package com.example.vadim.hw_8;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.BUTTON);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new FetchWeatherData().execute();
            }
        });
    }

    class FetchWeatherData extends AsyncTask<Void, Void, String> {

        HttpURLConnection httpURLConnection;
        BufferedReader bufferedReader;
        String JsonObj;

        @Override
        protected String doInBackground(Void... params) {
            try {
                URL url = new URL("http://api.openweathermap.org/data/2.5/weather?id=702550,ua&units=metric&appid=1bd5af4b7c8234953376d621296ef779");
                httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.connect();
                InputStream inputStream = httpURLConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    return null;
                }
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    buffer.append(line).append("\n");

                }
                if (buffer.length() == 0) {
                    return null;
                }
                JsonObj = buffer.toString();
            }
            catch (IOException e) {
                return null;
            }
            return JsonObj;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                JSONObject dataJsonObj = new JSONObject(s);
                String city_name = dataJsonObj.getString("name");
                JSONObject dataTemperatureJsonObj = dataJsonObj.getJSONObject("main");
                JSONObject dataWindJsonObj = dataJsonObj.getJSONObject("wind");

                JSONArray weatherJsonObj = dataJsonObj.getJSONArray("weather");
                String data = weatherJsonObj.getString(0);
                JSONObject dataWeatherJsonObj = new JSONObject(data);

                String temperature = String.valueOf(dataTemperatureJsonObj.getInt("temp"));
                String main = dataWeatherJsonObj.getString("main");
                String humidity = String.valueOf(dataTemperatureJsonObj.getInt("humidity"));
                String wind_speed = dataWindJsonObj.getString("speed");

                RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.RECYCLER_VIEW);
                Second mAdapter = new Second(city_name, main, temperature, humidity, wind_speed);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                mRecyclerView.setLayoutManager(mLayoutManager);
                mRecyclerView.setAdapter(mAdapter);
            }
            catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
