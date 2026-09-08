package com.kamalkavin96.filemanager.services.implimentation;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import org.springframework.stereotype.Service;

import com.kamalkavin96.filemanager.dto.response.UserRoleRes;
import com.kamalkavin96.filemanager.exception.RoleExistForUserException;
import com.kamalkavin96.filemanager.exception.RoleNotFoundException;
import com.kamalkavin96.filemanager.exception.UserNotFoundException;
import com.kamalkavin96.filemanager.models.Role;
import com.kamalkavin96.filemanager.models.User;
import com.kamalkavin96.filemanager.models.UserRoles;
import com.kamalkavin96.filemanager.repository.RoleRepo;
import com.kamalkavin96.filemanager.repository.UserRepo;
import com.kamalkavin96.filemanager.repository.UserRolesRepo;
import com.kamalkavin96.filemanager.services.UserRolesService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserRolesServiceImpl implements UserRolesService {

    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final UserRolesRepo userRolesRepo;

    @Override
    public UserRoleRes addUserRole(Long userId, Long roleId) {
        UserRoles userRoles = new UserRoles();

        User user = userRepo.findById(userId).orElseThrow(()-> new UserNotFoundException("User not found for Id: %s".formatted(userId)));
        Role role = roleRepo.findById(roleId).orElseThrow(()-> new RoleNotFoundException("Role not found for id: %s".formatted(roleId)));

        if(userRolesRepo.existsByUserIdAndRoleId(userId, roleId)){
            throw new RoleExistForUserException("User role already exist");
        }


        userRoles.setUserId(user.getId());
        userRoles.setRoleId(role.getId());
        userRoles.setCreatedAt(LocalDateTime.now(ZoneId.of("Asia/Kolkata")));
        userRolesRepo.save(userRoles);
        List<String> roles = userRolesRepo.findByUserId(userId).stream().map(Role::getName).toList();

        return  new UserRoleRes(
            roleId, 
            user.getUsername(), 
            user.getEmail(), 
            roles);

    }

}
