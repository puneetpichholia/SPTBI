package com.aakash.sptbi;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aakas on 26-02-2018.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                Startup_Fragment startup_fragment = new Startup_Fragment();
                return startup_fragment;

            case 1:
                Students_Fragment students_fragment = new Students_Fragment();
                return students_fragment;

            default:
                return null;
        }
    }


    @Override
    public int getCount() {
        return 2;
    }

    public CharSequence getPageTitle(int position){
        switch(position){
            case 0:
                return "Startups";

            case 1:
                return "Students";

            default:
                return null;
        }
    }



}