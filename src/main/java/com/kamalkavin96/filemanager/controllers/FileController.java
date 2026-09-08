package com.kamalkavin96.filemanager.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kamalkavin96.filemanager.dto.request.FileCreateReq;
import com.kamalkavin96.filemanager.dto.request.FileGetAllReq;
import com.kamalkavin96.filemanager.dto.response.FileCreateRes;
import com.kamalkavin96.filemanager.dto.response.FileGetAllRes;


@RestController
@RequestMapping("/api/v1/files")
public class FileController {

    @PostMapping("/create")
    public ResponseEntity<FileCreateRes> create(
        @RequestBody FileCreateReq fileCreateReq
    ){
        FileCreateRes fileCreateRes = new FileCreateRes(fileCreateReq.getFileName(), true);
        return ResponseEntity.ok(fileCreateRes);
    }

    @PostMapping("/get-all")
    public ResponseEntity<List<FileGetAllRes>> getAll(
        @RequestBody FileGetAllReq fileGetAllReq
    ){
        List<FileGetAllRes> fileGetAllResList = List.of(
            new FileGetAllRes("file1.txt", "base/kavin/file.txt"), 
            new FileGetAllRes("test.py", "base/kavin/test.py")
        );
        return ResponseEntity.ok(fileGetAllResList);
    }

}
