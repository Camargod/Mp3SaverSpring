package com.camargod.mp3saver.service;

import com.camargod.mp3saver.entity.UserUploader;
import com.camargod.mp3saver.exceptions.AlreadyExistItem;
import com.camargod.mp3saver.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder pwdEncoder;

    public List<UserUploader> getUsers(){
        return repository.findAll();
    }

    public Optional<UserUploader> getUploaderByName(String name){
        return repository.findByName(name);
    }
    @Value("${secret}")
    public String secret;

    public Optional<UserUploader> checkLogin(UserUploader user){
        return repository.findByName(user.getName());
    }

    public UserUploader saveUser(UserUploader user) {
        if(getUploaderByName(user.getName()).isEmpty()){
            UserUploader convertedUser = UserUploader.builder()
                    .name(user.getName())
                    .pwd(pwdEncoder.encode(user.getPwd()))
                    .build();
            return repository.save(convertedUser);
        }
        else{
            throw new AlreadyExistItem("Ja existe um usuario com esse nome.");
        }
    }

    public String generateJwtToken(UserUploader user){
        HashMap<String,Object> tokenData = new HashMap();
        tokenData.put("username",user.getName());
        tokenData.put("id",user.getUserId());

        return tokenGenerator(tokenData,user);
    }

    private String tokenGenerator(Map<String,Object> tokenData, UserUploader user){
        return Jwts.builder().addClaims(tokenData).setSubject(user.getName()).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 360000)).signWith(SignatureAlgorithm.HS512,secret).compact();
    }

   @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserUploader> user = getUploaderByName(username);
        return checkUser(user);
    }

    private UserDetails checkUser(Optional<UserUploader> user){
        List<GrantedAuthority> authorities = new ArrayList<>();
        UserDetails userDetails = null;
        if(user.isPresent()){
            UserUploader presentUser = user.get();
            userDetails = new org.springframework.security.core.userdetails.User(presentUser.getName(), presentUser.getPwd(),true,true,true,true,authorities);
        }
        return userDetails;
    }
}
