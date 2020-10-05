package com.jwtauth.entities;

import org.springframework.data.annotation.Id;
import org.springframework.security.core.GrantedAuthority;

public class Role implements GrantedAuthority {

    @Id
    private String id;
    private String name;
    private String label;

    public Role() {
    }

    public Role(String name) {
        this.name = name;
    }

    public Role(String name, String label) {
        this.name = name;
        this.label = label;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String getAuthority() {
        return null;
    }
}
