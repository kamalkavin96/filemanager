package com.kamalkavin96.filemanager.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kamalkavin96.filemanager.dto.request.CreateRoleReq;
import com.kamalkavin96.filemanager.dto.request.DeleteRoleReq;
import com.kamalkavin96.filemanager.dto.response.RoleRes;
import com.kamalkavin96.filemanager.exception.RoleNotFoundException;
import com.kamalkavin96.filemanager.services.RoleService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/role")
public class RoleController {

    private final RoleService roleService;

    @PostMapping
    public ResponseEntity<RoleRes> create(@RequestBody CreateRoleReq roleReq) {
        RoleRes role = roleService.create(roleReq);
        return ResponseEntity.ok(role);
    }

    @GetMapping
    public ResponseEntity<List<RoleRes>> getAll() {
        return ResponseEntity.ok(roleService.getAll());
    }

    @DeleteMapping
    public ResponseEntity<Map<String, Object>> delete(
            @Valid @RequestBody DeleteRoleReq deleteRoleReq) {
        return ResponseEntity.ok(roleService.delete(deleteRoleReq.getId()));
    }

    @ExceptionHandler
    public ResponseEntity<Map<String, Object>> handelRuntimeException(RoleNotFoundException exception){
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(Map.of("deleted", false, "message", exception.getMessage()));
    }

}
