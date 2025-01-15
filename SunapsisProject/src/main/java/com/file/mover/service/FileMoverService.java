package com.file.mover.service;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.*;
import java.nio.file.*;
import java.util.*;

@Service
public class FileMoverService {
    private static final Logger logger = LoggerFactory.getLogger(FileMoverService.class);

    public void moveCustomAlerts(String systemListPath, String sourceDir, String targetDir) {
        Set<String> systemAlerts = new HashSet<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(systemListPath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                systemAlerts.add(line.trim());
            }
        } catch (IOException e) {
            logger.error("Error reading system alerts: " + e.getMessage());
            return;
        }

        File targetDirectory = new File(targetDir);
        if (!targetDirectory.exists()) {
            if (!targetDirectory.mkdirs()) {
                logger.error("Failed to create target directory: " + targetDir);
                return;
            }
        }

        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(Paths.get(sourceDir))) {
            for (Path path : directoryStream) {
                File file = path.toFile();
                if (!systemAlerts.contains(file.getName())) {
                    File targetFile = new File(targetDir, file.getName());
                    if (file.renameTo(targetFile)) {
                        logger.info("Moved: " + file.getName() + " to " + targetDir);
                    } else {
                        logger.error("Failed to move: " + file.getName());
                    }
                }
            }
        } catch (IOException e) {
            logger.error("Error moving custom alerts: " + e.getMessage());
        }
    }
}
