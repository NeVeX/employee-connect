package com.nevex.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by mcunningham on 8/4/2017.
 */
public class LdapUserDto {

    @JsonProperty("email_address")
    private String emailAddress;
    @JsonProperty("full_name")
    private String fullName;

    public LdapUserDto() { }

    public LdapUserDto(String emailAddress, String fullName) {
        this.emailAddress = emailAddress;
        this.fullName = fullName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
