package com.hackaton.agilegamificator.presentation.main;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hackaton.agilegamificator.R;
import com.hackaton.agilegamificator.network.Issue;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dmitry Parshin on 27.04.2019.
 */
public class TaskAdapter extends RecyclerView.Adapter<TaskViewHolder> {

    private List<Issue> mIssues = new ArrayList<>();

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.tasks_item, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        final Issue issue = mIssues.get(position);
        holder.bind(issue);
    }

    @Override
    public int getItemCount() {
        return mIssues.size();
    }

    public void setData(@NonNull List<Issue> issues) {
        mIssues = new ArrayList<>(issues);
        notifyDataSetChanged();
    }
}
