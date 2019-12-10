package vn.edu.usth.usthweather;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class HomeFragmentPagerAdapter extends FragmentPagerAdapter {
    private final int PAGE_COUNT = 3;
    private final String titles[] = new String[] { "Hanoi", "Paris", "Toulouse" };

    HomeFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int page) {
        switch (page) {
            case 0:
                return WeatherAndForecastFragment.newInstance(0);
            case 1:
                return WeatherAndForecastFragment.newInstance(1);
            case 2:
                return WeatherAndForecastFragment.newInstance(2);
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int page) {
        return titles[page];
    }
}