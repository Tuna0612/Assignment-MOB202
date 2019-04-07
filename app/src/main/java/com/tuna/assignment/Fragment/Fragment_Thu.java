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

public class Fragment_Thu extends Fragment {
    private TabLayout tabLayoutThu;
    private ViewPager viewPagerThu;
    private ThuPagerAdapter adapterThu;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_thu, container, false);
        tabLayoutThu = view.findViewById(R.id.tabLayoutThu);
        viewPagerThu = view.findViewById(R.id.viewPagerThu);
        adapterThu = new ThuPagerAdapter(getFragmentManager());
        viewPagerThu.setAdapter(adapterThu);
        tabLayoutThu.setupWithViewPager(viewPagerThu);
        return view;
    }
}
