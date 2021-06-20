package com.camargod.mp3saver.service;

import com.camargod.mp3saver.entity.UserUploader;
import com.camargod.mp3saver.repository.UserRepository;
import com.camargod.mp3saver.request.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.session.Session;
import org.springframework.session.SessionRepository;
import org.springframework.stereotype.Service;

import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.Optional;

@Service
public class LoginService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SessionRepository sessionRepository;

    public ResponseEntity<String> generateTokenAndAuthenticate(UserRequest reqUser, HttpServletRequest requestServlet, HttpServletResponse responseServlet) throws Exception {
        UserUploader validatedUser = authenticate(reqUser);

        final UserDetails userDetails = userService.loadUserByUsername(validatedUser.getName());

        final String token = userService.generateJwtToken(validatedUser);

        final Cookie sessionIdCookie = setRedisSession(validatedUser.getName());
        responseServlet.addCookie(sessionIdCookie);

        return ResponseEntity.ok(token);
     }

     public Cookie setRedisSession(String username){
         Session session = sessionRepository.createSession();
         session.setAttribute("username",username);
         Cookie sessionIdCookie = new Cookie("SESSION",session.getId());
         sessionIdCookie.setHttpOnly(true);
         sessionIdCookie.setSecure(false);
         sessionRepository.save(session);
         return sessionIdCookie;
     }

     private UserUploader authenticate(UserRequest user) throws Exception {
         Optional<UserUploader> userExists = repository.findByName(user.getName());
         if(userExists.isPresent()){
             UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user.getName(),user.getPwd(), Collections.emptyList());
             manager.authenticate(auth);
             return userExists.get();
         }
         else{
             throw new Exception("IHAAAAAA");
         }

     }
}
