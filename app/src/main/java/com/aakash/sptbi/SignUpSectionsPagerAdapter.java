package com.aakash.sptbi;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by aakas on 26-02-2018.
 */
public class SignUpSectionsPagerAdapter extends FragmentPagerAdapter {

    public SignUpSectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                signup_startup startup_fragment = new signup_startup();
                return startup_fragment;

            case 1:
                signup_students students_fragment = new signup_students();
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