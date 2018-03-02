package com.aakash.sptbi;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Puneet on 03-Mar-18.
 */

public class ManagementSectionPagerAdapter extends FragmentPagerAdapter{
    public ManagementSectionPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                Startup_Fragment startup = new Startup_Fragment();
                return startup;

            case 1:
                Students_Fragment student= new Students_Fragment();
                return student;
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
