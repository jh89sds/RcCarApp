package com.arduino.jimmy.bluetoothconnection.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.arduino.jimmy.bluetoothconnection.fragment.MoveCarFragment;
import com.arduino.jimmy.bluetoothconnection.fragment.LightFragment;
import com.arduino.jimmy.bluetoothconnection.fragment.OptionFragment;
import com.arduino.jimmy.bluetoothconnection.fragment.TabFragment;

public class TabPagerAdapter extends FragmentStatePagerAdapter {

    public static final int MOVE_CAR_PAGE = 0;
    public static final int LIGHT_PAGE = 1;
    public static final int OPTION_PAGE = 2;
    // Count number of tabs
    private int tabCount;

    public TabPagerAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position) {

        TabFragment tabFragment;
        // Returning the current tabs
        switch (position) {
            case MOVE_CAR_PAGE:
                tabFragment = new MoveCarFragment();
                break;
            case LIGHT_PAGE:
                tabFragment = new LightFragment();
                break;
            case OPTION_PAGE:
                tabFragment = new OptionFragment();
                break;
            default:
                tabFragment = new MoveCarFragment();
                break;
        }
        return tabFragment;
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}