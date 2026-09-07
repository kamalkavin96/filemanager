package com.kamalkavin96.filemanager.configurations;

import java.nio.file.Files;
import java.nio.file.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DirectoryInitializer implements CommandLineRunner {

    Logger logger = LoggerFactory.getLogger(DirectoryInitializer.class);

    @Value("${app.filemanager.base-directory}")
    private String baseDirectory;

    @Override
    public void run(String... args) throws Exception {

        logger.info("Started Directory Initilization Configuration");
        

        Path baseFilePath = Path.of(baseDirectory);
        
        if (Files.exists(baseFilePath)) {
            logger.info("Base folder '%s' already exist".formatted(baseDirectory));
        } else {
            logger.info("Base folder '%s' alr exist".formatted(baseDirectory));
            Files.createDirectories(baseFilePath);
            logger.info("Base folder '%s' created succesfully".formatted(baseDirectory));
        }


        logger.info("Completed Directory Initilization Configuration");
    }

}
