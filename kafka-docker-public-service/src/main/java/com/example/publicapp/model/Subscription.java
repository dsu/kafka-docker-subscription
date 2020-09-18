package com.example.publicapp.model;

import com.google.common.base.MoreObjects;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Email;
import java.time.LocalDate;

@ApiModel(description = "All details about the subscriptions. ")
public class Subscription implements SubscriptionInterface {


    @ApiModelProperty(notes = "The database generated ID", required = false, readOnly = true)
    private long id;

    @ApiModelProperty(notes = "The newsletter id", required = true)
    private long newsletterId;

    @ApiModelProperty(notes = "The first name", required = false)
    private String firstName;

    @ApiModelProperty(notes = "Date of birth", required = true, example = "2016-01-01")
    private LocalDate dateOfBirth;

    @ApiModelProperty(notes = "Email", required = true, example = "test@domain.com")
    @Email
    private String email;

    @ApiModelProperty(notes = "Consent", required = true)
    private Boolean consent = false;

    @ApiModelProperty(notes = "Gender", required = false)
    private Gender gender;

    public Subscription() {

    }

    public Subscription(long newsletterId, LocalDate dateOfBirth, String email, Boolean consent) {
        this.newsletterId = newsletterId;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.consent = consent;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public long getNewsletterId() {
        return newsletterId;
    }

    @Override
    public void setNewsletterId(long newsletterId) {
        this.newsletterId = newsletterId;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    @Override
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public Boolean getConsent() {
        return consent;
    }

    @Override
    public void setConsent(Boolean consent) {
        this.consent = consent;
    }

    @Override
    public Gender getGender() {
        return gender;
    }

    @Override
    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", getId())
                .toString();
    }
}
