package com.kamalkavin96.filemanager.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kamalkavin96.filemanager.dto.auth.CustomUserDetails;
import com.kamalkavin96.filemanager.dto.request.folder.FolderCreateReq;
import com.kamalkavin96.filemanager.services.implimentation.FolderServicesImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/v1/folders")
@Tag(name = "Folder Management")
@RequiredArgsConstructor
public class FolderController {

    private final FolderServicesImpl folderServices;

    @PostMapping("/create")
    @Operation(summary = "Create Folder")
    public FolderCreateReq create(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @RequestBody FolderCreateReq folderCreateReq) {


        folderServices.create(customUserDetails, folderCreateReq);
        return folderCreateReq;
    }

    @DeleteMapping
    public String delete(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @RequestBody FolderCreateReq folderCreateReq) {

        return customUserDetails.getEmail();
    }

}
