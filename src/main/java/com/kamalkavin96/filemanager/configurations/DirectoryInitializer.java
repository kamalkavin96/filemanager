package com.kamalkavin96.filemanager.configurations;

import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DirectoryInitializer implements CommandLineRunner {

    @Value("${app.filemanager.base-directory}")
    private String baseDirectory;

    @Override
    public void run(String... args) throws Exception {

        log.info("Started Directory Initilization Configuration");
        

        Path baseFilePath = Path.of(baseDirectory);
        
        if (Files.exists(baseFilePath)) {
            log.info("Base folder '{}' already exist", baseDirectory);
        } else {
            log.info("Base folder '{}' alr exist", baseDirectory);
            Files.createDirectories(baseFilePath);
            log.info("Base folder '{}' created succesfully", baseDirectory);
        }


        log.info("Completed Directory Initilization Configuration");
    }

}
