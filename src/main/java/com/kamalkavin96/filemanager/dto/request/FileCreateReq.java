package com.kamalkavin96.filemanager.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FileCreateReq {

    private String fileName;
    private  String filePath;
    
}
