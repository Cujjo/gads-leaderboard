package com.gads.leaderboard;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.gads.leaderboard.fragments.LearningFragment;
import com.gads.leaderboard.fragments.SkillIQFragment;
import com.google.android.material.tabs.TabLayout;

public class LeaderboardActivity extends AppCompatActivity {
    View rootLayout;
    ViewPager viewPager;
    TabLayout tabLayout;
    Button submit;
    LearningFragment learningFragment;
    SkillIQFragment skillIQFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        learningFragment = new LearningFragment();
        skillIQFragment = new SkillIQFragment();
        submit = (Button) findViewById(R.id.button);
        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setAdapter(myPagerAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    public void showSubmissionForm(View view) {
        Intent intent = new Intent(this, SubmissionDetailsActivity.class);
        startActivity(intent);
    }

    class MyPagerAdapter extends FragmentPagerAdapter {
        String[] fragmentNames = {"Learning Leaders", "Skill IQ Leaders"};

        public MyPagerAdapter(FragmentManager fm, int behavior) {
            super(fm, behavior);
        }

        @Override
        public Fragment getItem(int position) {
            switch(position) {
                case 0:
                    return learningFragment;
                case 1:
                    return skillIQFragment;
            }
            return null;
        }

        @Override
        public int getCount() {
            return fragmentNames.length;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return fragmentNames[position];
        }
    }
}
