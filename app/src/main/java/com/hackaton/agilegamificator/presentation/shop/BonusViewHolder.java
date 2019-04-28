package com.hackaton.agilegamificator.presentation.shop;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.hackaton.agilegamificator.R;
import com.hackaton.agilegamificator.StringUtils;

/**
 * Created by Dmitry Parshin on 27.04.2019.
 */
public class BonusViewHolder extends RecyclerView.ViewHolder {

    private TextView mDescription;
    private TextView mPrice;

    public BonusViewHolder(@NonNull View itemView) {
        super(itemView);
        mDescription = itemView.findViewById(R.id.bonus_description);
        mPrice = itemView.findViewById(R.id.bonus_price);
    }

    public void bind(Bonus bonus){
        mDescription.setText(bonus.getDescription());
        mPrice.setText(StringUtils.formatBalance(bonus.getPrice()));
    }
}
