package com.hackaton.agilegamificator.presentation.main;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.hackaton.agilegamificator.R;
import com.hackaton.agilegamificator.network.RateRaw;

/**
 * Created by Dmitry Parshin on 28.04.2019.
 */
public class DashboardViewHolder extends RecyclerView.ViewHolder {

    private TextView mName;
    private TextView mBalance;

    public DashboardViewHolder(@NonNull View itemView) {
        super(itemView);
        mName = itemView.findViewById(R.id.dash_name);
        mBalance = itemView.findViewById(R.id.dash_balance);
    }

    public void bind(RateRaw raw) {
        mName.setText(raw.getName());
        mBalance.setText(String.valueOf(raw.getBalance()));
    }
}
