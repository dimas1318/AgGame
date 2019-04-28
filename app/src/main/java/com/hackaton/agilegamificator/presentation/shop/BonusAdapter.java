package com.hackaton.agilegamificator.presentation.shop;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hackaton.agilegamificator.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dmitry Parshin on 27.04.2019.
 */
public class BonusAdapter extends RecyclerView.Adapter<BonusViewHolder> {

    private List<Bonus> mBonuses = new ArrayList<>();

    private BonusPay mListener;

    public BonusAdapter(BonusPay mListener) {
        this.mListener = mListener;
    }

    @NonNull
    @Override
    public BonusViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.gift_item, parent, false);
        return new BonusViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BonusViewHolder holder, int position) {
        final Bonus bonus = mBonuses.get(position);
        holder.bind(bonus, mListener);
    }

    @Override
    public int getItemCount() {
        return mBonuses.size();
    }

    public void setData(@NonNull List<Bonus> bonuses) {
        mBonuses = new ArrayList<>(bonuses);
        notifyDataSetChanged();
    }
}
