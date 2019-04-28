package com.hackaton.agilegamificator.network;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

/**
 * Created by Dmitry Parshin on 28.04.2019.
 */
public class Balance {

    private int balance;

    public int getBalance() {
        return balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Balance balance1 = (Balance) o;
        return balance == balance1.balance;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(balance);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("balance", balance)
                .toString();
    }
}
