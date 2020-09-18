package com.example.producer.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.google.common.base.MoreObjects;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Table(name = "subscriptions", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"newsletterId", "email"})
})
@ApiModel(description = "All details about the substriction.")
public class Subscription implements SubscriptionInterface {

    @ApiModelProperty(notes = "The database generated ID", required = false, readOnly = true)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(updatable = false, nullable = false)
    private long id;

    @ApiModelProperty(notes = "The newsletter id", required = true)
    @Column(name = "newsletter_id",nullable = false)
    private long newsletterId;

    @ApiModelProperty(notes = "The first name", required = false)
    private String firstName;

    @ApiModelProperty(notes = "Date of birth", required = true, example = "2016-01-01")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private LocalDate dateOfBirth;

    @ApiModelProperty(notes = "Email", required = true)
    @Column(name = "email", nullable = false)
    private String email;


    @ApiModelProperty(notes = "The time the subscription has been saved", required = false, readOnly = true)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at",nullable = false)
    public LocalDateTime createdAt;

    @ApiModelProperty(notes = "Double Opt-in token", required = false, readOnly = true)
    @Column(nullable = false)
    private String optInToken;

    @ApiModelProperty(notes = "Consent", required = true)
    @Column(nullable = false)
    private Boolean consent = false;

    @ApiModelProperty(notes = "Gender", required = false)
    private SubscriptionInterface.Gender gender;

    @ApiModelProperty(notes = "Status", required = false, readOnly = true)
    private SubscriptionInterface.SubscriptionStatus status = SubscriptionInterface.SubscriptionStatus.NEW;

    public Subscription() {

    }

    public Subscription(long newsletterId, LocalDate dateOfBirth, String email, Boolean consent) {
        this.newsletterId = newsletterId;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.consent = consent;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(nullable = true)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(nullable = false)
    public long getNewsletterId() {
        return newsletterId;
    }

    public void setNewsletterId(long newsletterId) {
        this.newsletterId = newsletterId;
    }

    @Column(nullable = false)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(nullable = false)
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Column(nullable = false)
    public Boolean getConsent() {
        return consent;
    }

    public void setConsent(Boolean consent) {
        this.consent = consent;
    }

    @Column(nullable = true)
    public SubscriptionInterface.Gender getGender() {
        return gender;
    }


    public void setGender(SubscriptionInterface.Gender gender) {
        this.gender = gender;
    }

    public SubscriptionInterface.SubscriptionStatus getStatus() {
        return status;
    }

    public void setStatus(SubscriptionInterface.SubscriptionStatus status) {
        this.status = status;
    }

    @PrePersist
    void createdAt() {
        this.createdAt = LocalDateTime.now();
        this.optInToken = UUID.randomUUID().toString();
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", getId())
                .add("status", getStatus())
                .toString();
    }
}
