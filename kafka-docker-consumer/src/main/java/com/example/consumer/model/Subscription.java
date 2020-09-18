package com.example.consumer.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.StringJoiner;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Subscription implements SubscriptionInterface {

    private long id;

    private long newsletterId;

    private String firstName;

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate dateOfBirth;

    private String email;


    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    public LocalDateTime createdAt;

    private String optInToken;

    private Boolean consent = false;

    private Gender gender;

    private SubscriptionStatus status = SubscriptionStatus.NEW;

    public Subscription() { }

    public Subscription(long newsletterId, LocalDate dateOfBirth, String email, Boolean consent) {
        this.newsletterId = newsletterId;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.consent = consent;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public long getNewsletterId() {
        return newsletterId;
    }

    public void setNewsletterId(long newsletterId) {
        this.newsletterId = newsletterId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Boolean getConsent() {
        return consent;
    }

    public void setConsent(Boolean consent) {
        this.consent = consent;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(SubscriptionInterface.Gender gender) {
        this.gender = gender;
    }

    public SubscriptionStatus getStatus() {
        return status;
    }

    public void setStatus(SubscriptionStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getOptInToken() {
        return optInToken;
    }

    public void setOptInToken(String optInToken) {
        this.optInToken = optInToken;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Subscription.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("newsletterId=" + newsletterId)
                .add("firstName='" + firstName + "'")
                .add("dateOfBirth=" + dateOfBirth)
                .add("email='" + email + "'")
                .add("consent=" + consent)
                .add("gender=" + gender)
                .add("status=" + status)
                .toString();
    }
}
