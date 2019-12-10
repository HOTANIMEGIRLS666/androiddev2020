package vn.edu.usth.usthweather;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



public class WeatherAndForecastFragment extends Fragment {
    private int page;
    private TextView cur_city = null, cur_descript = null, cur_temp = null;

    public WeatherAndForecastFragment() {
    }

    public static WeatherAndForecastFragment newInstance(int p) {
        WeatherAndForecastFragment weatherAndForecastFragment = new WeatherAndForecastFragment();
        Bundle args = new Bundle();
        args.putInt("page", p);
        weatherAndForecastFragment.setArguments(args);
        return weatherAndForecastFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            page = getArguments().getInt("page", 0);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather_and_forecast, container, false);
        cur_descript = view.findViewById(R.id.description);
        cur_city = view.findViewById(R.id.city);
        cur_temp = view.findViewById(R.id.degree);

        switch (page){
            case 0:
                jsonDecoder("Hanoi");
                break;
            case 1:
                jsonDecoder("Paris");
                break;
            case 2:
                jsonDecoder("Toulouse");
                break;
            default:
                break;
        }
        return view;
    }

    private void jsonDecoder(String cityName){
            String url = ("http://api.openweathermap.org/data/2.5/weather?q=" + cityName + "&appid=db7d5e91e45fb33f36610f0ff366cb01");
            StringRequest request0 = new StringRequest(url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.i("StringRequest", "Json response " + response);
                            try{
                                JSONObject obj = new JSONObject(response);
                                cur_city.setText(obj.getString("name"));
                                cur_temp.setText(obj.getJSONObject("main").getString("temp") + " F");
                                JSONArray arr1 = obj.getJSONArray("weather");
                                for(int i=0;i<arr1.length();i++){
                                    cur_descript.setText(arr1.getJSONObject(i).getString("main"));
                                }
                            }catch (JSONException e) {
                                Log.e("StringRequest","XD");
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("StringRequest","ERROR!!!");
                        }
                    });
            WeatherActivity.getRequestQueue().add(request0);
    }
}
