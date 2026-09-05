package com.kamalkavin96.filemanager.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FileCreateRes {

    private String fileName;
    private Boolean isCreated;
    
}
