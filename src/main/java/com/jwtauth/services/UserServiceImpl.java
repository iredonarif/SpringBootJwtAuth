package com.jwtauth.services;

import com.jwtauth.entities.User;
import com.jwtauth.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findUserByUsername(String username) {
        return isValidEmail(username) ? userRepository.findByEmail(username) :
                userRepository.findByPhoneNumber(username);
    }

    private boolean isValidEmail(String val) {
        Pattern pattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
        Matcher mat = pattern.matcher(val);

        return mat.matches();
    }
}
