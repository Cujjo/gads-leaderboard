package com.gads.leaderboard;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.gads.leaderboard.fragments.LearningFragment;
import com.gads.leaderboard.fragments.SkillIQFragment;

public class MyPagerAdapter extends FragmentPagerAdapter {
    private static final int[] FRAGMENT_NAMES = new int[]{R.string.learning_tab_title, R.string.skill_iq_tab_title};
    Context context;

    public MyPagerAdapter(Context context, @NonNull FragmentManager fm) {
        super(fm);
        this.context = context;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position == 0)
            return new LearningFragment();
        else
            return new SkillIQFragment();
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return context.getResources().getString(FRAGMENT_NAMES[position]);
    }
}
