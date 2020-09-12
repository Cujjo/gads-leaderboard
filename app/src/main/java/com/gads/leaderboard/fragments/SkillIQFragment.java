package com.gads.leaderboard.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gads.leaderboard.DataViewModel;
import com.gads.leaderboard.R;
import com.gads.leaderboard.SkillIQAdapter;
import com.gads.leaderboard.model.SkillIQ;

import java.util.Objects;
import java.util.List;

public class SkillIQFragment extends Fragment {
    DataViewModel dataViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_skill_iq, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.skill_iq_recycler);
        dataViewModel = new ViewModelProvider(this).get(DataViewModel.class);
        dataViewModel.getSkillIQList();

        final SkillIQAdapter adapter = new SkillIQAdapter(getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        dataViewModel.skillIQMutableData.observe((LifecycleOwner) Objects.requireNonNull(getContext()), new Observer<List<SkillIQ>>() {
            @Override
            public void onChanged(List<SkillIQ> postModels) {
                adapter.setList(postModels);
            }
        });
    }
}
