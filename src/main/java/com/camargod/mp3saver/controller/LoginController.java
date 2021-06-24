package com.camargod.mp3saver.controller;

import com.camargod.mp3saver.request.UserRequest;
import com.camargod.mp3saver.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

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

    @GetMapping("/resources")
    public ResponseEntity getResources(HttpServletRequest requestServlet){
        try{
            Cookie[] reqCookies = requestServlet.getCookies();
            Cookie sessionCookie = (Cookie) Arrays.stream(reqCookies).filter(cookie -> cookie.getName().equals("SESSION")).toArray()[0];
            return ResponseEntity.ok(service.getResources(sessionCookie.getValue()));
        }
        catch (Exception err){
            return ResponseEntity.status(500).body(err.getMessage());
        }
    }
}
