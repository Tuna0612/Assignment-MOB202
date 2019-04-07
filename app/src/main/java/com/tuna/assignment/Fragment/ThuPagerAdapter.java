package com.tuna.assignment.Fragment;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ThuPagerAdapter extends FragmentPagerAdapter {
    public ThuPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new Fragment_Thu_KhoanThu();
            case 1:
                return new Fragment_Thu_LoaiThu();
            default:
                return new Fragment_Thu_KhoanThu();

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
                return "Khoản Thu";
            case 1:
                return "Loại Thu";
            default:
                return "Khoản Thu";

        }
    }
}
