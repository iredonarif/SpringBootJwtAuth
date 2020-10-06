package com.jwtauth.entities.responses;

public class AuthResponse {
    private ResponseStatus status;
    private ResponseData data;

    public AuthResponse() {
    }

    public ResponseStatus getStatus() {
        return status;
    }

    public void setStatus(ResponseStatus status) {
        this.status = status;
    }

    public ResponseData getData() {
        return data;
    }

    public void setData(ResponseData data) {
        this.data = data;
    }
}
