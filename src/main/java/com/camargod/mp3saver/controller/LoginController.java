package com.camargod.mp3saver.controller;

import com.camargod.mp3saver.request.UserRequest;
import com.camargod.mp3saver.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService service;

    @PostMapping("/authenticate")
    public ResponseEntity authenticate(@RequestBody UserRequest request, HttpServletRequest requestServlet, HttpServletResponse responseServlet){
        try{
            return ResponseEntity.ok(service.generateTokenAndAuthenticate(request,requestServlet,responseServlet));
        }
        catch (Exception err){
            return ResponseEntity.status(500).body(err.getMessage());
        }
    }
}
