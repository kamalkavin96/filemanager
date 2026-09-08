package com.kamalkavin96.filemanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kamalkavin96.filemanager.models.Role;
import com.kamalkavin96.filemanager.models.UserRoles;

public interface UserRolesRepo extends JpaRepository<UserRoles, Long> {

    @Query(value = """
                SELECT r.* FROM user_roles ur
                JOIN roles r on r.id = ur.role_id
                WHERE ur.user_id = 1
            """, nativeQuery = true)
    List<Role> findByUserId(Long userId);

    boolean existsByUserIdAndRoleId(Long userId, Long roleId);
}
