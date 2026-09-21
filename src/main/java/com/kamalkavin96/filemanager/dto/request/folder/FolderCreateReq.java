package com.kamalkavin96.filemanager.dto.request.folder;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FolderCreateReq {

    private List<FolderPathReq> folderPath;
    @NotBlank(message = "Folder name should not be blank")
    private String folderName;
}
