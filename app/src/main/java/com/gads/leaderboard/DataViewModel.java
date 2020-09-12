package com.gads.leaderboard;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.gads.leaderboard.datasource.PostClient;
import com.gads.leaderboard.model.*;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataViewModel extends ViewModel {
    public MutableLiveData<List<Learning>> learningMutableData = new MutableLiveData<>();
    public MutableLiveData<List<SkillIQ>> skillIQMutableData = new MutableLiveData<>();

    public void getLearningList() {
        PostClient.getInstance().getLearningList().enqueue(new Callback<List<Learning>>() {
            @Override
            public void onResponse(Call<List<Learning>> call, Response<List<Learning>> response) {
                if(response.isSuccessful())
                    learningMutableData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Learning>> call, Throwable t) {
                Log.d("getLearningList()", "onFailure: " + t);
            }
        });
    }

    public void getSkillIQList() {
        PostClient.getInstance().getSkillIQList().enqueue(new Callback<List<SkillIQ>>() {
            @Override
            public void onResponse(Call<List<SkillIQ>> call, Response<List<SkillIQ>> response) {
                if(response.isSuccessful())
                    skillIQMutableData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<SkillIQ>> call, Throwable t) {
                Log.d("getSkillIQList()", "onFailure: " + t);
            }
        });
    }
}
