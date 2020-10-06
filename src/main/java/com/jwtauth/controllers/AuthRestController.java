package com.jwtauth.controllers;

import com.jwtauth.entities.Role;
import com.jwtauth.entities.User;
import com.jwtauth.entities.requests.LoginRequest;
import com.jwtauth.entities.responses.AuthResponse;
import com.jwtauth.entities.responses.ResponseData;
import com.jwtauth.entities.responses.ResponseStatus;
import com.jwtauth.repositories.RoleRepository;
import com.jwtauth.repositories.UserRepository;
import com.jwtauth.services.UserDetailsServiceImpl;
import com.jwtauth.services.UserServiceImpl;
import com.jwtauth.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("auth")
public class AuthRestController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("signin")
    public AuthResponse signin(@RequestBody LoginRequest loginRequest) {
        Authentication authentication;
        AuthResponse response = new AuthResponse();
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );
        }
        catch (Exception e) {
            System.out.println("Error message: " + e.getMessage());
            response.setStatus(new ResponseStatus(400, "BAD_REQUEST", "Invalid username or password"));
            response.setData(null);

            return response;
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUsername());
        String token = jwtUtil.generateToken(userDetails);

        User user = userService.findUserByUsername(loginRequest.getUsername());
        response.setStatus(new ResponseStatus(200, "SUCCESS", "User authenticated successfully"));
        response.setData(new ResponseData(user, token));

        return response;
    }

    @PostMapping("signup")
    public AuthResponse signup(@Valid @RequestBody User user) {
        AuthResponse response = new AuthResponse();

        if (userRepository.existsByEmail(user.getEmail())) {
            response.setStatus(new ResponseStatus(400, "REQUEST_FAIL", "This Email already exists!"));
            response.setData(null);
        }
        if (userRepository.existsByPhoneNumber(user.getPhoneNumber())) {
            response.setStatus(new ResponseStatus(400, "REQUEST_FAIL", "This Phone number already exists!"));
            response.setData(null);
        }
        List<Role> roles = new ArrayList<>();
        if (user.getRoles() != null) {
            user.getRoles().forEach(role -> {
                Role theRole = roleRepository.findByName(role.getName());
                if (theRole != null) roles.add(theRole);
            });
        }
        else {
            roles.add(new Role("ROLE_USER"));
        }
        user.setRoles(roles);
        user.setPassword(encoder.encode(user.getPassword()));

        User savedUser = userRepository.save(user);

        response.setStatus(new ResponseStatus(200, "SUCCESS", "User created successfully"));
        response.setData(new ResponseData(savedUser));

        return response;
    }
}
