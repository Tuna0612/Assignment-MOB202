package com.tuna.assignment.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tuna.assignment.R;

public class Fragment_Chi extends Fragment {
    private TabLayout tabLayoutChi;
    private ViewPager viewPagerChi;
    private ChiPagerAdapter adapterChi;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chi, container, false);
        tabLayoutChi = view.findViewById(R.id.tabLayoutChi);
        viewPagerChi = view.findViewById(R.id.viewPagerChi);
        adapterChi = new ChiPagerAdapter(getFragmentManager());
        viewPagerChi.setAdapter(adapterChi);
        tabLayoutChi.setupWithViewPager(viewPagerChi);
        return view;
    }
}
