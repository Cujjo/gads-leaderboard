package com.gads.leaderboard;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.gads.leaderboard.model.Learning;

import java.text.MessageFormat;
import java.util.List;

public class LearningAdapter extends RecyclerView.Adapter<LearningAdapter.PostViewHolder> {
    List<Learning> learningList;
    Context context;

    public LearningAdapter(List<Learning> learningList, Context context) {
        this.learningList = learningList;
        this.context = context;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View learningView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_learning, parent, false);
        PostViewHolder postViewHolder = new PostViewHolder(learningView);
        return postViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        Learning current = learningList.get(position);
        holder.learningName.setText(current.getName());
        holder.learningHours.setText(MessageFormat.format("{0} learning hours,", current.getHours()));
        holder.learningCountry.setText(current.getCountry());
        Glide.with(context).load(current.getBadgeUrl()).into(holder.img);
        // Log.d("LearningAdapter", "ImageUrl is: " + current.getBadgeUrl());
    }

    @Override
    public int getItemCount() {
        return learningList.size();
    }

    public static class PostViewHolder extends RecyclerView.ViewHolder {
        public TextView learningName, learningHours, learningCountry;
        public ImageView img;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.learning_image);
            learningName = itemView.findViewById(R.id.learning_name);
            learningHours = itemView.findViewById(R.id.learning_hours);
            learningCountry = itemView.findViewById(R.id.learning_country);
        }
    }
}
