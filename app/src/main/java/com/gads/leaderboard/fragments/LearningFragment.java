package com.gads.leaderboard.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gads.leaderboard.LearningAdapter;
import com.gads.leaderboard.R;
import com.gads.leaderboard.datasource.PostClient;
import com.gads.leaderboard.model.Learning;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LearningFragment extends Fragment {
    private RecyclerView recyclerView;
    private LearningAdapter learningAdapter;
    private ProgressBar progressBar;

    public LearningFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_learning, container, false);
        recyclerView = rootView.findViewById(R.id.learning_recycler);
        progressBar = rootView.findViewById(R.id.learning_progress);
        getLearningLeaders();
        return rootView;
    }

    private void getLearningLeaders() {
        PostClient.getInstance()
                .getLearningList()
                .enqueue(new Callback<List<Learning>>() {
                    @Override
                    public void onResponse(Call<List<Learning>> call, Response<List<Learning>> response) {
                        if (response.isSuccessful()) {
                            List<Learning> learningList = response.body();
                            learningAdapter = new LearningAdapter(learningList, getActivity().getApplicationContext());
                            recyclerView.setAdapter(learningAdapter);
                            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                            progressBar.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Learning>> call, Throwable t) {
                        progressBar.setVisibility(View.VISIBLE);
                        Toast.makeText(getActivity().getApplicationContext(), "" + t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }
}
