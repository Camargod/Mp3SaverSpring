package com.camargod.mp3saver.controller;

import com.camargod.mp3saver.entity.SavedFile;
import com.camargod.mp3saver.mapper.FileAddRequest;
import com.camargod.mp3saver.service.SavedFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/file")
public class FileController {
    @Autowired
    private SavedFileService service;

    @GetMapping()
    public ResponseEntity<List<SavedFile>> getFiles(){
        return ResponseEntity.ok(service.getSavedFiles());
    }

    @PutMapping()
    public ResponseEntity<SavedFile> addFile(@RequestBody FileAddRequest file){
        return ResponseEntity.ok(service.setSavedFiles(file));
    }
}
