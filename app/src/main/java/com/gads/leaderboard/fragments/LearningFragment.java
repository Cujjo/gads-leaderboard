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
import com.gads.leaderboard.LearningAdapter;
import com.gads.leaderboard.R;
import com.gads.leaderboard.model.Learning;

import java.util.Objects;
import java.util.List;

public class LearningFragment extends Fragment {
    DataViewModel dataViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_learning, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.learning_recycler);
        dataViewModel = new ViewModelProvider(this).get(DataViewModel.class);
        dataViewModel.getLearningList();

        final LearningAdapter adapter = new LearningAdapter(getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        dataViewModel.learningMutableData.observe((LifecycleOwner) Objects.requireNonNull(getContext()), new Observer<List<Learning>>() {
            @Override
            public void onChanged(List<Learning> postModels) {
                adapter.setList(postModels);
            }
        });
    }
}
