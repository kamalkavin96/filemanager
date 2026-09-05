package com.kamalkavin96.filemanager.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kamalkavin96.filemanager.dto.request.FileCreateReq;
import com.kamalkavin96.filemanager.dto.response.FileCreateRes;


@RestController
@RequestMapping("/api/v1/files")
public class FileController {

    @PostMapping 
    public ResponseEntity<FileCreateRes> create(
        @RequestBody FileCreateReq fileCreateReq
    ){
        FileCreateRes fileCreateRes = new FileCreateRes(fileCreateReq.getFileName(), true);
        return ResponseEntity.ok(fileCreateRes);
    }

}
