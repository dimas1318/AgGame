package com.hackaton.agilegamificator.network;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import java.util.List;

/**
 * Created by Dmitry Parshin on 28.04.2019.
 */
public class Dashboard {

    private List<RateRaw> active;

    private List<RateRaw> total;

    public List<RateRaw> getActive() {
        return active;
    }

    public List<RateRaw> getTotal() {
        return total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dashboard dashboard = (Dashboard) o;
        return Objects.equal(active, dashboard.active) &&
                Objects.equal(total, dashboard.total);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(active, total);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("active", active)
                .add("total", total)
                .toString();
    }
}
