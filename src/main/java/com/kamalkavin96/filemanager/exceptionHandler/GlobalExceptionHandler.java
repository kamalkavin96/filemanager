package com.kamalkavin96.filemanager.exceptionHandler;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.kamalkavin96.filemanager.utils.ConstantVariable;

@RestControllerAdvice 
public class GlobalExceptionHandler {

    @ExceptionHandler(AuthorizationDeniedException.class)
    public ResponseEntity<Map<String, String>> handelRuntimeException(AuthorizationDeniedException exception) {
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(Map.of(ConstantVariable.MESSAGE, exception.getMessage()));
    }

}
