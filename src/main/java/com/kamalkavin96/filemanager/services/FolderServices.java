package com.kamalkavin96.filemanager.services;

import com.kamalkavin96.filemanager.dto.auth.CustomUserDetails;
import com.kamalkavin96.filemanager.dto.request.folder.FolderCreateReq;

public interface FolderServices {

    void create(CustomUserDetails customUserDetails, FolderCreateReq folderCreateReq);

    void delete(String folderName, String folderPath);

    void rename(String newFolderName, String oldFolderName, String folderPath);

    void createUserSpace(String folderName, Long userId);

    void deleteUserSpace(String folderName);
}
