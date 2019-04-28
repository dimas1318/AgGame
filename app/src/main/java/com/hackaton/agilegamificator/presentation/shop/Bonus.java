package com.hackaton.agilegamificator.presentation.shop;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

/**
 * Created by Dmitry Parshin on 27.04.2019.
 */
public class Bonus {

    private String mDescription;
    private int mPrice;

    public Bonus(String description, int price) {
        mDescription = description;
        mPrice = price;
    }

    public String getDescription() {
        return mDescription;
    }

    public int getPrice() {
        return mPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bonus bonus = (Bonus) o;
        return mPrice == bonus.mPrice &&
                Objects.equal(mDescription, bonus.mDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(mDescription, mPrice);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("mDescription", mDescription)
                .add("mPrice", mPrice)
                .toString();
    }
}
