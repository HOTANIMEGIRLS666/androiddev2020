package vn.edu.usth.usthweather;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_weather);
        Log.i("InfoTag", "onCreate");;

        PagerAdapter adapter = new HomeFragmentPagerAdapter(getSupportFragmentManager());
        ViewPager pager = findViewById(R.id.pager);
        TabLayout tabLayout = findViewById(R.id.tab);
        MediaPlayer mediaPlayer = MediaPlayer.create(this,R.raw.miiplaza);
        Toolbar toolbar = findViewById(R.id.toolbar);

        copyFileToExternalStorage(R.raw.miiplaza, "miiplaza.mp3");
        mediaPlayer.start();
        mediaPlayer.setLooping(true);

        setSupportActionBar(toolbar);
        pager.setOffscreenPageLimit(3);
        pager.setAdapter(adapter);
        tabLayout.setupWithViewPager(pager);

        weatherThingTask weatherTask = new weatherThingTask();
        weatherTask.execute();
    }

    private class weatherThingTask extends AsyncTask<URL, Integer, Bitmap> {
        @Override
        protected void onPreExecute(){
        }

        @Override
        protected Bitmap doInBackground(URL... param) {
            try {
                URL url = new URL("https://usth.edu.vn/" + "uploads/chuong-trinh/2017_01/logo-moi_2.png");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setDoInput(true);
                connection.connect();

                int response = connection.getResponseCode();
                Log.i("USTHWeather", "The response is: " + response);

                InputStream is = connection.getInputStream();
                Bitmap bitmap = BitmapFactory.decodeStream(is);
                connection.disconnect();

                return bitmap;
            }
            catch(IOException e){
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... progress) {
            //idk update progress or smth.
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            super.onPostExecute(result);

            ImageView logo = findViewById(R.id.logo);
            logo.setImageBitmap(result);

            ImageView logo2 = findViewById(R.id.logo2);
            logo2.setImageBitmap(result);
        }
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tool_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_refresh:
                Toast.makeText(this,"REFRESHING",Toast.LENGTH_LONG).show();
                return true;
            case R.id.action_more:
                Intent intent0 = new Intent(this,PrefActivity.class);
                startActivity(intent0);
                return true;
            default:
                super.onOptionsItemSelected(item);
        }
        return false;
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
