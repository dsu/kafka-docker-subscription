package com.example.publicapp.model;

import java.time.LocalDate;

public interface SubscriptionInterface {

    long getId();

    void setId(long id);

    String getFirstName();

    void setFirstName(String firstName);

    long getNewsletterId();

    void setNewsletterId(long newsletterId);

    String getEmail();

    void setEmail(String email);

    LocalDate getDateOfBirth();

    void setDateOfBirth(LocalDate dateOfBirth);

    Boolean getConsent();

    void setConsent(Boolean consent);

    Gender getGender();

    void setGender(Gender gender);

    public static enum Gender {
        MALE, FEMALE, OTHER
    }
}
