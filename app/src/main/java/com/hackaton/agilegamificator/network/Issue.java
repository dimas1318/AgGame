package com.hackaton.agilegamificator.network;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

/**
 * Created by Dmitry Parshin on 27.04.2019.
 */
public class Issue {

    private String name;

    private String summary;

    private String priority;

    private String status;

    public String getName() {
        return name;
    }

    public String getSummary() {
        return summary;
    }

    public String getPriority() {
        return priority;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Issue issue = (Issue) o;
        return Objects.equal(name, issue.name) &&
                Objects.equal(summary, issue.summary) &&
                Objects.equal(priority, issue.priority) &&
                Objects.equal(status, issue.status);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name, summary, priority, status);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("name", name)
                .add("summary", summary)
                .add("priority", priority)
                .add("status", status)
                .toString();
    }
}