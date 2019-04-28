package com.hackaton.agilegamificator.network;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import java.util.List;

/**
 * Created by Dmitry Parshin on 28.04.2019.
 */
public class Sprint {

    private String name;

    private List<Issue> issues;

    public String getName() {
        return name;
    }

    public List<Issue> getIssues() {
        return issues;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sprint sprint = (Sprint) o;
        return Objects.equal(name, sprint.name) &&
                Objects.equal(issues, sprint.issues);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name, issues);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("name", name)
                .add("issues", issues)
                .toString();
    }
}
