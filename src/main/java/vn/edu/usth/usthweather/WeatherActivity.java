package vn.edu.usth.usthweather;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.media.MediaPlayer;
import android.os.Environment;
import android.util.Log;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class WeatherActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_weather);
        Log.i("InfoTag", "onCreate");;

        PagerAdapter adapter = new HomeFragmentPagerAdapter(getSupportFragmentManager());
        ViewPager pager = findViewById(R.id.pager);
        TabLayout tabLayout = findViewById(R.id.tab);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        copyFileToExternalStorage(R.raw.miiplaza, "miiplaza.mp3");
        MediaPlayer mediaPlayer = MediaPlayer.create(this,R.raw.miiplaza);
        mediaPlayer.start();
        mediaPlayer.setLooping(true);

        pager.setOffscreenPageLimit(3);
        pager.setAdapter(adapter);
        tabLayout.setupWithViewPager(pager);
    }

    private void copyFileToExternalStorage(int resourceId, String resourceName){
        String pathSDCard = Environment.getExternalStorageDirectory() + "/Android/data/vn.edu.usth.usthweather/" + resourceName;
        try{
            InputStream in = getResources().openRawResource(resourceId);
            FileOutputStream out;
            out = new FileOutputStream(pathSDCard);
            byte[] buff = new byte[1024];
            int read;
            try {
                while ((read = in.read(buff)) > 0) {
                    out.write(buff, 0, read);
                }
            } finally {
                in.close();
                out.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
