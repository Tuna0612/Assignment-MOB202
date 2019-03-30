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

public class ThuFragment extends Fragment {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private ThuPager thuPager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.thu_fragment, container, false);
        viewPager = view.findViewById(R.id.viewpaper);
        tabLayout = view.findViewById(R.id.tablelayout);
        thuPager = new ThuPager(getFragmentManager());
        viewPager.setAdapter(thuPager);
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Quản Lý Thu");
    }
}
