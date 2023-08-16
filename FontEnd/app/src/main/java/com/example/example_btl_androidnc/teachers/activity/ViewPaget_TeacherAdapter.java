package com.example.example_btl_androidnc.teachers.activity;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.example_btl_androidnc.students.fragment.List_CourseFragment;
import com.example.example_btl_androidnc.students.fragment.Profile_UserFragment;
import com.example.example_btl_androidnc.teachers.fragment.HomeTeacherFragment;


public class ViewPaget_TeacherAdapter extends FragmentStatePagerAdapter {

    public ViewPaget_TeacherAdapter(FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new HomeTeacherFragment();
            case 1:
                return new List_CourseFragment();
            case 2:
                return new Profile_UserFragment();
            default:
                return new HomeTeacherFragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}