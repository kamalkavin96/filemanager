package com.kamalkavin96.filemanager.dto.request.folder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@AllArgsConstructor 
@NoArgsConstructor 
public class FolderPathReq {

    private String folderName;
    protected Long folderId;

}
