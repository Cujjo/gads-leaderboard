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
import com.gads.leaderboard.model.SkillIQ;

import java.text.MessageFormat;
import java.util.List;

public class SkillIQAdapter extends RecyclerView.Adapter<SkillIQAdapter.PostViewHolder> {
    List<SkillIQ> skillIQList;
    Context context;

    public SkillIQAdapter(List<SkillIQ> skillIQList, Context context) {
        this.skillIQList = skillIQList;
        this.context = context;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View skillIQView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_skill_iq, parent, false);
        PostViewHolder postViewHolder = new PostViewHolder(skillIQView);
        return postViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        SkillIQ current = skillIQList.get(position);
        holder.skillIQName.setText(current.getName());
        holder.skillIQScore.setText(MessageFormat.format("{0} skill IQ Score,", current.getScore()));
        holder.skillIQCountry.setText(current.getCountry());
        Glide.with(context).load(current.getBadgeUrl()).into(holder.img);
        // Log.d("SkillIQAdapter", "ImageUrl is: " + current.getBadgeUrl());
    }

    @Override
    public int getItemCount() {
        return skillIQList.size();
    }

    public class PostViewHolder extends RecyclerView.ViewHolder {
        public TextView skillIQName, skillIQScore, skillIQCountry;
        public ImageView img;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.skill_iq_image);
            skillIQName = itemView.findViewById(R.id.skill_iq_name);
            skillIQScore = itemView.findViewById(R.id.skill_iq_score);
            skillIQCountry = itemView.findViewById(R.id.skill_iq_country);
        }
    }
}
