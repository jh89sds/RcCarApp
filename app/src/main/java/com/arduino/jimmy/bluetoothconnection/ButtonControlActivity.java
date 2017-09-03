package com.arduino.jimmy.bluetoothconnection;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.arduino.jimmy.bluetoothconnection.adapter.TabPagerAdapter;
import com.arduino.jimmy.bluetoothconnection.fragment.MoveCarFragment;

import static com.arduino.jimmy.bluetoothconnection.adapter.TabPagerAdapter.MOVE_CAR_PAGE;

/**
 * Created by USER on 2017-08-27.
 */
public class ButtonControlActivity extends CommonActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_control);

        setTabs();
    }

    private void setTabs() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setTabLayout();

        // Initializing ViewPager
        initViewPager();

        // Creating TabPagerAdapter adapter
        createTabAdapter();

        // Set TabSelectedListener
        tabLayoutListener();

    }

    private void tabLayoutListener() {
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                if(tab.getPosition() == MOVE_CAR_PAGE){
                    MoveCarFragment moveCarPage = ((MoveCarFragment) getSupportFragmentManager().getFragments().get(MOVE_CAR_PAGE));
                    if(moveCarPage != null) {
                        moveCarPage.stopMove();
                    }
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void createTabAdapter() {
        TabPagerAdapter pagerAdapter = new TabPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }

    private void initViewPager() {
        viewPager = (ViewPager) findViewById(R.id.pager);
    }

    private void setTabLayout() {
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        tabLayout.addTab(makeTab(R.drawable.ic_car_move));
        tabLayout.addTab(makeTab(R.drawable.ic_car_light));
        tabLayout.addTab(makeTab(R.drawable.ic_star));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
    }

    private TabLayout.Tab makeTab(@DrawableRes int drawable) {
        TabLayout.Tab tab = tabLayout.newTab();
        tab.setIcon(drawable);
        tab.getIcon().setColorFilter(ContextCompat.getColor(this, R.color.colorPrimary), PorterDuff.Mode.SRC_IN);
        return tab;
    }

}
