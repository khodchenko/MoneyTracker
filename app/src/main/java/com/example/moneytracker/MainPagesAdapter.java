package com.example.moneytracker;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class MainPagesAdapter extends FragmentPagerAdapter {


    public MainPagesAdapter(FragmentManager fm, MainActivity mainActivity) {
        super(fm);
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new ItemsFragment();
        } else if (position == 1) {
            return new ItemsFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return "ДОХОДЫ";
        } else if (position == 1) {
            return "РАСХОДЫ";
        }
        return null;
    }
}
