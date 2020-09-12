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
import java.util.ArrayList;
import java.util.List;

public class LearningAdapter extends RecyclerView.Adapter<LearningAdapter.PostViewHolder> {
    private List<Learning> learningList = new ArrayList<>();
    Context context;

    public LearningAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PostViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_learning, parent));
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        holder.learningName.setText(learningList.get(position).getName());
        holder.learningHours.setText(MessageFormat.format("{0} learning hours,", learningList.get(position).getHours()));
        holder.learningCountry.setText(learningList.get(position).getCountry());
        Glide.with(context).load(learningList.get(position).getBadgeUrl()).into(holder.img);
        Log.d("LearningAdapter", "ImageUrl is: " + learningList.get(position).getBadgeUrl());
    }

    @Override
    public int getItemCount() {
        return learningList.size();
    }

    public void setList(List<Learning> list) {
        this.learningList = list;
        notifyDataSetChanged();
    }

    public class PostViewHolder extends RecyclerView.ViewHolder {
        TextView learningName, learningHours, learningCountry;
        ImageView img;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.learning_image);
            learningName = itemView.findViewById(R.id.learning_name);
            learningHours = itemView.findViewById(R.id.learning_hours);
            learningCountry = itemView.findViewById(R.id.learning_country);
        }
    }
}
