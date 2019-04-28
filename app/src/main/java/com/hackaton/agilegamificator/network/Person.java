package com.hackaton.agilegamificator.network;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

/**
 * Created by Dmitry Parshin on 27.04.2019.
 */
public class Person {

    private String emailAddress;
    private String accountId;

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getAccountId() {
        return accountId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equal(emailAddress, person.emailAddress) &&
                Objects.equal(accountId, person.accountId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(emailAddress, accountId);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("emailAddress", emailAddress)
                .add("accountId", accountId)
                .toString();
    }
}
