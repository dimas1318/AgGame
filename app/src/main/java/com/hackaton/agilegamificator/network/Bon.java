package com.hackaton.agilegamificator.network;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.hackaton.agilegamificator.StringUtils;

/**
 * Created by Dmitry Parshin on 28.04.2019.
 */
public class Bon {

    private String result;

    public String getResult() {
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bon bon = (Bon) o;
        return Objects.equal(result, bon.result);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(result);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("result", result)
                .toString();
    }
}
