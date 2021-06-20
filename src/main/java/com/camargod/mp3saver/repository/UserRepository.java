package com.camargod.mp3saver.repository;

import com.camargod.mp3saver.entity.UserUploader;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserUploader,Long> {
    Optional<UserUploader> findByName(String name);
}
