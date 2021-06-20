package com.camargod.mp3saver.controller;

import com.camargod.mp3saver.entity.UserUploader;
import com.camargod.mp3saver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<List<UserUploader>> getUsers(){
        return ResponseEntity.ok(service.getUsers());
    }

    @PutMapping
    public ResponseEntity<UserUploader> setUser(@RequestBody UserUploader user){
        return ResponseEntity.ok(service.saveUser(user));
    }
}
