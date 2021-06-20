package com.camargod.mp3saver.service;

import com.camargod.mp3saver.entity.SavedFile;
import com.camargod.mp3saver.mapper.FileAddRequest;
import com.camargod.mp3saver.repository.SavedFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SavedFileService {

    @Autowired SavedFileRepository repository;

    public List<SavedFile> getSavedFiles(){
        return repository.findAll();
    }

    public SavedFile setSavedFiles(FileAddRequest fileReq){
        SavedFile file = SavedFile.builder().name(fileReq.name).build();

        return repository.save(file);
    }
}
