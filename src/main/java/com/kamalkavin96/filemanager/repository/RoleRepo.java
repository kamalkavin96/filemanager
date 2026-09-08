package com.kamalkavin96.filemanager.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kamalkavin96.filemanager.models.Role;

public interface RoleRepo extends JpaRepository<Role, Long>{
    Optional<Role> findByName(String name);
}
