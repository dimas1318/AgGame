package com.hackaton.agilegamificator.network;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

/**
 * Created by Dmitry Parshin on 28.04.2019.
 */
public class RateRaw {

    private String name;

    private int balance;

    public String getName() {
        return name;
    }

    public int getBalance() {
        return balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RateRaw rateRaw = (RateRaw) o;
        return balance == rateRaw.balance &&
                Objects.equal(name, rateRaw.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name, balance);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("name", name)
                .add("balance", balance)
                .toString();
    }
}
