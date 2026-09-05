package com.kamalkavin96.filemanager.configurations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DirectoryInitializer implements CommandLineRunner {

    Logger logger = LoggerFactory.getLogger(DirectoryInitializer.class);

    @Override
    public void run(String... args) throws Exception {

        logger.info("Started Directory Initilization Configuration");
        logger.info("Completed Directory Initilization Configuration");
    }

}
