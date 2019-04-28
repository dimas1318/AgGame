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
    private View mPay;

    public BonusViewHolder(@NonNull View itemView) {
        super(itemView);
        mDescription = itemView.findViewById(R.id.bonus_description);
        mPrice = itemView.findViewById(R.id.bonus_price);
        mPay = itemView.findViewById(R.id.pay);
    }

    public void bind(Bonus bonus, BonusPay mListener) {
        mDescription.setText(bonus.getDescription());
        mPrice.setText(StringUtils.formatBalance(bonus.getPrice()));
        mPay.setOnClickListener(v -> mListener.pay(bonus.getPrice()));
    }
}
