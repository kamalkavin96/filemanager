package com.kamalkavin96.filemanager.services;

import com.kamalkavin96.filemanager.dto.response.UserRoleRes;

public interface UserRolesService {
    UserRoleRes addUserRole(Long userId, Long roleId);
}
