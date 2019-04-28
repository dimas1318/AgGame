package com.hackaton.agilegamificator.network;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

/**
 * Created by Dmitry Parshin on 27.04.2019.
 */
public class Priority {

    private String name;

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Priority priority = (Priority) o;
        return Objects.equal(name, priority.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("name", name)
                .toString();
    }
}
