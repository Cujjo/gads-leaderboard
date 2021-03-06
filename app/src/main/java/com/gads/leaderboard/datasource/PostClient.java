package com.gads.leaderboard.datasource;

import com.gads.leaderboard.model.*;
import retrofit2.Retrofit;
import retrofit2.Call;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.List;

public class PostClient {
    private LeaderboardService leaderboardService;
    private static GoogleDocsService googleDocsService;
    private static PostClient instance;

    public PostClient() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://gadsapi.herokuapp.com/").addConverterFactory(GsonConverterFactory.create()).build();
        leaderboardService = retrofit.create(LeaderboardService.class);
    }

    public static PostClient getInstance() {
        if(instance == null)
            instance = new PostClient();
        return instance;
    }

    public static GoogleDocsService getGoogleDocs() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://docs.google.com/forms/d/e/").addConverterFactory(GsonConverterFactory.create()).build();
        googleDocsService = retrofit.create(GoogleDocsService.class);
        return googleDocsService;
    }

    public Call<List<Learning>> getLearningList() {
        return leaderboardService.getLearningList();
    }

    public Call<List<SkillIQ>> getSkillIQList() {
        return leaderboardService.getSkillIQList();
    }
}
