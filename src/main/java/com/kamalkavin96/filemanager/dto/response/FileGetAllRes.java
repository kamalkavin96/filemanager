package com.kamalkavin96.filemanager.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data 
@AllArgsConstructor 
public class FileGetAllRes {

    private String fileName;
    private String filePath;
    
}
