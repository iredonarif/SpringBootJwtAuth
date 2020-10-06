package com.jwtauth.seed;

import com.jwtauth.entities.Role;
import com.jwtauth.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        loadRoles();
    }

    private void loadRoles() {
        String[][] roles = {
                {"SUPER_ADMIN", "Super Admin"},
                {"ROLE_ADMIN", "Admin"},
                {"ROLE_USER", "User"},
        };
        for (String[] role: roles) {
            if (!roleRepository.existsByName(role[0])) {
                roleRepository.save(new Role(role[0], role[1]));
            }
        }
    }
}
