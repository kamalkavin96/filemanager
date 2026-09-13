package com.kamalkavin96.filemanager.controllers;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kamalkavin96.filemanager.dto.request.RoleMappingReq;
import com.kamalkavin96.filemanager.dto.response.UserDetailRes;
import com.kamalkavin96.filemanager.exception.RoleExistForUserException;
import com.kamalkavin96.filemanager.exception.RoleNotFoundException;
import com.kamalkavin96.filemanager.exception.UserNotFoundException;
import com.kamalkavin96.filemanager.services.implimentation.UserRolesServiceImpl;
import com.kamalkavin96.filemanager.services.implimentation.UserServiceImpl;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/user-role")
@RequiredArgsConstructor
@Tag(name = "User Role Management")
public class UserRolesController {

    private final UserRolesServiceImpl userRolesServiceImpl;
    private final UserServiceImpl userServiceImpl;

    @PostMapping("/add")
    public ResponseEntity<UserDetailRes> addRole(
            @RequestBody RoleMappingReq roleMappingReq) {
        userRolesServiceImpl.addUserRole(roleMappingReq.getUserId(), roleMappingReq.getRoleId());
        return ResponseEntity.ok(userServiceImpl.getUser(roleMappingReq.getUserId()));
    }

    @PostMapping("/remove")
    public ResponseEntity<UserDetailRes> removeRole(
            @RequestBody RoleMappingReq roleMappingReq) {
        userRolesServiceImpl.removeRole(roleMappingReq.getUserId(), roleMappingReq.getRoleId());

        return ResponseEntity.ok(userServiceImpl.getUser(roleMappingReq.getUserId()));
    }

    @ExceptionHandler
    public ResponseEntity<Map<String, Object>> handelRoleNotFoundException(RoleNotFoundException exception){
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(Map.of("message", exception.getMessage()));
    }

    @ExceptionHandler
    public ResponseEntity<Map<String, Object>> handelRoleExistForUserException(UserNotFoundException exception){
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(Map.of("message", exception.getMessage()));
    }

    @ExceptionHandler
    public ResponseEntity<Map<String, Object>> handelRoleExistForUserException(RoleExistForUserException exception){
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(Map.of("message", exception.getMessage()));
    }
}
