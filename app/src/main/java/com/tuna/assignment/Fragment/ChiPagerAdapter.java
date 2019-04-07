package com.tuna.assignment.Fragment;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ChiPagerAdapter extends FragmentPagerAdapter {
    public ChiPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new Fragment_Chi_KhoanChi();
            case 1:
                return new Fragment_Chi_LoaiChi();
            default:
                return new Fragment_Chi_KhoanChi();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Khoản Chi";
            case 1:
                return "Loại Chi";
            default:
                return "Khoản Chi";
        }
    }
}
