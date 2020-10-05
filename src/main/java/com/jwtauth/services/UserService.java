package com.jwtauth.services;

import com.jwtauth.entities.User;

public interface UserService {

    User findUserByUsername(String username);
}
