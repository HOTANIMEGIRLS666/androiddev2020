package vn.edu.usth.usthweather;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class WeatherAndForecastFragment extends Fragment {
    public WeatherAndForecastFragment() {
    }

    public static WeatherAndForecastFragment newInstance() {
        WeatherAndForecastFragment fragmentFirst = new WeatherAndForecastFragment();
        return fragmentFirst;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_weather_and_forecast, container, false);
    }
}
