package com.aakash.sptbi;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class Management extends AppCompatActivity {

    private Toolbar mToolbar;

    private ViewPager mViewPager;
    private ManagementSectionPagerAdapter mSectionsPagerAdapter;

    private TabLayout mTabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_management);
        mToolbar = (Toolbar) findViewById(R.id.management_main_page_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Management");

        //Tabs
        mViewPager = (ViewPager)findViewById(R.id.management_main_tabPager);
        mSectionsPagerAdapter = new ManagementSectionPagerAdapter(getSupportFragmentManager());

        mViewPager.setAdapter(mSectionsPagerAdapter);
        mTabLayout = (TabLayout)findViewById(R.id.management_tabs);
        mTabLayout.setupWithViewPager(mViewPager);
    }

}
