package com.camargod.mp3saver.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserLoginRegisterDTO {

    @Autowired
    private PasswordEncoder passwordEncoder;

    String name;
    String pwd;

    public void pwd (String pwd){
        this.pwd = passwordEncoder.encode(pwd);
    }

    private PasswordEncoder passwordEncoder(){return this.passwordEncoder;}
}
