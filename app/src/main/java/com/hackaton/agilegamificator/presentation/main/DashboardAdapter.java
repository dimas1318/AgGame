package com.hackaton.agilegamificator.presentation.main;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hackaton.agilegamificator.R;
import com.hackaton.agilegamificator.network.RateRaw;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dmitry Parshin on 28.04.2019.
 */
public class DashboardAdapter extends RecyclerView.Adapter<DashboardViewHolder> {

    private List<RateRaw> mRaws = new ArrayList<>();

    @NonNull
    @Override
    public DashboardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dash_item, parent, false);
        return new DashboardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DashboardViewHolder holder, int position) {
        final RateRaw rateRaw = mRaws.get(position);
        holder.bind(rateRaw, position + 1);
    }

    @Override
    public int getItemCount() {
        return mRaws.size();
    }

    public void setData(@NonNull List<RateRaw> raws) {
        mRaws = new ArrayList<>(raws);
        notifyDataSetChanged();
    }
}
