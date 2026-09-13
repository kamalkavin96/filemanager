package com.kamalkavin96.filemanager.services;

import java.util.List;

import com.kamalkavin96.filemanager.dto.response.UserDetailRes;
import com.kamalkavin96.filemanager.models.Role;

public interface UserRolesService {
    UserDetailRes addUserRole(Long userId, Long roleId);
    void removeRole(Long userId, Long roleId);
    List<Role> getUserRole(Long userId);
}
