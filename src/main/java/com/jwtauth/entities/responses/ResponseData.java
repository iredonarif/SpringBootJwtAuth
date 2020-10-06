package com.jwtauth.entities.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jwtauth.entities.User;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseData {
    private User user;
    private String token;

    public ResponseData() {
    }

    public ResponseData(User user) {
        this.user = user;
    }
    public ResponseData(User user, String token) {
        this.user = user;
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
