package com.kamalkavin96.filemanager.services.implimentation;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.kamalkavin96.filemanager.dto.auth.CustomUserDetails;
import com.kamalkavin96.filemanager.dto.request.folder.FolderCreateReq;
import com.kamalkavin96.filemanager.dto.request.folder.FolderPathReq;
import com.kamalkavin96.filemanager.models.Folders;
import com.kamalkavin96.filemanager.models.UserBaseFolder;
import com.kamalkavin96.filemanager.repository.FolderRepo;
import com.kamalkavin96.filemanager.repository.UserBaseFolderRepo;
import com.kamalkavin96.filemanager.services.FolderServices;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class FolderServicesImpl implements FolderServices {

    @Value("${app.filemanager.base-directory}")
    private String baseDirectory;

    private final UserBaseFolderRepo userBaseFolderRepo;
    private final FolderRepo folderRepo;

    @Override
    public void create(
            CustomUserDetails customUserDetails, FolderCreateReq folderCreateReq) {

        Long userId = customUserDetails.getUserId();
        String email = customUserDetails.getEmail();
        String userBaseFolderStr = customUserDetails.getUserBaseFolder();

        UserBaseFolder userBaseFolder = userBaseFolderRepo.findByUserId(userId);
        List<Folders> userFolders = folderRepo.findByOwnerId(userId);


        


        List<String> userFolderName = userFolders.stream().map(uf->uf.getName()).toList();

        Path basePath = Path.of(baseDirectory);
        Path userBaseFolderPath = basePath.resolve(userBaseFolderStr);

        folderCreateReq.getFolderName();
        List<FolderPathReq> folderPathList = folderCreateReq.getFolderPath();


        




        // Path folder = userBaseFolder.resolve(folderPath);
        // Path newFolderPath = folder.resolve(folderName);

        // try {
        //     Files.createDirectories(newFolderPath);
        //     log.info("User folder created successfully: {}", newFolderPath);

        // } catch (IOException e) {
        //     log.error(e.getMessage());
        // }
    }

    @Override
    public void createUserSpace(String folderName, Long userId) {
        try {

            Path basePath = Path.of(baseDirectory);
            Path userPath = basePath.resolve(folderName).normalize();
            Files.createDirectories(userPath);
            UserBaseFolder userBaseFolder2 = new UserBaseFolder();
            userBaseFolder2.setName(folderName);
            userBaseFolder2.setUserId(userId);
            userBaseFolderRepo.save(userBaseFolder2);
            log.info("User base folder created successfully: {}", userPath);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public void deleteUserSpace(String folderName) {
        try {
            Path basePath = Path.of(baseDirectory);
            Path userPath = basePath.resolve(folderName);
            Files.deleteIfExists(userPath);
            log.info("User folder deleted succesfully!");
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public void delete(String folderName, String folderPath) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public void rename(String newFolderName, String oldFolderName, String folderPath) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'rename'");
    }

}
