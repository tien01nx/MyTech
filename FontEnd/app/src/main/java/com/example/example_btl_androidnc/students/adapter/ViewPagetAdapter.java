package com.example.example_btl_androidnc.students.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.example_btl_androidnc.students.fragment.Admin_HomeFragment;
import com.example.example_btl_androidnc.students.fragment.List_CourseFragment;
import com.example.example_btl_androidnc.students.fragment.Profile_UserFragment;


public class ViewPagetAdapter extends FragmentStatePagerAdapter {

    public ViewPagetAdapter(FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new Admin_HomeFragment();
            case 1:
               return new List_CourseFragment();
            case 2:
                return new Profile_UserFragment();
            default:
                return new Admin_HomeFragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
