package com.file.mover.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.file.mover.service.FileMoverService;

@RestController
@RequestMapping("/alerts")
public class FileMoverController {

    @Autowired
    private FileMoverService fileMoverService;

    private static final String rootPath = "C://Users/aceni/Downloads/code_problem/code_problem";
    private static final String systemListPath = rootPath + "/systemList.txt";
    private static final String sourceDir = rootPath + "/ioffice/alerts";
    private static final String targetDir = rootPath + "/ioffice/institution/alerts";

    @GetMapping("/moveCustomAlerts")
    public ResponseEntity<?> moveCustomAlerts() {
        try {
            fileMoverService.moveCustomAlerts(systemListPath, sourceDir, targetDir);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
