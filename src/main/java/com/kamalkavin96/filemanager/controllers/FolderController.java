package com.kamalkavin96.filemanager.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/v1/folders")
@Tag(name = "Folder Management")
public class FolderController {

    @GetMapping
    public String getMethodName(@RequestParam String param) {
        return "endpoint for test";
    }
    
}
