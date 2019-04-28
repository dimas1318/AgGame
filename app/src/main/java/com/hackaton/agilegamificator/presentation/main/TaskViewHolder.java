package com.hackaton.agilegamificator.presentation.main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.hackaton.agilegamificator.R;
import com.hackaton.agilegamificator.network.Issue;

/**
 * Created by Dmitry Parshin on 27.04.2019.
 */
public class TaskViewHolder extends RecyclerView.ViewHolder {

    private TextView mName;
    private TextView mSummary;
    private TextView mPriority;

    public TaskViewHolder(@NonNull View itemView) {
        super(itemView);
        mName = itemView.findViewById(R.id.task_name);
        mSummary = itemView.findViewById(R.id.task_summary);
        mPriority = itemView.findViewById(R.id.task_priority);
    }

    public void bind(Issue issue) {
        String status = issue.getStatus();
        Context context = itemView.getContext();
        if (status.equalsIgnoreCase("to do")) {
            itemView.setBackgroundColor(context.getResources().getColor(android.R.color.holo_red_light));
        } else if (status.equalsIgnoreCase("in progress")) {
            itemView.setBackgroundColor(context.getResources().getColor(android.R.color.holo_orange_light));
        } else {
            itemView.setBackgroundColor(context.getResources().getColor(android.R.color.holo_green_dark));
        }

        mName.setText(issue.getName());
        mSummary.setText(issue.getSummary());
        mPriority.setText(issue.getPriority());
    }
}
