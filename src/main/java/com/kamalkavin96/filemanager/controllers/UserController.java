package com.kamalkavin96.filemanager.controllers;

import com.kamalkavin96.filemanager.services.implimentation.UserServiceImpl;
import com.kamalkavin96.filemanager.utils.ConstantVariable;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kamalkavin96.filemanager.dto.request.user.CreateUserReq;
import com.kamalkavin96.filemanager.dto.response.UserDetailRes;
import com.kamalkavin96.filemanager.exception.UserNotFoundException;
import com.kamalkavin96.filemanager.exception.UsernameExistException;
import com.kamalkavin96.filemanager.services.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
@Tag(name = "User Management")
public class UserController {

    private final UserServiceImpl userServiceImpl;
    private final UserService userService;

    @PostMapping
    @Operation(summary = "Create User")
    public ResponseEntity<UserDetailRes> create(
            @Valid @RequestBody CreateUserReq createUserReq) {

        UserDetailRes newUser = userService.createUser(createUserReq);
        return ResponseEntity.ok(newUser);
    }

    @GetMapping
    @Operation(summary = "Get All Users")
    public ResponseEntity<List<UserDetailRes>> getAll() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{userId}")
    @Operation(summary = "Get User")
    public ResponseEntity<UserDetailRes> getUser(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(userService.getUser(userId));
    }

    @DeleteMapping("/{userId}")
    @Operation(summary = "Delete User")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable("userId") Long userId) {
        if (userServiceImpl.deleteUser(userId)) {
            return ResponseEntity.ok(
                Map.of(
                    ConstantVariable.DELETED, true, ConstantVariable.MESSAGE,
                    ConstantVariable.USER_DETETED.formatted(userId)
                )
            );
        }
        return ResponseEntity.ok(
                Map.of(
                    ConstantVariable.DELETED, false, 
                    ConstantVariable.MESSAGE, ConstantVariable.USER_NOT_DETETED
                )
            );
    }

    @ExceptionHandler
    @Operation(summary = "Update User")
    public ResponseEntity<Map<String, String>> handelRuntimeException(UsernameExistException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Map.of(ConstantVariable.MESSAGE, exception.getMessage()));
    }

    @ExceptionHandler
    public ResponseEntity<Map<String, String>> handelRuntimeException(UserNotFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(Map.of(ConstantVariable.MESSAGE, exception.getMessage()));
    }

}
