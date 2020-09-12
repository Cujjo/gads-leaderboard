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
import java.util.ArrayList;
import java.util.List;

public class SkillIQAdapter extends RecyclerView.Adapter<SkillIQAdapter.PostViewHolder> {
    private List<SkillIQ> skillIQList = new ArrayList<>();
    Context context;

    public SkillIQAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PostViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_skill_iq, parent));
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        holder.skillIQName.setText(skillIQList.get(position).getName());
        holder.skillIQScore.setText(MessageFormat.format("{0} skill IQ Score,", skillIQList.get(position).getScore()));
        holder.skillIQCountry.setText(skillIQList.get(position).getCountry());
        Glide.with(context).load(skillIQList.get(position).getBadgeUrl()).into(holder.img);
        Log.d("SkillIQAdapter", "ImageUrl is: " + skillIQList.get(position).getBadgeUrl());
    }

    @Override
    public int getItemCount() {
        return skillIQList.size();
    }

    public void setList(List<SkillIQ> list) {
        this.skillIQList = list;
        notifyDataSetChanged();
    }

    public class PostViewHolder extends RecyclerView.ViewHolder {
        TextView skillIQName, skillIQScore, skillIQCountry;
        ImageView img;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.skill_iq_image);
            skillIQName = itemView.findViewById(R.id.skill_iq_name);
            skillIQScore = itemView.findViewById(R.id.skill_iq_score);
            skillIQCountry = itemView.findViewById(R.id.skill_iq_country);
        }
    }
}
