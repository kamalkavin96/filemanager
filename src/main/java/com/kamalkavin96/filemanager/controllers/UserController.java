package com.kamalkavin96.filemanager.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kamalkavin96.filemanager.dto.request.CreateUserReq;
import com.kamalkavin96.filemanager.dto.response.UserRes;
import com.kamalkavin96.filemanager.services.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserRes> create(
            @Valid @RequestBody CreateUserReq createUserReq) {

        UserRes newUser = userService.createUser(createUserReq);
        return ResponseEntity.ok(newUser);
    }

    @GetMapping
    public ResponseEntity<List<UserRes>> getAll() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Long> getUser(@PathVariable("userId") Long userId){
        return ResponseEntity.ok(userId);
    }

    @ExceptionHandler
    public ResponseEntity<Map<String, String>> handelRuntimeException(RuntimeException exception){
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(null);
    }
    
}
