package vn.edu.usth.usthweather;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ForecastFragment extends Fragment {
    public ForecastFragment() {
    }

    @Override
    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TextView text = new TextView(getActivity());
        text.setText("Thursday");
        text.setTextSize(30);

        ImageView icon = new ImageView(getActivity());
        icon.setImageResource(R.drawable.weather_app1);


        LinearLayout lL = new LinearLayout(getActivity());
        lL.setBackgroundColor(0x20FF0000);
        lL.setOrientation(LinearLayout.VERTICAL);
        lL.addView(text);
        lL.addView(icon);

        return lL;
    }
}

