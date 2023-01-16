package eif.viko.lt.project.simulith.audit.controllers;

import eif.viko.lt.project.simulith.audit.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/storage")
public class StorageController {
    final FileStorageService fileStorageService;

    @Autowired
    public StorageController(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }
    @GetMapping("/all")
    public String listDirectory() {
        return fileStorageService.printFolder();
    }
}
