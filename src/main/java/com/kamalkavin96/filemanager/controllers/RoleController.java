package com.kamalkavin96.filemanager.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kamalkavin96.filemanager.dto.request.CreateRoleReq;
import com.kamalkavin96.filemanager.dto.request.RoleMappingReq;
import com.kamalkavin96.filemanager.dto.response.RoleRes;
import com.kamalkavin96.filemanager.dto.response.UserDetailRes;
import com.kamalkavin96.filemanager.exception.RoleExistForUserException;
import com.kamalkavin96.filemanager.exception.RoleNotFoundException;
import com.kamalkavin96.filemanager.services.RoleService;
import com.kamalkavin96.filemanager.services.UserRolesService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@RestController
@Tag(name = "Role Management")
@RequestMapping("/api/v1/role")
public class RoleController {

    private final RoleService roleService;
    private final UserRolesService userRolesService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Create Role")
    public ResponseEntity<RoleRes> create(@RequestBody CreateRoleReq roleReq) {
        RoleRes role = roleService.create(roleReq);
        return ResponseEntity.ok(role);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @Operation(summary = "Get All Roles")
    public ResponseEntity<List<RoleRes>> getAll() {
        return ResponseEntity.ok(roleService.getAll());
    }

    @DeleteMapping("/{roleId}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Delete User")
    public ResponseEntity<Map<String, Object>> delete(
            @PathVariable("roleId") Long roleId) {
        return ResponseEntity.ok(roleService.delete(roleId));
    }

    @PostMapping("map")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Map Role")
    public ResponseEntity<UserDetailRes> mapRoleToUser(
        @Valid @RequestBody RoleMappingReq roleMappingReq
    ){
        return ResponseEntity.ok(userRolesService.addUserRole(roleMappingReq.getUserId(), roleMappingReq.getRoleId()));
    }

    @ExceptionHandler
    public ResponseEntity<Map<String, Object>> handelRoleNotFoundException(RoleNotFoundException exception){
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(Map.of("deleted", false, "message", exception.getMessage()));
    }

    @ExceptionHandler
    public ResponseEntity<Map<String, Object>> handelRoleExistForUserException(RoleExistForUserException exception){
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(Map.of("message", exception.getMessage()));
    }

}
