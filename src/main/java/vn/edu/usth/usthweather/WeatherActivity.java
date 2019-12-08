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

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.tabs.TabLayout;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;


public class WeatherActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_weather);
        Log.i("InfoTag", "onCreate");

        PagerAdapter adapter = new HomeFragmentPagerAdapter(getSupportFragmentManager());
        ViewPager pager = findViewById(R.id.pager);
        TabLayout tabLayout = findViewById(R.id.tab);
        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.miiplaza);
        Toolbar toolbar = findViewById(R.id.toolbar);

        copyFileToExternalStorage(R.raw.miiplaza, "miiplaza.mp3");
        mediaPlayer.start();
        mediaPlayer.setLooping(true);

        setSupportActionBar(toolbar);
        pager.setOffscreenPageLimit(3);
        pager.setAdapter(adapter);
        tabLayout.setupWithViewPager(pager);

        System.setProperty("http.keepAlive", "false");

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(imageRequest);
    }

    Response.Listener<Bitmap> listener = new Response.Listener<Bitmap>() {
        @Override
        public void onResponse(Bitmap response) {
            ImageView iv = findViewById(R.id.logo);
            iv.setImageBitmap(response);
        }
    };

    ImageRequest imageRequest = new ImageRequest("https://usth.edu.vn/uploads/chuong-trinh/2017_01/logo-moi_2.png",
            listener, 0, 0, ImageView.ScaleType.CENTER,
            Bitmap.Config.ARGB_8888,null);

    private class WeatherThingTask extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected Bitmap doInBackground(String... param) {
            try {
                URL url = new URL(param[0]);
                HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setConnectTimeout(5000);
                connection.setReadTimeout(5000);
                connection.setDoInput(true);

                connection.connect();
                int response = connection.getResponseCode();
                Log.i("USTHWeather", "The response is: " + response);


                InputStream is = connection.getInputStream();
                Bitmap bitmap = BitmapFactory.decodeStream(is);
                return bitmap;
            }
            catch(Exception e){
                Log.i("WeatherThingTask","ERROR!");
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            Log.i("WeatherThingTask", "onPostExecute");

            ImageView logo = findViewById(R.id.logo);
            logo.setImageBitmap(bitmap);
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
