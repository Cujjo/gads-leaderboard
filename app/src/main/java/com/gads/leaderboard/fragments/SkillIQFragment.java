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

import com.gads.leaderboard.R;
import com.gads.leaderboard.SkillIQAdapter;
import com.gads.leaderboard.datasource.PostClient;
import com.gads.leaderboard.model.SkillIQ;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SkillIQFragment extends Fragment {
    private RecyclerView recyclerView;
    private SkillIQAdapter skillIQAdapter;
    private ProgressBar progressBar;

    public SkillIQFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_skill_iq, container, false);
        recyclerView = rootView.findViewById(R.id.skill_iq_recycler);
        progressBar = rootView.findViewById(R.id.skill_iq_progress);
        getSkillIQLeaders();
        return rootView;
    }

    private void getSkillIQLeaders() {
        PostClient.getInstance()
                .getSkillIQList()
                .enqueue(new Callback<List<SkillIQ>>() {
                    @Override
                    public void onResponse(Call<List<SkillIQ>> call, Response<List<SkillIQ>> response) {
                        if (response.isSuccessful()) {
                            List<SkillIQ> skillIQList = response.body();
                            skillIQAdapter = new SkillIQAdapter(skillIQList, getActivity().getApplicationContext());
                            recyclerView.setAdapter(skillIQAdapter);
                            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                            progressBar.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<SkillIQ>> call, Throwable t) {
                        progressBar.setVisibility(View.VISIBLE);
                        Toast.makeText(getActivity().getApplicationContext(), "" + t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }
}
