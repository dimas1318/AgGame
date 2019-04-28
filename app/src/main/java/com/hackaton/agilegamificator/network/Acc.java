package com.hackaton.agilegamificator.network;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

/**
 * Created by Dmitry Parshin on 28.04.2019.
 */
public class Acc {

    private String account_id;

    private String displayName;

    public String getAccount_id() {
        return account_id;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Acc acc = (Acc) o;
        return Objects.equal(account_id, acc.account_id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(account_id);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("account_id", account_id)
                .toString();
    }
}
