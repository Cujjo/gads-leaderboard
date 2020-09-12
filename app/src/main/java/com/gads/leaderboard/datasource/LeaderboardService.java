package com.gads.leaderboard.datasource;

import com.gads.leaderboard.model.*;
import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

public interface LeaderboardService {
    @GET("api/hours")
    Call<List<Learning>> getLearningList();

    @GET("api/skilliq")
    Call<List<SkillIQ>> getSkillIQList();
}
