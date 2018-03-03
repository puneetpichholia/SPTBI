package com.aakash.sptbi;

import android.nfc.Tag;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class SignUp extends AppCompatActivity {

    private static final String TAG = "SignUp";
    private Toolbar mToolbar;

    private ViewPager mViewPager;
    private SignUpSectionsPagerAdapter mSectionsPagerAdapter;

    private TabLayout mTabLayout;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mToolbar = (Toolbar) findViewById(R.id.signup_main_page_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Signup");

        //Tabs
        mViewPager = (ViewPager)findViewById(R.id.signup_main_tabPager);
        mSectionsPagerAdapter = new SignUpSectionsPagerAdapter(getSupportFragmentManager());

        mViewPager.setAdapter(mSectionsPagerAdapter);
        mTabLayout = (TabLayout)findViewById(R.id.signup_main_tabs);
        mTabLayout.setupWithViewPager(mViewPager);
    }
}
