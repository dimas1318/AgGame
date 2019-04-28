package com.hackaton.agilegamificator.network;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import java.util.List;

/**
 * Created by Dmitry Parshin on 27.04.2019.
 */
public class IssueList {

    private List<Issue> issues;

    public List<Issue> getIssues() {
        return issues;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IssueList issueList = (IssueList) o;
        return Objects.equal(issues, issueList.issues);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(issues);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("issues", issues)
                .toString();
    }
}
