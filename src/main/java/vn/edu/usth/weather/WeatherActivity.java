package vn.edu.usth.weather;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class WeatherActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        Log.i("InfoTag", "onCreate");
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.i("InfoTag", "onStart");
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.i("InfoTag", "onResume");
    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.i("InfoTag", "onPause");
    }
    @Override
    protected void onStop() {
        super.onStop();
        Log.i("InfoTag", "onStop");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("InfoTag", "onDestroy");
    }
}