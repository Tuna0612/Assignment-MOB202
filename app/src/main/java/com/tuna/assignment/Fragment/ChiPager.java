package com.tuna.assignment.Fragment;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ChiPager extends FragmentPagerAdapter {
    public ChiPager(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 1:
                return new Loai_Chi_Fragment();
            default:
                return new Khoan_Chi_Fragment();

        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 1:
                return "Loại Chi";
            default:
                return "Khoản Chi";
        }
    }
}
