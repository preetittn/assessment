package com.example.restfulservices2.restfulservices2.model;

import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("DynamicFiltering")
public class DynamicFilteringUser {

    private String username;

    private String password;

    public DynamicFilteringUser() {

    }


    public DynamicFilteringUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
