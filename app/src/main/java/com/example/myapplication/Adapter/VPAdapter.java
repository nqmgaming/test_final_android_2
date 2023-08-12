package com.example.myapplication.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.myapplication.Fragment.AboutFragment;
import com.example.myapplication.Fragment.TestFragment;

public class VPAdapter extends FragmentStateAdapter {
    public VPAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0) {
            return new TestFragment();
        } else if (position == 1) {
            return new AboutFragment();
        } else {
            return new TestFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
