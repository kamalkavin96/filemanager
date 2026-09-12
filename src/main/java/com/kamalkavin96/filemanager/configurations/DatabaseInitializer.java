package com.kamalkavin96.filemanager.configurations;

import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.kamalkavin96.filemanager.models.Role;
import com.kamalkavin96.filemanager.repository.RoleRepo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class DatabaseInitializer implements CommandLineRunner {

    private static final String ROLE_ADMIN = "ROLE_ADMIN";
    private static final String ROLE_USER = "ROLE_USER";

    private final RoleRepo roleRepo;

    @Override
    public void run(String... args) {

        log.info("Started database initialization");

        createRoleIfNotExists(ROLE_ADMIN);
        createRoleIfNotExists(ROLE_USER);

        log.info("Database initialization completed");
    }

    private void createRoleIfNotExists(String roleName) {

        if (roleRepo.findByName(roleName).isEmpty()) {

            Role role = new Role();
            role.setName(roleName);
            role.setCreatedAt(
                LocalDateTime.now(ZoneId.of("Asia/Kolkata"))
            );

            roleRepo.save(role);

            log.info("Created role: {}", roleName);

        } else {

            log.info("Role already exists: {}", roleName);
        }
    }
}