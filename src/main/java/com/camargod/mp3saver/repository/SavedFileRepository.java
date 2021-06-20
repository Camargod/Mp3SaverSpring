package com.camargod.mp3saver.repository;

import com.camargod.mp3saver.entity.SavedFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SavedFileRepository extends JpaRepository<SavedFile,Long> {
}
