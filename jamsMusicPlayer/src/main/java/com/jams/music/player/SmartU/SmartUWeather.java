package com.jams.music.player.SmartU;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jams.music.player.DBHelpers.SmartUDatabase;
import com.jams.music.player.Helpers.UIElementsHelper;
import com.jams.music.player.R;
import com.jams.music.player.Utils.Common;

import org.apache.commons.lang3.text.WordUtils;

import java.util.Calendar;

import zh.wang.android.apis.yweathergetter4a.WeatherInfo;
import zh.wang.android.apis.yweathergetter4a.YahooWeather;
import zh.wang.android.apis.yweathergetter4a.YahooWeatherExceptionListener;
import zh.wang.android.apis.yweathergetter4a.YahooWeatherInfoListener;

/**
 * Created by bidyut on 3/19/15.
 */
public class SmartUWeather extends Activity implements YahooWeatherInfoListener, YahooWeatherExceptionListener {

    boolean activityRunning;
    private SensorManager sensorManager;
    private TextView mTvWeather;
    private TextView mTvTitle;
    private Button mBtGetWeather;
    private Button mBtGetGPS;
    private EditText mEtAreaOfCity;
    private LinearLayout mWeatherInfosLayout;
    private String mWeather;
    private String mDaySegment;
    private int stepCounter = 0;
    private int counterSteps = 0;
    private int stepDetector = 0;

    private YahooWeather mYahooWeather = YahooWeather.getInstance(5000, 5000, true);

    private Context mContext;
    private SmartUWeather mSmartUWeather;
    private Common mApp;
    private Handler handler;
    private Cursor smartSongs;
    private SmartUDatabase db;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        mContext = this.getApplicationContext();
        mSmartUWeather = this;
        mApp = (Common) mContext;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);

        db = new SmartUDatabase(this);

        try {
            handler = new Handler();
            final Runnable r = new Runnable() {
                @Override
                public void run() {
                    smartSongs = db.getSmartCols();
                    handler.postDelayed(this, 1000);
                }
            };

            handler.postDelayed(r, 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Set the ActionBar background and text color.
        getActionBar().setBackgroundDrawable(UIElementsHelper.getGeneralActionBarBackground(mContext));
//**        getActionBar().setTitle(R.string.smart_playlists);
        int titleId = getResources().getIdentifier("action_bar_title", "id", "android");
        TextView actionBarText = (TextView) findViewById(titleId);
        actionBarText.setTextColor(0xFFFFFFFF);

        mYahooWeather.setExceptionListener(this);

//        mTvTitle = (TextView) findViewById(R.id.title_text);
//
//        mTvWeather = (TextView) findViewById(R.id.weather_text);
//        mEtAreaOfCity = (EditText) findViewById(R.id.current_location_edit);
//
//        mBtGetWeather = (Button) findViewById(R.id.fetch_weather_button);
//        mBtGetWeather.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String _location = mEtAreaOfCity.getText().toString();
//                if (!TextUtils.isEmpty(_location)) {
//                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//                    imm.hideSoftInputFromWindow(mEtAreaOfCity.getWindowToken(), 0);
//                    searchByPlaceName(_location);
//
//                    Toast.makeText(getApplicationContext(), "Searching by Place Name", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });

//        mBtGetGPS = (Button) findViewById(R.id.get_gps_button);
//        mBtGetGPS.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                searchByGPS();
//                Toast.makeText(getApplicationContext(), "Searching by GPS", Toast.LENGTH_SHORT).show();
//            }
//        });
        searchByGPS();


        Calendar now = Calendar.getInstance();
        int meridian = now.get(Calendar.AM_PM);
        mDaySegment = getDaySegment(now, meridian);

        Toast.makeText(this, "Try the " + mDaySegment + " Music!", Toast.LENGTH_LONG).show();

    }

    private void searchByGPS() {
        mYahooWeather.setSearchMode(YahooWeather.SEARCH_MODE.GPS);
        mYahooWeather.setUnit(YahooWeather.UNIT.CELSIUS);
        mYahooWeather.queryYahooWeatherByGPS(getApplicationContext(), this);
    }

//    private void searchByPlaceName(String location) {
//        mYahooWeather.setSearchMode(YahooWeather.SEARCH_MODE.PLACE_NAME);
//        mYahooWeather.setUnit(YahooWeather.UNIT.CELSIUS);
//        mYahooWeather.queryYahooWeatherByPlaceName(getApplicationContext(), location, SmartUWeather.this);
//    }

    @Override
    public void gotWeatherInfo(WeatherInfo weatherInfo) {
        // TODO Auto-generated method stub
        if (weatherInfo != null) {
//            if (mYahooWeather.getSearchMode() == YahooWeather.SEARCH_MODE.GPS) {
//                mEtAreaOfCity.setText("YOUR CURRENT LOCATION");
//            }

//            mTvTitle.setText(
//                    weatherInfo.getTitle() + "\n"
//                            + weatherInfo.getWOEIDneighborhood() + ", "
//                            + weatherInfo.getWOEIDCounty() + ", "
//                            + weatherInfo.getWOEIDState() + ", "
//                            + weatherInfo.getWOEIDCountry());
//            mTvWeather.setText("weather: " + weatherInfo.getCurrentText() + "\n" +
//                            "Visibility: " + weatherInfo.getAtmosphereVisibility()
//            );

            mWeather = getSmartWeather(weatherInfo.getCurrentText());
            Toast.makeText(this, "Current Weather is " + WordUtils.capitalize(mWeather), Toast.LENGTH_LONG).show();

//            for (int i = 0; i < YahooWeather.FORECAST_INFO_MAX_SIZE; i++) {
//                final WeatherInfo.ForecastInfo forecastInfo = weatherInfo.getForecastInfoList().get(i);
////                mTvWeather.setText("weather: " + forecastInfo.getForecastText() + "\n");
//                mWeather = forecastInfo.getForecastText();
//            }
//        } else {
//            Toast.makeText(getApplicationContext(), "Oops!", Toast.LENGTH_SHORT).show();
////            setNoResultLayout();
        }
    }

    public String getDaySegment(Calendar now, int meridian) {
        int hour = now.get(Calendar.HOUR);

        if (meridian == Calendar.PM &&
                hour >= 1 &&
                hour < 12) {
            hour += 12;
        }

        if (hour >= 5 && hour < 12) {
            return "Morning";
        } else if (hour >= 12 && hour < 17) {
            return "Afternoon";
        } else if (hour >= 17 && hour < 20) {
            return "Evening";
        } else {
            return "Night";
        }
    }

    public String getSmartWeather(String weather) {
        weather = weather.toLowerCase();

        if (weather.contains("tornado") ||
                weather.contains("storm") ||
                weather.contains("hurricane") ||
                weather.contains("thunder")) {
            return "storm";
        } else if (weather.contains("snow")) {
            return "snow";
        } else if (weather.contains("drizzle") ||
                weather.contains("rain") ||
                weather.contains("shower") ||
                weather.contains("hail")) {
            return "rain";
        } else if (weather.contains("dust") ||
                weather.contains("fog") ||
                weather.contains("haze") ||
                weather.contains("smoky") ||
                weather.contains("bluster") ||
                weather.contains("wind")) {
            return "windy";
        } else if (weather.contains("cold")) {
            return "cold";
        } else if (weather.contains("cloud")) {
            return "cloudy";
        } else if (weather.contains("clear")) {
            return "clear";
        } else if (weather.contains("sun") ||
                weather.contains("hot")) {
            return "sunny";
        } else {
            return "unavailable";
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        smartSongs.close();
        db.close();
    }
}
