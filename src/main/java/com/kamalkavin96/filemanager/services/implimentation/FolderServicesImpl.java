package com.kamalkavin96.filemanager.services.implimentation;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.kamalkavin96.filemanager.services.FolderServices;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FolderServicesImpl implements FolderServices {

    @Value("${app.filemanager.base-directory}")
    private String baseDirectory;

    @Override
    public void create(String folderName, String folderPath) {
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    @Override
    public void createUserSpace(String folderName) {
        try {

            Path basePath = Path.of(baseDirectory);
            Path userPath = basePath.resolve(folderName).normalize();
            Files.createDirectories(userPath);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

}
