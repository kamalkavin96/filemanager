package com.kamalkavin96.filemanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kamalkavin96.filemanager.dao.UserDetailDao;
import com.kamalkavin96.filemanager.models.Role;
import com.kamalkavin96.filemanager.models.UserRoles;

public interface UserRolesRepo extends JpaRepository<UserRoles, Long> {

    @Query(value = """
                SELECT r.* FROM user_roles ur
                JOIN roles r on r.id = ur.role_id
                WHERE ur.user_id = :userId
            """, nativeQuery = true)
    List<Role> findByUserId(Long userId);

    boolean existsByUserIdAndRoleId(Long userId, Long roleId);

    @Query(value = """
            SELECT
                u.id, 
                u.username, 
                u.email, 
                u.dob, 
                u.created_at as createdAt, 
                u.update_at as updateAt,
                STRING_AGG(DISTINCT r.name, ',' ORDER BY r.name) as roles
            FROM users u
            JOIN user_roles ur ON ur.user_id = u.id
            JOIN roles r on r.id = ur.role_id
            WHERE u.id = :userId
            GROUP BY
                u.id
            """, nativeQuery = true)
    UserDetailDao getUsersDetails(Long userId);

    @Query(value = """
            SELECT
                u.id, 
                u.username, 
                u.email, 
                u.dob, 
                u.created_at as createdAt, 
                u.update_at as updateAt,
                STRING_AGG(DISTINCT r.name, ',' ORDER BY r.name) as roles
            FROM users u
            JOIN user_roles ur ON ur.user_id = u.id
            JOIN roles r on r.id = ur.role_id
            WHERE u.email = :email
            GROUP BY
                u.id
            """, nativeQuery = true)
    UserDetailDao getUsersDetailsByEmail(String email);

    @Query(value = """
            SELECT
                u.id, 
                u.username, 
                u.email, 
                u.dob, 
                u.created_at as createdAt, 
                u.update_at as updateAt,
                STRING_AGG(DISTINCT r.name, ',' ORDER BY r.name) as roles
            FROM users u
            JOIN user_roles ur ON ur.user_id = u.id
            JOIN roles r on r.id = ur.role_id
            GROUP BY
                u.id
            """, nativeQuery = true)
    List<UserDetailDao> getAllUsersDetails();

    void deleteByUserId(Long userId);

    void deleteByUserIdAndRoleId(Long userId, Long roleId); 
}
