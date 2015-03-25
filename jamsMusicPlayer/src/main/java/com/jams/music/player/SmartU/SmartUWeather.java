package com.jams.music.player.SmartU;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jams.music.player.R;

import zh.wang.android.apis.yweathergetter4a.WeatherInfo;
import zh.wang.android.apis.yweathergetter4a.YahooWeather;
import zh.wang.android.apis.yweathergetter4a.YahooWeatherExceptionListener;
import zh.wang.android.apis.yweathergetter4a.YahooWeatherInfoListener;

/**
 * Created by bidyut on 3/19/15.
 */
public class SmartUWeather extends Activity implements YahooWeatherInfoListener, YahooWeatherExceptionListener {

    private TextView mTvWeather;
    private TextView mTvTitle;
    private Button mBtGetWeather;
    private Button mBtGetGPS;
    private EditText mEtAreaOfCity;
    private LinearLayout mWeatherInfosLayout;

    private YahooWeather mYahooWeather = YahooWeather.getInstance(5000, 5000, true);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);

        mYahooWeather.setExceptionListener(this);

        mTvTitle = (TextView) findViewById(R.id.title_text);

        mTvWeather = (TextView) findViewById(R.id.weather_text);
        mEtAreaOfCity = (EditText) findViewById(R.id.current_location_edit);

        mBtGetWeather = (Button) findViewById(R.id.fetch_weather_button);
        mBtGetWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String _location = mEtAreaOfCity.getText().toString();
                if (!TextUtils.isEmpty(_location)) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(mEtAreaOfCity.getWindowToken(), 0);
                    searchByPlaceName(_location);

                    Toast.makeText(getApplicationContext(), "Searching by Place Name", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mBtGetGPS = (Button) findViewById(R.id.get_gps_button);
        mBtGetGPS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchByGPS();
                Toast.makeText(getApplicationContext(), "Searching by GPS", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void searchByGPS() {
        mYahooWeather.setSearchMode(YahooWeather.SEARCH_MODE.GPS);
        mYahooWeather.queryYahooWeatherByGPS(getApplicationContext(), this);
    }

    private void searchByPlaceName(String location) {
        mYahooWeather.setSearchMode(YahooWeather.SEARCH_MODE.PLACE_NAME);
        mYahooWeather.queryYahooWeatherByPlaceName(getApplicationContext(), location, SmartUWeather.this);
    }

    @Override
    public void gotWeatherInfo(WeatherInfo weatherInfo) {
        // TODO Auto-generated method stub
        if (weatherInfo != null) {
            if (mYahooWeather.getSearchMode() == YahooWeather.SEARCH_MODE.GPS) {
                mEtAreaOfCity.setText("YOUR CURRENT LOCATION");
            }

            mTvTitle.setText(
                    weatherInfo.getTitle() + "\n"
                            + weatherInfo.getWOEIDneighborhood() + ", "
                            + weatherInfo.getWOEIDCounty() + ", "
                            + weatherInfo.getWOEIDState() + ", "
                            + weatherInfo.getWOEIDCountry());
            mTvWeather.setText("weather: " + weatherInfo.getCurrentText() + "\n" +
                            "Visibility: " + weatherInfo.getAtmosphereVisibility()
            );

            for (int i = 0; i < YahooWeather.FORECAST_INFO_MAX_SIZE; i++) {
                final WeatherInfo.ForecastInfo forecastInfo = weatherInfo.getForecastInfoList().get(i);
                mTvWeather.setText("weather: " + forecastInfo.getForecastText() + "\n");
            }
        } else {
            Toast.makeText(getApplicationContext(), "Oops!", Toast.LENGTH_SHORT).show();
//            setNoResultLayout();
        }
    }

    @Override
    public void onFailConnection(final Exception e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onFailParsing(final Exception e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onFailFindLocation(final Exception e) {
        // TODO Auto-generated method stub

    }

}
