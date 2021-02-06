package com.example.moneytracker;


import android.content.Context;
import android.util.Log;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import org.jetbrains.annotations.NotNull;

public class MainPagesAdapter extends FragmentPagerAdapter {

    public static final int PAGE_INCOMES = 0;
    public static final int PAGE_EXPENSES = 1;
    public static final int PAGE_BALANCE = 2;

    private final String[] titles;

    public MainPagesAdapter(FragmentManager fm, Context context) {
        super(fm);

        titles = context.getResources().getStringArray(R.array.tab_title);
    }

    @NotNull
    @Override
    public Fragment getItem(int position) {
        Log.i("MainPagesAdapter", "getItem position = " + position);

        switch (position) {
            case PAGE_INCOMES:
                return ItemsFragment.createItemsFragment(Item.TYPE_INCOMES);


            case PAGE_EXPENSES:
                return ItemsFragment.createItemsFragment(Item.TYPE_EXPENSES);

            case PAGE_BALANCE:
                return new BalanceFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}